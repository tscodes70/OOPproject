package com.mygdx.gamelayer.interfaces;

import com.mygdx.gameengine.interfaces.iMovable;

public interface iSpaceMovable extends iMovable{
	public void move(float deltaTime, float gravity);
}
