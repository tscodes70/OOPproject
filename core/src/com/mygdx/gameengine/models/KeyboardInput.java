package com.mygdx.gameengine.models;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.mygdx.gameengine.interfaces.iInputOutput;

public class KeyboardInput implements iInputOutput {

	@Override
	public boolean pollInputHold(int keyCode) {
		return Gdx.input.isKeyPressed(keyCode);
	}
	@Override
	public boolean pollInputPress(int keyCode) {
		return Gdx.input.isKeyJustPressed(keyCode);
	}

}