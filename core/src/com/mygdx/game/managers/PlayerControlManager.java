package com.mygdx.game.managers;


public class PlayerControlManager extends IOManager {
	
	public PlayerControlManager(){
		super();
	}

	public void moveLeft() {
		System.out.println("Move Left");
	}
	
	public void moveRight() {
		System.out.println("Move Right");
	}
	
	@Override
	public void checkKeyEvents() {
		if(super.pollLeftKey()) moveLeft();
		if(super.pollRightKey()) moveRight();
	}
}
