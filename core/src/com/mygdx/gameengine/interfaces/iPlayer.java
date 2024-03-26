package com.mygdx.gameengine.interfaces;

public interface iPlayer extends iEntity{

    boolean isPlayable();
    void setPlayable(boolean playable);
    
    int getLeftKeybind();
    int getRightKeybind();
    int getUpKeybind();
    int getDownKeybind();
}
