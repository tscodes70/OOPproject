package com.mygdx.gamelayer.screens;

import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.mygdx.gameengine.managers.AIControlManager;
import com.mygdx.gameengine.managers.ButtonControlManager;
import com.mygdx.gameengine.managers.ButtonManager;
import com.mygdx.gameengine.managers.CollisionManager;
import com.mygdx.gameengine.managers.EntityManager;
import com.mygdx.gameengine.managers.PlayerControlManager;
import com.mygdx.gameengine.managers.SceneManager;
import com.mygdx.gameengine.models.Button;
import com.mygdx.gameengine.models.Entity;
import com.mygdx.gameengine.models.Keyboard;
import com.mygdx.gameengine.models.Mouse;
import com.mygdx.gameengine.models.Scene;
import com.mygdx.gameengine.models.Sound;
import com.mygdx.gamelayer.simulation.AppSimulation;

public class LevelClearedScreen extends Scene {	
	private SpriteBatch batch;
    private ShapeRenderer shape;
    private ButtonManager buttonManager;
    private ButtonControlManager buttonControlManager;
    private AppSimulation simulation;
    private String planetName;
    private String planetImagePath;
    private Texture planetImage;
    private BitmapFont planetNameFont, messageFont;
    private GlyphLayout planetNameGlyph, messageGlyph;
    private String GLYPHMESSAGE = "has been successfully defended from pollution!";
	private final String IMAGE_PATH = "image";
	
	private final String CONTINUE = "continue";
	
	private final String IMAGE_SA = String.format("%s/spawnai.png", IMAGE_PATH);
	private final String IMAGE_SP = String.format("%s/spawnplayer.png", IMAGE_PATH);

	public LevelClearedScreen(HashMap<String, Texture> buttonTextures, Texture bgImage, Sound bgMusic, Mouse mouseDevice, AppSimulation simulation) {
		super(bgMusic,bgImage);
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		int screenWidth = Gdx.graphics.getWidth();

		this.simulation = simulation;
		
		// dynamically generate bitmap font of our desired size so it doesn't look pixelated
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 45;
		planetNameFont = generator.generateFont(parameter);
		planetNameFont.getData().setScale(1.25f);
		planetNameFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

		parameter.size = 22;
		messageFont = generator.generateFont(parameter);
		messageFont.getData().setScale(1.1f);
		messageFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

		generator.dispose(); // don't forget to dispose to avoid memory leaks!

        messageGlyph = new GlyphLayout(messageFont, GLYPHMESSAGE); //for getting width/height of text

		//Instantiate ButtonManager
		buttonManager = new ButtonManager();
		
		// add buttons
		buttonManager.add(new Button(buttonTextures.get("Continue"), 125, 0.2f, CONTINUE));
		
		buttonControlManager = new ButtonControlManager(buttonManager.getButtonList(), mouseDevice);
	}
	
	/**
	 * Renders objects in GameScreen
	 */
	@Override
	public void render() {
		float deltaTime = Gdx.graphics.getDeltaTime();
		super.render();
		
		//Gdx.gl.glClearColor(0, 0, 0, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		//entityManager.drawEntities(batch);
		buttonManager.drawButtons(batch);
		
		// draw planet image
		batch.draw(planetImage, super.centeredXPos(390f), 450, 390, 390);
		
		// draw planet name and message text
		planetNameFont.draw(batch, planetName, super.centeredXPos(planetNameGlyph.width), 420f);
		
		// wrapped text
		messageFont.draw(batch, GLYPHMESSAGE, super.centeredXPos(400f), 350f, 400f, Align.center, true);

		// draw stars score here
		messageFont.draw(batch, "<stars score goes here>", super.centeredXPos(400f), 260f, 400f, Align.center, true);

		batch.end();
		
		// fade in transition
		super.fadeIn(0.25f, deltaTime);

		Button clickedButton = buttonControlManager.handleClickEvents();
		if(clickedButton != null) {
			switch(clickedButton.getAction()) {
				case CONTINUE:
					// go back to level selection
					simulation.chooseLevel();
					break;
			}
		}
	}
	
	// set info of level that was completed
	public void setLevel(String planetName, Texture planetImage) {
        planetNameGlyph = new GlyphLayout(planetNameFont, planetName); //for getting width/height of text
		this.planetName = planetName;
		this.planetImage = planetImage;
	}
	
	/**
	 * Disposes GameScreen and its superclass resources
	 */
	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		shape.dispose();
		planetNameFont.dispose();
		messageFont.dispose();
		buttonManager.dispose();
		buttonControlManager.dispose();
	}
	
	
}
