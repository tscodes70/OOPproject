package com.mygdx.gamelayer.simulation;

import java.util.HashMap;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.gameengine.interfaces.iInput;
import com.mygdx.gameengine.interfaces.iOutput;
import com.mygdx.gameengine.managers.IOManager;
import com.mygdx.gameengine.managers.InputManager;
import com.mygdx.gameengine.managers.OutputManager;
import com.mygdx.gameengine.models.Keyboard;
import com.mygdx.gameengine.models.Mouse;
import com.mygdx.gameengine.models.Simulation;
import com.mygdx.gameengine.models.Sound;
import com.mygdx.gamelayer.factories.SpaceEntityFactory;
import com.mygdx.gamelayer.managers.SpaceSceneManager;
import com.mygdx.gamelayer.models.JSONReader;
import com.mygdx.gamelayer.models.Planet;
import com.mygdx.gamelayer.models.SpaceTexture;
import com.mygdx.gamelayer.screens.*;
public class AppSimulation extends Simulation {
	
	private int gameState;
	private SpaceSceneManager sceneManager;
	private Keyboard keyboardDevice;
	private Mouse mouseDevice;
	private InputManager<iInput> iManager;
	private OutputManager<iOutput> oManager;
	private IOManager ioManager;
	
	private JSONReader statsFile;
	
	private Sound bgSSMusic, bgGSMusic, bgESMusic;
	
	private SpaceTexture playerModel, projectileModel, explosionModel, startButtonModel, statsButtonModel, quitButtonModel;
	private SpaceTexture mercuryButtonModel, venusButtonModel, earthButtonModel, marsButtonModel, jupiterButtonModel, saturnButtonModel, uranusButtonModel, neptuneButtonModel, backButtonModel, continueButtonModel;
	private SpaceTexture bgSSImage, bgLSSImage, bgLCImage, bgLFImage, bgStatsImage, bgGSImage;
	private SpaceTexture mercuryPlanetModel, venusPlanetModel, earthPlanetModel, marsPlanetModel, jupiterPlanetModel, saturnPlanetModel, uranusPlanetModel, neptunePlanetModel;
	private SpaceTexture mercuryDebrisModel, venusDebrisModel, earthDebrisModel, marsDebrisModel, jupiterDebrisModel, saturnDebrisModel, uranusDebrisModel, neptuneDebrisModel;
	private SpaceTexture mercuryDebris2Model, venusDebris2Model, earthDebris2Model, marsDebris2Model, jupiterDebris2Model, saturnDebris2Model, uranusDebris2Model, neptuneDebris2Model;

	
	private SpaceEntityFactory spaceEntityFactory;

	
	private final int ENTERKEY = Keys.ENTER;
	private final int BACKSPACEKEY = Keys.BACKSPACE;
	private final int ESCAPEKEY = Keys.ESCAPE;
	
	private final String IMAGE_PATH = "image";
	private final String STATS_PATH = "stats";
	private final String AUDIO_PATH = "audio/music";
	
	// Stats Resource
	private final String STATS_FILE_PATH = String.format("%s/stats.json", STATS_PATH);
	
	// Player resources
	private final String IMAGE_PLAYER_PATH = String.format("%s/player.png", IMAGE_PATH);
	
	// Debris resources
	private final String IMAGE_MERCURY_DEBRIS_PATH = String.format("%s/mercury_debris.png", IMAGE_PATH);
	private final String IMAGE_VENUS_DEBRIS_PATH = String.format("%s/venus_debris.png", IMAGE_PATH);
	private final String IMAGE_EARTH_DEBRIS_PATH = String.format("%s/earth_debris.png", IMAGE_PATH);
	private final String IMAGE_MARS_DEBRIS_PATH = String.format("%s/mars_debris.png", IMAGE_PATH);
	private final String IMAGE_JUPITER_DEBRIS_PATH = String.format("%s/jupiter_debris.png", IMAGE_PATH);
	private final String IMAGE_SATURN_DEBRIS_PATH = String.format("%s/mercury_debris.png", IMAGE_PATH);
	private final String IMAGE_URANUS_DEBRIS_PATH = String.format("%s/uranus_debris.png", IMAGE_PATH);
	private final String IMAGE_NEPTUNE_DEBRIS_PATH = String.format("%s/neptune_debris.png", IMAGE_PATH);
	
