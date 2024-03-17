package com.mygdx.gamelayer.screens;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
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
import com.mygdx.gamelayer.simulation.AppSimulation;

public class GameScreen extends Scene {	
	private SpriteBatch batch;
    private ShapeRenderer shape;
    private EntityManager entityManager;
    private PlayerControlManager playerControlManager;
    private AIControlManager aiControlManager;
    private CollisionManager collisionManager;
    private ButtonManager buttonManager;
    private ButtonControlManager buttonControlManager;
    private AppSimulation simulation;
    private Keyboard keyboardDevice;
    private Mouse mouseDevice;
    
	private final String IMAGE_PATH = "image";

	private final String IMAGE_SA = String.format("%s/spawnai.png", IMAGE_PATH);
	private final String IMAGE_SP = String.format("%s/spawnplayer.png", IMAGE_PATH);
	
	private final int DEFAULT_ENTITY_SPEED = 2;
	private final int DEFAULT_ENTITY_RADIUS = 40;
	private final int DEFAULT_PLAYER_X = 400;
	private final int DEFAULT_PLAYER_Y = 250;
	private final Color DEFAULT_PLAYER_COLOR = Color.BLUE;
	private final Color DEFAULT_AI_COLOR = Color.RED;
	private final boolean COLLIDABLE = true;
	private final boolean AI_CONTROL = true;
	
	private final int INITIAL_MAX_SPAWN = 15;
	private final int INITIAL_MIN_SPAWN = 5;
	
	private BitmapFont font, countdownFont;
	private GlyphLayout countdown;
    private float countdownTime = 180f;
    private float delay;
    private Texture planet; // test
    private String planetName;
    private float gravity;
    // map planet to their respective gravity values for movement
    private HashMap<String, Float> planetGravityMapping;
	public GameScreen(String planetName, Texture planetImage, Sound bgMusic, Keyboard keyboardDevice, Mouse mouseDevice, AppSimulation simulation) {
		super(bgMusic);
		batch = new SpriteBatch();
		shape = new ShapeRenderer();

		this.keyboardDevice = keyboardDevice;
		this.mouseDevice = mouseDevice;
		this.simulation = simulation;
		this.planet = planetImage;
		this.planetName = planetName;
		
		planet.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		//Using texture render
//        Entity aiEntity = new Entity("image/ai.png", 50, 50, 2, true);
//        Entity playerEntity = new Entity("image/player.png", 50,50,2,false);
        
        //Instantiate EntityManager
		entityManager = new EntityManager();
		
		//Creation of Entities (Player Entity, AI Entities)
		entityManager.add(new Entity(DEFAULT_PLAYER_X, DEFAULT_PLAYER_Y, DEFAULT_ENTITY_SPEED, DEFAULT_ENTITY_RADIUS, DEFAULT_PLAYER_COLOR, false, true));
        for (int i=0; i<(int)Math.floor(Math.random() * INITIAL_MAX_SPAWN)+INITIAL_MIN_SPAWN; i++) {
        	entityManager.add(new Entity(
            		(float) (Math.random() * Gdx.graphics.getWidth()),
            		(float) (Math.random() * Gdx.graphics.getHeight()),
            		DEFAULT_ENTITY_SPEED,
            		DEFAULT_ENTITY_RADIUS,
            		DEFAULT_AI_COLOR, 
            		AI_CONTROL, 
            		COLLIDABLE));	
        }
        
		// set the planet gravity for use in movement controls
        planetGravityMapping = new HashMap<String, Float>();
        planetGravityMapping.put("Mercury", 0.38f);
        planetGravityMapping.put("Venus", 0.904f);
        planetGravityMapping.put("Earth", 1f);
        planetGravityMapping.put("Mars", 0.38f);
        
        font = new BitmapFont();
        
        // dynamically generate bitmap font of our desired size so it doesn't look pixelated
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 160; // font size in pixels
		countdownFont = generator.generateFont(parameter);
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
        countdown = new GlyphLayout(countdownFont, "3"); //for getting width/height of text
        
        // set gravity according to selected planet
		this.gravity = planetGravityMapping.get(planetName);

		//Instantiate AIControlManager
		aiControlManager = new AIControlManager(entityManager.getEntityList());
		
		//Instantiate PlayerControlManager
		playerControlManager = new PlayerControlManager(entityManager.getEntityList(), keyboardDevice);
		
		//Instantiate CollisionManager
		collisionManager = new CollisionManager(entityManager.getEntityList());
	}
	
	/**
	 * Renders objects in GameScreen
	 */
	@Override
	public void render() {
		float deltaTime = Gdx.graphics.getDeltaTime();
		update(deltaTime);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		shape.begin(ShapeRenderer.ShapeType.Filled);
		entityManager.drawEntities(shape);
		shape.end();

		
		batch.begin();
        font.draw(batch, "Time: " + (int) countdownTime, 10, Gdx.graphics.getHeight() - 10);
        
        // test draw planet image
        batch.draw(planet, 0, -(Gdx.graphics.getWidth() / 2f), Gdx.graphics.getWidth(), 
        		0.75f * ((float)Gdx.graphics.getWidth() /  (float)Gdx.graphics.getHeight()) * (float)Gdx.graphics.getHeight());
        
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
			aiControlManager.move(deltaTime);
			collisionManager.checkCollisions(entityManager);
			
			// test test
			// hardcoded to go to level cleared screen after 10s of gameplay
			if((int)countdownTime <= 170) {
				simulation.levelCleared(planetName, planet);
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
		if(delay == 0) countdownTime -= deltaTime;

        if (countdownTime <= 0) {
            countdownTime = 0; // Prevent negative countdown time
            // Handle countdown completion, e.g., end the game or take appropriate action
        }
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
		aiControlManager.dispose();
		collisionManager.dispose();
		entityManager.dispose();
	}
}
