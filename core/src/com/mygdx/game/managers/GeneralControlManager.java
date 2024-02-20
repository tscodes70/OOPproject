package com.mygdx.game.managers;

/**
 * Only a dummy class created to organize between
 *  PlayerControlManager and General Game Engine Controls
 */
public class GeneralControlManager extends IOManager {
	
	public GeneralControlManager(){
		super();
	}

	// All functions are only used for debugging purposes
	
	public void escape() {
		System.out.println("Esc key pressed");
	}

	public void enter() {
		System.out.println("Enter key pressed");
	}
	
	public void backspace() {
		System.out.println("backspace pressde");
	}
	public void leftClick() {
		System.out.println("left click at " + super.getMouseX() + ", " + super.getMouseY());
	}
	public void rightClick() {
		System.out.println("right click at " + super.getMouseX() + ", " + super.getMouseY());
	}
	
	// For console printing purposes
	public void checkKeyEvents() {
		if(super.pollEscapeKey()) escape();
		if(super.pollEnterKey()) enter();
		if(super.pollBackspaceKey()) backspace();
	}
	public void checkClickEvents(EntityManager entityManager, AIControlManager aiControlManager, PlayerControlManager playerControlManager, CollisionManager collisonManager) {
		if(super.pollLeftMouseButton()) leftClick();
		if(super.pollRightMouseButton()) rightClick();
	}
}
