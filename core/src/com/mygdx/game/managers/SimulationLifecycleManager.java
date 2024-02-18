package com.mygdx.game.managers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.models.Simulation;

public class SimulationLifecycleManager {
	
	private List<Simulation> simulations;
	public SimulationLifecycleManager() {
		simulations = new ArrayList<>();
		
	}
	
	public void addSimulation(Simulation simulation) {
        simulations.add(simulation);
    }

	// called by spacepewpew
    public void updateSimulation() {
        for (Simulation simulation : simulations) {
            if (!simulation.isStarted()) {
                simulation.initialize();
                simulation.start();
            }
            //simulation.update(Gdx.graphics.getDeltaTime());
            simulation.update();
        }
    }

	public List<Simulation> getSimulations() {
		return simulations;
	}

	public void setSimulations(List<Simulation> simulations) {
		this.simulations = simulations;
	}
    
    public void dispose() {
    	for(Simulation s: simulations) {
    		s.dispose();
    	}
    }
}
