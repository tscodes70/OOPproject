package com.mygdx.game.managers;
//import com.badlogic.gdx.Game;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.models.Scene;

public class SceneManager {
	
	private List<Scene> scenes;
	private int currentSceneCode = -1;
	private Scene activeScene;
		
	public SceneManager() {
		this.scenes = new ArrayList<Scene>();
	}
	
	public void addScene(Scene scene) {
		this.scenes.add(scene);
	}
	
	// set a scene as the active scene
	public void setScene(int sceneCode) {
		if(sceneCode != this.currentSceneCode) {
			this.currentSceneCode = sceneCode;
			
			if(this.activeScene != null) this.activeScene.hide(); // hide the current scene
			
			this.activeScene = this.scenes.get(this.currentSceneCode); // make the selected scene active
			this.activeScene.show(); // must always be called after changing the active scene
		}
	}
	
	//testing only!!
//	public void toggleScene() {
//		activeScene.dispose();
//
//		if(activeScene instanceof SplashScreen) {
//			currentSceneCode = com.mygdx.game.globals.Constants.GAME_SCREEN;
//			activeScene = scenes.get(currentSceneCode);
//		} else if(activeScene instanceof GameScreen) {
//			currentSceneCode = com.mygdx.game.globals.Constants.END_SCREEN;
//			activeScene = scenes.get(currentSceneCode);
//		} else if(activeScene instanceof EndScreen) {
//			currentSceneCode = com.mygdx.game.globals.Constants.SPLASH_SCREEN;
//			activeScene = scenes.get(currentSceneCode);
//		}
//		
//		setScreen(activeScene);
//	}
	
	public int getCurrentScene() {
		return this.currentSceneCode;
	}
	
	public void pauseCurrentScene() {
		this.activeScene.pause();
	}
	
	public void resumeCurrentScene() {
		this.activeScene.resume();
	}
	
	//@Override
//	public void render() {
//	}
	
	// make the active scene render itself
	// called by Simulation
	public void renderScene() {
		this.activeScene.render();
	}
	
	// dispose of scenes
	public void dispose() {
		for(Scene s: this.scenes) {
			s.dispose();
		}
	}
}
