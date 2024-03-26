package com.mygdx.gameengine.interfaces;

public interface iPlayer {

    boolean isPlayable();
    void setPlayable(boolean playable);
    
    int getLeftKeybind();
    int getRightKeybind();
    int getUpKeybind();
    int getDownKeybind();
}
