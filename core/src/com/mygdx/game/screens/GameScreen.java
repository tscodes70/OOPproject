package com.mygdx.game.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.managers.AIControlManager;
import com.mygdx.game.managers.CollisionManager;
import com.mygdx.game.managers.EntityManager;
import com.mygdx.game.managers.PlayerControlManager;
import com.mygdx.game.models.Entity;
import com.mygdx.game.models.Scene;

public class GameScreen extends Scene {	
	private SpriteBatch batch;
    private ShapeRenderer shape;
    private EntityManager entityManager;
    private PlayerControlManager playerControlManager;
    private AIControlManager aiControlManager;
    private CollisionManager collisonManager;
    
	public GameScreen(AssetManager manager, String spriteImageName, String bgMusicName) {
		super(manager, spriteImageName, bgMusicName);
		batch = new SpriteBatch();
		shape = new ShapeRenderer();

		//Using texture render
//        Entity aiEntity = new Entity("image/ai.png", 50, 50, 2, true);
//        Entity playerEntity = new Entity("image/player.png", 50,50,2,false);
		
		//Using shapes render
        Entity playerEntity = new Entity(100, 100, 2, 40, Color.BLUE, false, true);
        Entity aiEntity = new Entity(200,100,2,40,Color.RED, true, true);
		
        //Instantiate EM
		entityManager = new EntityManager();
		
		//Adding into EntityList
		entityManager.addEntity(playerEntity);
		entityManager.addEntity(aiEntity);
		
		//Instantiate AIControlManager
		aiControlManager = new AIControlManager(entityManager.getEntityList());
		
		//Instantiate PlayerControlManager
		playerControlManager = new PlayerControlManager(entityManager.getEntityList());
		
		//Instantiate CollisionManager
		collisonManager = new CollisionManager(entityManager.getEntityList());
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		batch.begin();
		entityManager.drawEntities(batch);
		batch.end();

		shape.begin(ShapeRenderer.ShapeType.Filled);
		entityManager.drawEntities(shape);
		shape.end();
		
		playerControlManager.checkKeyEvents();
		aiControlManager.moveAIControlledEntities();
		collisonManager.checkCollisions();
	}
	
	
}
