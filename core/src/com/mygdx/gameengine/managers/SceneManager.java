package com.mygdx.gameengine.managers;
//import com.badlogic.gdx.Game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.gamelayer.simulation.AppSimulation;
import com.mygdx.gameengine.interfaces.iOutput;
import com.mygdx.gameengine.models.Entity;
import com.mygdx.gameengine.models.Keyboard;
import com.mygdx.gameengine.models.Mouse;
import com.mygdx.gameengine.models.Scene;
import com.mygdx.gameengine.models.Sound;
import com.mygdx.gamelayer.models.Planet;
import com.mygdx.gamelayer.models.SpaceTexture;
import com.mygdx.gamelayer.screens.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class SceneManager{
	
	private List<Scene> sceneList;
	private List<Scene> updatedSceneList;
	private int currentSceneCode = -1;
	private Scene activeScene;
	
	private OutputManager<iOutput> oManager;
	
	private final int LEVEL_INFO_SCREEN = 2;

	private final int GAME_SCREEN = 3;
	
	private final int END_SCREEN = 5;

	private final String AUDIO_PATH = "audio/music";
	private final String BGAUDIO_GS_PATH = String.format("%s/burnt_toaster.mp3", AUDIO_PATH);
	
	// Lazy way (need change to use omanager)
	private Sound bgGSMusic = new Sound(BGAUDIO_GS_PATH);

	public SceneManager(OutputManager<iOutput> oManager) {
		this.sceneList = new ArrayList<Scene>();
		this.oManager = oManager;
	}
	
	/**
	 * Adds a scene into SceneManager list
	 * @param scene
	 */
	public void add(Scene scene) {
		this.sceneList.add(scene);
	}
	
	/**
	 * Remove a scene from SceneManager list
	 * @param scene
	 */
	public void remove(Scene scene) {
		this.sceneList.remove(scene);
		scene.dispose();
	}
	
	/**
	 * Retrieves updated entityList when entityList is modified
	 */
	public void update(List<Scene> sceneList) {
		updatedSceneList = new ArrayList<Scene>();
        for (Scene scene : sceneList) {
            	updatedSceneList.add(scene);
        }
        this.sceneList = updatedSceneList;
    }
	
	/**
	 * Sets a scene from list as the active scene
	 * @param sceneCode
	 */
	public void setScene(int sceneCode) {
		if(sceneCode != this.currentSceneCode) {
			this.currentSceneCode = sceneCode;
			
			if(this.activeScene != null) this.activeScene.hide(); // hide the current scene
			
			this.activeScene = this.sceneList.get(this.currentSceneCode); // make the selected scene active
			this.activeScene.show(); // must always be called after changing the active scene
		}
	}
	
	/**
	 * Reinitializes the game scene
	 * @param manager
	 */
	public void resetGameScene(Planet planet, IOManager iomanager, AppSimulation simulation) {
		Scene gameScene = sceneList.get(GAME_SCREEN);
		int gameSceneIndex = sceneList.indexOf(gameScene);
//		sceneList.set(gameSceneIndex, new GameScreen(buttonSA,buttonSP,bgGSMusic,keyboardDevice,mouseDevice));
		sceneList.set(gameSceneIndex, new GameScreen(planet, iomanager, simulation));
	}
	
	// enter the selected level for the game
	public void setGameLevel(Planet planet, IOManager iomanager, AppSimulation simulation) {
		Scene gameScene = sceneList.get(GAME_SCREEN);
		int gameSceneIndex = sceneList.indexOf(gameScene);
		sceneList.set(gameSceneIndex, new GameScreen(planet, iomanager, simulation));
	}
	
	// go to the info screen for the selected level
	public void setLevelInfo(Planet planet,  Keyboard keyboardDevice, Mouse mouseDevice) {
		PlanetInfoScreen levelInfoScene = (PlanetInfoScreen)sceneList.get(LEVEL_INFO_SCREEN);
		levelInfoScene.setChosenLevel(planet);
		setScene(LEVEL_INFO_SCREEN);
	}
	
	// set which planet to display in the level cleared screen
	public void setLevelClearedInfo(String planetName, Texture planetImage) {
		LevelClearedScreen levelClearedScene = (LevelClearedScreen)sceneList.get(END_SCREEN);
		levelClearedScene.setLevel(planetName, planetImage);
	}
	
	/**
	 * Renders active Scene
	 */
	public void renderScene() { this.activeScene.render(); }
	
	/**
	 * Updates active Scene
	 */
	public void updateScene() { this.activeScene.update(); }
	
	/**
	 * Pauses active Scene
	 */
	public void pauseCurrentScene() { this.activeScene.pause(); }
	
	/**
	 * Resumes active Scene
	 */
	public void resumeCurrentScene() { this.activeScene.resume(); }

	/**
	 * Disposal of SceneManager Resources
	 */
	public void dispose() {
		for(Scene s: sceneList) s.dispose();
		System.out.println("SceneManager Resources Disposed");
	}
	
	// Getter
	public int getCurrentScene() { return this.currentSceneCode; }

	public List<Scene> getSceneList() {
		return sceneList;
	}

	public void setSceneList(List<Scene> sceneList) {
		this.sceneList = sceneList;
	}

	public int getCurrentSceneCode() {
		return currentSceneCode;
	}

	public void setCurrentSceneCode(int currentSceneCode) {
		this.currentSceneCode = currentSceneCode;
	}

	public OutputManager<iOutput> getoManager() {
		return oManager;
	}

	public void setoManager(OutputManager<iOutput> oManager) {
		this.oManager = oManager;
	}
	
	
}
