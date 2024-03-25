package com.mygdx.gamelayer.models;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.mygdx.gameengine.interfaces.iAI;
import com.mygdx.gameengine.interfaces.iCollidable;
import com.mygdx.gameengine.interfaces.iMovable;
import com.mygdx.gameengine.models.Entity;
import com.mygdx.gamelayer.interfaces.iDebris;

import java.util.Random;

public class Debris extends Entity implements iDebris{
	private float currentHP;
	private float maxHP;
	private boolean aiControl;
	private boolean collidable;
	private Rectangle boundingBox;
	private int speedMultiplier;
	private SpaceTexture texture;
	
	private final int DEFAULT_ENTITY_SPEED_MULTIPLIER = 100;
	
	private Random random = new Random(); // Create a Random object to generate random numbers

	private float swing = 300;
	private int horizontalDirection = 1;
	private float horizontalSpeed = 0.5f;
	private float screenWidth = Gdx.graphics.getWidth();

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
		this.horizontalDirection = random.nextBoolean() ? 1 : -1;
	}
	
	public Debris(
			Texture texture,
			float width, 
			float height, 
			float positionX, 
			float positionY, 

			int speedMultiplier, 
			
			boolean aiControl, 
			boolean collidable) {
		super(texture, positionX, positionY, width, height);
		this.boundingBox = new Rectangle(positionX, positionY, width, height);
		this.speedMultiplier = speedMultiplier;
		this.aiControl = aiControl;
		this.collidable = collidable;
		this.horizontalDirection = random.nextBoolean() ? 1 : -1;
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
//        float distanceToMove = speedMultiplier * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime;
//        super.setPositionY(super.getPositionY() - distanceToMove);
		float verticalDistance = speedMultiplier * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime;
		super.setPositionY(super.getPositionY() - verticalDistance);
		
		// Calculate new position based on current direction and speed
		float horizontalDistance = horizontalSpeed * deltaTime * horizontalDirection * swing;
		float newPositionX = super.getPositionX() + horizontalDistance;
		
	    // Boundary checks to reverse direction at screen edges
		if (newPositionX < 0 || newPositionX > screenWidth) {
			horizontalDirection *= -1; // Reverse direction
			
			// Adjust newPositionX to ensure it's within bounds, preventing overshoot
			if (newPositionX < 0) newPositionX = 0;
			if (newPositionX > screenWidth) newPositionX = screenWidth;
		}
		
		// Update position
		super.setPositionX(newPositionX);
	}
	
	@Override
	public void dispose() {
		
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