package com.mygdx.game.interfaces;

public interface iCollidable<T, U> {
	
	public void checkCollisions(T manager);
	public void handleCollisions(T manager, U obj1, U obj2);
}	
