package com.mygdx.game.managers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
//import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.interfaces.iInputOutput;


public class IOManager implements iInputOutput {
	
	private int kLeft = Keys.LEFT;
	private int kRight = Keys.RIGHT;
	private int kEsc = Keys.ESCAPE;
	private int kEnter = Keys.ENTER;
	
	public IOManager() {
	}
	
	public boolean pollLeftKey() {
		if(Gdx.input.isKeyPressed(kLeft)) return true;
		else return false;
	}
	
	public boolean pollRightKey() {
		if(Gdx.input.isKeyPressed(kRight)) return true;
		else return false;
	}
	
	public boolean pollPauseKey() {
		if(Gdx.input.isKeyPressed(kEsc)) return true;
		else return false;
	}

	public boolean pollEnterKey() {
		if(Gdx.input.isKeyPressed(Keys.ENTER)) return true;
		else return false;
	}


	
}
