package com.mygdx.gamelayer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

public class testScreen extends Scene {	
	private SpriteBatch batch;
    private ShapeRenderer shape;
    private EntityManager entityManager;
    private PlayerControlManager playerControlManager;
    private AIControlManager aiControlManager;
    private CollisionManager collisionManager;
    private ButtonManager buttonManager;
    private ButtonControlManager buttonControlManager;
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
	
	private BitmapFont font;
    private float countdownTime = 180f;
    
	public testScreen(Sound bgMusic, Keyboard keyboardDevice, Mouse mouseDevice) {
		super(bgMusic);
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		
		this.keyboardDevice = keyboardDevice;
		this.mouseDevice = mouseDevice;

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
        
        font = new BitmapFont();
		
		
		//Instantiate AIControlManager
		aiControlManager = new AIControlManager(entityManager.getEntityList());
		
		//Instantiate PlayerControlManager
		playerControlManager = new PlayerControlManager(entityManager.getEntityList(),keyboardDevice);
		
		//Instantiate CollisionManager
		collisionManager = new CollisionManager(entityManager.getEntityList());
		
	}
	
	/**
	 * Renders objects in GameScreen
	 */
	@Override
	public void render() {
		float deltaTime = Gdx.graphics.getDeltaTime();
		update(Gdx.graphics.getDeltaTime());
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		//entityManager.drawEntities(batch);
        font.draw(batch, "Time: " + (int) countdownTime, 10, Gdx.graphics.getHeight() - 10);
		batch.end();

		shape.begin(ShapeRenderer.ShapeType.Filled);
		entityManager.drawEntities(shape);
		shape.setColor(Color.YELLOW);
		shape.arc(400, -250, 450, 0, 180);
		shape.end();
		
		playerControlManager.move(deltaTime);
		aiControlManager.move(deltaTime);
		collisionManager.checkCollisions(entityManager);

	}
	
	public void update(float deltaTime) {
		countdownTime -= deltaTime;

        // Check if the countdown has reached zero or below
        if (countdownTime <= 0) {
            countdownTime = 0; // Prevent negative countdown time
            // Handle countdown completion, e.g., end the game or take appropriate action
        }
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
		playerControlManager.dispose();
		aiControlManager.dispose();
		collisionManager.dispose();
		entityManager.dispose();

	}
	
	
}
