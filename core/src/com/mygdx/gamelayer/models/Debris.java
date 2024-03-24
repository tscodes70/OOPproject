package com.mygdx.gamelayer.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.mygdx.gameengine.interfaces.iAI;
import com.mygdx.gameengine.interfaces.iCollidable;
import com.mygdx.gameengine.interfaces.iMovable;
import com.mygdx.gameengine.models.Entity;
import com.mygdx.gamelayer.interfaces.iDebris;

public class Debris extends Entity implements iDebris{
	private float currentHP;
	private float maxHP;
	private boolean aiControl;
	private boolean collidable;
	private Rectangle boundingBox;
	private int speedMultiplier;
	
	private final int DEFAULT_ENTITY_SPEED_MULTIPLIER = 100;

	public Debris(
			float positionX, 
			float positionY, 
			int speedMultiplier, 
			float radius, 
			Color colour, 
			boolean aiControl, 
			boolean collidable) {
		super(positionX, positionY, radius, colour);
		this.boundingBox = new Rectangle(positionX-radius, positionY-radius, radius * 2, radius * 2);
		this.speedMultiplier = speedMultiplier;
		this.aiControl = aiControl;
		this.collidable = collidable;
	}
	
	@Override
    public void update(float deltaTime) {
    	// Update position based on speed and direction
		move(deltaTime);

        // Update bounding box position
        boundingBox.setPosition(super.getPositionX()-super.getRadius(), super.getPositionY()-super.getRadius());
    }
	
	public void move(float deltaTime) {
        // Move entity vertically proportionally to deltaTime and speed
        float distanceToMove = speedMultiplier * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime;
        super.setPositionY(super.getPositionY() - distanceToMove);
	}

// Getter Setter
	public float getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(float currentHP) {
		this.currentHP = currentHP;
	}

	public float getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(float maxHP) {
		this.maxHP = maxHP;
	}
	
	
// Interface Overrides
	@Override
	public boolean isAIControl() {
		return aiControl;
	}

	@Override
	public void setAIControl(boolean aiControl) {
		this.aiControl = aiControl;
	}

	@Override
	public boolean isCollidable() {
		return collidable;
	}

	@Override
	public void setCollidable(boolean collidable) {
		this.collidable = collidable;	
	}


	public Rectangle getBoundingBox() {
		return boundingBox;
	}


	public void setBoundingBox(Rectangle boundingBox) {
		this.boundingBox = boundingBox;
	}

	@Override
	public int getSpeedMultiplier() {
		return speedMultiplier;
	}

	@Override
	public void setSpeedMultiplier(int speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
		
	}


}
