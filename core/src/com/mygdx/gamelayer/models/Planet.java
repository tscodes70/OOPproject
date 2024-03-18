package com.mygdx.gamelayer.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.gameengine.interfaces.iCollidable;
import com.mygdx.gameengine.interfaces.iEntity;
import com.mygdx.gameengine.interfaces.iMovable;
import com.mygdx.gameengine.models.Entity;

public class Planet extends Entity implements iCollidable {
	
	private float currentHP;
	private float maxHP;
	private boolean collidable;
	private Rectangle boundingBox;

	public Planet(
			float positionX, 
			float positionY, 
			float radius, 
			Color colour,
			boolean collidable) {
		super(positionX, positionY, radius, colour);
		this.boundingBox = new Rectangle(positionX, positionY, radius * 2, radius * 2);
		this.collidable = collidable;
	}

// Getter Setter
	public float getCurrentHP() { return currentHP; }
	public void setCurrentHP(float currentHP) { this.currentHP = currentHP; }
	public float getMaxHP() { return maxHP; }
	public void setMaxHP(float maxHP) { this.maxHP = maxHP; }

// Interface Overrides
	@Override
	public boolean isCollidable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCollidable(boolean collidable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBoundingBox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBoundingBox(Rectangle boundingBox) {
		// TODO Auto-generated method stub
		
	}
	
}
