package com.mygdx.gamelayer.simulation;

import java.util.HashMap;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
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
import com.mygdx.gamelayer.screens.*;
public class AppSimulation extends Simulation {
	
	private int gameState;
	private SceneManager sceneManager;
	private Keyboard keyboardDevice;
	private Mouse mouseDevice;
	private InputManager<iInput> iManager;
	private OutputManager<iOutput> oManager;
	private IOManager ioManager;
	
	private Sound bgSSMusic, bgGSMusic, bgESMusic;
	
	private Texture buttonSA, buttonSP;
	
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
	
	// generic menu buttons
	private final String IMAGE_START_PATH = String.format("%s/start.png", IMAGE_PATH);
	private final String IMAGE_STATS_PATH = String.format("%s/stats.png", IMAGE_PATH);
	private final String IMAGE_QUIT_PATH = String.format("%s/quit.png", IMAGE_PATH);
	private final String IMAGE_BACK_PATH = String.format("%s/back.png", IMAGE_PATH);
	private final String IMAGE_CONTINUE_PATH = String.format("%s/continue.png", IMAGE_PATH);

	// planet select button image paths
	private final String IMAGE_MERCURY_PATH = String.format("%s/mercury.png", IMAGE_PATH);
	private final String IMAGE_VENUS_PATH = String.format("%s/venus.png", IMAGE_PATH);
	private final String IMAGE_EARTH_PATH = String.format("%s/earth.png", IMAGE_PATH);
	private final String IMAGE_MARS_PATH = String.format("%s/mars.png", IMAGE_PATH);
	private final String BGAUDIO_SS_PATH = String.format("%s/mii-channel.mp3", AUDIO_PATH);
	private final String BGAUDIO_GS_PATH = String.format("%s/burnt_toaster.mp3", AUDIO_PATH);
	private final String BGAUDIO_ES_PATH = String.format("%s/victory_fanfare.mp3", AUDIO_PATH);
	
	// background images
	private final String BGIMAGE_SS_PATH = String.format("%s/splash.jpg", IMAGE_PATH);
	private final String BGIMAGE_LSS_PATH = String.format("%s/choose_planet.png", IMAGE_PATH);
	private final String BGIMAGE_LC_PATH = String.format("%s/level_cleared.png", IMAGE_PATH);
	private final String BGIMAGE_STATS_PATH = String.format("%s/statsbg.png", IMAGE_PATH);


	// default planet image for game screen
	private final String IMAGE_PLANET_MERCURY_PATH = String.format("%s/mercury_planet.png", IMAGE_PATH);

	// indexes of the screens
	private final int SPLASH_SCREEN = 0;
	private final int LEVEL_SELECT_SCREEN = 1;
	private final int LEVEL_INFO_SCREEN = 2;
	private final int GAME_SCREEN = 3;
	private final int PAUSE_SCREEN = 4;
	private final int END_SCREEN = 5;
	private final int STATS_SCREEN = 6;
	
	// Button texture lists
	private HashMap<String, Texture> menuButtons;
	private HashMap<String, Texture> levelButtons;
	private HashMap<String, Texture> planetInfoButtons;
	private HashMap<String, Texture> levelClearedButtons;
	private HashMap<String, Texture> statsButtons;

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
			
			buttonSA = new Texture(IMAGE_SA);
			buttonSP = new Texture(IMAGE_SP);
			
			ioManager = new IOManager(iManager,oManager);
			
			// hashmaps of button textures to pass to their respective scenes
			// main menu buttons
			menuButtons = new HashMap<String, Texture>();
			menuButtons.put("Start", new Texture(IMAGE_START_PATH));
			menuButtons.put("Stats", new Texture(IMAGE_STATS_PATH));
			menuButtons.put("Quit", new Texture(IMAGE_QUIT_PATH));

			// planet selection buttons
			levelButtons = new HashMap<String, Texture>();
			levelButtons.put("Mercury", new Texture(IMAGE_MERCURY_PATH));
			levelButtons.put("Venus", new Texture(IMAGE_VENUS_PATH));
			levelButtons.put("Earth", new Texture(IMAGE_EARTH_PATH));
			levelButtons.put("Mars", new Texture(IMAGE_MARS_PATH));
			levelButtons.put("Back", new Texture(IMAGE_BACK_PATH));