	private final String IMAGE_MERCURY_DEBRIS_2_PATH = String.format("%s/mercury_debris_2.png", IMAGE_PATH);
	private final String IMAGE_VENUS_DEBRIS_2_PATH = String.format("%s/venus_debris_2.png", IMAGE_PATH);
	private final String IMAGE_EARTH_DEBRIS_2_PATH = String.format("%s/earth_debris_2.png", IMAGE_PATH);
	private final String IMAGE_MARS_DEBRIS_2_PATH = String.format("%s/mars_debris_2.png", IMAGE_PATH);
	private final String IMAGE_JUPITER_DEBRIS_2_PATH = String.format("%s/jupiter_debris_2.png", IMAGE_PATH);
	private final String IMAGE_SATURN_DEBRIS_2_PATH = String.format("%s/mercury_debris_2.png", IMAGE_PATH);
	private final String IMAGE_URANUS_DEBRIS_2_PATH = String.format("%s/uranus_debris_2.png", IMAGE_PATH);
	private final String IMAGE_NEPTUNE_DEBRIS_2_PATH = String.format("%s/neptune_debris_2.png", IMAGE_PATH);
	
	// Projectile resource
	private final String IMAGE_PROJECTILE_PATH = String.format("%s/projectile.png", IMAGE_PATH);
	
	// Explosion resource
	private final String IMAGE_EXPLOSION_PATH = String.format("%s/explosion.png", IMAGE_PATH);
	
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
	private final String IMAGE_JUPITER_PATH = String.format("%s/jupiter_button.png", IMAGE_PATH);
	private final String IMAGE_SATURN_PATH = String.format("%s/saturn_button.png", IMAGE_PATH);
	private final String IMAGE_URANUS_PATH = String.format("%s/uranus_button.png", IMAGE_PATH);
	private final String IMAGE_NEPTUNE_PATH = String.format("%s/neptune_button.png", IMAGE_PATH);

	private final String BGAUDIO_SS_PATH = String.format("%s/mii-channel.mp3", AUDIO_PATH);
	private final String BGAUDIO_GS_PATH = String.format("%s/burnt_toaster.mp3", AUDIO_PATH);
	private final String BGAUDIO_ES_PATH = String.format("%s/victory_fanfare.mp3", AUDIO_PATH);
	
	// background images
	private final String BGIMAGE_SS_PATH = String.format("%s/splash.jpg", IMAGE_PATH);
	private final String BGIMAGE_LSS_PATH = String.format("%s/choose_planet.png", IMAGE_PATH);
	private final String BGIMAGE_LC_PATH = String.format("%s/level_cleared.png", IMAGE_PATH);
	private final String BGIMAGE_LF_PATH = String.format("%s/level_failed.png", IMAGE_PATH);
	private final String BGIMAGE_STATS_PATH = String.format("%s/statsbg.png", IMAGE_PATH);
	private final String BGIMAGE_GAME_PATH = String.format("%s/gamebg.png", IMAGE_PATH);


	// default planet image for game screen
	private final String IMAGE_PLANET_MERCURY_PATH = String.format("%s/mercury_planet.png", IMAGE_PATH);
	private final String IMAGE_PLANET_VENUS_PATH = String.format("%s/venus_planet.png", IMAGE_PATH);
	private final String IMAGE_PLANET_EARTH_PATH = String.format("%s/earth_planet.png", IMAGE_PATH);
	private final String IMAGE_PLANET_MARS_PATH = String.format("%s/mars_planet.png", IMAGE_PATH);
	private final String IMAGE_PLANET_JUPITER_PATH = String.format("%s/jupiter_planet.png", IMAGE_PATH);
	private final String IMAGE_PLANET_SATURN_PATH = String.format("%s/saturn_planet.png", IMAGE_PATH);
	private final String IMAGE_PLANET_URANUS_PATH = String.format("%s/uranus_planet.png", IMAGE_PATH);
	private final String IMAGE_PLANET_NEPTUNE_PATH = String.format("%s/neptune_planet.png", IMAGE_PATH);


