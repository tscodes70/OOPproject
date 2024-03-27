package com.mygdx.gamelayer.managers;

import java.util.List;

import com.mygdx.gameengine.interfaces.iAI;
import com.mygdx.gameengine.managers.AIControlManager;
import com.mygdx.gameengine.models.Entity;

public class SpaceAIControlManager extends AIControlManager {

	public SpaceAIControlManager(List<Entity> entityList) {
		super(entityList);
	}
	
	public void addAI(Entity entity) {
		super.getAiEntityList().add(entity);
		
	}
	
	 public void removeAI(Entity entity) {
	        // Remove an AI entity from the game
	        super.getAiEntityList().remove(entity);
	    }

}
