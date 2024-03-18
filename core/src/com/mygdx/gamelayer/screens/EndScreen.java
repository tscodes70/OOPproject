package com.mygdx.gamelayer.screens;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.gameengine.models.Scene;
import com.mygdx.gameengine.models.Sound;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;

public class EndScreen extends Scene {
	
	private BitmapFont font;
	private GlyphLayout title;
	
    private final String GLYPHMESSAGE = "Game ended, press Esc to return";

	
	public EndScreen(Sound bgMusic) {
		super(bgMusic);
		this.font = new BitmapFont();
	    title = new GlyphLayout(font, GLYPHMESSAGE); //for getting width/height of text
	}
	
	/**
	 * Override the superclass method to draw using font instead of image texture
	 */
	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		font.draw(super.batch, GLYPHMESSAGE, super.centeredXPos(title.width), (Gdx.graphics.getHeight() / 2f) - (title.height * (1.5f/2)));
		font.getData().setScale(1.5f); // increase font size
		batch.end();
	}
	
	/**
	 * Disposal of end screen and superclass
	 */
	@Override
	public void dispose() {
		super.dispose();	
		font.dispose();
	}
}
