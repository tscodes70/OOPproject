package com.mygdx.game.managers;

import com.badlogic.gdx.Input;

public class PlayerControlManager extends IOManager {

	private boolean checkKey;
	
	public PlayerControlManager(){
		super();
	}


	
	public void moveLeft() {
		System.out.println("Move Left");
	}
	
	public void moveRight() {
		System.out.println("Move Right");
	}

	//public void pause() {
		//System.out.println("pause");
	//}

	//public void enter() {
		//System.out.println("continue/start");
	//}
	
	public void checkKeyEvents() {
		if(super.pollLeftKey()) moveLeft();
		if(super.pollRightKey()) moveRight();
		//if(super.pollPauseKey()) pause();
		//if(super.pollPauseKey()) enter();
	}


}
