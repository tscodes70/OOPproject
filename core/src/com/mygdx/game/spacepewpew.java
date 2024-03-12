package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.gameengine.managers.SimulationLifecycleManager;
//import com.mygdx.game.models.Simulation;
import com.mygdx.gamelayer.simulation.AppSimulation;
import com.mygdx.gameengine.models.Entity;
import com.mygdx.gameengine.models.Simulation;
import com.mygdx.gameengine.managers.EntityManager;
import java.util.List;
import java.util.ArrayList;


public class spacepewpew extends ApplicationAdapter {

	private SimulationLifecycleManager slm;

	
	@Override
	public void create() {
		
		// Simulation Lifecycle
		slm = new SimulationLifecycleManager();
		slm.add(new AppSimulation());
		slm.initSimulations();

	}

	@Override
	public void render() {
		for (Simulation s : slm.getSimulations()) {
			if(!s.isInitialized()) {
				s.initialize();
			}
			slm.renderSimulations();
		}
	}

	@Override
	public void dispose() {
		slm.dispose();
	}

}
