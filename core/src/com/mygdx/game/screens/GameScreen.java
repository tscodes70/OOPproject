package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.managers.AIControlManager;
import com.mygdx.game.managers.ButtonControlManager;
import com.mygdx.game.managers.ButtonManager;
import com.mygdx.game.managers.CollisionManager;
import com.mygdx.game.managers.EntityManager;
import com.mygdx.game.managers.PlayerControlManager;
import com.mygdx.game.models.Button;
import com.mygdx.game.models.Entity;
import com.mygdx.game.models.Scene;
import com.mygdx.game.globals.Globals;

public class GameScreen extends Scene {	
	private SpriteBatch batch;
    private ShapeRenderer shape;
    private EntityManager entityManager;
    private PlayerControlManager playerControlManager;
    private AIControlManager aiControlManager;
    private CollisionManager collisionManager;
    private ButtonManager buttonManager;
    private ButtonControlManager buttonControlManager;
    
	public GameScreen(AssetManager manager, String bgMusicName) {
		super(manager, bgMusicName);
		batch = new SpriteBatch();
		shape = new ShapeRenderer();

		//Using texture render
//        Entity aiEntity = new Entity("image/ai.png", 50, 50, 2, true);
//        Entity playerEntity = new Entity("image/player.png", 50,50,2,false);
        
        //Instantiate EntityManager
		entityManager = new EntityManager();
		
		//Creation of Entities (Player Entity, AI Entities)
		entityManager.addEntity(new Entity(100, 100, 2, 40, Color.BLUE, false, true));
        for (int i=0; i<(int)Math.floor(Math.random() * Globals.INITIAL_MAX_SPAWN)+Globals.INITIAL_MIN_SPAWN; i++) {
        	entityManager.addEntity(new Entity(
            		(float) (Math.random() * Gdx.graphics.getWidth()),
            		(float) (Math.random() * Gdx.graphics.getHeight()),
            		Globals.DEFAULT_ENTITY_SPEED,
            		Globals.DEFAULT_ENTITY_RADIUS,
            		Globals.DEFAULT_AI_COLOR, 
            		Globals.AI_CONTROL, 
            		Globals.COLLIDABLE));	
        }
		
		
		//Instantiate AIControlManager
		aiControlManager = new AIControlManager(entityManager.getEntityList());
		
		//Instantiate PlayerControlManager
		playerControlManager = new PlayerControlManager(entityManager.getEntityList());
		
		//Instantiate CollisionManager
		collisionManager = new CollisionManager(entityManager.getEntityList());
		
		//Instantiate ButtonManager
		buttonManager = new ButtonManager();
		
		// add buttons
		buttonManager.addButton(new Button(manager.get(Globals.IMAGE_BUTTON1), 20, 400, 0.6f));
		buttonManager.addButton(new Button(manager.get(Globals.IMAGE_BUTTON2), 20, 300, 0.6f));
		buttonControlManager = new ButtonControlManager(buttonManager.getButtonList());
	}
	
	/**
	 * Renders objects in GameScreen
	 */
	@Override
	public void render() {
		float deltaTime = Gdx.graphics.getDeltaTime();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		//entityManager.drawEntities(batch);
		buttonManager.drawButtons(batch);
		batch.end();

		shape.begin(ShapeRenderer.ShapeType.Filled);
		entityManager.drawEntities(shape);
		shape.end();
		
		playerControlManager.movePlayer(deltaTime);
		aiControlManager.moveAI(deltaTime);
		collisionManager.checkCollisions(entityManager);
		buttonControlManager.handleClickEvents(entityManager,aiControlManager,playerControlManager,collisionManager);

		

	}
	
	@Override
	public void update() {

	}
	
	/**
	 * Disposes GameScreen and its superclass resources
	 */
	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		shape.dispose();
		playerControlManager.dispose();
		aiControlManager.dispose();
		collisionManager.dispose();
		entityManager.dispose();
		buttonManager.dispose();
		buttonControlManager.dispose();

	}
	
	
}
