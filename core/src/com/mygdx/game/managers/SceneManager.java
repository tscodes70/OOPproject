package com.mygdx.game.managers;
//import com.badlogic.gdx.Game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.game.globals.Globals;

import com.mygdx.game.models.Scene;
import com.mygdx.game.screens.GameScreen;

public class SceneManager {
	
	private List<Scene> scenes;
	private int currentSceneCode = -1;
	private Scene activeScene;
		
	public SceneManager() {
		this.scenes = new ArrayList<Scene>();
	}
	
	/**
	 * Adds a scene into SceneManager list
	 * @param scene
	 */
	public void addScene(Scene scene) {
		this.scenes.add(scene);
	}
	
	/**
	 * Sets a scene from list as the active scene
	 * @param sceneCode
	 */
	public void setScene(int sceneCode) {
		if(sceneCode != this.currentSceneCode) {
			this.currentSceneCode = sceneCode;
			
			if(this.activeScene != null) this.activeScene.hide(); // hide the current scene
			
			this.activeScene = this.scenes.get(this.currentSceneCode); // make the selected scene active
			this.activeScene.show(); // must always be called after changing the active scene
		}
	}
	
	/**
	 * Reinitializes the game scene
	 * @param manager
	 */
	public void resetGameScene(AssetManager manager) {
		Scene gameScene = scenes.get(Globals.GAME_SCREEN);
		int gameSceneIndex = scenes.indexOf(gameScene);
		scenes.set(gameSceneIndex, new GameScreen(manager, Globals.BGAUDIO_GS));
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
		for(Scene s: this.scenes) s.dispose();
		System.out.println("SceneManager Resources Disposed");
	}
	
	// Getter
	public int getCurrentScene() { return this.currentSceneCode; }
}
