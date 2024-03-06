package com.mygdx.gameengine.models;

import com.badlogic.gdx.Gdx;
import com.mygdx.gameengine.interfaces.iInputOutput;

public class MouseInput implements iInputOutput{
	
	public float getMouseX() { return (float)Gdx.input.getX(); }

	public float getMouseY() { return (float)Gdx.input.getY(); }
	
	@Override
	public boolean pollInputHold(int inputCode) {
		return Gdx.input.isButtonPressed(inputCode);
	}

	@Override
	public boolean pollInputPress(int inputCode) {
		return Gdx.input.isButtonJustPressed(inputCode);
	}

}
