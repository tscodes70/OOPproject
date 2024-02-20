package com.mygdx.game.simulations;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.managers.SceneManager;
import com.mygdx.game.models.KeyboardInput;
import com.mygdx.game.models.MouseInput;
import com.mygdx.game.models.Simulation;
import com.mygdx.game.screens.EndScreen;
import com.mygdx.game.screens.PauseScreen;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.SplashScreen;

public class AppSimulation extends Simulation {
	
	private int gameState;
	private AssetManager manager;
	private SceneManager sceneManager;
	private KeyboardInput keyboardDevice;
	private MouseInput mouseDevice;
	
	private final int ENTERKEY = Keys.ENTER;
	private final int BACKSPACEKEY = Keys.BACKSPACE;
	private final int ESCAPEKEY = Keys.ESCAPE;
	private final int LEFTARROWKEY = Keys.LEFT;
	private final int RIGHTARROWKEY = Keys.RIGHT;
	private final int UPARROWKEY = Keys.UP;
	private final int DOWNARROWKEY = Keys.DOWN;
	
	private final int LEFTCLICKBUTTON = Buttons.LEFT;
	private final int RIGHTCLICKBUTTON = Buttons.RIGHT;
	
	private final String IMAGE_PATH = "image";
	private final String AUDIO_PATH = "audio/music";

	private final String IMAGE_SA = String.format("%s/spawnai.png", IMAGE_PATH);
	private final String IMAGE_SP = String.format("%s/spawnplayer.png", IMAGE_PATH);
	
	private final String BGAUDIO_SS = String.format("%s/mii-channel.mp3", AUDIO_PATH);
	private final String BGAUDIO_GS = String.format("%s/megalovania.mp3", AUDIO_PATH);
	private final String BGAUDIO_ES = String.format("%s/bbq.mp3", AUDIO_PATH);
	private final String BGIMAGE_SS = String.format("%s/splash.jpg", IMAGE_PATH);
	
	private final int SPLASH_SCREEN = 0;
	private final int GAME_SCREEN = 1;
	private final int PAUSE_SCREEN = 2;
	private final int END_SCREEN = 3;


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
			manager.load(BGAUDIO_SS, Music.class); // splash screen music
			manager.load(BGAUDIO_GS, Music.class); // game scene music
			manager.load(BGAUDIO_ES, Music.class); // end scene music
			manager.load(BGIMAGE_SS, Texture.class);
			manager.load(IMAGE_SA, Texture.class);
			manager.load(IMAGE_SP, Texture.class);
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
		
		// Instantiate KeyboardInput
		keyboardDevice = new KeyboardInput();
		keyboardDevice.add(LEFTARROWKEY);
		keyboardDevice.add(RIGHTARROWKEY);
		keyboardDevice.add(UPARROWKEY);
		keyboardDevice.add(DOWNARROWKEY);
		keyboardDevice.add(ESCAPEKEY);	
		keyboardDevice.add(ENTERKEY);
		keyboardDevice.add(BACKSPACEKEY);
		
		// Instantiate MouseInput
		mouseDevice = new MouseInput();
		mouseDevice.add(LEFTCLICKBUTTON);
		mouseDevice.add(RIGHTCLICKBUTTON);
		
		// Instantiate SceneManager & All AppSimulation Scenes
		sceneManager = new SceneManager();
		sceneManager.add(new SplashScreen(manager, manager.get(BGIMAGE_SS),BGAUDIO_SS));
		sceneManager.add(new GameScreen(manager, BGAUDIO_GS,keyboardDevice,mouseDevice));
		sceneManager.add(new PauseScreen());
		sceneManager.add(new EndScreen(manager, BGAUDIO_ES));

		// Set Starting Scene
		gameState = SPLASH_SCREEN;
		sceneManager.setScene(SPLASH_SCREEN);
		
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
		if (keyboardDevice.pollInputPress(ENTERKEY) && gameState == SPLASH_SCREEN) {
			gameState = GAME_SCREEN;
			sceneManager.setScene(gameState);
		}
		
		// Transition from END_SCREEN to SPLASH_SCREEN (ESC key)
		if (keyboardDevice.pollInputPress(ESCAPEKEY) && gameState == END_SCREEN) {
			gameState = SPLASH_SCREEN;
			sceneManager.setScene(gameState);
		}

		// Transition from GAME_SCREEN to PAUSE_SCREEN and vice-versa (ESC key)
		if (keyboardDevice.pollInputPress(ESCAPEKEY) && (gameState == GAME_SCREEN || (gameState == PAUSE_SCREEN))) {
			gameState = (gameState == GAME_SCREEN) ? PAUSE_SCREEN : GAME_SCREEN;
			sceneManager.setScene(gameState);
		}

		// Transition from PAUSE_SCREEN/END_SCREEN to GAME_SCREEN and vice-versa (ENTER key)
		if (keyboardDevice.pollInputPress(ENTERKEY) && (gameState == PAUSE_SCREEN || gameState == SPLASH_SCREEN)) {
			gameState = GAME_SCREEN;
			sceneManager.setScene(gameState);
		}
        // Transition from PAUSE_SCREEN/GAME_SCREEN to SPLASH_SCREEN (BACKSPACE key)
		if (keyboardDevice.pollInputPress(BACKSPACEKEY) && (gameState == GAME_SCREEN || gameState == PAUSE_SCREEN)) {
			gameState = END_SCREEN;
			sceneManager.resetGameScene(manager, keyboardDevice, mouseDevice);
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







