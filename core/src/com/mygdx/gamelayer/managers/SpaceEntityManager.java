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
	
	public void updateEntityHealth(Entity collidedEntity, float damage) {
	    if (collidedEntity instanceof Player) {
	        ((Player)collidedEntity).getHealthBar().setCurrentValue(((Player)collidedEntity).getHealthBar().getCurrentValue()-damage);;
	    } else if (collidedEntity instanceof Planet) {
	       ((Planet)collidedEntity).setCurrentHP(((Planet)collidedEntity).getCurrentHP()-damage);
	    } else {
	        // Handle unexpected entity types
	        System.out.println("Unhandled entity type: " + collidedEntity.getClass().getSimpleName());
	    }
	}
	
	@Override
	public void drawEntities(SpriteBatch batch) {
		for (Entity entity : super.getEntityList()) {
			if (entity.getTex() != null) {
				if(entity instanceof Planet) {
					entity.draw(batch, entity.getWidth(), entity.getHeight());
				}
			    if (entity instanceof Player) {
			        entity.draw(batch, entity.getWidth(), entity.getHeight());
			    }
			    if (entity instanceof Debris) {
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
			    if (entity instanceof Player) {
			    	// stats bar
			        ((Player)entity).getHealthBar().draw(shape);
			        ((Player)entity).getStaminaBar().draw(shape);
			    }

			    if (entity instanceof Projectile) {
			        entity.draw(shape);
			    }
			
		}
	}
	
}
