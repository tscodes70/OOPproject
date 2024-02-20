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
	
	/**
	 * Adds a Simulation into SimulationLifeCycleManager list
	 * @param simulation
	 */
	public void addSimulation(Simulation simulation) {
        simulations.add(simulation);
    }

	/**
	 * Renders All Simulations
	 */
    public void renderSimulation() {
        for (Simulation simulation : simulations) {
            if (!simulation.isStarted()) {
                simulation.initialize();
                simulation.start();
            }
            simulation.render();
            simulation.update();
        }
    }

    /**
     * Disposal of SimulationLifeCycleManager
     */
    public void dispose() {
    	for(Simulation s: simulations) s.dispose();
    	System.out.println("SimulationLifeCycleManager Resources Disposed");
    }

    // Setter Getters
	public List<Simulation> getSimulations() { return simulations; }

	public void setSimulations(List<Simulation> simulations) { this.simulations = simulations; }
    
  
}
