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
import com.mygdx.gamelayer.interfaces.iStatic;
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
		 if(x instanceof iAI && y instanceof iAI ) {
//			 System.out.println("Collision Between 2 AIs - Allowed");
		 }else if(x instanceof iPlayer && y instanceof iPlayer){
//			 System.out.println("Collision Between 2 Players - Allowed");
		 }else if (x instanceof iAI && y instanceof iPlayer){
				 this.remove(x);	//update collidableList to remove entity
				 entityManager.remove((Entity) x);	//update entityManager to remove entity
				 aiControlManager.remove((iAI) x);
				 System.out.println("Collision Between Player & AI - AI Entity Removed");
		 }else if (x instanceof iPlayer && y instanceof iAI) {
				 this.remove(y);	//update collidableList to remove entity
				 entityManager.remove((Entity) y);	//update entityManager to remove entity
				 aiControlManager.remove((iAI) y);
				 System.out.println("Collision Between Player & AI - AI Entity Removed");
		 }
//		 }else if (x instanceof iStatic && y instanceof iPlayer){
//			 	 ((Entity) y).setPositionY(((Planet) x).getBoundingBox().height + ((Planet) x).getBoundingBox().y);	//update entityManager to remove entity
//		 }else if (x instanceof iPlayer && y instanceof iStatic) {
//			 	 ((Entity) x).setPositionY(((Planet) y).getBoundingBox().height + ((Planet) y).getBoundingBox().y);	//update entityManager to remove entity
//		 }

	 }
}
