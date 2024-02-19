package com.mygdx.game.managers;

public class GeneralControlManager extends IOManager {
	public GeneralControlManager(){
		super();
	}

	public void pause() {
		System.out.println("pause");
	}

	public void enter() {
		System.out.println("continue/start");
	}
	
	public void backspace() {
		System.out.println("end game");
	}
	public void leftClick() {
		System.out.println("left click at " + super.getMouseX() + ", " + super.getMouseY());
	}
	public void rightClick() {
		System.out.println("right click at " + super.getMouseX() + ", " + super.getMouseY());
	}
	@Override
	public void checkKeyEvents() {
		if(super.pollPauseKey()) pause();
		if(super.pollEnterKey()) enter();
		if(super.pollBackspaceKey()) backspace();
	}
	public void checkClickEvents() {
		if(super.pollLeftMouseButton()) leftClick();
		if(super.pollRightMouseButton()) rightClick();
	}
}
