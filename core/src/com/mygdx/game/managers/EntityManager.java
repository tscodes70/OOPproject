package com.mygdx.game.managers;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.models.Entity;

public class EntityManager {
    private List<Entity> entityList;

    public EntityManager() {
        entityList = new ArrayList<Entity>();
    }
    
    public void addEntity(Entity entity) {
    	entityList.add(entity);
    }
    
    public void removeEntity(Entity entity) {
    	//implement remove entity logic here
    	entityList.remove(entity);
    }

	public void drawEntities(SpriteBatch batch) {
		for (Entity entity : this.entityList) {
			if (entity.getTex() != null) entity.draw(batch);
		}
	}

	public void drawEntities(ShapeRenderer shape) {
		for (Entity entity : entityList) {
			if (entity.getTex() == null) entity.draw(shape);
		}
	}

	public void moveEntities() {
		for(Entity entity : entityList) {
			entity.updateBoundingBox();
		}
	}

	public void setEntityList(List<Entity> entityList) {
		this.entityList = entityList;
	}
	
	public List<Entity> getEntityList() {
		return entityList;
	}
}