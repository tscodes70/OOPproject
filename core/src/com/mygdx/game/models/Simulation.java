package com.mygdx.game.models;

import com.mygdx.game.managers.SceneManager;

//public abstract class Simulation extends Game{
public abstract class Simulation {

    private boolean initialized;
    private boolean started;

	protected SceneManager sm;

    public void initialize() {
        if (!initialized) {
            initialized = true;
        }
    }

    public void start() {
        if (!started) {
            // Begin simulation logic here
            started = true;
        }
    }
   
    public void stop() {
        // Clean up resources (optional)
        started = false;
    }
    
	public void dispose() {
        initialized = false;
	}
	
    public abstract void render();
    public abstract void update();

//	@Override
//    public void pause() {
//		super.pause();
//        // Pause simulation logic (optional)
//    }
//
//	@Override
//    public void resume() {
//		super.resume();
//        // Resume simulation logic (optional)
//    }
//

	// Getter Setter
	public boolean isInitialized() {
		return initialized;
	}
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
	public boolean isStarted() {
		return started;
	}
	public void setStarted(boolean started) {
		this.started = started;
	}


}
