package com.mygdx.gamelayer.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.mygdx.gameengine.interfaces.iCollidable;
import com.mygdx.gameengine.models.Entity;
import com.mygdx.gamelayer.interfaces.iStatic;

public class Planet extends Entity implements iCollidable,iStatic {
	
	private float currentHP;
	private float maxHP;
	private boolean collidable;
	private Rectangle boundingBox;

	public Planet(
			Texture texture,
			float positionX, 
			float positionY, 
			float width, 
			float height,
			boolean collidable) {
		super(texture,positionX, positionY, width, height);
		this.boundingBox = new Rectangle(positionX, positionY, width, height);
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
		return collidable;
	}

	@Override
	public void setCollidable(boolean collidable) {
		// TODO Auto-generated method stub
		this.collidable = collidable;
	}

	@Override
	public Rectangle getBoundingBox() {
		// TODO Auto-generated method stub
		return boundingBox;
	}
	
	@Override
	public void setBoundingBox(Rectangle boundingBox) {
		// TODO Auto-generated method stub
		this.boundingBox = boundingBox;
	}

	@Override
	public void update(float deltatime) {
		// TODO Auto-generated method stub
		
	}

	
}
