package com.mygdx.gamelayer.screens;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.gameengine.interfaces.iPlayer;
import com.mygdx.gameengine.managers.AIControlManager;
import com.mygdx.gameengine.managers.ButtonControlManager;
import com.mygdx.gameengine.managers.ButtonManager;
import com.mygdx.gameengine.managers.CollisionManager;
import com.mygdx.gameengine.managers.EntityManager;
import com.mygdx.gameengine.managers.IOManager;
import com.mygdx.gameengine.managers.PlayerControlManager;
import com.mygdx.gameengine.models.Entity;
import com.mygdx.gameengine.models.Keyboard;
import com.mygdx.gameengine.models.Mouse;
import com.mygdx.gameengine.models.Scene;
import com.mygdx.gameengine.models.Sound;
import com.mygdx.gamelayer.factories.SpaceEntityFactory;
import com.mygdx.gamelayer.managers.SpaceAIControlManager;
import com.mygdx.gamelayer.managers.SpaceCollisionManager;
import com.mygdx.gamelayer.managers.SpaceEntityManager;
import com.mygdx.gamelayer.models.Debris;
import com.mygdx.gamelayer.models.Planet;
import com.mygdx.gamelayer.models.Player;
import com.mygdx.gamelayer.models.Projectile;
import com.mygdx.gamelayer.models.SpaceTexture;
import com.mygdx.gamelayer.models.StatsBar;
import com.mygdx.gamelayer.simulation.AppSimulation;

public class GameScreen extends Scene {	
	private SpriteBatch batch;
    private ShapeRenderer shape;
    private SpaceEntityManager spaceEntityManager;
    private PlayerControlManager playerControlManager;
    private SpaceAIControlManager spaceAIControlManager;
    private SpaceCollisionManager spaceCollisionManager;
    private ButtonManager buttonManager;
    private ButtonControlManager buttonControlManager;
    private AppSimulation simulation;
    private Keyboard keyboardDevice;
    private Mouse mouseDevice;
    
    private Planet planet;
	
	private BitmapFont font, countdownFont;
	private GlyphLayout countdown;
    private float countdownTime = 180f;
    private float delay;

    private int playerPoints = 100;//Initial points
    
    private Player player1;
    private float projectileSpawnTimer = 0;
    private float debrisSpawnTimer = 0;
    
    private SpaceEntityFactory spaceEntityFactory;
    
    private final int DEBRIS_MIN_SPAWN = 3;
    private final int DEBRIS_MAX_SPAWN = 8;
    
	public GameScreen(Planet planet, IOManager ioManager, AppSimulation simulation) {
		super(
				(Sound) ioManager.getOutputManager().retrieve("GSBGMusic"), 
				(SpaceTexture)ioManager.getOutputManager().retrieve("GSBGImage"));

		this.keyboardDevice = (Keyboard) ioManager.getInputManager().retrieve(1);
		this.mouseDevice = (Mouse) ioManager.getInputManager().retrieve(2);
		this.simulation = simulation;
		this.planet = planet;
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        font = new BitmapFont();
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
        
        //Instantiate SpaceEntityManager & SpaceEntityFactory
		spaceEntityManager = new SpaceEntityManager();
		spaceEntityFactory = new SpaceEntityFactory(ioManager);
		
		// Populate SpaceEntityManager of Player & Debris
		player1 = (Player)spaceEntityFactory.createEntity("Player",1, Keys.LEFT, Keys.RIGHT, Keys.UP, Keys.DOWN);
		spaceEntityManager.add(player1);
		
		Player player2 = (Player)spaceEntityFactory.createEntity("Player",2, Keys.A, Keys.D, Keys.W, Keys.S);
		spaceEntityManager.add(player2);
		
        for (int i=0; i<(int)Math.floor(Math.random() * DEBRIS_MAX_SPAWN) + DEBRIS_MIN_SPAWN; i++) {
        	spaceEntityManager.add((Debris)spaceEntityFactory.createEntity("Debris", planet.getName()));	
        	}
        
        // Populate SpaceEntityManager with selected planet
        spaceEntityManager.add(planet);
        
		//Instantiate SpaceAIControlManager
		spaceAIControlManager = new SpaceAIControlManager(spaceEntityManager.getEntityList());
		
		//Instantiate PlayerControlManager
		playerControlManager = new PlayerControlManager(spaceEntityManager.getEntityList(), keyboardDevice);
		
		//Instantiate SpaceCollisionManager
		spaceCollisionManager = new SpaceCollisionManager(spaceEntityManager.getEntityList());
        
        // Dynamically generate bitmap font (prevent pixellation)
		parameter.size = 125; // font size in pixels
		countdownFont = generator.generateFont(parameter);
		countdownFont.getData().setScale(1.25f);
		countdownFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        countdown = new GlyphLayout(countdownFont, "3"); //for getting width/height of text
        generator.dispose(); // prevent mem leaks

	}
	
