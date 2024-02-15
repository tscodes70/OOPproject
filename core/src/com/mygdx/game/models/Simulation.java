package com.mygdx.game.models;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Simulation extends Game{

    private boolean initialized;
    private boolean started;

    public void initialize() {
        if (!initialized) {
            // Perform initialization tasks here (e.g., load resources)
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

    public abstract void render(SpriteBatch batch);
    
    public void stop() {
        // Clean up resources (optional)
        started = false;
    }

	@Override
    public void pause() {
		super.pause();
        // Pause simulation logic (optional)
    }

	@Override
    public void resume() {
		super.resume();
        // Resume simulation logic (optional)
    }

	@Override
    public void dispose() {
		super.dispose();
        // Dispose of resources (optional)
        initialized = false;
    }
    
	@Override
	public void render () {
		super.render();
//		pcm.checkKeyEvents();
//		ScreenUtils.clear(1, 0, 0, 1);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

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
