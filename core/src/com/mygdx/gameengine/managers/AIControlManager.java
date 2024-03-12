package com.mygdx.gameengine.managers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.mygdx.gameengine.interfaces.iMovable;
import com.mygdx.gameengine.models.Entity;

// Manages AI-controlled entities
public class AIControlManager implements iMovable{ 
	
    private List<Entity> aiEntityList;
    private List<Entity> updatedEntityList;
    
	private final int MAX_ENTITY_SPEED = 10;
	private final int DEFAULT_ENTITY_SPEED_MULTIPLIER = 100;
    
    /**
     * Constructor that takes a list of entities, extracts each entity
     * that has variable aiControl=true.
     * @param entityList
     */
    public AIControlManager(List<Entity> entityList) {
        aiEntityList = new ArrayList<Entity>();
        
        for(Entity entity : entityList) {
        	if(entity.isAiControl()) aiEntityList.add(entity);
        }
    }
    
    /**
     * Adds an AI entity into AIControlManager list
     * @param entity
     */
    public void add(Entity entity) {
    	aiEntityList.add(entity);
    }
    
    /**
     * Removes an AI entity from the AIControlManager list
     * @param entity
     */
    public void remove(Entity entity) {
    	aiEntityList.remove(entity);
    	entity.dispose();
    }
    
    /**
     * Main movement method to move all AI entities, utilizes deltaTime for distance
     * calculation of AI entities.
     * @param deltaTime
     */
    public void move(float deltaTime) {
        for (Entity entity : this.aiEntityList) {
            // Move entity vertically proportionally to deltaTime and speed
            float distanceToMove = entity.getSpeed() * DEFAULT_ENTITY_SPEED_MULTIPLIER * deltaTime;
            entity.setPositionY(entity.getPositionY() - distanceToMove);

            // Check if entity has moved off-screen
            if (entity.getPositionY() < 220) {
                // Reset entity's position to top of screen
                entity.setPositionY(Gdx.graphics.getHeight());
//                if (entity.getSpeed() <= MAX_ENTITY_SPEED) {
//                   entity.setSpeed(entity.getSpeed() + 2);
//                }
            }
            entity.update(deltaTime);
        }
    }
    
    /**
     * Retrieves updated entityList when entityList is modified
     * @param entityList
     */
    public void update(List<Entity> entityList) {
    	updatedEntityList = new ArrayList<Entity>();
        for (Entity entity : entityList) {
            if (entity.isAiControl()) {
                updatedEntityList.add(entity);
            }
        }
        this.aiEntityList = updatedEntityList;
    }

    /**
     * Disposing of AIControlManager Resources
     */
    public void dispose() {
    	for (Entity e : aiEntityList) e.dispose();
    	if(updatedEntityList != null) for (Entity e : updatedEntityList) e.dispose();
		System.out.println("AIControlManager Resources Disposed");
    }

    // Getter Setters
	public List<Entity> getAiEntityList() {
		return aiEntityList;
	}
	public void setAiEntityList(List<Entity> aiEntityList) {
		this.aiEntityList = aiEntityList;
	}
    
}
