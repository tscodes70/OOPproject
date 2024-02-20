package com.mygdx.game.interfaces;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.models.Entity;

public interface iCollidable {
	
	public Rectangle getBoundingBox();
//    public boolean collidesWith(Entity other);
	public boolean isCollidable();
}	
