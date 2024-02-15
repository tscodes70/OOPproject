package com.mygdx.game.managers;

import com.mygdx.game.models.Entity;

public class EntityManager {
    private List<Entity> entityList;

    public EntityManager(List<Entity> entityList) {
        this.entityList = entityList;
    }
}