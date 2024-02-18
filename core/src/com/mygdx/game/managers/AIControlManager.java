package com.mygdx.game.managers;

import java.util.List;
import com.mygdx.game.models.Entity;

public class AIControlManager extends EntityManager { 

    public AIControlManager(List<Entity> entityList) {
        super(entityList);
    }


    public void moveAIControlledEntities() {
        for (Entity entity : getEntityList()) {
            if (entity.isAiControl()) {
                entity.setPositionY(entity.getPositionY() - entity.getSpeed());
                if (entity.getPositionY() < 0) {
                    entity.setPositionY(400);
                    if (entity.getSpeed() <= 9) {
                        entity.setSpeed(entity.getSpeed() + 2);
                    }
                }
            }
        }
    }
}
