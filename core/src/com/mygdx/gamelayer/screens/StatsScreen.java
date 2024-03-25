package com.mygdx.gamelayer.screens;

import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
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
import com.mygdx.gamelayer.models.JSONReader;
import com.mygdx.gamelayer.simulation.AppSimulation;

public class StatsScreen extends Scene {	
	private SpriteBatch batch;
    private ShapeRenderer shape;
    private ButtonManager buttonManager;
    private ButtonControlManager buttonControlManager;
    private AppSimulation simulation;
    private JSONReader statsFile;

    private BitmapFont descriptionFont;

	private final String RETURN_TO_MAIN = "ReturnToMain";
	
	// y coordinates of the description text
	private final float DESCRIPTION_Y_POS = 850f;
	private final float TEXTAREA_WIDTH = 600f;

	public StatsScreen(HashMap<String, Texture> buttonTextures, JSONReader statsFile, IOManager iomanager, AppSimulation simulation) {
		super(
				(Sound)iomanager.getOutputManager().retrieve("SSBGMusic"),
				(Texture)iomanager.getOutputManager().retrieve("StatsBGImage"));
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		int screenWidth = Gdx.graphics.getWidth();

		this.simulation = simulation;
		this.statsFile = statsFile;
		
		// dynamically generate bitmap font of our desired size so it doesn't look pixelated
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();

		parameter.size = 22;
		descriptionFont = generator.generateFont(parameter);

		generator.dispose(); // don't forget to dispose to avoid memory leaks!

		//Instantiate ButtonManager
		buttonManager = new ButtonManager();
		
		// add buttons
		buttonManager.add(new Button(buttonTextures.get("Back"), 75, 0.2f, RETURN_TO_MAIN));
		
		buttonControlManager = new ButtonControlManager(buttonManager.getButtonList(), (Mouse)iomanager.getInputManager().retrieve(2));
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
		descriptionFont.draw(batch, "Mercury : " + statsFile.getJsonInt("mercury_highscore"), super.centeredXPos(600f), DESCRIPTION_Y_POS - 40f, TEXTAREA_WIDTH, Align.left, true);
		descriptionFont.draw(batch, "Venus : " + statsFile.getJsonInt("venus_highscore"), super.centeredXPos(600f), DESCRIPTION_Y_POS - 80f, TEXTAREA_WIDTH, Align.left, true);
		descriptionFont.draw(batch, "Earth : " + statsFile.getJsonInt("earth_highscore"), super.centeredXPos(600f), DESCRIPTION_Y_POS - 120f, TEXTAREA_WIDTH, Align.left, true);
		descriptionFont.draw(batch, "Mars : " + statsFile.getJsonInt("earth_highscore"), super.centeredXPos(600f), DESCRIPTION_Y_POS - 160f, TEXTAREA_WIDTH, Align.left, true);
		descriptionFont.draw(batch, "Jupiter : " + statsFile.getJsonInt("earth_highscore"), super.centeredXPos(600f), DESCRIPTION_Y_POS - 200f, TEXTAREA_WIDTH, Align.left, true);
		descriptionFont.draw(batch, "Uranus : " + statsFile.getJsonInt("earth_highscore"), super.centeredXPos(600f), DESCRIPTION_Y_POS - 240f, TEXTAREA_WIDTH, Align.left, true);
		descriptionFont.draw(batch, "Neptune : " + statsFile.getJsonInt("earth_highscore"), super.centeredXPos(600f), DESCRIPTION_Y_POS - 280f, TEXTAREA_WIDTH, Align.left, true);

		batch.end();
		
		// fade in transition
		super.fadeIn(0.25f, deltaTime);

		Button clickedButton = buttonControlManager.handleClickEvents();
		if(clickedButton != null) {
			switch(clickedButton.getAction()) {
				case RETURN_TO_MAIN:
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
