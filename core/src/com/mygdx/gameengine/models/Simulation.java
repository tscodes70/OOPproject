package com.mygdx.gameengine.models;

public abstract class Simulation {

    private boolean initialized;
    private boolean started;

    public abstract void render();
    public abstract void update();
    
    /**
     * Initialization of Simulation, set initialization=true
     */
    public void initialize() {
        if (!initialized) initialized = true;
    }

    /**
     * Start of Simulation, set started=true
     */
    public void start() {
        if (!started) started = true;
    }
   
    /**
     * Stop of Simulation, set started=false
     */
    public void stop() {
        started = false;
    }
    
    /**
     * Disposal of Simulation, set initialized=false
     */
	public void dispose() {
        initialized = false;
	}

    public String getInitializationStatus() {
    	if (initialized) return "Initialized";
    	else return "Not Initialized";
    }
    
    public String getStartedStatus() {
    	if (started) return "Running";
    	else return "Stopped";
    }

	// Getter Setter
	public boolean isInitialized() { return initialized; }
	
	public void setInitialized(boolean initialized) { this.initialized = initialized; }

	public boolean isStarted() { return started; }
	
	public void setStarted(boolean started) { this.started = started; }


}
