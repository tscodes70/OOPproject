package com.mygdx.game.models;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.interfaces.iInputOutput;

public class MouseInput implements iInputOutput{

	private List<Integer> buttonCodeList;
	
	public MouseInput() {
		buttonCodeList = new ArrayList<Integer>();
	}
	
	public void add(int buttonCode) {
		buttonCodeList.add(buttonCode);
	}
	
	public void remove(int buttonCode) {
		buttonCodeList.remove(buttonCode);
	}
	
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
