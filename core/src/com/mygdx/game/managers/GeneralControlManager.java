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
	
	@Override
	public void checkKeyEvents() {
		if(super.pollPauseKey()) pause();
		if(super.pollEnterKey()) enter();
		if(super.pollBackspaceKey()) enter();
	}
}
