package com.mygdx.gamelayer.models;

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

public class Planet extends Entity implements iPlanet {
	
	private float currentHP;
	private float maxHP;
	private boolean collidable;
	private Rectangle boundingBox;
	private String name;
	private String info;
	private float gravity;
	
	private final float GRAVITYSCALE = 0.7f;

	public Planet(
			String name,
			SpaceTexture texture,
			float width, 
			float height,
			float positionX, 
			float positionY, 
			float gravity,
			boolean collidable) {
		super(texture,positionX, positionY, width, height);
		this.boundingBox = new Rectangle(positionX, positionY, width, height);
		this.collidable = collidable;
		this.name = name;
		this.info = String.format("This is a description of %s.", name);
		this.setGravity(gravity);
		
		this.currentHP = 5;
		this.maxHP = 5;
	}



// Interface Overrides
	@Override
	public void handleCollision(Entity collidedEntity, SpaceEntityManager spaceEntityManager, SpaceAIControlManager spaceAIControlManager, PlayerControlManager spacePlayerControlManager) {
		if (collidedEntity instanceof iDebris) {
			// Reduce Planet health
			spaceEntityManager.updateEntityHealth(this,1);
						 
			// Remove Debris
			spaceEntityManager.remove((Entity) collidedEntity);
			spaceAIControlManager.remove((iAI) collidedEntity);
		}
	}
	
	@Override
	public void update(float deltatime) {
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
	
	@Override public boolean isCollidable() { return collidable; }
	@Override public void setCollidable(boolean collidable) { this.collidable = collidable; }
	@Override public Rectangle getBoundingBox() { return boundingBox; }
	@Override public void setBoundingBox(Rectangle boundingBox) { this.boundingBox = boundingBox; }



	
	// Getter Setter
	public float getCurrentHP() { return currentHP; }
	public void setCurrentHP(float currentHP) { this.currentHP = currentHP; }
	public float getMaxHP() { return maxHP; }
	public void setMaxHP(float maxHP) { this.maxHP = maxHP; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getInfo() { return info; }
	public void setInfo(String info) { this.info = info; }
	public float getGravity() { return gravity; }
	public void setGravity(float gravity) { this.gravity = gravity; }
	public float getScaledGravity() { return ((gravity/3.7f)*GRAVITYSCALE); }



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
