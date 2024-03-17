package com.mygdx.gamelayer.screens;

import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

public class LevelSelectScreen extends Scene {	
	private SpriteBatch batch;
    private ShapeRenderer shape;
    private ButtonManager buttonManager;
    private ButtonControlManager buttonControlManager;
    private AppSimulation simulation;
    private float fadeInOverlayOpacity = 1.0f;
	private final String IMAGE_PATH = "image";

	private final String IMAGE_SA = String.format("%s/spawnai.png", IMAGE_PATH);
	private final String IMAGE_SP = String.format("%s/spawnplayer.png", IMAGE_PATH);
    
	
	public LevelSelectScreen(HashMap<String, Texture> buttonTextures, Texture bgImage, Sound bgMusic, Mouse mouseDevice, AppSimulation simulation) {
		super(bgMusic,bgImage);
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		int screenWidth = Gdx.graphics.getWidth();
		
		this.simulation = simulation;
		
		//Instantiate ButtonManager
		buttonManager = new ButtonManager();
		
		// add buttons
		buttonManager.add(new Button(buttonTextures.get("Mercury"), (screenWidth - buttonTextures.get("Mercury").getWidth()) / 2, 775, 0.9f, "ViewLevelInfo"));
		buttonManager.add(new Button(buttonTextures.get("Venus"), (screenWidth - buttonTextures.get("Venus").getWidth()) / 2, 700, 0.9f, "ViewLevelInfo"));
		buttonManager.add(new Button(buttonTextures.get("Earth"), (screenWidth  - buttonTextures.get("Earth").getWidth()) / 2, 625, 0.9f, "ViewLevelInfo"));
		buttonManager.add(new Button(buttonTextures.get("Mars"), (screenWidth  - buttonTextures.get("Mars").getWidth()) / 2, 550, 0.9f, "ViewLevelInfo"));
		
		// buttons for additional levels go here
		
		buttonManager.add(new Button(buttonTextures.get("Back"), (screenWidth  - buttonTextures.get("Back").getWidth()) / 2, 125, 0.9f, "ReturnToMain"));

		// set button data (planet name)
		buttonManager.getButtonList().get(0).addData("planet", "Mercury");
		buttonManager.getButtonList().get(1).addData("planet", "Venus");
		buttonManager.getButtonList().get(2).addData("planet", "Earth");
		buttonManager.getButtonList().get(3).addData("planet", "Mars");

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
		
		batch.end();
		
		super.fadeIn(0.25f, deltaTime);

		Button clickedButton = buttonControlManager.handleClickEvents();
		
		if(clickedButton != null) {
			switch(clickedButton.getAction()) {
				case "ViewLevelInfo":
					// go to planet info screen
					simulation.showLevelInfo((String)clickedButton.getData().get("planet"));
					break;
				case "ReturnToMain":
					// go back to main menu
					simulation.returnToMainMenu();
			}
		}
	}
	
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

	}
	
	
}
