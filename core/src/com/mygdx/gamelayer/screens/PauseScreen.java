
package com.mygdx.gamelayer.screens;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.gameengine.models.Scene;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;

public class PauseScreen extends Scene {

    private BitmapFont font;
    private GlyphLayout title;
    
    private final String GLYPHMESSAGE = "Paused, Enter/ESC to return. Backspace to end demo";
    
    public PauseScreen() {
        super();
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
        font.draw(super.batch, GLYPHMESSAGE,
                (Gdx.graphics.getWidth() / 2f) - (title.width * (1.5f/2)), (Gdx.graphics.getHeight() / 2f) - (title.height * (1.5f/2)));
        font.getData().setScale(1.5f); // increase font size
        batch.end();
    }

    /**
	 * Disposal of PauseScreen and Superclass Resources
	 */
    @Override
    public void dispose() {
    	super.dispose();
        font.dispose();
    }
}
