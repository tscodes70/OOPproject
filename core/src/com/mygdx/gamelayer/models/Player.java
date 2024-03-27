package com.mygdx.gamelayer.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.mygdx.gameengine.interfaces.iAI;
import com.mygdx.gameengine.interfaces.iCollidable;
import com.mygdx.gameengine.interfaces.iPlayer;
import com.mygdx.gameengine.managers.PlayerControlManager;
import com.mygdx.gameengine.models.Entity;
import com.mygdx.gamelayer.interfaces.iDebris;
import com.mygdx.gamelayer.interfaces.iPlanet;
import com.mygdx.gamelayer.interfaces.iProjectile;
import com.mygdx.gamelayer.interfaces.iSpacePlayer;
import com.mygdx.gamelayer.managers.SpaceAIControlManager;
import com.mygdx.gamelayer.managers.SpaceEntityManager;

public class Player extends Entity implements iSpacePlayer {
	
	private int id;
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
	private int shiftKeybind;
	
	
	private final float DEFAULT_PLAYER_STAMINA_DRAIN_RATE = 1;
	private final int DEFAULT_ENTITY_SPEED_MULTIPLIER = 100;



	public Player(
			int id,
			float positionX, 
			float positionY, 
			int speedMultiplier, 
			float radius, 
			Color colour, 
			boolean playable, 
			boolean collidable) {
		
		super(positionX, positionY, radius, colour);
		
		this.id = id;
		this.playable = playable;
		this.collidable = collidable;
		this.boundingBox = new Rectangle(positionX-radius, positionY-radius, radius * 2, radius * 2);
		this.speedMultiplier = speedMultiplier;
		
		// HP and Stamina
		this.healthBar = new StatsBar(positionX-45, positionY-60, Color.GREEN, Color.RED, 100, 10, 30, 100);
		this.staminaBar = new StatsBar(positionX-45, positionY-70, Color.YELLOW, Color.GRAY, 100, 10, 50, 100);

	}
	
