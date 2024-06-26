package com.mygdx.gameengine.managers;
//import com.badlogic.gdx.Game;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.gameengine.interfaces.iOutput;
import com.mygdx.gameengine.models.Scene;
import com.mygdx.gameengine.models.Sound;


public class SceneManager{
	
	private List<Scene> sceneList;
	private List<Scene> updatedSceneList;
	private int currentSceneCode = -1;
	private Scene activeScene;
	
	private OutputManager<iOutput> oManager;

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
