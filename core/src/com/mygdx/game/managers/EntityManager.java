package com.mygdx.game.managers;

import java.util.List;

import com.mygdx.game.models.Entity;

public class EntityManager {
    private List<Entity> entityList;

    public EntityManager(List<Entity> entityList) {
        this.setEntityList(entityList);
    }

	public List<Entity> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<Entity> entityList) {
		this.entityList = entityList;
	}
}