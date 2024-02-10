package com.mygdx.game.managers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
//import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.interfaces.iInputOutput;


public class IOManager implements iInputOutput {
	
	public IOManager() {
	}
	
	public boolean pollLeftKey() {
		if(Gdx.input.isKeyPressed(Keys.LEFT)) return true;
		else return false;
	}
	
	public boolean pollRightKey() {
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) return true;
		else return false;
	}
	
	public boolean pollPauseKey() {
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) return true;
		else return false;
	}


	
}
