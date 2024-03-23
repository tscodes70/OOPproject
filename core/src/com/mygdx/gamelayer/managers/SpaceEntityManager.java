package com.mygdx.gamelayer.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.gameengine.managers.EntityManager;
import com.mygdx.gameengine.models.Entity;
import com.mygdx.gamelayer.models.Debris;
import com.mygdx.gamelayer.models.Planet;
import com.mygdx.gamelayer.models.Player;
import com.mygdx.gamelayer.models.Projectile;

public class SpaceEntityManager extends EntityManager{

	public SpaceEntityManager() {
		super();
	}
	
	@Override
	public void drawEntities(SpriteBatch batch) {
		for (Entity entity : super.getEntityList()) {
			if (entity.getTex() != null) {
				if(entity instanceof Planet) {
					entity.draw(batch, entity.getWidth(), entity.getHeight());
				}
				
			}
		}
	}
	
	/**
	 * Draws Shaped Entities using ShapeRenderer
	 * @param shape
	 */
	@Override
	public void drawEntities(ShapeRenderer shape) {
		for (Entity entity : super.getEntityList()) {
			if (entity.getTex() == null && (entity instanceof Debris || entity instanceof Player || entity instanceof Projectile))entity.draw(shape);
		}
	}
	
}
