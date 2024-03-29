package com.mygdx.gamelayer.screens;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
import com.mygdx.gamelayer.models.Planet;
import com.mygdx.gamelayer.models.TextFileHandler;
import com.mygdx.gamelayer.simulation.AppSimulation;

public class PlanetInfoScreen extends Scene {	
	private SpriteBatch batch;
    private ShapeRenderer shape;
    private ButtonManager buttonManager;
    private ButtonControlManager buttonControlManager;
    private AppSimulation simulation;
    private Planet planet;
    private Mouse mouseDevice;
	private TextFileHandler statsFile;
	
    private BitmapFont planetNameFont, descriptionFont, descriptionFont2;
    private GlyphLayout planetNameGlyph, descriptionGlyph;
	
	private final String START = "Start";
	private final String SELECT_LEVEL = "SelectLevel";
	
	// y coordinates of the description text
	private final float DESCRIPTION_Y_POS = 695f;
	private final float TEXTAREA_WIDTH = 600f;


	public PlanetInfoScreen(HashMap<String, Texture> buttonTextures, TextFileHandler statsFile,IOManager ioManager, AppSimulation simulation) {
		super(
				(Sound)ioManager.getOutputManager().retrieve("SSBGMusic"),
				(Texture)ioManager.getOutputManager().retrieve("PISBGImage"));
		
		batch = new SpriteBatch();
		shape = new ShapeRenderer();

		this.simulation = simulation;
		this.mouseDevice = (Mouse)ioManager.getInputManager().retrieve(2);
		this.statsFile = statsFile;
		
		// dynamically generate bitmap font of our desired size so it doesn't look pixelated
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		
		FreeTypeFontGenerator generatorRetro = new FreeTypeFontGenerator(Gdx.files.internal("fonts/retro.ttf"));
		parameter.size = 45;
		planetNameFont = generatorRetro.generateFont(parameter); // font size 12 pixels
		planetNameFont.getData().setScale(1.25f);
		planetNameFont.setColor(Color.YELLOW);
		planetNameFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

		parameter.size = 15;
		descriptionFont = generator.generateFont(parameter);
		descriptionFont.getData().setScale(1.1f);
		descriptionFont.setColor(Color.BLACK);
		descriptionFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		parameter.size = 20;
		descriptionFont2 = generator.generateFont(parameter);
		descriptionFont2.getData().setScale(1.1f);
		descriptionFont2.setColor(Color.BLACK);
		descriptionFont2.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

		generator.dispose(); // don't forget to dispose to avoid memory leaks!

		//Instantiate ButtonManager
		buttonManager = new ButtonManager();
		
		// add buttons
		buttonManager.add(new Button(buttonTextures.get("Start"), 500, 75, 0.2f, START));
		buttonManager.add(new Button(buttonTextures.get("Back"), 100, 75, 0.2f, SELECT_LEVEL));
		
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
		buttonManager.drawButtons(batch);
		
		String[] planetFacts = planet.getInfo().split("<");

		// Draw planet name
		planetNameFont.draw(batch, planet.getName(), super.centeredXPos(planetNameGlyph.width), 880f);

		// Draw wrapped text for planet description
		float yPos = DESCRIPTION_Y_POS;
		for (int i = 0; i < planetFacts.length; i++) {
		    descriptionFont.draw(batch, planetFacts[i].trim(), super.centeredXPos(300f), yPos, TEXTAREA_WIDTH, Align.left, true);
		    yPos -= 100; // Adjust vertical position for the next line
		}

		// Draw additional information
		descriptionFont2.draw(batch, "Gravity: " + planet.getGravity() + " m/s^2", super.centeredXPos(200f), yPos, TEXTAREA_WIDTH, Align.left, true);
		descriptionFont2.draw(batch, "Highscore: " + statsFile.getMap().get(planet.getName().toLowerCase() + "_highscore"), super.centeredXPos(200f), yPos-30, TEXTAREA_WIDTH, Align.left, true);

		batch.end();
		
		// fade in transition
		super.fadeIn(0.25f, deltaTime);

		Button clickedButton = buttonControlManager.handleClickEvents();
		if(clickedButton != null) {
			switch(clickedButton.getAction()) {
				case START:
					// start game
					simulation.setGameLevel(planet);
					break;
				case SELECT_LEVEL:
					// go back to planet selection
					simulation.chooseLevel();
					break;
			}
		}
	}
	
	// set which planet's info to display
	public void setChosenLevel(Planet planet) {
		this.planet = planet;
		
        planetNameGlyph = new GlyphLayout(planetNameFont, planet.getName()); //for getting width/height of text	
        descriptionGlyph = new GlyphLayout();
        descriptionGlyph.setText(descriptionFont, planet.getInfo(), Color.CLEAR, 600f, Align.left, true);
	}
	
	@Override
	public void update() {

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
		descriptionFont.dispose();
		descriptionFont2.dispose();
		buttonManager.dispose();
		buttonControlManager.dispose();
	}
}
