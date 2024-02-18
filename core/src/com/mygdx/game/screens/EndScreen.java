package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;

public class EndScreen extends Scene {
	private BitmapFont font;
	GlyphLayout title;
	public EndScreen(AssetManager manager, String bgMusicName) {
		super(manager, bgMusicName);
		this.font = new BitmapFont();
	    title = new GlyphLayout(font, "Game ended, press Esc to return"); //for getting width/height of text
	}
	
	// override the superclass method to draw using font instead of image texture
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		font.draw(super.batch, "Game ended, press Esc to return", 
				(Gdx.graphics.getWidth() / 2f) - (title.width * (1.5f/2)), (Gdx.graphics.getHeight() / 2f) - (title.height * (1.5f/2)));
		font.getData().setScale(1.5f); // increase font size
		batch.end();
	}
	
	// Dispose of scene resources
	@Override
	public void dispose() {
		bgMusic.dispose();
		batch.dispose();	
		font.dispose();
		System.out.println("disposed");
	}
}
