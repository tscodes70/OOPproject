package com.mygdx.gameengine.managers;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.gameengine.models.Entity;

public class EntityManager {
    private List<Entity> entityList;

    public EntityManager() {
        entityList = new ArrayList<Entity>();
    }
    
    /**
     * Adds an entity into the entityManager list
     * @param entity
     */
    public void add(Entity entity) {
    	entityList.add(entity);
    }
    
    /**
     * Removes an entity from the entityManager list and disposes its resources
     * @param entity
     */
    public void remove(Entity entity) {
    	//implement remove entity logic here
    	entityList.remove(entity);
    	entity.dispose();
    }

    /**
     * Draws Textured Entities using SpriteBatch
     * @param batch
     */
	public void drawEntities(SpriteBatch batch) {
		for (Entity entity : this.entityList) {
			if (entity.getTex() != null) entity.draw(batch);
		}
	}
	
	/**
	 * Draws Shaped Entities using ShapeRenderer
	 * @param shape
	 */
	public void drawEntities(ShapeRenderer shape) {
		for (Entity entity : entityList) {
			if (entity.getTex() == null) entity.draw(shape);
		}
	}
	
	/**
	 * Disposal of EntityManager Resources
	 */
	public void dispose() {
		for(Entity e : entityList) e.dispose();
		System.out.println("EntityManager Resources Disposed");
	}

	public void setEntityList(List<Entity> entityList) { this.entityList = entityList; }
	
	public List<Entity> getEntityList() { return entityList; }
	

	
}