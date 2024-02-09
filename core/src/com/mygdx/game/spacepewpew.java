package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.managers.PlayerControlManager;
import com.mygdx.game.screens.SplashScreen;

public class spacepewpew extends Game {
	SpriteBatch batch;
	Texture img;
	
	//audio testing
	public static AssetManager manager;
	
	@Override
	public void create () {

//		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");
		
		// Input Processor
		PlayerControlManager pcm = new PlayerControlManager();
		
		//Audio testing
		manager = new AssetManager();
		manager.load("audio/music/megalovania.mp3", Music.class);
		manager.finishLoading();
		
		setScreen(new SplashScreen());
	}

	@Override
	public void render () {
		super.render();

//		ScreenUtils.clear(1, 0, 0, 1);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
	}
	
	@Override
	public void dispose () {
		super.dispose();
//		batch.dispose();
//		img.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}
	
	@Override
	public void pause() {
		super.pause();
	}
	
	@Override
	public void resume() {
		super.resume();
	}
}
