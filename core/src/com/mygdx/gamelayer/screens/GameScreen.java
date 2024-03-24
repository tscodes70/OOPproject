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
import com.mygdx.gameengine.managers.AIControlManager;
import com.mygdx.gameengine.managers.ButtonControlManager;
import com.mygdx.gameengine.managers.ButtonManager;
import com.mygdx.gameengine.managers.CollisionManager;
import com.mygdx.gameengine.managers.EntityManager;
import com.mygdx.gameengine.managers.PlayerControlManager;
import com.mygdx.gameengine.models.Entity;
import com.mygdx.gameengine.models.Keyboard;
import com.mygdx.gameengine.models.Mouse;
import com.mygdx.gameengine.models.Scene;
import com.mygdx.gameengine.models.Sound;
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
	
	private final int DEFAULT_PLAYER_X = 400;
	private final int DEFAULT_PLAYER_Y = 250;
	private final int DEFAULT_PLAYER_SPEED = 3;
	

	private final int DEFAULT_PROJECTILE_SPEED = 5;
	private final int DEFAULT_PROJECTILE_WIDTH = 5;
	private final int DEFAULT_PROJECTILE_HEIGHT = 30;
	
	private final int DEFAULT_ENTITY_RADIUS = 40; // remove
	private final int DEFAULT_ENTITY_WIDTH = 80;
	private final int DEFAULT_ENTITY_HEIGHT = 100;
	private final int DEFAULT_ENTITY_SPEED = 1;

	private final Color DEFAULT_AI_COLOR = Color.RED;
	private final boolean COLLIDABLE = true;
	private final boolean PLAYABLE = true;
	private final boolean AI_CONTROL = true;
	
	private final int INITIAL_MAX_SPAWN = 8;
	private final int INITIAL_MIN_SPAWN = 3;
	
	private BitmapFont font, countdownFont;
	private GlyphLayout countdown;
    private float countdownTime = 180f;
    private float delay;

    private int playerPoints = 100;//Initial points
    
    private Player player1;
    private float projectileSpawnTimer = 0;
    private float debrisSpawnTimer = 0;
    
	public GameScreen(Planet planet, SpaceTexture playerModel, SpaceTexture bgTexture, Sound bgMusic, Keyboard keyboardDevice, Mouse mouseDevice, AppSimulation simulation) {
		super(bgMusic, bgTexture);
		batch = new SpriteBatch();
		shape = new ShapeRenderer();

		this.keyboardDevice = keyboardDevice;
		this.mouseDevice = mouseDevice;
		this.simulation = simulation;
		this.planet = planet;
        
        //Instantiate EntityManager
		spaceEntityManager = new SpaceEntityManager();

		//Creation of Entities (Player Entity, AI Entities)
		player1 = new Player(
				DEFAULT_PLAYER_X, 
				DEFAULT_PLAYER_Y, 
				DEFAULT_PLAYER_SPEED, 
				DEFAULT_ENTITY_WIDTH, 
				DEFAULT_ENTITY_HEIGHT,
				playerModel,
				PLAYABLE,
				COLLIDABLE,
				
				Keys.LEFT,
				Keys.RIGHT,
				Keys.UP,
				Keys.DOWN);
		spaceEntityManager.add(player1);
		
        for (int i=0; i<(int)Math.floor(Math.random() * INITIAL_MAX_SPAWN)+INITIAL_MIN_SPAWN; i++) {
        	spaceEntityManager.add(new Debris(
            		(float) (Math.random() * Gdx.graphics.getWidth()),
            		(float)(Gdx.graphics.getHeight() + (float)(Math.random() * 600)),
            		DEFAULT_ENTITY_SPEED,
            		DEFAULT_ENTITY_RADIUS,
            		DEFAULT_AI_COLOR,
            		AI_CONTROL,
            		COLLIDABLE));	
        }
        
        spaceEntityManager.add(planet);
        
        font = new BitmapFont();
        
        // dynamically generate bitmap font of our desired size so it doesn't look pixelated
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 125; // font size in pixels
		countdownFont = generator.generateFont(parameter);
		countdownFont.getData().setScale(1.25f);
		countdownFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

		generator.dispose(); // don't forget to dispose to avoid memory leaks!
        countdown = new GlyphLayout(countdownFont, "3"); //for getting width/height of text

		//Instantiate AIControlManager
		spaceAIControlManager = new SpaceAIControlManager(spaceEntityManager.getEntityList());
		
		//Instantiate PlayerControlManager
		playerControlManager = new PlayerControlManager(spaceEntityManager.getEntityList(), keyboardDevice);
		
		//Instantiate CollisionManager
		spaceCollisionManager = new SpaceCollisionManager(spaceEntityManager.getEntityList());
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
        font.draw(batch, "Time: " + (int) countdownTime, 10, Gdx.graphics.getHeight() - 10);
        	spaceEntityManager.drawEntities(batch);     	
        //to display points on screen
        font.draw(batch, "Points: \n" + playerPoints, 10, Gdx.graphics.getHeight() - 50);
        
		batch.end();
		
		// fade in effect should be called after everything else
		super.fadeIn(delay, deltaTime);

		// show 3-2-1 countdown before starting game i.e. scene not yet fully faded in
		if(!super.isFadedIn()) {
			batch.begin();
			float countdownTextX = super.centeredXPos(countdown.width);
	        float countdownTextY = ((float)Gdx.graphics.getHeight() / 2f) + (countdown.height / 2f);
	        countdownFont.draw(batch, Integer.toString((int)delay), countdownTextX, countdownTextY);
			batch.end();
		} else {
			// only start executing game logic when the scene transition is complete
			playerControlManager.move(deltaTime);
			spaceAIControlManager.move(deltaTime);
			spaceCollisionManager.checkCollisions(spaceEntityManager,spaceAIControlManager);
			
			// test test
			// hardcoded to go to level cleared screen after 10s of gameplay
			if((int)countdownTime == 0) {
				simulation.levelCleared(planet.getName(), planet.getTex());
			}
		}
	}
	
	public void update(float deltaTime) {
		delay -= deltaTime;
        // Check if the countdown has reached zero or below
		if (delay <= 0) {
            delay = 0; // Prevent negative countdown time
            // Handle countdown completion, e.g., end the game or take appropriate action
        }

		// start decreasing the main counter once delay over
		if(delay == 0) {
			countdownTime -= deltaTime;
		    // Accumulate time for spawning 
		    projectileSpawnTimer += deltaTime;
		    debrisSpawnTimer += deltaTime;
		}

	    // Spawn projectiles
	    if (projectileSpawnTimer >= 0.2 && countdownTime > 0) {
	        spaceEntityManager.add(new Projectile(player1.getPositionX()+35, player1.getPositionY() + 50, DEFAULT_PROJECTILE_WIDTH, DEFAULT_PROJECTILE_HEIGHT, Color.GREEN, true, true, DEFAULT_PROJECTILE_SPEED));
	        spaceAIControlManager.update(spaceEntityManager.getEntityList());
	        spaceCollisionManager.update(spaceEntityManager.getEntityList());
	        projectileSpawnTimer -= 0.2; // Reset the timer for next second
	    }
	    
	    // Spawn debris
	    if (debrisSpawnTimer >= 1 && countdownTime > 0) {
	    	for (int i=0; i<(int)Math.floor(Math.random() * INITIAL_MAX_SPAWN)+INITIAL_MIN_SPAWN; i++) {
	        	spaceEntityManager.add(new Debris(
	            		(float) (Math.random() * Gdx.graphics.getWidth()),
	            		(float)(Gdx.graphics.getHeight() + (float)(Math.random() * 600)),
	            		DEFAULT_ENTITY_SPEED,
	            		DEFAULT_ENTITY_RADIUS,
	            		DEFAULT_AI_COLOR,
	            		AI_CONTROL,
	            		COLLIDABLE));	
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
