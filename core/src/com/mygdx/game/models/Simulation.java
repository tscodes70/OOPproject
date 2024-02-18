package com.mygdx.game.models;

import com.mygdx.game.managers.SceneManager;

//public abstract class Simulation extends Game{
public abstract class Simulation {

    private boolean initialized;
    private boolean started;
    private boolean active;
    private int gameState;
	protected SceneManager sm;

    public void initialize() {
        if (!initialized) {
        	gameState = 0;
            // Perform initialization tasks here (e.g., load resources)
        	sm = new SceneManager();	
    		sm.setScene(com.mygdx.game.globals.Constants.SPLASH_SCREEN);
            initialized = true;
        }
    }

    public void start() {
        if (!started) {
            // Begin simulation logic here
            started = true;
        }
    }

    public abstract void update(float deltaTime);

//    public abstract void render(SpriteBatch batch);
   
    public void stop() {
        // Clean up resources (optional)
        started = false;
    }

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
//	@Override
//    public void dispose() {
//		super.dispose();
//        // Dispose of resources (optional)
//        initialized = false;
//    }
//    
	//@Override
//	public void render () {
		//super.render();
//		pcm.checkKeyEvents();
//		ScreenUtils.clear(1, 0, 0, 1);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
//	}
    
    public void update() {
    	// currently implemented in AppSimulation
    }
//	
//	@Override
//	public void resize(int width, int height) {
//		super.resize(width, height);
//	}

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
	public void dispose() {
		sm.dispose(); // make scene manager dispose its resources
	}

}
