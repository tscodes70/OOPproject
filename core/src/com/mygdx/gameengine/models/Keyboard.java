package com.mygdx.gameengine.models;

import com.badlogic.gdx.Gdx;
import com.mygdx.gameengine.interfaces.iInput;

public class Keyboard implements iInput {
	
	public Keyboard() {
		
	}
	@Override
	public boolean pollInputHold(int keyCode) {
		return Gdx.input.isKeyPressed(keyCode);
	}
	@Override
	public boolean pollInputPress(int keyCode) {
		return Gdx.input.isKeyJustPressed(keyCode);
	}

}
