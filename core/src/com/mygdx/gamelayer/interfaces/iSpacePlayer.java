package com.mygdx.gamelayer.interfaces;

import com.mygdx.gameengine.interfaces.iCollidable;
import com.mygdx.gameengine.interfaces.iPlayer;

public interface iSpacePlayer extends iPlayer, iCollidable {
    
    void moveLeft(float deltaTime, float gravity);
    void moveRight(float deltaTime, float gravity);
    void moveUp(float deltaTime, float gravity);
    void moveDown(float deltaTime, float gravity);
    void playerGravity(float deltaTime, float gravity);
}
