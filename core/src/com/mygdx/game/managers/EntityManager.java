package com.mygdx.game.managers;

import java.util.List;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.models.Entity;

public class EntityManager {
    private List<Entity> entityList;

    public EntityManager(List<Entity> entityList) {
        this.setEntityList(entityList);
    }

	public List<Entity> getEntityList() {
		return entityList;
	}

	public void drawEntities(SpriteBatch batch) {
		for (Entity entity : entityList) {
			entity.draw(batch);
		}
	}

	public void drawEntities(ShapeRenderer shape) {
		for (Entity entity : entityList) {
			entity.draw(shape);
		}
	}

	public void moveEntities() {
	}

	public void setEntityList(List<Entity> entityList) {
		this.entityList = entityList;
	}
}