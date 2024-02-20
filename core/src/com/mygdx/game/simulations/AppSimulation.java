package com.mygdx.game.simulations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
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
	
	private int gameState;
	private AssetManager manager;
	private SceneManager sceneManager;
	private GeneralControlManager generalControlManager;

	/**
	 * This method is called upon initialization of the AppSimulation.
	 * The AssetManager object is instantiated here and loads the necessary resources
	 * needed for AppSimulation.
	 */
	@Override
	public void initialize() {
		super.initialize();
		
		try {
			// Load all resources for this simulation
			manager = new AssetManager();
			manager.load(Globals.BGAUDIO_SS, Music.class); // splash screen music
			manager.load(Globals.BGAUDIO_GS, Music.class); // game scene music
			manager.load(Globals.BGAUDIO_ES, Music.class); // end scene music
			manager.load(Globals.BGIMAGE_SS, Texture.class);
			manager.load(Globals.IMAGE_BUTTON1, Texture.class);
			manager.load(Globals.IMAGE_BUTTON2, Texture.class);
			manager.finishLoading();
		}catch(Exception ex) {
			System.out.println("INIT ERROR - Unable to load resources, check resource paths.");
			dispose();
			System.exit(0);
		}

		
		System.out.println(String.format("Simulation Name: %s", this.getClass().getName()));
		System.out.println(String.format("Simulation: %s", super.getInitializationStatus()));

	}

	/**
	 * This method is called after initialization of AppSimulation.
	 * All AppSimulation Scenes are instantiated and AppSimulation Starting Scene is set.
	 * GeneralControlManager is instantiated.
	 */
	@Override
	public void start() {
		super.start();

		// Instantiate GeneralIOManager
		generalControlManager = new GeneralControlManager();
		
		// Instantiate SceneManager & All AppSimulation Scenes
		sceneManager = new SceneManager();
		sceneManager.addScene(new SplashScreen(manager, manager.get(Globals.BGIMAGE_SS),Globals.BGAUDIO_SS));
		sceneManager.addScene(new GameScreen(manager, Globals.BGAUDIO_GS));
		sceneManager.addScene(new PauseScreen());
		sceneManager.addScene(new EndScreen(manager, Globals.BGAUDIO_ES));

		// Set Starting Scene
		gameState = Globals.SPLASH_SCREEN;
		sceneManager.setScene(Globals.SPLASH_SCREEN);
		
		System.out.println(String.format("Simulation Status: %s", super.getStartedStatus()));
	}

	/**
	 * Updates AppSimulation
	 */
	@Override
	public void update() {
		sceneManager.updateScene();
	}

	/**
	 * Renders AppSimulation
	 */
	@Override
	public void render() {

		// Transition from SPLASH_SCREEN to GAME_SCREEN (ENTER key)
		if (generalControlManager.pollEnterKey() && gameState == Globals.SPLASH_SCREEN) {
			gameState = Globals.GAME_SCREEN;
			sceneManager.setScene(gameState);
		}
		
		// Transition from END_SCREEN to SPLASH_SCREEN (ESC key)
		if (generalControlManager.pollEscapeKey() && gameState == Globals.END_SCREEN) {
			gameState = Globals.SPLASH_SCREEN;
			sceneManager.setScene(gameState);
		}

		// Transition from GAME_SCREEN to PAUSE_SCREEN and vice-versa (ESC key)
		if (generalControlManager.pollEscapeKey() && (gameState == Globals.GAME_SCREEN || (gameState == Globals.PAUSE_SCREEN))) {
			gameState = (gameState == Globals.GAME_SCREEN) ? Globals.PAUSE_SCREEN : Globals.GAME_SCREEN;
			sceneManager.setScene(gameState);
		}

		// Transition from PAUSE_SCREEN/END_SCREEN to GAME_SCREEN and vice-versa (ENTER key)
		if (generalControlManager.pollEnterKey() && (gameState == Globals.PAUSE_SCREEN || gameState == Globals.SPLASH_SCREEN)) {
			gameState = Globals.GAME_SCREEN;
			sceneManager.setScene(gameState);
		}
        // Transition from PAUSE_SCREEN/GAME_SCREEN to SPLASH_SCREEN (BACKSPACE key)
		if (generalControlManager.pollBackspaceKey() && (gameState == Globals.GAME_SCREEN || gameState == Globals.PAUSE_SCREEN)) {
			gameState = Globals.END_SCREEN;
			sceneManager.resetGameScene(manager);
			sceneManager.setScene(gameState);
		}
		
		sceneManager.renderScene();
	}
	
	/**
	 * Disposal of AppSimulation and its superclass Resources
	 */
	@Override
	public void dispose() {
		super.dispose();
		if (manager != null) manager.dispose();
		if (sceneManager != null) sceneManager.dispose();
		
		System.out.println("AppSimulation Resources Disposed");
	}


}







