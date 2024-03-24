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

public class StatsScreen extends Scene {	
	private SpriteBatch batch;
    private ShapeRenderer shape;
    private ButtonManager buttonManager;
    private ButtonControlManager buttonControlManager;
    private AppSimulation simulation;

    private BitmapFont descriptionFont;
	private final String IMAGE_PATH = "image";

	private final String IMAGE_SA = String.format("%s/spawnai.png", IMAGE_PATH);
	private final String IMAGE_SP = String.format("%s/spawnplayer.png", IMAGE_PATH);
	
	// y coordinates of the description text
	private final float DESCRIPTION_Y_POS = 850f;
	private final float TEXTAREA_WIDTH = 600f;

	public StatsScreen(HashMap<String, Texture> buttonTextures, Texture bgImage, Sound bgMusic, Mouse mouseDevice, AppSimulation simulation) {
		super(bgMusic,bgImage);
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		int screenWidth = Gdx.graphics.getWidth();

		this.simulation = simulation;
		
		// dynamically generate bitmap font of our desired size so it doesn't look pixelated
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();

		parameter.size = 22;
		descriptionFont = generator.generateFont(parameter);

		generator.dispose(); // don't forget to dispose to avoid memory leaks!

		//Instantiate ButtonManager
		buttonManager = new ButtonManager();
		
		// add buttons
		buttonManager.add(new Button(buttonTextures.get("Back"), 75, 0.2f, "ReturnToMain"));
		
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
		
		// statistics text
		descriptionFont.draw(batch, "Statistics1: placeholder", super.centeredXPos(600f), DESCRIPTION_Y_POS - 40f, TEXTAREA_WIDTH, Align.left, true);
		descriptionFont.draw(batch, "Statistics2: placeholder", super.centeredXPos(600f), DESCRIPTION_Y_POS - 80f, TEXTAREA_WIDTH, Align.left, true);
		descriptionFont.draw(batch, "Statistics3: placeholder", super.centeredXPos(600f), DESCRIPTION_Y_POS - 120f, TEXTAREA_WIDTH, Align.left, true);

		batch.end();
		
		// fade in transition
		super.fadeIn(0.25f, deltaTime);

		Button clickedButton = buttonControlManager.handleClickEvents();
		if(clickedButton != null) {
			switch(clickedButton.getAction()) {
				case "ReturnToMain":
					simulation.returnToMainMenu();
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
		descriptionFont.dispose();
		buttonManager.dispose();
		buttonControlManager.dispose();
	}
}