	// indexes of the screens
	private final int SPLASH_SCREEN = 0;
	private final int LEVEL_SELECT_SCREEN = 1;
	private final int LEVEL_INFO_SCREEN = 2;
	private final int GAME_SCREEN = 3;
	private final int PAUSE_SCREEN = 4;
	private final int END_SCREEN = 5;
	private final int END_SCREEN_FAILURE = 6;
	private final int STATS_SCREEN = 7;
	
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
			// Load JSON stats file
			statsFile = new JSONReader(STATS_FILE_PATH);
			
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
			bgLFImage = new SpaceTexture(BGIMAGE_LF_PATH);
			bgStatsImage = new SpaceTexture(BGIMAGE_STATS_PATH);
			bgGSImage = new SpaceTexture(BGIMAGE_GAME_PATH);
			
			startButtonModel = new SpaceTexture(IMAGE_STARTBUTTON_PATH);
			statsButtonModel = new SpaceTexture(IMAGE_STATSBUTTON_PATH);
			quitButtonModel = new SpaceTexture(IMAGE_QUITBUTTON_PATH);
			backButtonModel = new SpaceTexture(IMAGE_BACKBUTTON_PATH);
			continueButtonModel = new SpaceTexture(IMAGE_CONTINUEBUTTON_PATH);
			
			// Player Model
			playerModel = new SpaceTexture(IMAGE_PLAYER_PATH);
			
			// Projectile Model
			projectileModel = new SpaceTexture(IMAGE_PROJECTILE_PATH);
			
			explosionModel = new SpaceTexture(IMAGE_EXPLOSION_PATH);
			
			// Debris Model
			mercuryDebrisModel = new SpaceTexture(IMAGE_MERCURY_DEBRIS_PATH);
			venusDebrisModel = new SpaceTexture(IMAGE_VENUS_DEBRIS_PATH);
			earthDebrisModel = new SpaceTexture(IMAGE_EARTH_DEBRIS_PATH);
			marsDebrisModel = new SpaceTexture(IMAGE_MARS_DEBRIS_PATH);
			jupiterDebrisModel = new SpaceTexture(IMAGE_JUPITER_DEBRIS_PATH);
			saturnDebrisModel = new SpaceTexture(IMAGE_SATURN_DEBRIS_PATH);
			uranusDebrisModel = new SpaceTexture(IMAGE_URANUS_DEBRIS_PATH);
			neptuneDebrisModel = new SpaceTexture(IMAGE_NEPTUNE_DEBRIS_PATH);
			
			mercuryDebris2Model = new SpaceTexture(IMAGE_MERCURY_DEBRIS_2_PATH);
			venusDebris2Model = new SpaceTexture(IMAGE_VENUS_DEBRIS_2_PATH);
			earthDebris2Model = new SpaceTexture(IMAGE_EARTH_DEBRIS_2_PATH);
			marsDebris2Model = new SpaceTexture(IMAGE_MARS_DEBRIS_2_PATH);
			jupiterDebris2Model = new SpaceTexture(IMAGE_JUPITER_DEBRIS_2_PATH);
			saturnDebris2Model = new SpaceTexture(IMAGE_SATURN_DEBRIS_2_PATH);
			uranusDebris2Model = new SpaceTexture(IMAGE_URANUS_DEBRIS_2_PATH);
			neptuneDebris2Model = new SpaceTexture(IMAGE_NEPTUNE_DEBRIS_2_PATH);
			
			// planet button models
			mercuryButtonModel = new SpaceTexture(IMAGE_MERCURY_PATH);
			venusButtonModel = new SpaceTexture(IMAGE_VENUS_PATH);
			earthButtonModel = new SpaceTexture(IMAGE_EARTH_PATH);
			marsButtonModel = new SpaceTexture(IMAGE_MARS_PATH);
			jupiterButtonModel = new SpaceTexture(IMAGE_JUPITER_PATH);
			saturnButtonModel = new SpaceTexture(IMAGE_SATURN_PATH);
			uranusButtonModel = new SpaceTexture(IMAGE_URANUS_PATH);
			neptuneButtonModel = new SpaceTexture(IMAGE_NEPTUNE_PATH);
			
