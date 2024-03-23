//package com.mygdx.gamelayer.managers;
//
//import com.badlogic.gdx.graphics.Texture;
//import com.mygdx.gameengine.interfaces.iOutput;
//import com.mygdx.gameengine.managers.OutputManager;
//import com.mygdx.gameengine.managers.SceneManager;
//import com.mygdx.gameengine.models.Keyboard;
//import com.mygdx.gameengine.models.Mouse;
//import com.mygdx.gameengine.models.Scene;
//import com.mygdx.gameengine.models.Sound;
//import com.mygdx.gamelayer.models.Planet;
//import com.mygdx.gamelayer.models.SpaceTexture;
//import com.mygdx.gamelayer.screens.GameScreen;
//import com.mygdx.gamelayer.screens.LevelClearedScreen;
//import com.mygdx.gamelayer.screens.PlanetInfoScreen;
//import com.mygdx.gamelayer.simulation.AppSimulation;
//
//public class SpaceSceneManager extends SceneManager{
//
//	private final int LEVEL_INFO_SCREEN = 2;
//
//	private final int GAME_SCREEN = 3;
//	
//	private final int END_SCREEN = 5;
//	
//	private final String AUDIO_PATH = "audio/music";
//	private final String BGAUDIO_GS_PATH = String.format("%s/burnt_toaster.mp3", AUDIO_PATH);
//	
//	private Sound bgGSMusic = new Sound(BGAUDIO_GS_PATH);
//	
//	public SpaceSceneManager(OutputManager<iOutput> oManager) {
//		super(oManager);
//		// TODO Auto-generated constructor stub
//	}
//	
//	@Override
//	public void resetGameScene(Planet planet, SpaceTexture playerModel, Keyboard keyboardDevice, Mouse mouseDevice, AppSimulation simulation) {
//		Scene gameScene = super.getSceneList().get(GAME_SCREEN);
//		int gameSceneIndex = super.getSceneList().indexOf(gameScene);
////		sceneList.set(gameSceneIndex, new GameScreen(buttonSA,buttonSP,bgGSMusic,keyboardDevice,mouseDevice));
//		super.getSceneList().set(gameSceneIndex, new GameScreen(planet, playerModel, bgGSMusic,keyboardDevice,mouseDevice, simulation));
//	}
//	
//	// enter the selected level for the game
//	@Override
//	public void setGameLevel(Planet planet, SpaceTexture playerModel, Keyboard keyboardDevice, Mouse mouseDevice, AppSimulation simulation) {
//		Scene gameScene = super.getSceneList().get(GAME_SCREEN);
//		int gameSceneIndex = super.getSceneList().indexOf(gameScene);
//		super.getSceneList().set(gameSceneIndex, new GameScreen(planet, playerModel, bgGSMusic, keyboardDevice,mouseDevice, simulation));
//	}
//	
//	// go to the info screen for the selected level
//	@Override
//	public void setLevelInfo(Planet planet,  Keyboard keyboardDevice, Mouse mouseDevice) {
//		PlanetInfoScreen levelInfoScene = (PlanetInfoScreen)super.getSceneList().get(LEVEL_INFO_SCREEN);
//		levelInfoScene.setChosenLevel(planet);
//		setScene(LEVEL_INFO_SCREEN);
//	}
//	
//	// set which planet to display in the level cleared screen
//	@Override
//	public void setLevelClearedInfo(String planetName, Texture planetImage) {
//		LevelClearedScreen levelClearedScene = (LevelClearedScreen)super.getSceneList().get(END_SCREEN);
//		levelClearedScene.setLevel(planetName, planetImage);
//	}
//
//}
