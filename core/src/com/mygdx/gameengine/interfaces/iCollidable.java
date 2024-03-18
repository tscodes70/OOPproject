package com.mygdx.gameengine.interfaces;

import com.badlogic.gdx.math.Rectangle;

public interface iCollidable {

    boolean isCollidable();
    void setCollidable(boolean collidable);
    Rectangle getBoundingBox();
    void setBoundingBox(Rectangle boundingBox);
    
}	
