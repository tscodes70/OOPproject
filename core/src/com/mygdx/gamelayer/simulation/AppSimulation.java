package com.mygdx.gamelayer.simulation;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.gameengine.interfaces.iInput;
import com.mygdx.gameengine.interfaces.iOutput;
import com.mygdx.gameengine.managers.IOManager;
import com.mygdx.gameengine.managers.InputManager;
import com.mygdx.gameengine.managers.OutputManager;
import com.mygdx.gameengine.managers.SceneManager;
import com.mygdx.gameengine.models.Keyboard;
import com.mygdx.gameengine.models.Mouse;
import com.mygdx.gameengine.models.Simulation;
import com.mygdx.gameengine.models.Sound;
import com.mygdx.gamelayer.screens.EndScreen;
import com.mygdx.gamelayer.screens.PauseScreen;
import com.mygdx.gamelayer.screens.GameScreen;
import com.mygdx.gamelayer.screens.SplashScreen;
import com.mygdx.gamelayer.screens.testScreen;

public class AppSimulation extends Simulation {
	
	private int gameState;
	private SceneManager sceneManager;
	private Keyboard keyboardDevice;
	private Mouse mouseDevice;
	private InputManager<iInput> iManager;
	private OutputManager<iOutput> oManager;
	private IOManager ioManager;
	
	private Sound bgSSMusic, bgGSMusic, bgESMusic;
	
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
	
	private final String BGAUDIO_SS_PATH = String.format("%s/mii-channel.mp3", AUDIO_PATH);
	private final String BGAUDIO_GS_PATH = String.format("%s/burnt_toaster.mp3", AUDIO_PATH);
	private final String BGAUDIO_ES_PATH = String.format("%s/victory_fanfare.mp3", AUDIO_PATH);
	
	private final String BGIMAGE_SS_PATH = String.format("%s/splash.jpg", IMAGE_PATH);
	
	private final int SPLASH_SCREEN = 0;
	private final int GAME_SCREEN = 1;
	private final int PAUSE_SCREEN = 2;
	private final int END_SCREEN = 3;
	
	// Lazy way (need change to use omanager)
	private Texture buttonSA = new Texture(IMAGE_SA);
	private Texture buttonSP = new Texture(IMAGE_SP);
	


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
			// Instantiate IOManagers
			iManager = new InputManager<iInput>();
			keyboardDevice = new Keyboard();
			mouseDevice = new Mouse();
			iManager.add(keyboardDevice);
			iManager.add(mouseDevice);
			
			oManager = new OutputManager<iOutput>();
			bgSSMusic = new Sound(BGAUDIO_SS_PATH);
			bgGSMusic = new Sound(BGAUDIO_GS_PATH);
			bgESMusic = new Sound(BGAUDIO_ES_PATH);
			oManager.add(BGAUDIO_SS_PATH,bgSSMusic);
			oManager.add(BGAUDIO_GS_PATH,bgGSMusic);
			oManager.add(BGAUDIO_ES_PATH,bgESMusic);
			
			ioManager = new IOManager(iManager,oManager);
			
		}catch(Exception ex) {
			System.out.println("INIT ERROR - Unable to load resources, check resource paths.");
			dispose();
			System.exit(0);
		}

		
		System.out.println(String.format("Simulation Name: %s", this.getClass().getName()));
		System.out.println(String.format("Simulation Init Status: %s", super.getInitializationStatus()));

	}

	/**
	 * This method is called after initialization of AppSimulation.
	 * All AppSimulation Scenes are instantiated and AppSimulation Starting Scene is set.
	 */
	@Override
	public void start() {
		super.start();
		
		// Instantiate SceneManager & All AppSimulation Scenes
		sceneManager = new SceneManager(oManager);
		Texture bgSSImage = new Texture(BGIMAGE_SS_PATH);
		sceneManager.add(new SplashScreen(bgSSMusic,bgSSImage));
		sceneManager.add(new testScreen(bgGSMusic,keyboardDevice,mouseDevice));
//		sceneManager.add(new GameScreen(buttonSA,buttonSP,bgGSMusic,keyboardDevice,mouseDevice));
		sceneManager.add(new PauseScreen());
		sceneManager.add(new EndScreen(bgESMusic));

		// Set Starting Scene
		gameState = SPLASH_SCREEN;
		sceneManager.setScene(SPLASH_SCREEN);
		
		System.out.println(String.format("Simulation Run Status: %s", super.getStartedStatus()));
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
			sceneManager.resetGameScene(keyboardDevice, mouseDevice);
			sceneManager.setScene(gameState);
		}
		
		sceneManager.renderScene();
	}
	
	/**
	 * Disposal of AppSimulation and its superclass Resources
	 */
	@Override
	public void dispose() {
		super.stop();
		super.dispose();
		if (sceneManager != null) sceneManager.dispose();
		
		System.out.println("AppSimulation Resources Disposed");
	}


}







