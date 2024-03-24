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
import com.mygdx.gameengine.models.Entity;
import com.mygdx.gamelayer.interfaces.iProjectile;
import com.mygdx.gamelayer.interfaces.iDebris;
import com.mygdx.gamelayer.interfaces.iPlanet;
import com.mygdx.gamelayer.models.Planet;

public class SpaceCollisionManager extends CollisionManager {
	
	public SpaceCollisionManager() {
		super();
	}
	
	public SpaceCollisionManager(List<Entity> entityList) {
		super(entityList);
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
	@Override
	 public void handleCollisions(EntityManager entityManager, AIControlManager aiControlManager, iCollidable x, iCollidable y) {
		 if (x instanceof iDebris && y instanceof iPlayer){
				 this.remove(x);	//update collidableList to remove entity
				 entityManager.remove((Entity) x);	//update entityManager to remove entity
				 aiControlManager.remove((iAI) x);
				 System.out.println("Collision Between Player & Debris - Debris Entity Removed");
		 }else if (x instanceof iPlayer && y instanceof iDebris) {
				 this.remove(y);	//update collidableList to remove entity
				 entityManager.remove((Entity) y);	//update entityManager to remove entity
				 aiControlManager.remove((iAI) y);
				 System.out.println("Collision Between Player & Debris - Debris Entity Removed");
		 }else if(x instanceof iProjectile && y instanceof iDebris) {
			 this.remove(x);
			 this.remove(y);//update collidableList to remove entity
			 entityManager.remove((Entity) x);	//update entityManager to remove entity
			 entityManager.remove((Entity) y);
			 aiControlManager.remove((iAI) x);
			 aiControlManager.remove((iAI) y);
			 System.out.println("Collision Between Projectile & Debris - Both Removed");
		 }else if(x instanceof iDebris && y instanceof iProjectile) {
			 this.remove(x);
			 this.remove(y);//update collidableList to remove entity
			 entityManager.remove((Entity) x);	//update entityManager to remove entity
			 entityManager.remove((Entity) y);
			 aiControlManager.remove((iAI) x);
			 aiControlManager.remove((iAI) y);
			 System.out.println("Collision Between Projectile & Debris - Both Removed");
		 }
		 else if (x instanceof iPlanet && y instanceof iDebris){
			 this.remove(y);	//update collidableList to remove entity
			 entityManager.remove((Entity) y);	//update entityManager to remove entity
			 aiControlManager.remove((iAI) y);
			 System.out.println("Collision Between Planet & Debris - AI Entity Removed");
		 }else if (x instanceof iDebris && y instanceof iPlanet) {
			 this.remove(x);	//update collidableList to remove entity
			 entityManager.remove((Entity) x);	//update entityManager to remove entity
			 aiControlManager.remove((iAI) x);
			 System.out.println("Collision Between Planet & Debris - AI Entity Removed");
		 }

	 }
}