			// planet image models
			mercuryPlanetModel = new SpaceTexture(IMAGE_PLANET_MERCURY_PATH);
			venusPlanetModel = new SpaceTexture(IMAGE_PLANET_VENUS_PATH);
			earthPlanetModel = new SpaceTexture(IMAGE_PLANET_EARTH_PATH);
			marsPlanetModel = new SpaceTexture(IMAGE_PLANET_MARS_PATH);
			jupiterPlanetModel = new SpaceTexture(IMAGE_PLANET_JUPITER_PATH);
			saturnPlanetModel = new SpaceTexture(IMAGE_PLANET_SATURN_PATH);
			uranusPlanetModel = new SpaceTexture(IMAGE_PLANET_URANUS_PATH);
			neptunePlanetModel = new SpaceTexture(IMAGE_PLANET_NEPTUNE_PATH);

			oManager.add("SSBGMusic",bgSSMusic);
			oManager.add("GSBGMusic",bgGSMusic);
			oManager.add("ESBGMusic",bgESMusic);
			
			oManager.add("SSBGImage", bgSSImage);
			oManager.add("LSSBGImage", bgLSSImage);
			oManager.add("LCBGImage", bgLCImage);
			oManager.add("LFBGImage", bgLFImage);
			oManager.add("StatsBGImage", bgStatsImage);
			oManager.add("GSBGImage", bgGSImage);
			
			oManager.add("PlayerTexture", playerModel);
			
			oManager.add("ProjectileTexture", projectileModel);
			oManager.add("ExplosionTexture", explosionModel);
			
			oManager.add("MercuryDebrisTexture", mercuryDebrisModel);
			oManager.add("VenusDebrisTexture", venusDebrisModel);
			oManager.add("EarthDebrisTexture", earthDebrisModel);
			oManager.add("MarsDebrisTexture", marsDebrisModel);
			oManager.add("JupiterDebrisTexture", jupiterDebrisModel);
			oManager.add("SaturnDebrisTexture", saturnDebrisModel);
			oManager.add("UranusDebrisTexture", uranusDebrisModel);
			oManager.add("NeptuneDebrisTexture", neptuneDebrisModel);
			
			oManager.add("MercuryDebris2Texture", mercuryDebris2Model);
			oManager.add("VenusDebris2Texture", venusDebris2Model);
			oManager.add("EarthDebris2Texture", earthDebris2Model);
			oManager.add("MarsDebris2Texture", marsDebris2Model);
			oManager.add("JupiterDebris2Texture", jupiterDebris2Model);
			oManager.add("SaturnDebris2Texture", saturnDebris2Model);
			oManager.add("UranusDebris2Texture", uranusDebris2Model);
			oManager.add("NeptuneDebris2Texture", neptuneDebris2Model);
			
			oManager.add("StartButtonTexture", startButtonModel);
			oManager.add("StatsButtonTexture", statsButtonModel);
			oManager.add("QuitButtonTexture", quitButtonModel);
			oManager.add("BackButtonTexture", backButtonModel);
			oManager.add("ContinueButtonTexture", continueButtonModel);
			
			oManager.add("MercuryButtonTexture", mercuryButtonModel);
			oManager.add("VenusButtonTexture", venusButtonModel);
			oManager.add("EarthButtonTexture", earthButtonModel);
			oManager.add("MarsButtonTexture", marsButtonModel);
			oManager.add("JupiterButtonTexture", jupiterButtonModel);
			oManager.add("SaturnButtonTexture", saturnButtonModel);
			oManager.add("UranusButtonTexture", uranusButtonModel);
			oManager.add("NeptuneButtonTexture", neptuneButtonModel);

