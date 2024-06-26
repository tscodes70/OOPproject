package com.mygdx.gamelayer.screens;

import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.gameengine.managers.IOManager;
import com.mygdx.gameengine.models.Entity;
import com.mygdx.gameengine.models.Keyboard;
import com.mygdx.gameengine.models.Mouse;
import com.mygdx.gameengine.models.Scene;
import com.mygdx.gameengine.models.Sound;
import com.mygdx.gamelayer.factories.SpaceEntityFactory;
import com.mygdx.gamelayer.interfaces.iSpacePlayer;
import com.mygdx.gamelayer.managers.SpaceAIControlManager;
import com.mygdx.gamelayer.managers.SpaceCollisionManager;
import com.mygdx.gamelayer.managers.SpaceEntityManager;
import com.mygdx.gamelayer.managers.SpacePlayerControlManager;
import com.mygdx.gamelayer.models.Debris;
import com.mygdx.gamelayer.models.Explosion;
import com.mygdx.gamelayer.models.Planet;
import com.mygdx.gamelayer.models.Player;
import com.mygdx.gamelayer.models.Projectile;
import com.mygdx.gamelayer.models.SpaceTexture;
import com.mygdx.gamelayer.simulation.AppSimulation;

public class GameScreen extends Scene {	
	private SpriteBatch batch;
    private ShapeRenderer shape;
    private SpaceEntityManager spaceEntityManager;
    private SpacePlayerControlManager spaceplayerControlManager;
    private SpaceAIControlManager spaceAIControlManager;
    private SpaceCollisionManager spaceCollisionManager;
    private AppSimulation simulation;
    private Keyboard keyboardDevice;
    
    private Planet planet;

	private BitmapFont countdownFont,topHudFont,bottomHudFont;
	private GlyphLayout countdown, planetText, livesText, tempVarText, tempVarDetailsText;
	
    private float countdownTime = 120f;
    private float delay;

    private int playerPoints = 0;//Initial points
    private int pointCheckpoint = 1;
    
    private String tempVarMessage = "";
    private String tempVarMessageDetails = "";
    
    private boolean tempEffect = false;
    private float tempEffectTimer = 10f;

	private Player player1;
    private float projectileSpawnTimer = 0;
    private float projectileSpawnThreshold = 0.1f;
    private float debrisSpawnTimer = 0;
    private float debrisSpawnThreshold = 1f;
    private float temperatureVariationTimer = 0;
    private float temperatureVariationThreshhold = 40f;
    
    private SpaceEntityFactory spaceEntityFactory;
    
    private final int DEBRIS_MIN_SPAWN = 2;
    private final int DEBRIS_MAX_SPAWN = 4;
    
