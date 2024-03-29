package com.mygdx.gamelayer.screens;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.mygdx.gameengine.managers.ButtonControlManager;
import com.mygdx.gameengine.managers.ButtonManager;
import com.mygdx.gameengine.managers.IOManager;
import com.mygdx.gameengine.models.Button;
import com.mygdx.gameengine.models.Mouse;
import com.mygdx.gameengine.models.Scene;
import com.mygdx.gameengine.models.Sound;
import com.mygdx.gamelayer.models.TextFileHandler;
import com.mygdx.gamelayer.simulation.AppSimulation;

public class LevelClearedScreen extends Scene {	
	private SpriteBatch batch;
    private ShapeRenderer shape;
    private ButtonManager buttonManager;
    private ButtonControlManager buttonControlManager;
    private AppSimulation simulation;
    private String planetName;
    private Texture planetImage;
    private BitmapFont planetNameFont, messageFont;
    private GlyphLayout planetNameGlyph;
    private String GLYPHMESSAGE = "has been successfully defended from pollution!";
    private int playerPoints;
	private Mouse mouseDevice;
	private TextFileHandler statsFile;
	private boolean statsFileUpdated;
	
	private final String CONTINUE = "continue";


	public LevelClearedScreen(HashMap<String, Texture> buttonTextures,TextFileHandler statsFile, IOManager ioManager, AppSimulation simulation) {
		super(
				(Sound)ioManager.getOutputManager().retrieve("ESBGMusic"),
				(Texture)ioManager.getOutputManager().retrieve("LCBGImage"));
		batch = new SpriteBatch();
		shape = new ShapeRenderer();

		this.simulation = simulation;
		this.mouseDevice = (Mouse)ioManager.getInputManager().retrieve(2);
		this.statsFile = statsFile;
		this.statsFileUpdated = false;
		
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

        planetNameGlyph = new GlyphLayout(planetNameFont, "");
        
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
		messageFont.draw(batch, "Your score: " + playerPoints, super.centeredXPos(400f), 260f, 400f, Align.center, true);

		batch.end();
		
		if(planetName != null && !statsFileUpdated) {
			String key = planetName.toLowerCase() + "_highscore";
			if(statsFile.getMap().get(key) < playerPoints) {
				statsFile.updateValueForKey(key, playerPoints);
			}
			statsFileUpdated = true;
		}
		
		
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
	public void setLevel(String planetName, Texture planetImage, int playerPoints) {
        planetNameGlyph.setText(planetNameFont, planetName);
		this.planetName = planetName;
		this.planetImage = planetImage;
		this.playerPoints = playerPoints;
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
