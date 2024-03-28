package com.mygdx.gameengine.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Scene {

	protected SpriteBatch batch;
	protected Sound bgMusic;
    private ShapeRenderer overlay;
	protected Sprite bgImage;
    private float fadeInOverlayOpacity = 1.0f;

	/**
	 * Constructor for Scenes that contain Background Image and Music
	 * @param bgMusic
	 * @param bgImage
	 */
	public Scene(Sound bgMusic, Texture bgImage) {
		batch = new SpriteBatch();
		bgImage.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		this.bgImage = new Sprite(bgImage);
		this.bgImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.bgMusic = bgMusic;
		overlay = new ShapeRenderer();
	}
	
	/**
	 * Constructor for Scenes that only contain Background Music
	 * @param bgMusic
	 */
	public Scene(Sound bgMusic) {
		batch = new SpriteBatch();
		this.bgMusic = bgMusic;
		overlay = new ShapeRenderer();
	}
	
	/**
	 * Constructor for Scenes that do not contain Background Image
	 * and Music
	 */
	public Scene() {
		batch = new SpriteBatch();
	}
	
	// visually fade in the scene with a specified delay
	public void fadeIn(float duration, float deltaTime) {
		if(fadeInOverlayOpacity > 0f) {
			fadeInOverlayOpacity -= (1 / duration * deltaTime);
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			
			// shape overlay covering entire screen
			// shape opacity is gradually reduced to 0 to achieve fade in effect
			overlay.begin(ShapeRenderer.ShapeType.Filled);
			overlay.setColor(0,0,0, fadeInOverlayOpacity);
			overlay.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			overlay.end();
			
			Gdx.gl.glDisable(GL20.GL_BLEND);
		}
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
		// reset fade in overlay opacity for transition
		fadeInOverlayOpacity = 1.0f;
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
	
	// check if fade in transition is complete
	public boolean isFadedIn() {
		return fadeInOverlayOpacity <= 0f;
	}
	
	// calculate centered x coordinates
	protected float centeredXPos(float width) {
		return ((float)Gdx.graphics.getWidth() / 2) - ((float)width / 2);
	}
	
	/**
	 * Dispose scene resources
	 */
	public void dispose() {
		if(bgMusic != null) bgMusic.dispose();
		if(batch != null) batch.dispose();
	}
}
