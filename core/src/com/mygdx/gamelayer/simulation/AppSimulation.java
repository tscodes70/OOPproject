package com.mygdx.gamelayer.simulation;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
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
import com.mygdx.gamelayer.models.Planet;
import com.mygdx.gamelayer.models.SpaceTexture;
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
	
	private SpaceTexture playerModel, debrisModel, startButtonModel, statsButtonModel, quitButtonModel;
	private SpaceTexture mercuryButtonModel, venusButtonModel, earthButtonModel, marsButtonModel, backButtonModel, continueButtonModel;
	private SpaceTexture bgSSImage, bgLSSImage, bgLCImage, bgStatsImage, bgGSImage;
	private SpaceTexture mercuryPlanetModel, venusPlanetModel, earthPlanetModel, marsPlanetModel;
	
	private Planet mercuryPlanet,venusPlanet,earthPlanet, marsPlanet;
	
	private float planetPositionX,planetPositionY,planetWidth,planetHeight;
	private boolean planetCollidable;

	private final boolean COLLIDABLE = true;
	
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
	
	// Player & Debris resources
	private final String IMAGE_PLAYER_PATH = String.format("%s/player.png", IMAGE_PATH);
//	private final String IMAGE_DEBRIS_PATH = String.format("%s/spawnplayer.png", IMAGE_PATH);
	
	// generic menu buttons
	private final String IMAGE_STARTBUTTON_PATH = String.format("%s/start_button.png", IMAGE_PATH);
	private final String IMAGE_STATSBUTTON_PATH = String.format("%s/stats_button.png", IMAGE_PATH);
	private final String IMAGE_QUITBUTTON_PATH = String.format("%s/quit_button.png", IMAGE_PATH);
	private final String IMAGE_BACKBUTTON_PATH = String.format("%s/back_button.png", IMAGE_PATH);
	private final String IMAGE_CONTINUEBUTTON_PATH = String.format("%s/continue_button.png", IMAGE_PATH);

	// planet select button image paths
	private final String IMAGE_MERCURY_PATH = String.format("%s/mercury_button.png", IMAGE_PATH);
	private final String IMAGE_VENUS_PATH = String.format("%s/venus_button.png", IMAGE_PATH);
	private final String IMAGE_EARTH_PATH = String.format("%s/earth_button.png", IMAGE_PATH);
	private final String IMAGE_MARS_PATH = String.format("%s/mars_button.png", IMAGE_PATH);
	private final String BGAUDIO_SS_PATH = String.format("%s/mii-channel.mp3", AUDIO_PATH);
	private final String BGAUDIO_GS_PATH = String.format("%s/burnt_toaster.mp3", AUDIO_PATH);
	private final String BGAUDIO_ES_PATH = String.format("%s/victory_fanfare.mp3", AUDIO_PATH);
	
	// background images
	private final String BGIMAGE_SS_PATH = String.format("%s/splash.jpg", IMAGE_PATH);
	private final String BGIMAGE_LSS_PATH = String.format("%s/choose_planet.png", IMAGE_PATH);
	private final String BGIMAGE_LC_PATH = String.format("%s/level_cleared.png", IMAGE_PATH);
	private final String BGIMAGE_STATS_PATH = String.format("%s/statsbg.png", IMAGE_PATH);
	private final String BGIMAGE_GAME_PATH = String.format("%s/gamebg.png", IMAGE_PATH);


	// default planet image for game screen
	private final String IMAGE_PLANET_MERCURY_PATH = String.format("%s/mercury_planet.png", IMAGE_PATH);
	private final String IMAGE_PLANET_VENUS_PATH = String.format("%s/venus_planet.png", IMAGE_PATH);
	private final String IMAGE_PLANET_EARTH_PATH = String.format("%s/earth_planet.png", IMAGE_PATH);
	private final String IMAGE_PLANET_MARS_PATH = String.format("%s/mars_planet.png", IMAGE_PATH);


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
	
	// Planet Hashmap
	private HashMap<String, Planet> planetHashmap;

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
			
			// background image textures (NOT ADDED INTO OMANAGER YET)
			bgSSImage = new SpaceTexture(BGIMAGE_SS_PATH);
			bgLSSImage = new SpaceTexture(BGIMAGE_LSS_PATH);
			bgLCImage = new SpaceTexture(BGIMAGE_LC_PATH);
			bgStatsImage = new SpaceTexture(BGIMAGE_STATS_PATH);
			bgGSImage = new SpaceTexture(BGIMAGE_GAME_PATH);
			
			startButtonModel = new SpaceTexture(IMAGE_STARTBUTTON_PATH);
			statsButtonModel = new SpaceTexture(IMAGE_STATSBUTTON_PATH);
			quitButtonModel = new SpaceTexture(IMAGE_QUITBUTTON_PATH);
			backButtonModel = new SpaceTexture(IMAGE_BACKBUTTON_PATH);
			continueButtonModel = new SpaceTexture(IMAGE_CONTINUEBUTTON_PATH);
			
			playerModel = new SpaceTexture(IMAGE_PLAYER_PATH);
