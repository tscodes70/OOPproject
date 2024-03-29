package com.mygdx.gamelayer.managers;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.gameengine.interfaces.iOutput;
import com.mygdx.gameengine.managers.IOManager;
import com.mygdx.gameengine.managers.OutputManager;
import com.mygdx.gameengine.managers.SceneManager;
import com.mygdx.gameengine.models.Scene;
import com.mygdx.gameengine.models.Sound;
import com.mygdx.gamelayer.models.Planet;
import com.mygdx.gamelayer.screens.GameScreen;
import com.mygdx.gamelayer.screens.LevelClearedScreen;
import com.mygdx.gamelayer.screens.LevelFailedScreen;
import com.mygdx.gamelayer.screens.PlanetInfoScreen;
import com.mygdx.gamelayer.simulation.AppSimulation;

public class SpaceSceneManager extends SceneManager{

	private final int LEVEL_INFO_SCREEN = 2;

	private final int GAME_SCREEN = 3;
	
	private final int END_SCREEN = 5;
	private final int END_SCREEN_FAILURE = 6;
	
	public SpaceSceneManager(OutputManager<iOutput> oManager) {
		super(oManager);
		// TODO Auto-generated constructor stub
	}
	
	// enter the selected level for the game
	public void setGameLevel(Planet planet, IOManager ioManager, AppSimulation simulation) {
		Scene gameScene = super.getSceneList().get(GAME_SCREEN);
		int gameSceneIndex = super.getSceneList().indexOf(gameScene);
		super.getSceneList().set(gameSceneIndex, new GameScreen(planet, ioManager, simulation));
		gameScene.dispose();
	}
	
	// go to the info screen for the selected level
	public void setLevelInfo(Planet planet) {
		PlanetInfoScreen levelInfoScene = (PlanetInfoScreen)super.getSceneList().get(LEVEL_INFO_SCREEN);
		levelInfoScene.setChosenLevel(planet);
		super.setScene(LEVEL_INFO_SCREEN);
	}
	
	// set which planet to display in the level cleared screen
	public void setLevelClearedInfo(String planetName, Texture planetImage, int playerPoints) {
		LevelClearedScreen levelClearedScene = (LevelClearedScreen)super.getSceneList().get(END_SCREEN);
		levelClearedScene.setLevel(planetName, planetImage, playerPoints);
	}
	
	public void setLevelFailedInfo(String planetName, Texture planetImage, int playerPoints) {
		LevelFailedScreen levelFailedScene = (LevelFailedScreen)super.getSceneList().get(END_SCREEN_FAILURE);
		levelFailedScene.setLevel(planetName, planetImage, playerPoints);
	}
}
