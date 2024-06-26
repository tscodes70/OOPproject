package com.mygdx.gamelayer.interfaces;

import com.mygdx.gameengine.interfaces.iCollidable;
import com.mygdx.gameengine.interfaces.iPlayer;

public interface iSpacePlayer extends iPlayer, iSpaceCollidable {
    
    void moveLeft(float deltaTime, float gravity, boolean boost);
    void moveRight(float deltaTime, float gravity, boolean boost);
    void moveUp(float deltaTime, float gravity, boolean boost);
    void moveDown(float deltaTime, float gravity, boolean boost);
    void playerGravity(float deltaTime, float gravity);
}
