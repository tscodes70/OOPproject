package com.mygdx.gamelayer.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.gameengine.interfaces.iAI;
import com.mygdx.gameengine.interfaces.iCollidable;
import com.mygdx.gameengine.interfaces.iMovable;
import com.mygdx.gameengine.models.Entity;
import com.mygdx.gamelayer.interfaces.iProjectile;

public class Projectile extends Entity implements iProjectile {
	
	private boolean collidable;
	private Rectangle boundingBox;
	private int speedMultiplier;
	private boolean aiControl;
	private float damage;
	
	private final int DEFAULT_ENTITY_SPEED_MULTIPLIER = 100;
	
	public Projectile(
			float positionX, 
			float positionY, 
			float width,
			float height,
			Color colour, 
			boolean collidable,
			boolean aiControl,
			int speedMultiplier) {
		
		super(positionX, positionY, width, height, colour);
		this.boundingBox = new Rectangle(positionX, positionY, width, height);
		this.collidable = collidable;
		this.speedMultiplier = speedMultiplier;
		this.aiControl = aiControl;
	}
	
	public void update(float deltaTime) {
		move(deltaTime);
		
        // Update bounding box position
        boundingBox.setPosition(super.getPositionX(), super.getPositionY());
    }
	
	public void move(float deltaTime) {
        // Move entity vertically proportionally to deltaTime and speed
        float distanceToMove = speedMultiplier * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime;
        super.setPositionY(super.getPositionY() + distanceToMove);

        // Check if entity has moved off-screen
        if (super.getPositionY() > 1024) {
            // Reset entity's position to top of screen
        	super.setPositionY(Gdx.graphics.getHeight());
//            if (entity.getSpeed() <= MAX_ENTITY_SPEED) {
//               entity.setSpeed(entity.getSpeed() + 2);
//            }
        }
	}

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
	public boolean isAIControl() {
		// TODO Auto-generated method stub
		return aiControl;
	}

	@Override
	public void setAIControl(boolean aiControl) {
		// TODO Auto-generated method stub
		this.aiControl = aiControl;
		
	}

	@Override
	public int getSpeedMultiplier() {
		// TODO Auto-generated method stub
		return speedMultiplier;
	}

	@Override
	public void setSpeedMultiplier(int speedMultiplier) {
		// TODO Auto-generated method stub
		this.speedMultiplier = speedMultiplier;
		
	}

}
