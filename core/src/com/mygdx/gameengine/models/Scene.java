package com.mygdx.gameengine.models;

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
	protected AssetManager manager;
	protected Music bgMusic;
	
	/**
	 * Constructor for Scenes that contain Background Image and Music
	 * @param manager
	 * @param bgTexture
	 * @param bgMusicName
	 */
	public Scene(AssetManager manager, Texture bgTexture, String bgMusicName) {
		this.manager = manager;
		
		batch = new SpriteBatch();
		bgSprite = new Sprite(bgTexture);
		bgSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		bgMusic = manager.get(bgMusicName,Music.class);
		bgMusic.setLooping(true);
		bgMusic.setVolume(0.15f);
	}
	
	/**
	 * Constructor for Scenes that only contain Background Music
	 * @param manager
	 * @param bgTexture
	 * @param bgMusicName
	 */
	public Scene(AssetManager manager, String bgMusicName) {
		this.manager = manager;
		
		batch = new SpriteBatch();
		bgMusic = manager.get(bgMusicName,Music.class);
		bgMusic.setLooping(true);
		bgMusic.setVolume(0.15f);
	}
	
	/**
	 * Constructor for Scenes that do not contain Background Image
	 * and Music
	 * @param manager
	 * @param bgTexture
	 * @param bgMusicName
	 */
	public Scene() {
		batch = new SpriteBatch();
	}

	/**
	 * Renders background texture, called by SceneManager Class
	 */
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.batch.begin();
		this.bgSprite.draw(batch);
		this.batch.end();
	}

	/**
	 * Displays Scene, called during scene transition to display active scene
	 */
	public void show() {
		if(bgMusic != null) {
			bgMusic.setPosition(0);
			bgMusic.play();
		}
	}
	
	/**
	 * Hides Scene, called during scene transition to hide non-active scenes
	 */
	public void hide() {
		if(bgMusic != null) bgMusic.pause();
	}
	
	/**
	 * Pauses Scene, called during scene transition to pause scene
	 */
	public void pause() {
		if(bgMusic != null) bgMusic.pause();
	}
	
	/**
	 * Resume Scene, called during scene transition to resume scene
	 */
	public void resume() {
		if(bgMusic != null) bgMusic.play();
	}
	
	/**
	 * Update Scene
	 */
	public void update() {

	}

	/**
	 * Dispose scene resources
	 */
	public void dispose() {
		if(bgMusic != null) bgMusic.dispose();
		if(batch != null) batch.dispose();
		if(manager != null) manager.dispose();
	}
}
