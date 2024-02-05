package com.mygdx.game.managers;

import com.mygdx.game.interfaces.iInputOutput;

public abstract class IOManager implements iInputOutput {
	private float horizontalInput,verticalInput;
	
	
	public IOManager() {
		
	}
	
	public abstract boolean keyUp(int keycode);
	public abstract boolean keyDown(int keycode);
	
	
	public float getHorizontalInput() {
		return horizontalInput;
	}
	public void setHorizontalInput(float horizontalInput) {
		horizontalInput = this.horizontalInput;
	}
	public float getVerticalInput() {
		return verticalInput;
	}
	public void setVerticalInput(float verticalInput) {
		verticalInput = this.verticalInput;
	}
	
}
