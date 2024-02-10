package com.mygdx.game.managers;

import com.badlogic.gdx.Input;

public class PlayerControlManager extends IOManager {

	private boolean checkKey;
	
	public PlayerControlManager(){
		super();
	}
	
//not done yet ~
	
	public void moveLeft() {
		System.out.println("Move Left");
	}
	
	public void moveRight() {
		System.out.println("Move Right");
	}
	
	public void checkKeyEvents() {
		if(super.pollLeftKey()) moveLeft();
		if(super.pollRightKey()) moveRight();

	}


}
