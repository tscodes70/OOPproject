package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
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


public class spacepewpew extends ApplicationAdapter {

	private SimulationLifecycleManager slm;

	
	@Override
	public void create() {
		
		// Simulation Lifecycle
		slm = new SimulationLifecycleManager();
		slm.addSimulation(new AppSimulation());
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
		slm.renderSimulation();

	}
	
	public void update() {
		slm.updateSimulation();
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
//		batch.dispose();
//		shape.dispose();
	}

}
