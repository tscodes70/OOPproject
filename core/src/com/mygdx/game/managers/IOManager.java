package com.mygdx.game.managers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.globals.Globals;
//import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.interfaces.iInputOutput;


public abstract class IOManager implements iInputOutput {
	
	private int kLeft = Globals.KEYBIND_LEFT;
	private int kRight = Globals.KEYBIND_RIGHT;
	private int kUp = Globals.KEYBIND_UP;
	private int kDown = Globals.KEYBIND_DOWN;
	private int kEsc = Globals.KEYBIND_ESCAPE;
	private int kEnter = Globals.KEYBIND_ENTER;
	private int kBackspace = Globals.KEYBIND_BACKSPACE;
	private int btnLeft = Globals.MOUSE_LEFT;
	private int btnRight = Globals.MOUSE_RIGHT;

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
	public boolean pollUpKey() {
		if(Gdx.input.isKeyPressed(kUp)) return true;
		else return false;
	}
	public boolean pollDownKey() {
		if(Gdx.input.isKeyPressed(kDown)) return true;
		else return false;
	}
	
	// For General Inputs
	public boolean pollEscapeKey() {
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
	
}
