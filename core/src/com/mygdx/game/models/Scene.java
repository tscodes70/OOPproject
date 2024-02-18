package com.mygdx.game.models;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Scene {
	protected Sprite bgSprite;
	protected SpriteBatch batch;
	protected Texture bgTexture;
	protected Music bgMusic;
	
	protected AssetManager manager;
	
	public Scene(AssetManager manager, String spriteImageName, String bgMusicName) {
		this.manager = manager;
		
		batch = new SpriteBatch();
		bgTexture = new Texture(Gdx.files.internal(spriteImageName));
		bgSprite = new Sprite(bgTexture);
		bgSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		bgMusic = manager.get(bgMusicName,Music.class);
		bgMusic.setLooping(true);
		bgMusic.setVolume(0.15f);
	}
	
	public Scene(AssetManager manager, String bgMusicName) {
		this.manager = manager;
		
		batch = new SpriteBatch();
		bgMusic = manager.get(bgMusicName,Music.class);
		bgMusic.setLooping(true);
		bgMusic.setVolume(0.15f);
	}

	
	//@Override
	// render background texture, called by scene manager
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		bgSprite.draw(batch);
		batch.end();
	}
	
	//@Override
	// logic to run when scene is made active
	public void show() {
		bgMusic.setPosition(0);
		bgMusic.play();
	}
	
	//@Override
	// logic to run when scene is hidden
	public void hide() {
		bgMusic.pause();
	}
	
	//@Override
	// logic to run when scene is paused
	public void pause() {
		bgMusic.pause();
		// TODO Auto-generated method stub
	}
	
	//@Override
	// logic to run when scene is resumed
	public void resume() {
		bgMusic.play();
		// TODO Auto-generated method stub
	}

	//@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}
	
	//@Override
	// Dispose of scene resources
	public void dispose() {
		bgMusic.dispose();
		bgTexture.dispose();
		batch.dispose();
		System.out.println("disposed");
	}
}
