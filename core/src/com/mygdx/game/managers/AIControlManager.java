package com.mygdx.game.managers;

import java.util.ArrayList;
import java.util.List;
import com.mygdx.game.models.Entity;

public class AIControlManager { 

    private List<Entity> aiEntityList;
    
    public AIControlManager(List<Entity> entityList) {
        aiEntityList = new ArrayList<Entity>();
        
        for(Entity entity : entityList) {
        	if(entity.isAiControl()) aiEntityList.add(entity);
        }
    }

    public void moveAIControlledEntities() {
        for (Entity entity : this.aiEntityList) {
                entity.setPositionY(entity.getPositionY() - entity.getSpeed());
                if (entity.getPositionY() < 0) {
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
