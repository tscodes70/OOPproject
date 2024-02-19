package com.mygdx.game.simulations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
//import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.game.managers.GeneralControlManager;
import com.mygdx.game.managers.SceneManager;
import com.mygdx.game.models.Simulation;
import com.mygdx.game.screens.EndScreen;
import com.mygdx.game.screens.PauseScreen;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.SplashScreen;
import com.mygdx.game.globals.Globals;

public class AppSimulation extends Simulation {

	private AssetManager manager;

	private SplashScreen splashScene;
	private GameScreen gameScene;
	private PauseScreen pauseScene;
	private EndScreen endScene;
	private int gameState;

	private SceneManager sceneManager;
	private GeneralControlManager generalControlManager;

	@Override
	public void initialize() {
		super.initialize();
		// General IO Processor
		generalControlManager = new GeneralControlManager();

		// Load specific resources for this simulation
		manager = new AssetManager();
		manager.load(Globals.BGAUDIO_SS, Music.class); // splash screen music
		manager.load(Globals.BGAUDIO_GS, Music.class); // game scene music
		manager.load(Globals.BGAUDIO_ES, Music.class); // end scene music
		manager.finishLoading();

	}

	@Override
	public void start() {
		super.start();
		// Begin specific simulation logic

		// instantiate scene instances
		splashScene = new SplashScreen(manager, Globals.BGIMAGE_SS, Globals.BGAUDIO_SS);
		gameScene = new GameScreen(manager, Globals.BGAUDIO_GS);
		pauseScene = new PauseScreen(manager, Globals.BGAUDIO_GS);
		endScene = new EndScreen(manager, Globals.BGAUDIO_ES);//changed to EndScreen, was previously set to pause but we didn't have a screen so I made one and renamed this

		this.sceneManager = new SceneManager();
		this.sceneManager.addScene(splashScene);
		this.sceneManager.addScene(gameScene);
		this.sceneManager.addScene(pauseScene);//this adds a scene into scenemanager list
		this.sceneManager.addScene(endScene);

		// Initial Scene
		gameState = 0;
		sceneManager.setScene(Globals.SPLASH_SCREEN);
	}

	@Override
	public void update() {
		System.out.println("update");

	}

	@Override
	public void render() {

		// Enter key to move from splash screen to game
		if (generalControlManager.pollEnterKey() && gameState == Globals.SPLASH_SCREEN) {
			gameState = Globals.GAME_SCREEN;
			this.sceneManager.setScene(gameState);
		}

		// Pause key to toggle pause screen or play screen depends on where you are therefore the else if.
		// YOu can use escape here to go back... if we want to keep things simple i can just remove it
		// so that we can just use enter to continue
		if (generalControlManager.pollPauseKey()) {
			if (gameState == Globals.GAME_SCREEN) {
				gameState = Globals.PAUSE_SCREEN;
			} else if (gameState == Globals.PAUSE_SCREEN) {
				gameState = Globals.GAME_SCREEN;
			}
			this.sceneManager.setScene(gameState);
		}

		// Enter key to continue from pause screen
		if (generalControlManager.pollEnterKey() && (gameState == Globals.PAUSE_SCREEN || gameState == Globals.END_SCREEN)) {
			gameState = Globals.GAME_SCREEN;
			this.sceneManager.setScene(gameState);
		}
        // be it anywhere the backspace allows a reset to the menu
		if (generalControlManager.pollBackspaceKey() && (gameState == Globals.GAME_SCREEN || gameState == Globals.PAUSE_SCREEN)) {
			if (gameState == Globals.PAUSE_SCREEN) {
				gameState = Globals.GAME_SCREEN;
			} else {
				gameState = Globals.SPLASH_SCREEN;
			}
			this.sceneManager.setScene(gameState);
		}

		generalControlManager.checkKeyEvents();
		generalControlManager.checkClickEvents();
		// print key events in console
		// Render the things in simulation
		// rendering moved to within scene
		this.sceneManager.renderScene();

/* This was the prev code
    	// press enter to move from splash screen to game
    	if(generalControlManager.pollEnterKey() && gameState == Globals.SPLASH_SCREEN) {
    		gameState = Globals.GAME_SCREEN;
    		this.sceneManager.setScene(gameState);
		}

    	// press right or left arrow to end game
    	if(generalControlManager.pollBackspaceKey() && gameState == Globals.GAME_SCREEN) {
			gameState = Globals.END_SCREEN;
			this.sceneManager.setScene(gameState);
    	}

    	// press escape key to go back to splash screen
    	if(generalControlManager.pollPauseKey() && gameState == Globals.END_SCREEN) {
			gameState = Globals.SPLASH_SCREEN;
			this.sceneManager.setScene(gameState);
    	}

    	generalControlManager.checkKeyEvents();
    	generalControlManager.checkClickEvents();
    	// print key events in console
    	// Render the things in simulation
    	// rendering moved to within scene
    	this.sceneManager.renderScene();
 */
	}

	@Override
	public void dispose() {
		super.dispose();
		manager.dispose();
	}


}







