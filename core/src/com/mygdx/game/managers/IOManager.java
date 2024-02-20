package com.mygdx.game.managers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
//import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.interfaces.iInputOutput;


public abstract class IOManager implements iInputOutput {
	
	private int kLeft = Keys.LEFT;
	private int kRight = Keys.RIGHT;
	private int kEsc = Keys.ESCAPE;
	private int kEnter = Keys.ENTER;
	private int kBackspace = Keys.BACKSPACE;
	private int btnLeft = Buttons.LEFT;
	private int btnRight = Buttons.RIGHT;

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
		if(Gdx.input.isKeyJustPressed(kEsc)) return true;
		else return false;
	}
	public boolean pollEnterKey() {
		if(Gdx.input.isKeyJustPressed(kEnter)) return true;
		else return false;
	}
	public boolean pollBackspaceKey() {
		if(Gdx.input.isKeyJustPressed(kBackspace)) return true;
		else return false;
	}
	public boolean pollLeftMouseButton() {
		if(Gdx.input.isButtonJustPressed(btnLeft)) return true;
		else return false;
	}
	public boolean pollRightMouseButton() {
		if(Gdx.input.isButtonJustPressed(btnRight)) return true;
		else return false;
	}
	public int getMouseX() {
		return Gdx.input.getX();
	}
	public int getMouseY() {
		return Gdx.input.getY();
	}
	public abstract void checkKeyEvents();
	public abstract void checkClickEvents();
}
