package com.mygdx.game.simulations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.managers.PlayerControlManager;
import com.mygdx.game.models.Simulation;
import com.mygdx.game.screens.SplashScreen;

public class AppSimulation extends Simulation{
	
	private SpriteBatch batch;
	private Texture img;
	
	//audio testing
	private static AssetManager manager;
	private PlayerControlManager pcm;

    @Override
    public void initialize() {
        super.initialize();

    	// Input Processor testing
		pcm = new PlayerControlManager();

		
		//Audio testing
		manager = new AssetManager();
		manager.load("audio/music/megalovania.mp3", Music.class);
		manager.finishLoading();
		
		setScreen(new SplashScreen(manager));
		
        // Load specific resources for this simulation
    }

    @Override
    public void start() {
        super.start();
        // Begin specific simulation logic
    }

    @Override
    public void update(float deltaTime) {
        // Update things in simulation and simulation state
    }

    @Override
    public void render() {
    	super.render();
        // Render the things in simulation
    	pcm.checkKeyEvents();
    	// render(batch);
    	
    }
    
	@Override
	public void render(SpriteBatch batch) {
		// Render things on screen
		
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}



    // ... other lifecycle methods

}