	/**
	 * Renders objects in GameScreen
	 */
	@Override
	public void render() {
		super.render();
		float deltaTime = Gdx.graphics.getDeltaTime();
		update(deltaTime);

		shape.begin(ShapeRenderer.ShapeType.Filled);
			// Draw Entities (Shapes)
			spaceEntityManager.drawEntities(shape);
//			 Show hitboxes
//			for(Entity entity: spaceEntityManager.getEntityList()) {
//				if(entity instanceof Planet) {
//					Rectangle x = ((Planet) entity).getBoundingBox();
//					shape.setColor(Color.CYAN);
//					shape.rect(x.getX(), x.getY(), x.getWidth(), x.getHeight());
//				}else if(entity instanceof Player) {
//					Rectangle x = ((Player) entity).getBoundingBox();
//					shape.setColor(Color.CYAN);
//					shape.rect(x.getX(), x.getY(), x.getWidth(), x.getHeight());
//				}else if(entity instanceof Debris) {
//					Rectangle x = ((Debris) entity).getBoundingBox();
//					shape.setColor(Color.CYAN);
//					shape.rect(x.getX(), x.getY(), x.getWidth(), x.getHeight());
//				}else if(entity instanceof Projectile) {
//					Rectangle x = ((Projectile) entity).getBoundingBox();
//					shape.setColor(Color.CYAN);
//					shape.rect(x.getX(), x.getY(), x.getWidth(), x.getHeight());
//				}
//			}
		shape.end();

		
		batch.begin();
			// HUD display (time left and points)
	        font.draw(batch, "Time: " + (int) countdownTime, 10, Gdx.graphics.getHeight() - 10);
	        font.draw(batch, "Points: \n" + playerPoints, 10, Gdx.graphics.getHeight() - 50);
	        
	        // Draw Entities (Sprite)
	        spaceEntityManager.drawEntities(batch); 
		batch.end();
		
		// Screen Transition Fade-In Effect
		super.fadeIn(delay, deltaTime);

		// Countdown before Screen Fade-in
		if(!super.isFadedIn()) {
			batch.begin();
				float countdownTextX = super.centeredXPos(countdown.width);
		        float countdownTextY = ((float)Gdx.graphics.getHeight() / 2f) + (countdown.height / 2f);
		        countdownFont.draw(batch, Integer.toString((int)delay), countdownTextX, countdownTextY);
			batch.end();
		} else {
			// Start Executing Game
			playerControlManager.move(deltaTime);
			spaceAIControlManager.move(deltaTime);
			spaceCollisionManager.checkCollisions(spaceEntityManager,spaceAIControlManager,playerControlManager);
			
			// TESTING: hardcoded to go to level cleared screen after 10s of gameplay
			if((int)countdownTime == 0) {
				System.out.println("YOU VERY ZAI BRO");
				simulation.levelCleared(planet.getName(), planet.getTex());
			}
			for (iPlayer player : playerControlManager.getPlayerList()) {
				if(((Player)player).getHealthBar().getCurrentValue() <= 0) {
					System.out.println("YOU BAO ZHA ALR BRO");
					simulation.levelCleared(planet.getName(), planet.getTex());
				}
			}
			if(planet.getCurrentHP() <= 0) {
				System.out.println("PLANET BAO ZHA ALR BRO");
				simulation.levelCleared(planet.getName(), planet.getTex());
			}
		}
	}
	
	public void update(float deltaTime) {
		// TESTING: PRINT
		System.out.println("Planet Hp " + planet.getCurrentHP() + "/" + planet.getMaxHP());
		delay -= deltaTime;
        // Check if the countdown has reached zero or below
		if (delay <= 0) {
            delay = 0; // Prevent negative countdown time
            // Handle countdown completion, e.g., end the game or take appropriate action
        }

		// After Delay, start game timer
		if(delay == 0) {
			countdownTime -= deltaTime;
		    // Accumulate time for spawning 
		    projectileSpawnTimer += deltaTime;
		    debrisSpawnTimer += deltaTime;
		}
		
	    // Spawn projectiles
	    if (projectileSpawnTimer >= 0.2 && countdownTime > 0) {
	    	for (iPlayer player : playerControlManager.getPlayerList()) {
	        spaceEntityManager.add((Projectile)spaceEntityFactory.createDynamicEntity("Projectile",((Player)player).getPositionX(),((Player)player).getPositionY()));
	    	}
	        spaceAIControlManager.update(spaceEntityManager.getEntityList());
	        spaceCollisionManager.update(spaceEntityManager.getEntityList());
	        projectileSpawnTimer -= 0.2; // Reset the timer for next second
	    }
	    
	    // Spawn debris
	    if (debrisSpawnTimer >= 1 && countdownTime > 0) {
	    	for (int i=0; i<(int)Math.floor(Math.random() * DEBRIS_MAX_SPAWN)+DEBRIS_MIN_SPAWN; i++) {
	        	spaceEntityManager.add((Debris)spaceEntityFactory.createEntity("Debris", planet.getName()));	
	        }
	    	spaceAIControlManager.update(spaceEntityManager.getEntityList());
	        spaceCollisionManager.update(spaceEntityManager.getEntityList());
	        debrisSpawnTimer -= 1;
	    }

	    // Prevent negative countdown time
        if (countdownTime <= 0) countdownTime = 0;
        
	}
	
	// set the delay for fade in effect
	@Override
	public void show() {
		super.show();
		delay = 4f;
	}
	
	/**
	 * Disposes GameScreen and its superclass resources
	 */
	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		shape.dispose();
		font.dispose();
		countdownFont.dispose();
		playerControlManager.dispose();
		spaceAIControlManager.dispose();
		spaceCollisionManager.dispose();
		spaceEntityManager.dispose();
	}
}
