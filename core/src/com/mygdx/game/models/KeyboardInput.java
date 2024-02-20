package com.mygdx.game.models;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.interfaces.iInputOutput;

public class KeyboardInput implements iInputOutput {

	private List<Integer> keyCodeList;
	
	public KeyboardInput() {
		keyCodeList = new ArrayList<Integer>();
	}
	
	public void add(int keyCode) {
		keyCodeList.add(keyCode);
	}
	
	public void remove(int keyCode) {
		keyCodeList.remove(keyCode);
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