	public GameScreen(Planet planet, IOManager ioManager, AppSimulation simulation) {
		super(
				(Sound) ioManager.getOutputManager().retrieve("GSBGMusic"), 
				(SpaceTexture)ioManager.getOutputManager().retrieve("GSBGImage"));

		this.keyboardDevice = (Keyboard) ioManager.getInputManager().retrieve(1);
		this.simulation = simulation;
		this.planet = planet;
		planet.resetHP();
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arcadeclassic.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
        
        //Instantiate SpaceEntityManager & SpaceEntityFactory
		spaceEntityManager = new SpaceEntityManager();
		spaceEntityFactory = new SpaceEntityFactory(ioManager);
		
		// Populate SpaceEntityManager of Player & Debris
		player1 = (Player)spaceEntityFactory.createEntity("Player",1, Keys.LEFT, Keys.RIGHT, Keys.UP, Keys.DOWN, Keys.SHIFT_RIGHT);
		spaceEntityManager.add(player1);
		
		Player player2 = (Player)spaceEntityFactory.createEntity("Player",2, Keys.A, Keys.D, Keys.W, Keys.S, Keys.SHIFT_LEFT);
		spaceEntityManager.add(player2);
		
        for (int i=0; i<(int)Math.floor(Math.random() * DEBRIS_MAX_SPAWN) + DEBRIS_MIN_SPAWN; i++) {
        	spaceEntityManager.add((Debris)spaceEntityFactory.createEntity("Debris", planet.getName()));	
        	}
        
        // Populate SpaceEntityManager with selected planet
        spaceEntityManager.add(planet);
        
		//Instantiate SpaceAIControlManager
		spaceAIControlManager = new SpaceAIControlManager(spaceEntityManager.getEntityList());
		
		//Instantiate SpacePlayerControlManager
		spaceplayerControlManager = new SpacePlayerControlManager(spaceEntityManager.getEntityList(), keyboardDevice);
		
		//Instantiate SpaceCollisionManager
		spaceCollisionManager = new SpaceCollisionManager(spaceEntityManager.getEntityList());
        
        // Dynamically generate bitmap font (prevent pixellation)
		parameter.size = 125; // font size in pixels
		countdownFont = generator.generateFont(parameter);
		countdownFont.getData().setScale(1.25f);
		countdownFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        countdown = new GlyphLayout(countdownFont, "3"); //for getting width/height of text
        
     	// Dynamically generate bitmap font (prevent pixellation)
     	parameter.size = 20; // font size in pixels
     	topHudFont = generator.generateFont(parameter);
     	topHudFont.getData().setScale(1.25f);
     	topHudFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
     	
     	parameter.size = 40; 
     	bottomHudFont = generator.generateFont(parameter);
     	bottomHudFont.getData().setScale(1.25f);
     	bottomHudFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
     	
     	planetText = new GlyphLayout(bottomHudFont, planet.getName());     
     	livesText = new GlyphLayout(bottomHudFont, "Lives 5");  
     	tempVarText = new GlyphLayout(bottomHudFont, "Temperature Variation Incoming!");  
     	tempVarDetailsText = new GlyphLayout(bottomHudFont, "No Usage Of Stamina!");  
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
	        // Draw Entities (Sprite)
	        spaceEntityManager.drawEntities(batch); 
			// HUD display (time left and points)
	        topHudFont.draw(batch, "Time " + (int) countdownTime, 10, Gdx.graphics.getHeight() - 10);
	        topHudFont.draw(batch, "Score " + playerPoints, 10, Gdx.graphics.getHeight() - 50);

	        bottomHudFont.draw(batch, planet.getName(), super.centeredXPos(planetText.width), 130);
	        bottomHudFont.draw(batch, "Lives " + (int)planet.getCurrentHP() , super.centeredXPos(livesText.width), 80);
	        
	        topHudFont.draw(batch, tempVarMessage, 200, 512);
	        topHudFont.draw(batch, tempVarMessageDetails, 200, 490);
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
			for (iSpacePlayer player : spaceplayerControlManager.getSpacePlayerList()) {
				((Player)player).playerGravity(deltaTime, planet.getScaledGravity());//for gravity effect, renders gravity
			}
			spaceplayerControlManager.move(deltaTime,planet.getScaledGravity());
			spaceAIControlManager.move(deltaTime);
			spaceCollisionManager.checkCollisions(spaceEntityManager,spaceAIControlManager,spaceplayerControlManager,this);
			
			// Projectile go past screen
			List<Entity> entityList = spaceEntityManager.getEntityList();
			Iterator<Entity> iterator = entityList.iterator();
			while (iterator.hasNext()) {
			    Entity projectile = iterator.next();
			    if (projectile instanceof Projectile) {
			        if (((Projectile) projectile).getPositionY() >= Gdx.graphics.getHeight()) {
			            iterator.remove(); // Safely remove the current element
			            spaceAIControlManager.update(spaceEntityManager.getEntityList());
			        }
			    }
			}
			
			// level successfully cleared
			if((int)countdownTime == 0) {
				simulation.levelCleared(planet.getName(), planet.getTex(), playerPoints);
			}
			
			// if player pass away, show the player's texture in the level failed screen
			for (iSpacePlayer player : spaceplayerControlManager.getSpacePlayerList()) {
				if(((Player)player).getHealthBar().getCurrentValue() <= 0) {
					simulation.levelFailed("You",((Player)player).getTex(), playerPoints);
				}
			}
			
			// if planet pass away
			if(planet.getCurrentHP() <= 0) {
				simulation.levelFailed(planet.getName(), planet.getTex(), playerPoints);
			}
			
			// Update projectile speed and spawn
			if(playerPoints >= (pointCheckpoint *10000) && pointCheckpoint < 2) {
				spaceEntityFactory.setProjectileSpeedMultiplier(spaceEntityFactory.getProjectileSpeedMultiplier()+1);
				projectileSpawnThreshold -= 0.05f;
				pointCheckpoint++;
			}
						
		}
	}
	
	public void handleTempVariance(float deltaTime) {
		if((int)tempEffectTimer <= 0) {
			tempEffect=false;
		}

		// Temperature Variation
	    if(temperatureVariationTimer >= temperatureVariationThreshhold-5 && countdownTime >0) {
	    	if(planet.getName().equals("Neptune")) {
		    	tempVarMessage = "Temperature Variation Incoming!";
		    	tempVarMessageDetails = "Cold Debuff!";
		    	if(temperatureVariationTimer >= temperatureVariationThreshhold) {
		    		tempEffect = true;
		    		temperatureVariationTimer -= 40;
		    	}
	    	}if(planet.getName().equals("Venus")) {
	    		tempVarMessage = "Temperature Variation Incoming!";
	    		tempVarMessageDetails = "HP Tick Damage!";
		    	if(temperatureVariationTimer >= temperatureVariationThreshhold) {
		    		tempEffect = true;
		    		temperatureVariationTimer -= 40;
		    	}
	    	}
	    }else {
	    	tempVarMessage = "";
	    	tempVarMessageDetails = "";
	    }
	    
	    // Temperature Effect Timer 
	    if(tempEffect) {
    		if(planet.getName().equals("Neptune")) {
    			for (iSpacePlayer player : spaceplayerControlManager.getSpacePlayerList()) {
		    		((Player)player).getStaminaBar().setCurrentValue(0.00f);
	    		}
    			tempEffectTimer -= deltaTime;
	    	}if(planet.getName().equals("Venus")) {
	    		for (iSpacePlayer player : spaceplayerControlManager.getSpacePlayerList()) {
		    		((Player)player).getHealthBar().setCurrentValue(((Player)player).getHealthBar().getCurrentValue()-0.01f);;
	    		}
    			tempEffectTimer -= deltaTime;
	    	}
	    }else {
	    	tempEffectTimer = 10;
	    }
	}
	
	public void handleProjectileSpawn(float deltaTime){
		// Spawn projectiles
	    if (projectileSpawnTimer >= projectileSpawnThreshold && countdownTime > 0) {
	    	if(pointCheckpoint >= 2) {
	    		for (iSpacePlayer player : spaceplayerControlManager.getSpacePlayerList()) {
			        spaceEntityManager.add((Projectile)spaceEntityFactory.createDynamicEntity("DoubleProjectileLeft",((Player)player).getPositionX(),((Player)player).getPositionY()));
			        spaceEntityManager.add((Projectile)spaceEntityFactory.createDynamicEntity("DoubleProjectileRight",((Player)player).getPositionX(),((Player)player).getPositionY()));
			    	}
	    	}else {
	    		for (iSpacePlayer player : spaceplayerControlManager.getSpacePlayerList()) {
			        spaceEntityManager.add((Projectile)spaceEntityFactory.createDynamicEntity("SingleProjectile",((Player)player).getPositionX(),((Player)player).getPositionY()));
			    	}
	    	}
	        projectileSpawnTimer -= 0.2; // Reset the timer for next second
	    }
	}
	
	public void handleDebrisSpawn(float deltaTime) {
	    // Spawn debris
	    if (debrisSpawnTimer >= debrisSpawnThreshold && countdownTime > 0) {
	    	for (int i=0; i<(int)Math.floor(Math.random() * DEBRIS_MAX_SPAWN)+DEBRIS_MIN_SPAWN; i++) {
	        	spaceEntityManager.add((Debris)spaceEntityFactory.createEntity("Debris", planet.getName()));	
	        }
	        debrisSpawnTimer -= 1;
	    }
	}
	
	public void handleExplosionSpawn(float deltaTime) {
	    // Handle Explosion Effects (Remove after delay)
	    for (Entity entity : spaceEntityManager.getEntityList()) {
	    	if(entity instanceof Explosion) {
	    		((Explosion)entity).update(deltaTime);
	    	}
	    }
	}
	
	public void update(float deltaTime) {
		// Countdown delay
		delay -= deltaTime;
		
		// Prevent negative timers
		if (delay <= 0) delay = 0; 
        if (countdownTime <= 0) countdownTime = 0;
		
		// After Delay, start game timer
		if(delay == 0) {
			countdownTime -= deltaTime;
		    // Accumulate time for spawning 
		    projectileSpawnTimer += deltaTime;
		    debrisSpawnTimer += deltaTime;
		    // Accumulate time for temp variation
		    temperatureVariationTimer += deltaTime;
		}
		
		handleTempVariance(deltaTime);
		handleProjectileSpawn(deltaTime);
		handleDebrisSpawn(deltaTime);
		handleExplosionSpawn(deltaTime);
        
    	spaceplayerControlManager.update(spaceEntityManager.getEntityList());
        spaceAIControlManager.update(spaceEntityManager.getEntityList());
        spaceCollisionManager.update(spaceEntityManager.getEntityList());
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
		bottomHudFont.dispose();
		topHudFont.dispose();
		countdownFont.dispose();
		spaceplayerControlManager.dispose();
		spaceAIControlManager.dispose();
		spaceCollisionManager.dispose();
		spaceEntityManager.dispose();
	}
	
    public int getPlayerPoints() {
		return playerPoints;
	}

	public void setPlayerPoints(int playerPoints) {
		this.playerPoints = playerPoints;
	}

	public SpaceEntityFactory getSpaceEntityFactory() {
		return spaceEntityFactory;
	}

	public void setSpaceEntityFactory(SpaceEntityFactory spaceEntityFactory) {
		this.spaceEntityFactory = spaceEntityFactory;
	}
	
	public Planet getPlanet() {
		return planet;
	}

	public void setPlanet(Planet planet) {
		this.planet = planet;
	}
	
	
	
}
