package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.spacepewpew;

public class SplashScreen implements Screen {
	
	private Sprite splash;
	private SpriteBatch batch;
	
	private Music bgMusic;
	
//	public SplashScreen(Music bgMusic) {
//		this.bgMusic = bgMusic;
//	}
//	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		
		Texture splashTexture = new Texture(Gdx.files.internal("image/splash.jpg"));
		splash = new Sprite(splashTexture);
		splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		bgMusic = spacepewpew.manager.get("audio/music/megalovania.mp3",Music.class);
		bgMusic.setLooping(true);
		bgMusic.setVolume(0.1f);
		bgMusic.play();
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		splash.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
}
