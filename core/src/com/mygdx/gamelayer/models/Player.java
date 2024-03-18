package com.mygdx.gamelayer.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.gameengine.interfaces.iCollidable;
import com.mygdx.gameengine.interfaces.iPlayer;
import com.mygdx.gameengine.models.Entity;

public class Player extends Entity implements iCollidable, iPlayer {
	
	private float currentHP;
	private float maxHP;
	private float currentStamina;
	private float maxStamina;
	private float staminaRegenRate;
	private boolean playable;
	private boolean collidable;
	private Rectangle boundingBox;
	private int speedMultiplier;
	
	private int leftKeybind;
	private int rightKeybind;
	private int upKeybind;
	private int downKeybind;
	
	private final int DEFAULT_ENTITY_SPEED_MULTIPLIER = 100;



	public Player(
			float positionX, 
			float positionY, 
			int speedMultiplier, 
			float radius, 
			Color colour, 
			boolean playable, 
			boolean collidable,
			
			int leftKeybind,
			int rightKeybind,
			int upKeybind,
			int downKeybind) {
		
		super(positionX, positionY, radius, colour);

		this.playable = playable;
		this.collidable = collidable;
		this.boundingBox = new Rectangle(positionX, positionY, radius * 2, radius * 2);
		this.speedMultiplier = speedMultiplier;
		
		this.leftKeybind = leftKeybind;
		this.rightKeybind = rightKeybind;
		this.upKeybind = upKeybind;
		this.downKeybind = downKeybind;
	}
	
    public void update(float deltaTime) {
        // Update bounding box position
        boundingBox.setPosition(super.getPositionX(), super.getPositionY());
    }
    

	 /**
	  * Moves Player towards the left by the set default speed
	  * @param deltaTime
	  */
	 public void moveLeft(float deltaTime) {
		        float distanceToMove = speedMultiplier * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime;

		        // Ensure entity is within left boundary of screen
		        if (super.getPositionX() - distanceToMove - super.getRadius() >= 0) {
		        	super.setPositionX(super.getPositionX() - distanceToMove);
		        	update(deltaTime);
		        }
		    }
		

	 /**
	  * Moves Player towards the right by the set default speed
	  * @param deltaTime
	  */
	 public void moveRight(float deltaTime) {
		        float distanceToMove = speedMultiplier * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime;

		        // Ensure entity is within right boundary of screen
		        if (super.getPositionX() + distanceToMove + super.getRadius() <= Gdx.graphics.getWidth()) {
		        	super.setPositionX(super.getPositionX() + distanceToMove);
		        	update(deltaTime);		        
		            }
		    }
		

	 /**
	  * Moves Player towards the top by the set default speed
	  * @param deltaTime
	  */
	 public void moveUp(float deltaTime) {
		        float distanceToMove = speedMultiplier * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime;

		        // Ensure entity is within top boundary of screen
		        if (super.getPositionY() + distanceToMove + super.getRadius() <= Gdx.graphics.getHeight()) {  // Adjusted boundary check
		        	super.setPositionY(super.getPositionY() + distanceToMove);
		        	update(deltaTime);		       
		            }
		    }
		

	 /**
	  * Moves Player towards the bottom by the set default speed
	  * @param deltaTime
	  */
	 public void moveDown(float deltaTime) {
		        float distanceToMove = speedMultiplier * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime;

		        // Ensure entity is within bottom boundary of screen
		        if (super.getPositionY() - distanceToMove - super.getRadius() >= 0) {
		        	super.setPositionY(super.getPositionY() - distanceToMove);
		        	update(deltaTime);		        }
		    }
		
    
    

// Getter Setter
	public float getCurrentHP() { return currentHP; }
	public void setCurrentHP(float currentHP) { this.currentHP = currentHP; }
	public float getMaxHP() { return maxHP; }
	public void setMaxHP(float maxHP) { this.maxHP = maxHP;}
	public float getCurrentStamina() { return currentStamina; }
	public void setCurrentStamina(float currentStamina) { this.currentStamina = currentStamina; }
	public float getMaxStamina() { return maxStamina; }
	public void setMaxStamina(float maxStamina) { this.maxStamina = maxStamina; }
	public float getStaminaRegenRate() { return staminaRegenRate; }
	public void setStaminaRegenRate(float staminaRegenRate) { this.staminaRegenRate = staminaRegenRate; }
	public int getLeftKeybind() {return leftKeybind;}
	public void setLeftKeybind(int leftKeybind) {this.leftKeybind = leftKeybind;}
	public int getRightKeybind() {return rightKeybind;}
	public void setRightKeybind(int rightKeybind) {this.rightKeybind = rightKeybind;}
	public int getUpKeybind() {return upKeybind;}
	public void setUpKeybind(int upKeybind) {this.upKeybind = upKeybind;}
	public int getDownKeybind() {return downKeybind;}
	public void setDownKeybind(int downKeybind) {this.downKeybind = downKeybind;}
	
// my brain thinking how to incorporate imovable
	public int getSpeedMultiplier() {
		return speedMultiplier;
	}

	public void setSpeedMultiplier(int speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
		
	}
	
// Interfaces Overrides
	
	@Override
	public boolean isPlayable() {
		return playable;
	}

	@Override
	public void setPlayable(boolean playable) {
		this.playable = playable;
	}

	@Override
	public boolean isCollidable() {
		return collidable;
	}

	@Override
	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}

	@Override
	public Rectangle getBoundingBox() {
		return boundingBox;
	}

	@Override
	public void setBoundingBox(Rectangle boundingBox) {
		this.boundingBox = boundingBox;
	}



	
	

}
