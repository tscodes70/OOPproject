package com.mygdx.gamelayer.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.gameengine.interfaces.iAI;
import com.mygdx.gameengine.interfaces.iCollidable;
import com.mygdx.gameengine.interfaces.iMovable;
import com.mygdx.gameengine.managers.PlayerControlManager;
import com.mygdx.gameengine.models.Entity;
import com.mygdx.gamelayer.interfaces.iDebris;
import com.mygdx.gamelayer.interfaces.iPlanet;
import com.mygdx.gamelayer.interfaces.iProjectile;
import com.mygdx.gamelayer.interfaces.iSpacePlayer;
import com.mygdx.gamelayer.managers.SpaceAIControlManager;
import com.mygdx.gamelayer.managers.SpaceEntityManager;

public class Projectile extends Entity implements iProjectile {
	
	private boolean collidable;
	private Rectangle boundingBox;
	private float speedMultiplier;
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
			float speedMultiplier) {
		
		super(positionX, positionY, width, height, colour);
		this.boundingBox = new Rectangle(positionX, positionY, width, height);
		this.collidable = collidable;
		this.speedMultiplier = speedMultiplier;
		this.aiControl = aiControl;
	}
	
	public Projectile(
			Texture texture,
			float positionX, 
			float positionY, 
			float width,
			float height,
			boolean collidable,
			boolean aiControl,
			float speedMultiplier) {
		
		super(texture, positionX, positionY, width, height);
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
	public void handleCollision(Entity collidedEntity, SpaceEntityManager spaceEntityManager, SpaceAIControlManager spaceAIControlManager, PlayerControlManager spacePlayerControlManager) {
		if(collidedEntity instanceof iSpacePlayer) {
				
		}else if (collidedEntity instanceof iPlanet) {
	
		}else if (collidedEntity instanceof iDebris) {
			// Remove Debris
			spaceEntityManager.remove((Entity) collidedEntity);
			spaceAIControlManager.remove((iAI) collidedEntity);
				 
			// Remove Projectile
			spaceEntityManager.remove((Entity) this);	
			spaceAIControlManager.remove((iAI) this);
			
		}else if (collidedEntity instanceof iProjectile) {
		}
	}
	 
	@Override
	public void move(float deltaTime, float gravity) {
		// TODO Auto-generated method stub
			
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
	public float getSpeedMultiplier() {
		// TODO Auto-generated method stub
		return speedMultiplier;
	}

	@Override
	public void setSpeedMultiplier(float speedMultiplier) {
		// TODO Auto-generated method stub
		this.speedMultiplier = speedMultiplier;
		
	}



}
