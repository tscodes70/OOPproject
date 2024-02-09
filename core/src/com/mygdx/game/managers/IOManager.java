package com.mygdx.game.managers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;


public abstract class IOManager implements InputProcessor {

	public IOManager() {
		Gdx.input.setInputProcessor(this);
	}
	
	public abstract boolean keyUp(int keycode);
	public abstract boolean keyDown(int keycode);
	
	public void loadAssets() {
		
	}

	
}
