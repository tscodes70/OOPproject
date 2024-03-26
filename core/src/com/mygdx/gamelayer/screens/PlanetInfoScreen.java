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
import com.mygdx.gameengine.managers.IOManager;
import com.mygdx.gameengine.managers.PlayerControlManager;
import com.mygdx.gameengine.managers.SceneManager;
import com.mygdx.gameengine.models.Button;
import com.mygdx.gameengine.models.Entity;
import com.mygdx.gameengine.models.Keyboard;
import com.mygdx.gameengine.models.Mouse;
import com.mygdx.gameengine.models.Scene;
import com.mygdx.gameengine.models.Sound;
import com.mygdx.gamelayer.models.Planet;
import com.mygdx.gamelayer.simulation.AppSimulation;

public class PlanetInfoScreen extends Scene {	
	private SpriteBatch batch;
    private ShapeRenderer shape;
    private ButtonManager buttonManager;
    private ButtonControlManager buttonControlManager;
    private AppSimulation simulation;
    private Planet planet;
    private Mouse mouseDevice;
    
    private BitmapFont planetNameFont, descriptionFont;
    private GlyphLayout planetNameGlyph, descriptionGlyph;
	private final String IMAGE_PATH = "image";
	
	private final String START = "Start";
	private final String SELECT_LEVEL = "SelectLevel";
	
	// y coordinates of the description text
	private final float DESCRIPTION_Y_POS = 725f;
	private final float TEXTAREA_WIDTH = 600f;

	public PlanetInfoScreen(HashMap<String, Texture> buttonTextures, IOManager ioManager, AppSimulation simulation) {
		super(
				(Sound)ioManager.getOutputManager().retrieve("SSBGMusic"),
				(Texture)ioManager.getOutputManager().retrieve("LSSBGImage"));
		
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		int screenWidth = Gdx.graphics.getWidth();

		this.simulation = simulation;
		this.mouseDevice = (Mouse)ioManager.getInputManager().retrieve(2);
		
		// dynamically generate bitmap font of our desired size so it doesn't look pixelated
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 45;
		planetNameFont = generator.generateFont(parameter); // font size 12 pixels
		planetNameFont.getData().setScale(1.25f);
		planetNameFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

		parameter.size = 20;
		descriptionFont = generator.generateFont(parameter);
		descriptionFont.getData().setScale(1.1f);
		descriptionFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

		generator.dispose(); // don't forget to dispose to avoid memory leaks!

		//Instantiate ButtonManager
		buttonManager = new ButtonManager();
		
		// add buttons
		buttonManager.add(new Button(buttonTextures.get("Start"), 175, 0.2f, START));
		buttonManager.add(new Button(buttonTextures.get("Back"), 75, 0.2f, SELECT_LEVEL));
		
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
		buttonManager.drawButtons(batch);
		
		// draw planet name
		planetNameFont.draw(batch, planet.getName(), super.centeredXPos(planetNameGlyph.width), 825f);
		
		// draw wrapped text for planet description
		descriptionFont.draw(batch, planet.getInfo(),  super.centeredXPos(600f), DESCRIPTION_Y_POS, TEXTAREA_WIDTH, Align.left, true);
		
		// vertical position of these elements are dynamic based on height of the description text
		descriptionFont.draw(batch, "Buffs: placeholder", super.centeredXPos(600f), DESCRIPTION_Y_POS - descriptionGlyph.height - 40f, TEXTAREA_WIDTH, Align.left, true);
		descriptionFont.draw(batch, "Debuffs: placeholder", super.centeredXPos(600f), DESCRIPTION_Y_POS - descriptionGlyph.height - 80f, TEXTAREA_WIDTH, Align.left, true);
		descriptionFont.draw(batch, "Highscore: placeholder/3 stars", super.centeredXPos(600f), DESCRIPTION_Y_POS - descriptionGlyph.height - 120f, TEXTAREA_WIDTH, Align.left, true);

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
		buttonManager.dispose();
		buttonControlManager.dispose();
	}
}
