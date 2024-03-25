package com.mygdx.gamelayer.managers;

import java.util.List;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.gameengine.interfaces.iAI;
import com.mygdx.gameengine.interfaces.iCollidable;
import com.mygdx.gameengine.interfaces.iPlayer;
import com.mygdx.gameengine.managers.AIControlManager;
import com.mygdx.gameengine.managers.CollisionManager;
import com.mygdx.gameengine.managers.EntityManager;
import com.mygdx.gameengine.managers.PlayerControlManager;
import com.mygdx.gameengine.models.Entity;
import com.mygdx.gamelayer.interfaces.iProjectile;
import com.mygdx.gamelayer.interfaces.iDebris;
import com.mygdx.gamelayer.interfaces.iPlanet;
import com.mygdx.gamelayer.models.Planet;
import com.mygdx.gamelayer.models.Player;

public class SpaceCollisionManager extends CollisionManager {
	
	public SpaceCollisionManager() {
		super();
	}
	
	public SpaceCollisionManager(List<Entity> entityList) {
		super(entityList);
	}
	
	  /**
     * Method to check and detect collisions between entities,
     * Calls handleCollisions method to then resolve these detected collisions.
     * @param entityManager
     */
	 public void checkCollisions(SpaceEntityManager entityManager, SpaceAIControlManager aiControlManager, PlayerControlManager playerControlManager) {
		 for (int i = 0; i < super.getCollidableList().size() - 1; i++) {
	            for (int j = i + 1; j < super.getCollidableList().size(); j++) {

	                if (super.getCollidableList().get(i).getBoundingBox().overlaps(super.getCollidableList().get(j).getBoundingBox())) {
	                	handleCollisions(entityManager, aiControlManager,playerControlManager, super.getCollidableList().get(i),super.getCollidableList().get(j));
	                	
	                }
	            }
	        }
	    }
	
	 /**
	  * Resolves collisions between 2 Entities, for this demonstration, 
	  * AI collision with AI is allowed.
	  * Player collision with Player is allowed.
	  * When AI collides with Player, AI entity is removed.
	  * @param entityManager
	  * @param x
	  * @param y
	  */
	 public void handleCollisions(SpaceEntityManager entityManager, SpaceAIControlManager aiControlManager, PlayerControlManager playerControlManager, iCollidable x, iCollidable y) {
		 if (x instanceof iDebris && y instanceof iPlayer){
			 	// Remove Debris
				 this.remove(x);	
				 entityManager.remove((Entity) x);	
				 aiControlManager.remove((iAI) x);
				 
				 // Reduce player health
				 entityManager.updateEntityHealth(((Entity)y),10);
				 playerControlManager.update(entityManager.getEntityList());
				 
				 System.out.println("Collision Between Player & Debris - Debris Entity Removed");
		 }else if (x instanceof iPlayer && y instanceof iDebris) {
				 // Remove Debris
				 this.remove(y);	
				 entityManager.remove((Entity) y);	
				 aiControlManager.remove((iAI) y);
				 
				// Reduce player health
				 entityManager.updateEntityHealth(((Entity)x),10);
				 playerControlManager.update(entityManager.getEntityList());
				 
				 System.out.println("Collision Between Player & Debris - Debris Entity Removed");
		 }else if(x instanceof iProjectile && y instanceof iDebris) {
				 // Remove Projectile
				 this.remove(x);
				 entityManager.remove((Entity) x);	
				 aiControlManager.remove((iAI) x);
				 
				// Remove Debris
				 this.remove(y);
				 entityManager.remove((Entity) y);
				 aiControlManager.remove((iAI) y);
				 
				 System.out.println("Collision Between Projectile & Debris - Both Removed");
		 }else if(x instanceof iDebris && y instanceof iProjectile) {
			 // Remove Projectile
			 this.remove(y);
			 entityManager.remove((Entity) y);
			 aiControlManager.remove((iAI) y);
			 
			// Remove Debris
			 this.remove(x);
			 entityManager.remove((Entity) x);	
			 aiControlManager.remove((iAI) x);
			 
			 System.out.println("Collision Between Projectile & Debris - Both Removed");
		 }
		 else if (x instanceof iPlanet && y instanceof iDebris){
			 
			 // Remove Debris
			 this.remove(y);
			 entityManager.remove((Entity) y);
			 aiControlManager.remove((iAI) y);
			 
			 // Reduce Planet health
			 entityManager.updateEntityHealth(((Entity)x),1);
			 
			 System.out.println("Collision Between Planet & Debris - AI Entity Removed");
		 }else if (x instanceof iDebris && y instanceof iPlanet) {
			 // Remove Debris
			 this.remove(x);
			 entityManager.remove((Entity) x);
			 aiControlManager.remove((iAI) x);
			 
			 // Reduce Planet health
			 entityManager.updateEntityHealth(((Entity)y),1);
			 
			 System.out.println("Collision Between Planet & Debris - AI Entity Removed");
		 }

	 }
}
