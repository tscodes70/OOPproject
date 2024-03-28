
package com.mygdx.gamelayer.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.mygdx.gameengine.models.Scene;
import com.badlogic.gdx.Gdx;

public class PauseScreen extends Scene {
    private BitmapFont font;
    private GlyphLayout title;
    private ShapeRenderer overlay;
    private final String GLYPHMESSAGE = "This game is paused. Press Enter/ESC to resume or Backspace to exit game.";
    
    public PauseScreen() {
        super();
        // translucent shape that acts as overlay
        this.overlay = new ShapeRenderer();
		overlay.setColor(0,0,0,0.0125f);
		// dynamically generate bitmap font of our desired size so it doesn't look pixelated
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 24;
		font = generator.generateFont(parameter);
		
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
        title = new GlyphLayout(); //for getting width/height of text
        title.setText(font, GLYPHMESSAGE, Color.CLEAR, 600f, Align.center, true);
    }

    /**
	 * Override the superclass method to draw using font instead of image texture
	 */
    @Override
    public void render() {
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
		// draw the transparent background overlay
		overlay.begin(ShapeRenderer.ShapeType.Filled);
		overlay.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		overlay.end();
		
		Gdx.gl.glDisable(GL20.GL_BLEND);
		
		// draw the pause screen message
        batch.begin();
		font.draw(super.batch, GLYPHMESSAGE,  super.centeredXPos(title.width),(Gdx.graphics.getHeight() / 2f) - (title.height / 2f), 600f, Align.center, true);

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
