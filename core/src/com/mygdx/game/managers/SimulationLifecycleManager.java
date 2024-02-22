package com.mygdx.game.managers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.interfaces.iManager;
import com.mygdx.game.models.Entity;
import com.mygdx.game.models.Simulation;

public class SimulationLifecycleManager implements iManager<Simulation> {
	private List<Simulation> simulationList;
	private List<Simulation> updatedSimulationList;
	
	public SimulationLifecycleManager() {
		simulationList = new ArrayList<>();
		
	}
	
	/**
	 * Add a Simulation into SimulationLifeCycleManager list
	 * @param simulation
	 */
	public void add(Simulation simulation) {
		simulationList.add(simulation);
    }
	
	/**
	 * Remove a Simulation from SimulationLifeCycleManager list
	 * @param simulation
	 */
	public void remove(Simulation simulation) {
		simulationList.remove(simulation);
    }
	
	/**
	 * Retrieves updated simulationList when it is modified
	 * @param simulation
	 */
	public void update(List<Simulation> simulationList) {
		updatedSimulationList = new ArrayList<Simulation>();
		 for (Simulation entity : simulationList) updatedSimulationList.add(entity);
	        this.simulationList = updatedSimulationList;
	}

	/**
	 * Renders All Simulations
	 */
    public void renderSimulations() {
        for (Simulation simulation : simulationList) {
            if (!simulation.isInitialized()) System.out.println(String.format("Simulation Init Status: %s", simulation.getStartedStatus()));
            if (!simulation.isStarted()) System.out.println(String.format("Simulation Run Status: %s", simulation.getStartedStatus()));

            simulation.render();
            simulation.update();
        }
    }
    
    /**
	 * Initialize All Simulations
	 */
    public void initSimulations() {
    	for (Simulation simulation : simulationList) {
    		if (!simulation.isInitialized()) {
    			simulation.initialize();
    			simulation.start();
    		}
    	}
    }

    /**
     * Disposal of SimulationLifeCycleManager
     */
    public void dispose() {
    	for(Simulation s: simulationList) s.dispose();
    	System.out.println("SimulationLifeCycleManager Resources Disposed");
    }

    // Setter Getters
	public List<Simulation> getSimulations() { return simulationList; }

	public void setSimulations(List<Simulation> simulations) { this.simulationList = simulations; }
    
  
}