//			debrisModel = new SpaceTexture(IMAGE_DEBRIS_PATH);
			
			mercuryButtonModel = new SpaceTexture(IMAGE_MERCURY_PATH);
			venusButtonModel = new SpaceTexture(IMAGE_VENUS_PATH);
			earthButtonModel = new SpaceTexture(IMAGE_EARTH_PATH);
			marsButtonModel = new SpaceTexture(IMAGE_MARS_PATH);
			
			mercuryPlanetModel = new SpaceTexture(IMAGE_PLANET_MERCURY_PATH);
			venusPlanetModel = new SpaceTexture(IMAGE_PLANET_VENUS_PATH);
			earthPlanetModel = new SpaceTexture(IMAGE_PLANET_EARTH_PATH);
			marsPlanetModel = new SpaceTexture(IMAGE_PLANET_MARS_PATH);
			
			oManager.add("SSBGMusic",bgSSMusic);
			oManager.add("GSBGMusic",bgGSMusic);
			oManager.add("ESBGMusic",bgESMusic);
			
			oManager.add("PlayerTexture", playerModel);
			oManager.add("DebrisTexture", debrisModel);
			
			oManager.add("StartButtonTexture", startButtonModel);
			oManager.add("StatsButtonTexture", statsButtonModel);
			oManager.add("QuitButtonTexture", quitButtonModel);
			oManager.add("BackButtonTexture", backButtonModel);
			oManager.add("ContinueButtonTexture", continueButtonModel);
			
			oManager.add("MercuryButtonTexture", mercuryButtonModel);
			oManager.add("VenusButtonTexture", venusButtonModel);
			oManager.add("EarthButtonTexture", earthButtonModel);
			oManager.add("MarsButtonTexture", marsButtonModel);
			
			oManager.add("MercuryPlanetTexture", mercuryPlanetModel);
			oManager.add("VenusPlanetTexture", venusPlanetModel);
			oManager.add("EarthPlanetTexture", earthPlanetModel);
			oManager.add("MarsPlanetTexture", marsPlanetModel);
			
			ioManager = new IOManager(iManager,oManager);
			
			// Planet Dimenensions
			planetPositionX = 0;
			planetPositionY = -(Gdx.graphics.getWidth() / 2f);
			planetWidth = Gdx.graphics.getWidth();
			planetHeight = 0.75f * ((float)Gdx.graphics.getWidth() /  (float)Gdx.graphics.getHeight()) * (float)Gdx.graphics.getHeight();
			planetCollidable = COLLIDABLE;
			
			
			// hashmaps of button textures to pass to their respective scenes
			// main menu buttons
			menuButtons = new HashMap<String, Texture>();
			menuButtons.put("Start", (Texture)ioManager.getOutputManager().retrieve("StartButtonTexture"));
			menuButtons.put("Stats", (Texture)ioManager.getOutputManager().retrieve("StatsButtonTexture"));
			menuButtons.put("Quit", (Texture)ioManager.getOutputManager().retrieve("QuitButtonTexture"));

			// planet selection buttons
			levelButtons = new HashMap<String, Texture>();
			levelButtons.put("Mercury", (Texture)ioManager.getOutputManager().retrieve("MercuryButtonTexture"));
			levelButtons.put("Venus", (Texture)ioManager.getOutputManager().retrieve("VenusButtonTexture"));
			levelButtons.put("Earth", (Texture)ioManager.getOutputManager().retrieve("EarthButtonTexture"));
			levelButtons.put("Mars", (Texture)ioManager.getOutputManager().retrieve("MarsButtonTexture"));
			levelButtons.put("Back", (Texture)ioManager.getOutputManager().retrieve("QuitButtonTexture"));

			// planet info screen buttons
			planetInfoButtons = new HashMap<String, Texture>();
			planetInfoButtons.put("Start", (Texture)ioManager.getOutputManager().retrieve("StartButtonTexture"));
			planetInfoButtons.put("Back", (Texture)ioManager.getOutputManager().retrieve("BackButtonTexture"));

			// level cleared screen buttons
			levelClearedButtons = new HashMap<String, Texture>();
			levelClearedButtons.put("Continue", (Texture)ioManager.getOutputManager().retrieve("ContinueButtonTexture"));
			
			// stats screen buttons
			statsButtons = new HashMap<String,Texture>();
			statsButtons.put("Back", (Texture)ioManager.getOutputManager().retrieve("BackButtonTexture"));

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
		
		// Creating Planets
		mercuryPlanet = new Planet(
				"Mercury",
				(SpaceTexture)ioManager.getOutputManager().retrieve("MercuryPlanetTexture"), 
				planetPositionX,
				planetPositionY,
				1.5f, //gravity
				planetWidth, 
				planetHeight, 
				planetCollidable);
		venusPlanet = new Planet(
				"Venus",
				(SpaceTexture)ioManager.getOutputManager().retrieve("VenusPlanetTexture"), 
				planetPositionX,
				planetPositionY,
				1.5f,
				planetWidth, 
				planetHeight, 
				planetCollidable);
		earthPlanet = new Planet(
				"Earth",
				(SpaceTexture)ioManager.getOutputManager().retrieve("EarthPlanetTexture"), 
				planetPositionX,
				planetPositionY,
				1.5f,
				planetWidth, 
				planetHeight, 
				planetCollidable);
		marsPlanet = new Planet(
				"Mars",
				(SpaceTexture)ioManager.getOutputManager().retrieve("MarsPlanetTexture"), 
				planetPositionX,
				planetPositionY,
				1.5f,
				planetWidth, 
				planetHeight, 
				planetCollidable);
		
		// Planet Hashmap
		planetHashmap = new HashMap<String, Planet>();
		planetHashmap.put("Mercury",mercuryPlanet);
		planetHashmap.put("Venus",venusPlanet);
		planetHashmap.put("Earth",earthPlanet);
		planetHashmap.put("Mars",marsPlanet);

		// a reference to appsimulation is passed to scenes so that they can request changes of game state
		sceneManager.add(new MainMenuScreen(menuButtons, bgSSImage, bgSSMusic ,mouseDevice, this));
		sceneManager.add(new LevelSelectScreen(levelButtons, planetHashmap, bgLSSImage, bgSSMusic ,mouseDevice, this));
		sceneManager.add(new PlanetInfoScreen(planetInfoButtons, bgLSSImage, bgSSMusic ,mouseDevice, this));
		//sceneManager.add(new SplashScreen(bgSSMusic,bgSSImage));
		sceneManager.add(new GameScreen(mercuryPlanet, playerModel, bgGSImage, bgGSMusic,keyboardDevice,mouseDevice, this));
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
	public void setGameLevel(Planet planet) {
		gameState = GAME_SCREEN;
		sceneManager.setGameLevel(planet, playerModel, bgGSImage, keyboardDevice, mouseDevice, this);
		sceneManager.setScene(gameState);
	}
	
	// display planet info
	public void showLevelInfo(Planet planet) {
		gameState = LEVEL_INFO_SCREEN;
		sceneManager.setLevelInfo(planet, keyboardDevice, mouseDevice);
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







