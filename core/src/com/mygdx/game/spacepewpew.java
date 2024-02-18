package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.managers.SimulationLifecycleManager;
//import com.mygdx.game.models.Simulation;
import com.mygdx.game.simulations.AppSimulation;
import com.mygdx.game.models.Entity;
import com.mygdx.game.managers.EntityManager;
import java.util.List;
import java.util.ArrayList;


public class spacepewpew implements ApplicationListener {

	private SimulationLifecycleManager slm;
	private SpriteBatch batch;
    private ShapeRenderer shape;
    private EntityManager entityManager;
	
	@Override
	public void create() {
		
		// Simulation Lifecycle
		slm = new SimulationLifecycleManager();
		slm.addSimulation(new AppSimulation());

		// Batch draw sprites & textures in LibGDX
		batch = new SpriteBatch();
		shape = new ShapeRenderer();

        Entity aiEntity = new Entity("badlogic.jpg", 50, 50, 2, true);
        Entity playerEntity = new Entity(100, 100, 2, 40, Color.BLUE);

		// Creation of ArrayList for entityList
		List<Entity> entityList = new ArrayList<Entity>();

		//Adding into EntityList
		entityList.add(aiEntity);
		entityList.add(playerEntity);

		entityManager = new EntityManager(entityList);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		// for now only one simulation in the entire game
//		for (Simulation s : slm.getSimulations()) {
//			if(!s.isInitialized()) {
//				s.initialize();
//			}
//			slm.update();
//			s.update();
//		}
		slm.updateSimulation();

		batch.begin();
		entityManager.drawEntities(batch);
		batch.end();

		shape.begin(ShapeRenderer.ShapeType.Filled);
		entityManager.drawEntities(shape);
		shape.end();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		slm.dispose();
		batch.dispose();
		shape.dispose();
	}

}
