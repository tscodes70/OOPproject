package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.mygdx.game.managers.SimulationLifecycleManager;
import com.mygdx.game.models.Simulation;
import com.mygdx.game.simulations.AppSimulation;

public class spacepewpew implements ApplicationListener {

	private SimulationLifecycleManager slm;
	
	@Override
	public void create() {

//		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");
		
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
		for (Simulation s : slm.getSimulations()) {
			s.initialize();
			s.render();
		}
		
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
		
	}

}
