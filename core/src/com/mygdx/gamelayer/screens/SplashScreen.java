package com.mygdx.gamelayer.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gameengine.models.Scene;
import com.mygdx.gameengine.models.Sound;

public class SplashScreen extends Scene {
	public SplashScreen(Sound bgMusic, Texture bgImage) {
		super(bgMusic,bgImage);
		this.batch = new SpriteBatch();
	}
	
}