	public Player(
			int id,
			Texture texture,
			float width,
			float height,
			float positionX, 
			float positionY, 
			
			int speedMultiplier,
			float staminaRegenRate,

			boolean playable, 
			boolean collidable,
			
			int leftKeybind,
			int rightKeybind,
			int upKeybind,
			int downKeybind,
			int shiftKeybind) {
		
		super(texture, positionX, positionY, width, height);

		this.id = id;
		this.playable = playable;
		this.collidable = collidable;
		this.boundingBox = new Rectangle(positionX, positionY, width, height);
		this.speedMultiplier = speedMultiplier;
		this.staminaRegenRate = staminaRegenRate;
		
		this.leftKeybind = leftKeybind;
		this.rightKeybind = rightKeybind;
		this.upKeybind = upKeybind;
		this.downKeybind = downKeybind;
		this.shiftKeybind = shiftKeybind;

		// HP and Stamina
		this.healthBar = new StatsBar(positionX, positionY, Color.GREEN, Color.RED, 80, 5, 100, 100);
		this.staminaBar = new StatsBar(positionX, positionY-5, Color.YELLOW, Color.GRAY, 80, 5, 100, 100);

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
    
    public void regenStaminaBar(float deltaTime) {
        // Regen stamina bar
		if (staminaBar.getCurrentValue() < staminaBar.getMaxValue()) {
				staminaBar.setCurrentValue(staminaBar.getCurrentValue() + staminaRegenRate);
		}
    }
    
    public void playerGravity(float deltaTime, float gravity) {//TESTESTESTESTESTESTEST, might not need accel and decel here
		// Check if the up key is pressed
		if (Gdx.input.isKeyPressed(upKeybind)) {
			if (Gdx.input.isKeyPressed(shiftKeybind)) moveUp(deltaTime, gravity, true);
			else moveUp(deltaTime, gravity, false);

		} else {
			// Move the player down based on gravity
			 float distanceToDrop = speedMultiplier * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime * gravity;
			// Apply Gravity
			if((super.getPositionY() - distanceToDrop) <= 200) super.setPositionY(200);
			else super.setPositionY(super.getPositionY() - distanceToDrop);
			
		}
		
		update(deltaTime);
	}

	 /**
	  * Moves Player towards the left by the set default speed
	  * @param deltaTime
	  */
	 public void moveLeft(float deltaTime, float gravity, boolean boost) {
		 float distanceToMove = (speedMultiplier * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime) / gravity;
		 float currentStamina = staminaBar.getCurrentValue();
		 
		 System.out.print(boost);
		 if (boost) {
			 if (currentStamina > 0 && currentStamina <= staminaBar.getMaxValue()) {
				 distanceToMove = ((speedMultiplier + 5) * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime) / gravity;
				 currentStamina -= DEFAULT_PLAYER_STAMINA_DRAIN_RATE;
				 staminaBar.setCurrentValue(currentStamina);
			 }
		 }

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
	 public void moveRight(float deltaTime, float gravity, boolean boost) {
		 float distanceToMove = (speedMultiplier * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime) / gravity;
		 float currentStamina = staminaBar.getCurrentValue();

		 if (boost) {
			 if (currentStamina > 0 && currentStamina <= staminaBar.getMaxValue()) {
				 distanceToMove = ((speedMultiplier + 5) * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime) / gravity;
				 currentStamina -= DEFAULT_PLAYER_STAMINA_DRAIN_RATE;
				 staminaBar.setCurrentValue(currentStamina);
			 }
		 }

		 // Ensure entity is within right boundary of screen
		 if (super.getPositionX() + distanceToMove + super.getRadius() <= Gdx.graphics.getWidth() - super.getWidth()) {
			 super.setPositionX(super.getPositionX() + distanceToMove);
			 update(deltaTime);
		 }
	 }
		

	 /**
	  * Moves Player towards the top by the set default speed
	  * @param deltaTime
	  */
	 public void moveUp(float deltaTime, float gravity, boolean boost) {
		 float distanceToMove = (speedMultiplier * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime) / gravity;
		 float currentStamina = staminaBar.getCurrentValue();

		 if (boost) {
			 if (currentStamina > 0 && currentStamina <= staminaBar.getMaxValue()) {
				 distanceToMove = ((speedMultiplier + 5) * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime) / gravity;
				 currentStamina -= DEFAULT_PLAYER_STAMINA_DRAIN_RATE;
				 staminaBar.setCurrentValue(currentStamina);
			 }
		 
		 }

		 // Ensure entity is within top boundary of screen
		 if (super.getPositionY() + distanceToMove + super.getRadius() <= Gdx.graphics.getHeight() - getBoundingBox().height) {  // Adjusted boundary check
			 super.setPositionY(super.getPositionY() + distanceToMove);
			 update(deltaTime);
		 }
	 }
		

	 /**
	  * Moves Player towards the bottom by the set default speed
	  * @param deltaTime
	  */
	 public void moveDown(float deltaTime, float gravity, boolean boost) {
		 float distanceToMove = (speedMultiplier * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime) / gravity;
		 float currentStamina = staminaBar.getCurrentValue();

		 if (boost) {
			 if (currentStamina > 0 && currentStamina <= staminaBar.getMaxValue()) {
				 distanceToMove = ((speedMultiplier + 5) * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime) / gravity;
				 currentStamina -= DEFAULT_PLAYER_STAMINA_DRAIN_RATE;
				 staminaBar.setCurrentValue(currentStamina);
			 }
		 
		 }
	

		 // Ensure entity is within bottom boundary of screen
		 if (super.getPositionY() - distanceToMove - super.getRadius() >= 200) {
			 super.setPositionY(super.getPositionY() - distanceToMove);
			 update(deltaTime);
		 }
	 }
		
    
	// Interfaces Overrides
	 @Override
		public void handleCollision(Entity collidedEntity, SpaceEntityManager spaceEntityManager, SpaceAIControlManager spaceAIControlManager, PlayerControlManager spacePlayerControlManager) {
			if(collidedEntity instanceof iSpacePlayer) {
				
			}else if (collidedEntity instanceof iPlanet) {
	
			}else if (collidedEntity instanceof iDebris) {
				// Reduce player health
				spaceEntityManager.updateEntityHealth(this,10);
				
				// Remove debris entity
				spaceEntityManager.remove((Entity) collidedEntity);	
				spaceAIControlManager.remove((iAI) collidedEntity);
				
				// Update player health
				spacePlayerControlManager.update(spaceEntityManager.getEntityList());
			}else if (collidedEntity instanceof iProjectile) {
			}
		}
		
		@Override
		public void draw(ShapeRenderer shape) {
			shape.setColor(super.getColour());
	        if(super.getRadius() != 0) {
	        	shape.circle(getPositionX(), getPositionY(), getRadius());
	        }else if(super.getWidth() != 0) {
	        	shape.rect(getPositionX(), getPositionY(), getWidth(), getHeight());
	        }
		}

		@Override
		public void draw(SpriteBatch batch, float width, float height) {
			batch.draw(super.getTex(), getPositionX(), getPositionY(), width, height);
		}
		
		@Override public boolean isPlayable() { return playable; }
		@Override public void setPlayable(boolean playable) { this.playable = playable; }
		@Override public boolean isCollidable() { return collidable; }
		@Override public void setCollidable(boolean collidable) { this.collidable = collidable; }
		@Override public Rectangle getBoundingBox() { return boundingBox; }
		@Override public void setBoundingBox(Rectangle boundingBox) { this.boundingBox = boundingBox; }


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
	public int getShiftKeybind() {return shiftKeybind;}
	public void setShiftKeybind(int shiftKeybind) {this.shiftKeybind = shiftKeybind;}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public StatsBar getHealthBar() { return healthBar; }
	public void setHealthBar(StatsBar healthBar) { this.healthBar = healthBar; }
	public StatsBar getStaminaBar() { return staminaBar; }
	public void setStaminaBar(StatsBar staminaBar) { this.staminaBar = staminaBar; }
	public int getSpeedMultiplier() { return speedMultiplier; }
	public void setSpeedMultiplier(int speedMultiplier) { this.speedMultiplier = speedMultiplier; }

	@Override
	public boolean isAIControl() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAIControl(boolean aiControl) {
		// TODO Auto-generated method stub
		
	}

	
	







	
	

}