			// planet info screen buttons
			planetInfoButtons = new HashMap<String, Texture>();
			planetInfoButtons.put("Start", new Texture(IMAGE_START_PATH));
			planetInfoButtons.put("Back", new Texture(IMAGE_BACK_PATH));

			// level cleared screen buttons
			levelClearedButtons = new HashMap<String, Texture>();
			levelClearedButtons.put("Continue", new Texture(IMAGE_CONTINUE_PATH));
			
			// stats screen buttons
			statsButtons = new HashMap<String,Texture>();
			statsButtons.put("Back", new Texture(IMAGE_BACK_PATH));

		} catch(Exception ex) {
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
		
		// background image textures
		Texture bgSSImage = new Texture(BGIMAGE_SS_PATH);
		Texture bgLSSImage = new Texture(BGIMAGE_LSS_PATH);
		Texture planetImage = new Texture(IMAGE_PLANET_MERCURY_PATH); // default planet image for game screen
		Texture bgLCImage = new Texture(BGIMAGE_LC_PATH);
		Texture bgStatsImage = new Texture(BGIMAGE_STATS_PATH);

		// a reference to appsimulation is passed to scenes so that they can request changes of game state
		sceneManager.add(new MainMenuScreen(menuButtons, bgSSImage, bgSSMusic ,mouseDevice, this));
		sceneManager.add(new LevelSelectScreen(levelButtons, bgLSSImage, bgSSMusic ,mouseDevice, this));
		sceneManager.add(new PlanetInfoScreen(planetInfoButtons, bgLSSImage, bgSSMusic ,mouseDevice, this));
		//sceneManager.add(new SplashScreen(bgSSMusic,bgSSImage));
		sceneManager.add(new GameScreen("Mercury", planetImage, bgGSMusic,keyboardDevice,mouseDevice, this));
//		sceneManager.add(new GameScreen(buttonSA,buttonSP,bgGSMusic,keyboardDevice,mouseDevice));
		sceneManager.add(new PauseScreen());
		sceneManager.add(new LevelClearedScreen(levelClearedButtons, bgLCImage, bgESMusic, mouseDevice, this));
		sceneManager.add(new StatsScreen(statsButtons, bgStatsImage, bgESMusic, mouseDevice, this));

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
//			gameState = END_SCREEN;
//			sceneManager.resetGameScene("Mercury", new Texture(IMAGE_PLANET_MERCURY), keyboardDevice, mouseDevice, this);
//			sceneManager.setLevelClearedInfo("Mercury");
			gameState = SPLASH_SCREEN;
			sceneManager.setScene(gameState);
		}
		
		sceneManager.renderScene();
	}
	
	// go to level selection screen
	public void chooseLevel() {
		gameState = LEVEL_SELECT_SCREEN;
		sceneManager.setScene(gameState);
	}
	
	// start the game
	public void setGameLevel(String planetName, String planetImagePath) {
		gameState = GAME_SCREEN;
		sceneManager.setGameLevel(planetName, new Texture(planetImagePath), keyboardDevice, mouseDevice, this);
		sceneManager.setScene(gameState);
	}
	
	// display planet info
	public void showLevelInfo(String planetName) {
		gameState = LEVEL_INFO_SCREEN;
		sceneManager.setLevelInfo(planetName, keyboardDevice, mouseDevice);
		sceneManager.setScene(gameState);
	}
	
	// go back to main menu
	public void returnToMainMenu() {
		gameState = SPLASH_SCREEN;
		sceneManager.setScene(gameState);
	}
	
	// end of level
	public void levelCleared(String planetName, Texture planetImage) {
		gameState = END_SCREEN;
		sceneManager.setLevelClearedInfo(planetName, planetImage);
		sceneManager.setScene(gameState);
	}
	
	// display planet info
	public void showStats() {
		gameState = STATS_SCREEN;
		sceneManager.setScene(gameState);
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







