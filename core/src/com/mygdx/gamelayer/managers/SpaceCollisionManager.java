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
import com.mygdx.gamelayer.interfaces.iSpacePlayer;
import com.mygdx.gamelayer.interfaces.iDebris;
import com.mygdx.gamelayer.interfaces.iPlanet;
import com.mygdx.gamelayer.models.Debris;
import com.mygdx.gamelayer.models.Planet;
import com.mygdx.gamelayer.models.Player;
import com.mygdx.gamelayer.models.Projectile;
import com.mygdx.gamelayer.screens.GameScreen;

public class SpaceCollisionManager extends CollisionManager {
	
	private final int DEFAULT_DEBRIS_WIDTH=120;
	
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
	 public void checkCollisions(SpaceEntityManager entityManager, SpaceAIControlManager aiControlManager, SpacePlayerControlManager playerControlManager, GameScreen gamescreen) {
		 for (int i = 0; i < super.getCollidableList().size() - 1; i++) {
	            for (int j = i + 1; j < super.getCollidableList().size(); j++) {

	                if (super.getCollidableList().get(i).getBoundingBox().overlaps(super.getCollidableList().get(j).getBoundingBox())) {
	                	handleCollisions(entityManager, aiControlManager,playerControlManager, super.getCollidableList().get(i),super.getCollidableList().get(j), gamescreen);
	                	
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
	 public void handleCollisions(SpaceEntityManager entityManager, SpaceAIControlManager aiControlManager, SpacePlayerControlManager playerControlManager, iCollidable x, iCollidable y, GameScreen gamescreen) {
		 if (x instanceof iDebris && y instanceof iSpacePlayer){
			 // Remove Debris
			this.remove(x);	
			gamescreen.setPlayerPoints(gamescreen.getPlayerPoints()-50);
			((Debris)x).handleCollision(((Player)y),entityManager,aiControlManager,playerControlManager);
			System.out.println("Collision Between Player & Debris - Debris Entity Removed");
				 
		 }else if (x instanceof iSpacePlayer && y instanceof iDebris) {
			// Remove Debris
			this.remove(y);	
			gamescreen.setPlayerPoints(gamescreen.getPlayerPoints()-50);
			((Player)x).handleCollision(((Debris)y),entityManager,aiControlManager,playerControlManager);
			System.out.println("Collision Between Player & Debris - Debris Entity Removed");
				 
		 }else if(x instanceof iProjectile && y instanceof iDebris) {
			 // Remove Projectile & Debris
			this.remove(x);
			this.remove(y);
			gamescreen.setPlayerPoints(gamescreen.getPlayerPoints()+100);

			if(((Debris)y).getWidth()==DEFAULT_DEBRIS_WIDTH) {
				// Spawn 2 more smaller debris
				for(int i = 0; i < 2; i++) entityManager.add(gamescreen.getSpaceEntityFactory().createDynamicEntity("Debris", gamescreen.getPlanet().getName(),((Debris)y).getPositionX(),((Debris)y).getPositionY()));
			}
			
			aiControlManager.update(entityManager.getEntityList());
			
			((Projectile)x).handleCollision(((Debris)y),entityManager,aiControlManager,playerControlManager);
			System.out.println("Collision Between Projectile & Debris - Both Removed");
				 
		 }else if(x instanceof iDebris && y instanceof iProjectile) {
			 // Remove Projectile & Debris
			 this.remove(y);
			 this.remove(x);
			 gamescreen.setPlayerPoints(gamescreen.getPlayerPoints()+100);

			 
			 if(((Debris)x).getWidth()==DEFAULT_DEBRIS_WIDTH) {
				 // Spawn 2 more smaller debris
				 for(int i = 0; i < 2; i++) entityManager.add(gamescreen.getSpaceEntityFactory().createDynamicEntity("Debris", gamescreen.getPlanet().getName(),((Debris)x).getPositionX(),((Debris)x).getPositionY()));
			 }
			 aiControlManager.update(entityManager.getEntityList());
			
			 ((Debris)x).handleCollision(((Projectile)y),entityManager,aiControlManager,playerControlManager);
			 System.out.println("Collision Between Projectile & Debris - Both Removed");
			 
		 }else if (x instanceof iPlanet && y instanceof iDebris){
			 // Remove Debris
			 this.remove(y);
			 
			 ((Planet)x).handleCollision(((Debris)y),entityManager,aiControlManager,playerControlManager);
			 System.out.println("Collision Between Planet & Debris - AI Entity Removed");
			 
		 }else if (x instanceof iDebris && y instanceof iPlanet) {
			 // Remove Debris
			 this.remove(x);
			 
			 ((Debris)x).handleCollision(((Planet)y),entityManager,aiControlManager,playerControlManager);
			 System.out.println("Collision Between Planet & Debris - AI Entity Removed");
			 
		 }

	 }
}
