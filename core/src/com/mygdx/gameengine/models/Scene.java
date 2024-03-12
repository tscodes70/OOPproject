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

	protected SpriteBatch batch;
	protected Sound bgMusic;
	protected Sprite bgImage;
	
	/**
	 * Constructor for Scenes that contain Background Image and Music
	 * @param bgMusic
	 * @param bgImage
	 */
	public Scene(Sound bgMusic, Texture bgImage) {

		batch = new SpriteBatch();
		this.bgImage = new Sprite(bgImage);
		this.bgImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.bgMusic = bgMusic;
	}
	
	/**
	 * Constructor for Scenes that only contain Background Music
	 * @param bgMusic
	 */
	public Scene(Sound bgMusic) {
		batch = new SpriteBatch();
		this.bgMusic = bgMusic;
	}
	
	/**
	 * Constructor for Scenes that do not contain Background Image
	 * and Music
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
		this.bgImage.draw(batch);
		this.batch.end();
	}

	/**
	 * Displays Scene, called during scene transition to display active scene
	 */
	public void show() {
		if(bgMusic != null) {
			bgMusic.output();
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
		if(bgMusic != null) bgMusic.output();
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
	}
}
