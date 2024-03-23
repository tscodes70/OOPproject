package com.mygdx.gamelayer.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.mygdx.gameengine.interfaces.iCollidable;
import com.mygdx.gameengine.interfaces.iPlayer;
import com.mygdx.gameengine.models.Entity;

public class Player extends Entity implements iCollidable, iPlayer {
	
	private StatsBar healthBar;
	private StatsBar staminaBar;
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
		this.boundingBox = new Rectangle(positionX-radius, positionY-radius, radius * 2, radius * 2);
		this.speedMultiplier = speedMultiplier;
		
		this.leftKeybind = leftKeybind;
		this.rightKeybind = rightKeybind;
		this.upKeybind = upKeybind;
		this.downKeybind = downKeybind;
		
		// HP and Stamina
		this.healthBar = new StatsBar(positionX-45, positionY-60, Color.GREEN, Color.RED, 100, 10, 30, 100);
		this.staminaBar = new StatsBar(positionX-45, positionY-70, Color.YELLOW, Color.GRAY, 100, 10, 50, 100);

	}
	
	public Player(
			float positionX, 
			float positionY, 
			int speedMultiplier, 
			float width,
			float height,
			Texture texture, 
			boolean playable, 
			boolean collidable,
			
			int leftKeybind,
			int rightKeybind,
			int upKeybind,
			int downKeybind) {
		
		super(texture, positionX, positionY, width, height);

		this.playable = playable;
		this.collidable = collidable;
		this.boundingBox = new Rectangle(positionX, positionY, width, height);
		this.speedMultiplier = speedMultiplier;
		
		this.leftKeybind = leftKeybind;
		this.rightKeybind = rightKeybind;
		this.upKeybind = upKeybind;
		this.downKeybind = downKeybind;
		
		// HP and Stamina
		this.healthBar = new StatsBar(positionX, positionY, Color.GREEN, Color.RED, 80, 5, 30, 100);
		this.staminaBar = new StatsBar(positionX, positionY-5, Color.YELLOW, Color.GRAY, 80, 5, 50, 100);

	}
	
    public void update(float deltaTime) {
        // Update bounding box position
        boundingBox.setPosition(super.getPositionX()-super.getRadius(), super.getPositionY()-super.getRadius());
    
        // Update stats bar position
        healthBar.setPositionX(super.getPositionX());
        healthBar.setPositionY(super.getPositionY());
        staminaBar.setPositionX(super.getPositionX());
        staminaBar.setPositionY(super.getPositionY()-5);
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
		        if (super.getPositionY() - distanceToMove - super.getRadius() >= 200) {
		        	super.setPositionY(super.getPositionY() - distanceToMove);
		        	update(deltaTime);		        }
		    }
		
    
    

// Getter Setter
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
	
	
public StatsBar getHealthBar() {
		return healthBar;
	}

	public void setHealthBar(StatsBar healthBar) {
		this.healthBar = healthBar;
	}

	public StatsBar getStaminaBar() {
		return staminaBar;
	}

	public void setStaminaBar(StatsBar staminaBar) {
		this.staminaBar = staminaBar;
	}

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
