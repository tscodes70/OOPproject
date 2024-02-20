package com.mygdx.game.managers;
//import com.badlogic.gdx.Game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.game.interfaces.iManager;
import com.mygdx.game.models.Entity;
import com.mygdx.game.models.KeyboardInput;
import com.mygdx.game.models.MouseInput;
import com.mygdx.game.models.Scene;
import com.mygdx.game.screens.GameScreen;

public class SceneManager implements iManager<Scene>{
	
	private List<Scene> sceneList;
	private List<Scene> updatedSceneList;
	private int currentSceneCode = -1;
	private Scene activeScene;
	
	private final int GAME_SCREEN = 1;
	private final String AUDIO_PATH = "audio/music";
	private final String BGAUDIO_GS = String.format("%s/megalovania.mp3", AUDIO_PATH);
		
	public SceneManager() {
		this.sceneList = new ArrayList<Scene>();
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
	public void resetGameScene(AssetManager manager, KeyboardInput keyboardDevice, MouseInput mouseDevice) {
		Scene gameScene = sceneList.get(GAME_SCREEN);
		int gameSceneIndex = sceneList.indexOf(gameScene);
		sceneList.set(gameSceneIndex, new GameScreen(manager,BGAUDIO_GS,keyboardDevice,mouseDevice));
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
}
