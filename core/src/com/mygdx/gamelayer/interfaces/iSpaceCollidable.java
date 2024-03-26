package com.mygdx.gamelayer.interfaces;

import com.mygdx.gameengine.interfaces.iCollidable;
import com.mygdx.gameengine.managers.PlayerControlManager;
import com.mygdx.gameengine.models.Entity;
import com.mygdx.gamelayer.managers.SpaceAIControlManager;
import com.mygdx.gamelayer.managers.SpaceEntityManager;

public interface iSpaceCollidable extends iCollidable {
    
    void handleCollision(Entity entity, SpaceEntityManager entityManager, SpaceAIControlManager aiControlManager, PlayerControlManager playerControlManager);
}
