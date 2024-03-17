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

public class PlanetInfoScreen extends Scene {	
	private SpriteBatch batch;
    private ShapeRenderer shape;
    private ButtonManager buttonManager;
    private ButtonControlManager buttonControlManager;
    private AppSimulation simulation;
    private String planetName;
    private String planetImagePath;
    private String planetDescription;
    
    private BitmapFont planetNameFont, descriptionFont;
    private GlyphLayout planetNameGlyph, descriptionGlyph;
	private final String IMAGE_PATH = "image";

	private final String IMAGE_SA = String.format("%s/spawnai.png", IMAGE_PATH);
	private final String IMAGE_SP = String.format("%s/spawnplayer.png", IMAGE_PATH);
	
	// planet image paths
	private final String IMAGE_PLANET_MERCURY = String.format("%s/mercury_planet.png", IMAGE_PATH);
	private final String IMAGE_PLANET_VENUS = String.format("%s/venus_planet.png", IMAGE_PATH);
	private final String IMAGE_PLANET_EARTH = String.format("%s/earth_planet.png", IMAGE_PATH);
	private final String IMAGE_PLANET_MARS = String.format("%s/mars_planet.png", IMAGE_PATH);

	private HashMap<String, String[]> planetInfoMapping;
	
	// y coordinates of the description text
	private final float DESCRIPTION_Y_POS = 725f;
	private final float TEXTAREA_WIDTH = 600f;

	public PlanetInfoScreen(HashMap<String, Texture> buttonTextures, Texture bgImage, Sound bgMusic, Mouse mouseDevice, AppSimulation simulation) {
		super(bgMusic,bgImage);
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		int screenWidth = Gdx.graphics.getWidth();

		this.simulation = simulation;
		
		// dynamically generate bitmap font of our desired size so it doesn't look pixelated
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 56;
		planetNameFont = generator.generateFont(parameter); // font size 12 pixels
		
		parameter.size = 22;
		descriptionFont = generator.generateFont(parameter);
		
		generator.dispose(); // don't forget to dispose to avoid memory leaks!

        // mapping of planet name to their respective image paths and description
        planetInfoMapping = new HashMap<String, String[]>();
        
        planetInfoMapping.put("Mercury", new String[] { IMAGE_PLANET_MERCURY, "This is a description of Mercury. " 
        						+ "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean luctus ac lectus in varius. Pellentesque euismod dui ut leo fermentum."});
        
        planetInfoMapping.put("Venus", new String[] { IMAGE_PLANET_VENUS, "This is a description of Venus. "
        						+ "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean luctus ac lectus in varius. Pellentesque euismod dui ut leo fermentum." });
        
        // newlines to test dynamic text positioning
        planetInfoMapping.put("Earth", new String[] { IMAGE_PLANET_EARTH, "This is a longer description of Earth. \n\n\n\n\n\n\n\n"
        						+ "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean luctus ac lectus in varius. Pellentesque euismod dui ut leo fermentum." });
        
        planetInfoMapping.put("Mars", new String[] { IMAGE_PLANET_MARS, "This is a description of Mars. "
								+ "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean luctus ac lectus in varius. Pellentesque euismod dui ut leo fermentum." });

		//Instantiate ButtonManager
		buttonManager = new ButtonManager();
		
		// add buttons
		buttonManager.add(new Button(buttonTextures.get("Start"), (screenWidth - buttonTextures.get("Start").getWidth()) / 2, 175, 0.9f, "Start"));
		buttonManager.add(new Button(buttonTextures.get("Back"), (screenWidth  - buttonTextures.get("Back").getWidth()) / 2, 75, 0.9f, "SelectLevel"));
		
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
		planetNameFont.draw(batch, planetName, super.centeredXPos(planetNameGlyph.width), 825f);
		
		// draw wrapped text for planet description
		descriptionFont.draw(batch, planetDescription,  super.centeredXPos(600f), DESCRIPTION_Y_POS, TEXTAREA_WIDTH, Align.left, true);
		
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
				case "Start":
					// start game
					simulation.setGameLevel(planetName, planetImagePath);
					break;
				case "SelectLevel":
					// go back to planet selection
					simulation.chooseLevel();
					break;
			}
		}
	}
	
	// set which planet's info to display
	public void setChosenLevel(String planetName) {
		this.planetName = planetName;
		planetImagePath = planetInfoMapping.get(planetName)[0];
		planetDescription = planetInfoMapping.get(planetName)[1];
		
        planetNameGlyph = new GlyphLayout(planetNameFont, planetName); //for getting width/height of text	
        descriptionGlyph = new GlyphLayout();
        descriptionGlyph.setText(descriptionFont, planetDescription, Color.CLEAR, 600f, Align.left, true);
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
