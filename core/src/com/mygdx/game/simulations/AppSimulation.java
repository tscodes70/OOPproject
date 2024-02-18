package com.mygdx.game.simulations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
//import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.managers.GeneralControlManager;
import com.mygdx.game.managers.SceneManager;
import com.mygdx.game.models.Simulation;
import com.mygdx.game.screens.EndScreen;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.SplashScreen;

public class AppSimulation extends Simulation {

	private AssetManager manager;

	private SplashScreen splashScene;
	private GameScreen gameScene;
	private EndScreen endScene;
    private int gameState;

	private SceneManager sceneManager;
	private GeneralControlManager generalControlManager;
	
    @Override
    public void initialize() {
        super.initialize();
    	// General IO Processor testing
        generalControlManager = new GeneralControlManager();
		
        // Load specific resources for this simulation
		manager = new AssetManager();
		manager.load("audio/music/mii-channel.mp3", Music.class); // splash screen music
		manager.load("audio/music/megalovania.mp3", Music.class); // game scene music
		manager.load("audio/music/bbq.mp3", Music.class); // game scene music
		manager.finishLoading();
		
		// instantiate scene instances
		splashScene = new SplashScreen(manager, "image/splash.jpg", "audio/music/mii-channel.mp3");
		gameScene = new GameScreen(manager, "image/badlogic.jpg", "audio/music/megalovania.mp3");
		endScene = new EndScreen(manager, "audio/music/bbq.mp3");
		
		this.sceneManager = new SceneManager();
		this.sceneManager.addScene(splashScene);
		this.sceneManager.addScene(gameScene);
		this.sceneManager.addScene(endScene);
		
		// Initial Scene
		gameState = 0;
    	sceneManager.setScene(com.mygdx.game.globals.Constants.SPLASH_SCREEN);
		
    }

    @Override
    public void start() {
        super.start();
        // Begin specific simulation logic
    }

    // update simulation. currently called from simulation lifecycle manager
    @Override
    public void update() {
    	// testing game logic is currently here
    	
    	// press enter to move from splash screen to game
    	if(generalControlManager.pollEnterKey() && gameState == com.mygdx.game.globals.Constants.SPLASH_SCREEN) {
    		gameState = com.mygdx.game.globals.Constants.GAME_SCREEN;
    		this.sceneManager.setScene(gameState);
		}
    	
    	// press right or left arrow to end game
    	if(generalControlManager.pollBackspaceKey() && gameState == com.mygdx.game.globals.Constants.GAME_SCREEN) {
			gameState = com.mygdx.game.globals.Constants.END_SCREEN;
			this.sceneManager.setScene(gameState);
    	}
    	
    	// press escape key to go back to splash screen
    	if(generalControlManager.pollPauseKey() && gameState == com.mygdx.game.globals.Constants.END_SCREEN) {
			gameState = com.mygdx.game.globals.Constants.SPLASH_SCREEN;
			this.sceneManager.setScene(gameState);
    	}
    	
    	// Render the things in simulation
    	// rendering moved to within scene
    	this.sceneManager.renderScene();
    	
    	// print key events in console
    	generalControlManager.checkKeyEvents();
    	// render(batch);
    	
    }
    
	//@Override
	public void render(SpriteBatch batch) {
		// Render things on screen
		// rendering currently moved inside scene
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}
	
	public void dispose() {
		manager.dispose();
	}

//	@Override
//	public void create() {
//		// TODO Auto-generated method stub
//		
//	}


    // ... other lifecycle methods

}