			oManager.add("MercuryPlanetTexture", mercuryPlanetModel);
			oManager.add("VenusPlanetTexture", venusPlanetModel);
			oManager.add("EarthPlanetTexture", earthPlanetModel);
			oManager.add("MarsPlanetTexture", marsPlanetModel);
			oManager.add("JupiterPlanetTexture", jupiterPlanetModel);
			oManager.add("SaturnPlanetTexture", saturnPlanetModel);
			oManager.add("UranusPlanetTexture", uranusPlanetModel);
			oManager.add("NeptunePlanetTexture", neptunePlanetModel);

			ioManager = new IOManager(iManager,oManager);
			
			
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
			levelButtons.put("Jupiter", (Texture)ioManager.getOutputManager().retrieve("JupiterButtonTexture"));
			levelButtons.put("Saturn", (Texture)ioManager.getOutputManager().retrieve("SaturnButtonTexture"));
			levelButtons.put("Uranus", (Texture)ioManager.getOutputManager().retrieve("UranusButtonTexture"));
			levelButtons.put("Neptune", (Texture)ioManager.getOutputManager().retrieve("NeptuneButtonTexture"));
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
			ex.printStackTrace();
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
		sceneManager = new SpaceSceneManager(oManager);
		
		// Instantiate SpaceEntityFactory
		spaceEntityFactory = new SpaceEntityFactory(ioManager);
		
		// Planet Hashmap
		planetHashmap = new HashMap<String, Planet>();
		planetHashmap.put("Mercury", (Planet) spaceEntityFactory.createDynamicEntity("Planet", "Mercury", 3.7f));
		planetHashmap.put("Venus", (Planet) spaceEntityFactory.createDynamicEntity("Planet", "Venus", 8.87f));
		planetHashmap.put("Earth", (Planet) spaceEntityFactory.createDynamicEntity("Planet", "Earth", 9.81f));
		planetHashmap.put("Mars", (Planet) spaceEntityFactory.createDynamicEntity("Planet", "Mars", 3.71f));
		planetHashmap.put("Jupiter", (Planet) spaceEntityFactory.createDynamicEntity("Planet", "Jupiter", 24.79f));
		planetHashmap.put("Saturn", (Planet) spaceEntityFactory.createDynamicEntity("Planet", "Saturn", 10.44f));
		planetHashmap.put("Uranus", (Planet) spaceEntityFactory.createDynamicEntity("Planet", "Uranus", 8.69f));
		planetHashmap.put("Neptune", (Planet) spaceEntityFactory.createDynamicEntity("Planet", "Neptune", 11.15f));



		// a reference to appsimulation is passed to scenes so that they can request changes of game state
		sceneManager.add(new MainMenuScreen(menuButtons, ioManager, this));
		sceneManager.add(new LevelSelectScreen(levelButtons, planetHashmap, ioManager, this));
		sceneManager.add(new PlanetInfoScreen(planetInfoButtons, ioManager, this));
		sceneManager.add(new GameScreen((Planet)planetHashmap.get("Mercury"), ioManager, this));
		sceneManager.add(new PauseScreen());
		sceneManager.add(new LevelClearedScreen(levelClearedButtons, ioManager, this));
		sceneManager.add(new LevelFailedScreen(levelClearedButtons, ioManager, this));
		sceneManager.add(new StatsScreen(statsButtons, statsFile, ioManager, this));

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
		sceneManager.setGameLevel(planet, ioManager, this);
		sceneManager.setScene(gameState);
	}
	
	// display planet info
	public void showLevelInfo(Planet planet) {
		gameState = LEVEL_INFO_SCREEN;
		sceneManager.setLevelInfo(planet);
		sceneManager.setScene(gameState);
	}
	
	// go back to main menu
	public void returnToMainMenu() {
		gameState = SPLASH_SCREEN;
		sceneManager.setScene(gameState);
	}
	
	// end of level
	public void levelCleared(String planetName, Texture planetImage, int playerPoints) {
		gameState = END_SCREEN;
		sceneManager.setLevelClearedInfo(planetName, planetImage, playerPoints);
		sceneManager.setScene(gameState);
	}
	
	public void levelFailed(String planetName, Texture planetImage, int playerPoints) {
		gameState = END_SCREEN_FAILURE;
		sceneManager.setLevelFailedInfo(planetName, planetImage, playerPoints);
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







