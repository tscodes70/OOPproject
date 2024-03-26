package com.mygdx.gameengine.managers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.mygdx.gameengine.interfaces.iAI;
import com.mygdx.gameengine.interfaces.iMovable;
import com.mygdx.gameengine.interfaces.iPlayer;
import com.mygdx.gameengine.models.Entity;

// Manages AI-controlled entities
public class AIControlManager{ 
	
    private List<iAI> aiList;
    
    /**
     * Constructor that takes a list of entities, extracts each entity
     * that has variable aiControl=true.
     * @param entityList
     */
    public AIControlManager(List<Entity> entityList) {
    	aiList = new ArrayList<iAI>();
    	extractAI(entityList);
    }
    
    /**
     * Adds an AI entity into AIControlManager list
     * @param entity
     */
    public void add(iAI ai) {
    	aiList.add(ai);
    }
    
    /**
     * Removes an AI entity from the AIControlManager list
     * @param entity
     */
    public void remove(iAI ai) {
    	aiList.remove(ai);
    	((Entity)ai).dispose();
    }
    
    public void extractAI(List<Entity> entityList) {
		List<iAI> updatedAIList = new ArrayList<iAI>();
		
		// Iterate over entities and add playable ones to playerList
	    for (Entity entity : entityList) {
	        if (entity instanceof iAI) {
	        	iAI aiEntity = (iAI) entity;
	            if (aiEntity.isAIControl()) {
	            	updatedAIList.add(aiEntity);
	            }
	        }
	    }
	    aiList = updatedAIList;
    }
    
    /**
     * Main movement method to move all AI entities, utilizes deltaTime for distance
     * calculation of AI entities.
     * @param deltaTime
     */
    public void move(float deltaTime) {
        for (iAI ai : aiList) ((Entity)ai).update(deltaTime);
    }
    
    /**
     * Retrieves updated entityList when entityList is modified
     * @param entityList
     */
    public void update(List<Entity> entityList) {
    	extractAI(entityList);
    }

    /**
     * Disposing of AIControlManager Resources
     */
    public void dispose() {
    	for (iAI ai : aiList) ((Entity)ai).dispose();
		System.out.println("AIControlManager Resources Disposed");
    }

    // Getter Setters
	public List<iAI> getAiEntityList() {
		return aiList;
	}
	public void setAiEntityList(List<iAI> aiEntityList) {
		this.aiList = aiEntityList;
	}
    
}
