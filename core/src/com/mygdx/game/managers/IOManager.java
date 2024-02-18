package com.mygdx.game.managers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
//import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.interfaces.iInputOutput;


public abstract class IOManager implements iInputOutput {
	
	private int kLeft = Keys.LEFT;
	private int kRight = Keys.RIGHT;
	private int kEsc = Keys.ESCAPE;
	private int kEnter = Keys.ENTER;
	private int kBackspace = Keys.BACKSPACE;
	
	public IOManager() {
	}
	
	// For Player Inputs
	public boolean pollLeftKey() {
		if(Gdx.input.isKeyPressed(kLeft)) return true;
		else return false;
	}
	public boolean pollRightKey() {
		if(Gdx.input.isKeyPressed(kRight)) return true;
		else return false;
	}
	
	// For General Inputs
	public boolean pollPauseKey() {
		if(Gdx.input.isKeyPressed(kEsc)) return true;
		else return false;
	}
	public boolean pollEnterKey() {
		if(Gdx.input.isKeyPressed(kEnter)) return true;
		else return false;
	}
	public boolean pollBackspaceKey() {
		if(Gdx.input.isKeyPressed(kBackspace)) return true;
		else return false;
	}
	
	public abstract void checkKeyEvents();


	
}
