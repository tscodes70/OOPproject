package com.mygdx.game.managers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.models.Entity;

public class PlayerControlManager extends IOManager {
	
    private List<Entity> playerEntityList;
    private List<Entity> updatedEntityList;

	
	public PlayerControlManager(List<Entity> entityList){
		super();
		playerEntityList = new ArrayList<Entity>();
		
		for(Entity entity : entityList) {
        	if(!entity.isAiControl()) playerEntityList.add(entity);
        }
	}
	
	 public void update(List<Entity> entityList) {
	    	updatedEntityList = new ArrayList<Entity>();
	        for (Entity entity : entityList) {
	            if (!entity.isAiControl()) {
	                updatedEntityList.add(entity);
	            }
	        }
	        this.playerEntityList = updatedEntityList;
	    }

	public void moveLeft() {
		for (Entity entity : this.playerEntityList) {
			//Ensure entity is within left boundary of screen
			if (entity.getPositionX() - entity.getSpeed() - entity.getRadius()>= 0) {
				entity.setPositionX(entity.getPositionX() - entity.getSpeed());
				entity.updateBoundingBox();
			}
    	}
	}
	
	public void moveRight() {
		for (Entity entity : this.playerEntityList) {
			//Ensure entity is within right boundary of screen
			if (entity.getPositionX() + entity.getSpeed() + entity.getRadius() <= Gdx.graphics.getWidth()) {
				entity.setPositionX(entity.getPositionX() + entity.getSpeed());
				entity.updateBoundingBox();
			}
    	}
	}
	
	public void moveUp() {
		for (Entity entity : this.playerEntityList) {
			//Ensure entity is within right boundary of screen
			if (entity.getPositionY() + entity.getSpeed() + entity.getRadius() <= Gdx.graphics.getWidth()) {
				entity.setPositionY(entity.getPositionY() + entity.getSpeed());
				entity.updateBoundingBox();
			}
    	}
	}
	
	public void moveDown() {
		for (Entity entity : this.playerEntityList) {
			//Ensure entity is within right boundary of screen
			if (entity.getPositionY() - entity.getSpeed() - entity.getRadius() <= Gdx.graphics.getWidth()) {
				entity.setPositionY(entity.getPositionY() - entity.getSpeed());
				entity.updateBoundingBox();
			}
    	}
	}
	
	@Override
	public void checkKeyEvents() {
		if(super.pollLeftKey()) moveLeft();
		if(super.pollRightKey()) moveRight();
		if(super.pollUpKey()) moveUp();
		if(super.pollDownKey()) moveDown();
	}

	@Override
	public void checkClickEvents(EntityManager entityManager, AIControlManager aiControlManager, PlayerControlManager pcm, CollisionManager cm) {
		
	}
}
