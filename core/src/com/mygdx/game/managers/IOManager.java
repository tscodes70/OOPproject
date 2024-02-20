package com.mygdx.game.managers;


import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.interfaces.iInputOutput;
import com.mygdx.game.interfaces.iManager;


public class IOManager<T extends iInputOutput> implements iManager<T> {
	
	private List<T> IOList;
	private List<T> updatedIOList;


	public IOManager() {
		IOList = new ArrayList<T>();
	}

	@Override
	public void add(T obj) {
		IOList.add(obj);
	}

	@Override
	public void remove(T obj) {
		IOList.remove(obj);
	}
	
	@Override
	public void update(List<T> IOList) {
		updatedIOList = new ArrayList<T>();
		for(T obj : IOList) updatedIOList.add(obj);
		this.IOList = updatedIOList;
	}


	
//	// For Player Inputs
//	public boolean pollLeftKey() {
//		if(Gdx.input.isKeyPressed(kLeft)) return true;
//		else return false;
//	}
//	public boolean pollRightKey() {
//		if(Gdx.input.isKeyPressed(kRight)) return true;
//		else return false;
//	}
//	public boolean pollUpKey() {
//		if(Gdx.input.isKeyPressed(kUp)) return true;
//		else return false;
//	}
//	public boolean pollDownKey() {
//		if(Gdx.input.isKeyPressed(kDown)) return true;
//		else return false;
//	}
//	
//	// For General Inputs
//	public boolean pollEscapeKey() {
//		if(Gdx.input.isKeyJustPressed(kEsc)) return true;
//		else return false;
//	}
//	public boolean pollEnterKey() {
//		if(Gdx.input.isKeyJustPressed(kEnter)) return true;
//		else return false;
//	}
//	public boolean pollBackspaceKey() {
//		if(Gdx.input.isKeyJustPressed(kBackspace)) return true;
//		else return false;
//	}
//	
//	// Mouse Inputs
//	public boolean pollLeftMouseButton() {
//		if(Gdx.input.isButtonJustPressed(btnLeft)) return true;
//		else return false;
//	}
//	public boolean pollRightMouseButton() {
//		if(Gdx.input.isButtonJustPressed(btnRight)) return true;
//		else return false;
//	}
//	public int getMouseX() {
//		return Gdx.input.getX();
//	}
//	public int getMouseY() {
//		return Gdx.input.getY();
//	}
//	
}
