package com.mygdx.game.managers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.interfaces.iManager;
import com.mygdx.game.interfaces.iMovable;
import com.mygdx.game.models.Entity;
import com.mygdx.game.models.KeyboardInput;

public class PlayerControlManager implements iManager<Entity>, iMovable {
	
    private List<Entity> playerEntityList;
    private List<Entity> updatedEntityList;
    private IOManager<KeyboardInput> keyboardDevice;
    
	private final int LEFTKEY = Keys.LEFT;
	private final int RIGHTKEY = Keys.RIGHT;
	private final int UPKEY = Keys.UP;
	private final int DOWNKEY = Keys.DOWN;
	
	private final int DEFAULT_ENTITY_SPEED_MULTIPLIER = 100;

	/**
	 * Constructor that takes a list of entities, extracts each entity
	 * that has variable aiControl=false.
	 * @param entityList
	 */
	public PlayerControlManager(List<Entity> entityList, IOManager<KeyboardInput> keyboardDevice){
		super();
		this.keyboardDevice = keyboardDevice;
		playerEntityList = new ArrayList<Entity>();
		
		for(Entity entity : entityList) {
        	if(!entity.isAiControl()) playerEntityList.add(entity);
        }
	}
	
	/**
     * Adds a Player entity into PlayerControlManager list
     * @param entity
     */
	public void add(Entity entity) {
		playerEntityList.add(entity);
	}
	
	/**
     * Removes a Player entity from PlayerControlManager list
     * @param entity
     */
	public void remove(Entity entity) {
		playerEntityList.remove(entity);
		entity.dispose();
	}
	
	/**
     * Retrieves updated entityList when entityList is modified
     * @param entityList
     */
	 public void update(List<Entity> entityList) {
	    	updatedEntityList = new ArrayList<Entity>();
	        for (Entity entity : entityList) {
	            if (!entity.isAiControl()) {
	                updatedEntityList.add(entity);
	            }
	        }
	        this.playerEntityList = updatedEntityList;
	    }

	 /**
	  * Moves Player towards the left by the set default speed
	  * @param deltaTime
	  */
	 public void moveLeft(float deltaTime) {
		    for (Entity entity : this.playerEntityList) {
		        float distanceToMove = entity.getSpeed() * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime;

		        // Ensure entity is within left boundary of screen
		        if (entity.getPositionX() - distanceToMove - entity.getRadius() >= 0) {
		            entity.setPositionX(entity.getPositionX() - distanceToMove);
		            entity.update(deltaTime);
		        }
		    }
		}

	 /**
	  * Moves Player towards the right by the set default speed
	  * @param deltaTime
	  */
	 public void moveRight(float deltaTime) {
		    for (Entity entity : this.playerEntityList) {
		        float distanceToMove = entity.getSpeed() * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime;

		        // Ensure entity is within right boundary of screen
		        if (entity.getPositionX() + distanceToMove + entity.getRadius() <= Gdx.graphics.getWidth()) {
		            entity.setPositionX(entity.getPositionX() + distanceToMove);
		            entity.update(deltaTime);		        
		            }
		    }
		}

	 /**
	  * Moves Player towards the top by the set default speed
	  * @param deltaTime
	  */
	 public void moveUp(float deltaTime) {
		    for (Entity entity : this.playerEntityList) {
		        float distanceToMove = entity.getSpeed() * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime;

		        // Ensure entity is within top boundary of screen
		        if (entity.getPositionY() + distanceToMove + entity.getRadius() <= Gdx.graphics.getHeight()) {  // Adjusted boundary check
		            entity.setPositionY(entity.getPositionY() + distanceToMove);
		            entity.update(deltaTime);		       
		            }
		    }
		}

	 /**
	  * Moves Player towards the bottom by the set default speed
	  * @param deltaTime
	  */
	 public void moveDown(float deltaTime) {
		    for (Entity entity : this.playerEntityList) {
		        float distanceToMove = entity.getSpeed() * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime;

		        // Ensure entity is within bottom boundary of screen
		        if (entity.getPositionY() - distanceToMove - entity.getRadius() >= 0) {
		            entity.setPositionY(entity.getPositionY() - distanceToMove);
		            entity.update(deltaTime);		        }
		    }
		}
	
	 /**
	  * Main movement handler methods that calls movement methods
	  * based on respective movement key presses
	  * @param deltaTime
	  */
	public void move(float deltaTime) {
		if(keyboardDevice.pollInputHold(LEFTKEY)) moveLeft(deltaTime);
		if(keyboardDevice.pollInputHold(RIGHTKEY)) moveRight(deltaTime);
		if(keyboardDevice.pollInputHold(UPKEY)) moveUp(deltaTime);
		if(keyboardDevice.pollInputHold(DOWNKEY)) moveDown(deltaTime);
	}
	
	/**
	 *  Disposal of PlayerControlManager Resources
	 */
	public void dispose() {
		for (Entity e : playerEntityList) e.dispose();
		if(updatedEntityList != null) for (Entity e : updatedEntityList) e.dispose();
		System.out.println("PlayerControlManager Resources Disposed");
	}

}
