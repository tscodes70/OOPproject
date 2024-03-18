package com.mygdx.gameengine.interfaces;

public interface iMovable {
    int getSpeedMultiplier();
    void setSpeedMultiplier(int speedMultiplier);
	public void move(float deltaTime);
}
