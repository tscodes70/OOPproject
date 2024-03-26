package com.mygdx.gamelayer.models;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.mygdx.gameengine.interfaces.iAI;
import com.mygdx.gameengine.interfaces.iCollidable;
import com.mygdx.gameengine.interfaces.iMovable;
import com.mygdx.gameengine.interfaces.iPlayer;
import com.mygdx.gameengine.managers.PlayerControlManager;
import com.mygdx.gameengine.models.Entity;
import com.mygdx.gamelayer.interfaces.iDebris;
import com.mygdx.gamelayer.interfaces.iPlanet;
import com.mygdx.gamelayer.interfaces.iProjectile;
import com.mygdx.gamelayer.interfaces.iSpacePlayer;
import com.mygdx.gamelayer.managers.SpaceAIControlManager;
import com.mygdx.gamelayer.managers.SpaceEntityManager;

import java.util.Random;

public class Debris extends Entity implements iDebris{
	private float currentHP;
	private float maxHP;
	private boolean aiControl;
	private boolean collidable;
	private Rectangle boundingBox;
	private int speedMultiplier;
	private float gravity;
	
	
	private final int DEFAULT_ENTITY_SPEED_MULTIPLIER = 100;
	
	private Random random = new Random(); // Create a Random object to generate random numbers

	private float swing = 300;
	private int horizontalDirection = 1;
	private float horizontalSpeed = 0.5f;
	private float screenWidth = Gdx.graphics.getWidth();

	// Circle Shape Debris
	public Debris(
			float positionX, 
			float positionY, 
			int speedMultiplier, 
			float radius, 
			Color colour, 
			float gravity,
			boolean aiControl, 
			boolean collidable) {
		super(positionX, positionY, radius, colour);
		this.boundingBox = new Rectangle(positionX-radius, positionY-radius, radius * 2, radius * 2);
		this.speedMultiplier = speedMultiplier;
		this.gravity = gravity;
		this.aiControl = aiControl;
		this.collidable = collidable;
		this.horizontalDirection = random.nextBoolean() ? 1 : -1;
	}
	
	// Textured Debris
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
		move(deltaTime, gravity);

        // Update bounding box position
        boundingBox.setPosition(super.getPositionX()-super.getRadius(), super.getPositionY()-super.getRadius());
    }
	
	public void move(float deltaTime, float gravity) {
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
	
// Interface Overrides
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
	
	@Override
	public void handleCollision(Entity collidedEntity, SpaceEntityManager spaceEntityManager, SpaceAIControlManager spaceAIControlManager, PlayerControlManager spacePlayerControlManager) {
		if(collidedEntity instanceof iSpacePlayer) {
			// Reduce player health
			spaceEntityManager.updateEntityHealth(((Player)collidedEntity),10);
			
			// Remove debris entity
			spaceEntityManager.remove((Entity) this);	
			spaceAIControlManager.remove((iAI) this);
			
			// Update player health
			spacePlayerControlManager.update(spaceEntityManager.getEntityList());
			
		}else if (collidedEntity instanceof iPlanet) {
			 // Reduce Planet health
			spaceEntityManager.updateEntityHealth(((Entity)collidedEntity),1);
			 
			// Remove Debris
			spaceEntityManager.remove((Entity) this);
			spaceAIControlManager.remove((iAI) this);
	
		}else if (collidedEntity instanceof iDebris) {
		
		}else if (collidedEntity instanceof iProjectile) {
			// Remove Debris
			spaceEntityManager.remove((Entity) collidedEntity);	
			spaceAIControlManager.remove((iAI) collidedEntity);
			 
			// Remove Projectile
			spaceEntityManager.remove((Entity) this);
			spaceAIControlManager.remove((iAI) this);
		}
	}
	
	@Override public boolean isAIControl() { return aiControl; }
	@Override public void setAIControl(boolean aiControl) { this.aiControl = aiControl; }
	@Override public boolean isCollidable() { return collidable; }
	@Override public void setCollidable(boolean collidable) { this.collidable = collidable; }
	@Override public Rectangle getBoundingBox() { return boundingBox; }
	@Override public void setBoundingBox(Rectangle boundingBox) { this.boundingBox = boundingBox; }
	@Override public int getSpeedMultiplier() { return speedMultiplier; }
	@Override public void setSpeedMultiplier(int speedMultiplier) { this.speedMultiplier = speedMultiplier; }
	
	// Getter Setter
	public float getCurrentHP() { return currentHP; }
	public void setCurrentHP(float currentHP) { this.currentHP = currentHP; }
	public float getMaxHP() { return maxHP; }
	public void setMaxHP(float maxHP) { this.maxHP = maxHP; }

}