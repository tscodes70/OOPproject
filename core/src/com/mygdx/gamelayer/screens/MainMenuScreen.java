package com.mygdx.gamelayer.screens;

import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

public class MainMenuScreen extends Scene {	
	private SpriteBatch batch;
    private ShapeRenderer shape;
    private ButtonManager buttonManager;
    private ButtonControlManager buttonControlManager;
    private AppSimulation simulation;
    
	private final String IMAGE_PATH = "image";
	
	private final String SELECT_LEVEL = "SelectLevel";
	private final String STATS = "Stats";
	private final String QUIT = "Quit";
	
	private final String IMAGE_SA = String.format("%s/spawnai.png", IMAGE_PATH);
	private final String IMAGE_SP = String.format("%s/spawnplayer.png", IMAGE_PATH);
	
	private Texture titleTexture;
	
	//Set bg image
	Texture bgImage = new Texture(Gdx.files.internal("image/spacep.png"));

	
	public MainMenuScreen(HashMap<String, Texture> buttonTextures, Texture bgImage, Sound bgMusic, Mouse mouseDevice, AppSimulation simulation) {
		super(bgMusic,bgImage);
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		int screenWidth = Gdx.graphics.getWidth();
		
		//for title
		titleTexture = new Texture(Gdx.files.internal("image/fonttwo.png"));
		
		
		this.simulation = simulation;
		
		//Instantiate ButtonManager
		buttonManager = new ButtonManager();
		
		// add buttons
		buttonManager.add(new Button(buttonTextures.get("Start"), 475, 0.2f, SELECT_LEVEL ));
		buttonManager.add(new Button(buttonTextures.get("Stats"), 375, 0.2f,  STATS));
		buttonManager.add(new Button(buttonTextures.get("Quit"), 275, 0.2f, QUIT));
		
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
		
		//draw the background image
		if(bgImage != null) {
			batch.draw(bgImage, 0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		}
		
//		//draw title text
		if(titleTexture != null) {	
			// Define the new width and height of the title image 
			float scaleX = 0.5f;
		    float scaleY = 0.5f;
		    
			batch.draw(titleTexture, 160, 720, titleTexture.getWidth() * scaleX, titleTexture.getHeight() * scaleY);
		}
		
		
		//entityManager.drawEntities(batch);
		buttonManager.drawButtons(batch);
		batch.end();
		
		super.fadeIn(0.25f, deltaTime);

		Button clickedButton = buttonControlManager.handleClickEvents();
		
		if(clickedButton != null) {
			switch(clickedButton.getAction()) {
				case SELECT_LEVEL :
					// go to planet selection screen
					simulation.chooseLevel();
					break;
				case  STATS:
					simulation.showStats();
					break;
				case QUIT:
					// exit application
					Gdx.app.exit();
					break;
			}
		}
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
		buttonManager.dispose();
		buttonControlManager.dispose();
		titleTexture.dispose();
	}
}
