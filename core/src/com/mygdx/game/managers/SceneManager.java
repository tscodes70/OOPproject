package com.mygdx.game.managers;
//import com.badlogic.gdx.Game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.*;

public class SceneManager {
	private List<Scene> scenes;
	private int currentSceneCode = -1;
	private Scene activeScene;
	private SplashScreen splashScene;
	private GameScreen gameScene;
	private EndScreen endScene;
	private AssetManager manager;
		
	public SceneManager() {
		scenes = new ArrayList<Scene>();
		
		//Audio testing
		manager = new AssetManager();
		manager.load("audio/music/mii-channel.mp3", Music.class); // splash screen music
		manager.load("audio/music/megalovania.mp3", Music.class); // game scene music
		manager.load("audio/music/bbq.mp3", Music.class); // game scene music
		manager.finishLoading();
		
		// instantiate scene instances
		splashScene = new SplashScreen(manager, "image/splash.jpg", "audio/music/mii-channel.mp3");
		gameScene = new GameScreen(manager, "image/badlogic.jpg", "audio/music/megalovania.mp3");
		endScene = new EndScreen(manager, "audio/music/bbq.mp3");
		
		// add scene instances to list of scene
		scenes.add(splashScene);
		scenes.add(gameScene);
		scenes.add(endScene);
	}
	
	// set a scene as the active scene
	public void setScene(int sceneCode) {
		if(sceneCode != currentSceneCode) {
			currentSceneCode = sceneCode;
			
			if(activeScene != null) activeScene.hide(); // hide the current scene
			
			activeScene = scenes.get(currentSceneCode); // make the selected scene active
			activeScene.show(); // must always be called after changing the active scene
			
			//setScreen(scenes.get(sceneCode));
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
		return currentSceneCode;
	}
	
	public void pauseCurrentScene() {
		activeScene.pause();
	}
	
	public void resumeCurrentScene() {
		activeScene.resume();
	}
	
	//@Override
//	public void render() {
//	}
	
	// make the active scene render itself
	// called by Simulation
	public void renderScene() {
		activeScene.render(Gdx.graphics.getDeltaTime());
	}
	
	// dispose of scenes, then dispose asset manager
	public void dispose() {
		for(Scene s: scenes) {
			s.dispose();
		}
		manager.dispose();
	}
}
