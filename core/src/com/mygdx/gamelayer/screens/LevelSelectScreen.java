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
import com.mygdx.gameengine.managers.OutputManager;
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

public class LevelSelectScreen extends Scene {	
	private SpriteBatch batch;
    private ShapeRenderer shape;
    private ButtonManager buttonManager;
    private ButtonControlManager buttonControlManager;
    private AppSimulation simulation;
    private float fadeInOverlayOpacity = 1.0f;
    
    private final String VIEW_LEVEL_INFO = "ViewLevelInfo";
    private final String RETURN_TO_MAIN = "ReturnToMain";

    private HashMap<String, Planet> planetHashmap;
	
	public LevelSelectScreen(HashMap<String, Texture> buttonTextures, HashMap<String, Planet> planetHashmap, Texture bgImage, Sound bgMusic, Mouse mouseDevice, AppSimulation simulation) {
		super(bgMusic,bgImage);
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		int screenWidth = Gdx.graphics.getWidth();
		
		this.simulation = simulation;
		
		//Instantiate ButtonManager
		buttonManager = new ButtonManager();
		
		// add buttons
		buttonManager.add(new Button(buttonTextures.get("Mercury"), 775, 0.2f, VIEW_LEVEL_INFO));
		buttonManager.add(new Button(buttonTextures.get("Venus"), 700, 0.2f, VIEW_LEVEL_INFO));
		buttonManager.add(new Button(buttonTextures.get("Earth"), 625, 0.2f, VIEW_LEVEL_INFO));
		buttonManager.add(new Button(buttonTextures.get("Mars"), 550, 0.2f, VIEW_LEVEL_INFO));
		buttonManager.add(new Button(buttonTextures.get("Jupiter"), 475, 0.2f, VIEW_LEVEL_INFO));
		buttonManager.add(new Button(buttonTextures.get("Saturn"), 400, 0.2f, VIEW_LEVEL_INFO));
		buttonManager.add(new Button(buttonTextures.get("Uranus"), 325, 0.2f, VIEW_LEVEL_INFO));
		buttonManager.add(new Button(buttonTextures.get("Neptune"), 250, 0.2f, VIEW_LEVEL_INFO));

		// buttons for additional levels go here
		
		buttonManager.add(new Button(buttonTextures.get("Back"), 125, 0.2f, RETURN_TO_MAIN));

		// set button data (planet name)
		buttonManager.getButtonList().get(0).addData("planet", "Mercury");
		buttonManager.getButtonList().get(1).addData("planet", "Venus");
		buttonManager.getButtonList().get(2).addData("planet", "Earth");
		buttonManager.getButtonList().get(3).addData("planet", "Mars");
		buttonManager.getButtonList().get(4).addData("planet", "Jupiter");
		buttonManager.getButtonList().get(5).addData("planet", "Saturn");
		buttonManager.getButtonList().get(6).addData("planet", "Uranus");
		buttonManager.getButtonList().get(7).addData("planet", "Neptune");

		buttonControlManager = new ButtonControlManager(buttonManager.getButtonList(), mouseDevice);
		this.planetHashmap = planetHashmap;
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
				case VIEW_LEVEL_INFO:
					// go to planet info screen
					simulation.showLevelInfo(planetHashmap.get((String)clickedButton.getData().get("planet")));
					break;
				case RETURN_TO_MAIN:
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
