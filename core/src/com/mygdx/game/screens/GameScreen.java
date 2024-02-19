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

public class GameScreen extends Scene {	
	private SpriteBatch batch;
    private ShapeRenderer shape;
    private EntityManager entityManager;
    private PlayerControlManager playerControlManager;
    private AIControlManager aiControlManager;
    private CollisionManager collisonManager;
    private ButtonManager buttonManager;
    private ButtonControlManager buttonControlManager;
    private Entity[] circles;
    
	public GameScreen(AssetManager manager, String bgMusicName) {
		super(manager, bgMusicName);
		batch = new SpriteBatch();
		shape = new ShapeRenderer();

		//Using texture render
//        Entity aiEntity = new Entity("image/ai.png", 50, 50, 2, true);
//        Entity playerEntity = new Entity("image/player.png", 50,50,2,false);
		
		//Using shapes render
        Entity playerEntity = new Entity(100, 100, 2, 40, Color.BLUE, false, true);
        
        Button button = new Button("image/button1.png", 20, 400, 0.6f);
        Button button2 = new Button("image/button2.png", 20, 300, 0.6f);
        
        circles = new Entity[10];
        for (int i=0; i<circles.length; i++) {
        	float positionX = (float) (Math.random() * Gdx.graphics.getWidth());
        	float positionY = (float) (Math.random() * Gdx.graphics.getHeight());
            circles[i] = new Entity(positionX ,positionY ,2,40,Color.RED, true, true);	
        }
		
        
        //Instantiate EM
		entityManager = new EntityManager();
		
		//Adding into EntityList
		entityManager.addEntity(playerEntity);
		for (Entity circle : circles) {
        	entityManager.addEntity(circle);
        	}		
		//Instantiate AIControlManager
		aiControlManager = new AIControlManager(entityManager.getEntityList());
		
		//Instantiate PlayerControlManager
		playerControlManager = new PlayerControlManager(entityManager.getEntityList());
		
		//Instantiate CollisionManager
		collisonManager = new CollisionManager(entityManager.getEntityList());
		
		// create button manager
		buttonManager = new ButtonManager();
		
		// add buttons
		buttonManager.addButton(button);
		buttonManager.addButton(button2);
		buttonControlManager = new ButtonControlManager(buttonManager.getButtonList());
	}
	
	@Override
	public void render() {
		// super.render();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		//entityManager.drawEntities(batch);
		buttonManager.drawButtons(batch);
		batch.end();

		shape.begin(ShapeRenderer.ShapeType.Filled);
		entityManager.drawEntities(shape);
		shape.end();
		
		playerControlManager.checkKeyEvents();
		buttonControlManager.checkClickEvents();
		aiControlManager.moveAIControlledEntities();
		collisonManager.checkCollisions(entityManager);
	}
	
	
}
