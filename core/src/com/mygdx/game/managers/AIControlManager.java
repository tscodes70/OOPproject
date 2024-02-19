package com.mygdx.game.managers;

import java.util.ArrayList;
import java.util.List;
import com.mygdx.game.models.Entity;

// Manages AI-controlled entities
public class AIControlManager { 
	
    private List<Entity> aiEntityList;
    
    // Filters and store only AI-controlled entities
    public AIControlManager(List<Entity> entityList) {
        aiEntityList = new ArrayList<Entity>();
        
        for(Entity entity : entityList) {
        	if(entity.isAiControl()) aiEntityList.add(entity);
        }
    }
    
    // Updates position of all AI-controlled entities 
    public void moveAIControlledEntities() {
        for (Entity entity : this.aiEntityList) {
        		// Move entity vertically based on speed
                entity.setPositionY(entity.getPositionY() - entity.getSpeed());
                
                // Check is entity has moved off-screen
                if (entity.getPositionY() < 0) {
                	// Reset entity's position to top of screen
                    entity.setPositionY(400);
                    if (entity.getSpeed() <= 9) {
                        entity.setSpeed(entity.getSpeed() + 2);
                    }
                }
                entity.updateBoundingBox();
        }
    }



	public List<Entity> getAiEntityList() {
		return aiEntityList;
	}
	public void setAiEntityList(List<Entity> aiEntityList) {
		this.aiEntityList = aiEntityList;
	}
    
    
}
