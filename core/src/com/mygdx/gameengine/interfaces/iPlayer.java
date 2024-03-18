package com.mygdx.gameengine.interfaces;

public interface iPlayer extends iEntity{

    boolean isPlayable();
    void setPlayable(boolean playable);
    
    void moveLeft(float deltaTime);
    void moveRight(float deltaTime);
    void moveUp(float deltaTime);
    void moveDown(float deltaTime);
    
    int getLeftKeybind();
    int getRightKeybind();
    int getUpKeybind();
    int getDownKeybind();
}
