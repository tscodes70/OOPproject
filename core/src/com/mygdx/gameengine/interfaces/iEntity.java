package com.mygdx.gameengine.interfaces;

public interface iEntity {

    float getPositionX();
    void setPositionX(float x);
    float getPositionY();
    void setPositionY(float y);
    void update(float deltatime);
    
    
    // Disposal
    void dispose();
}
