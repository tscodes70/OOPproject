package com.mygdx.gameengine.managers;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.gameengine.interfaces.iAI;
import com.mygdx.gameengine.interfaces.iCollidable;
import com.mygdx.gameengine.interfaces.iPlayer;
import com.mygdx.gameengine.models.Entity;

public class CollisionManager{
	private List<iCollidable> collidableList;
	
	
	public CollisionManager() {
		collidableList = new ArrayList<iCollidable>();
	}
	
	/**
	 * Constructor that takes a list of entities, extracts each entity
     * that has variable collidable=true.
	 * @param entityList
	 */
	public CollisionManager(List<Entity> entityList) {
	    collidableList = new ArrayList<>();
		extractCollidable(entityList);
	}
	
	public void add(iCollidable collisionObj) {
		collidableList.add(collisionObj);
	}
	
	public void remove(iCollidable collisionObj) {
		collidableList.remove(collisionObj);
	}
	
	public void update(List<Entity> entityList) {
		extractCollidable(entityList);
	}
	
	public void extractCollidable(List<Entity> entityList) {
		List<iCollidable> updatedCollidableList = new ArrayList<iCollidable>();
		
		// Iterate over entities and add collidable ones to collidableList
	    for (Entity entity : entityList) {
	        if (entity instanceof iCollidable) {
	            iCollidable collidableEntity = (iCollidable) entity;
	            if (collidableEntity.isCollidable()) {
	            	updatedCollidableList.add(collidableEntity);
	            }
	        }
	    }
	    
	    System.out.print("Populate c list");
	    collidableList = updatedCollidableList;
	}
	
    /**
     * Method to check and detect collisions between entities,
     * Calls handleCollisions method to then resolve these detected collisions.
     * @param entityManager
     */
	 public void checkCollisions(EntityManager entityManager, AIControlManager aiControlManager) {
		 for (int i = 0; i < collidableList.size() - 1; i++) {
	            for (int j = i + 1; j < collidableList.size(); j++) {

	                if (collidableList.get(i).getBoundingBox().overlaps(collidableList.get(j).getBoundingBox())) {
	                	handleCollisions(entityManager, aiControlManager, collidableList.get(i),collidableList.get(j));
	                	
	                }
	            }
	        }
	    }
	 
	 public List<iCollidable> getCollidableList() {
		return collidableList;
	}

	public void setCollidableList(List<iCollidable> collidableList) {
		this.collidableList = collidableList;
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
	 public void handleCollisions(EntityManager entityManager, AIControlManager aiControlManager, iCollidable x, iCollidable y) {
//		 if(x instanceof iAI && y instanceof iAI ) {
////			 System.out.println("Collision Between 2 AIs - Allowed");
//		 }else if(x instanceof iPlayer && y instanceof iPlayer){
////			 System.out.println("Collision Between 2 Players - Allowed");
//		 }else if (x instanceof iAI && y instanceof iPlayer){
//				 this.remove(x);	//update collidableList to remove entity
//				 entityManager.remove((Entity) x);	//update entityManager to remove entity
//				 aiControlManager.remove((iAI) x);
//				 System.out.println("Collision Between Player & AI - AI Entity Removed");
//		 }else if (x instanceof iPlayer && y instanceof iAI) {
//				 this.remove(y);	//update collidableList to remove entity
//				 entityManager.remove((Entity) y);	//update entityManager to remove entity
//				 aiControlManager.remove((iAI) y);
//				 System.out.println("Collision Between Player & AI - AI Entity Removed");
//		 }

	 }
	 
	 /**
	  * Disposal of CollisionManager Resources
	  */
	 
	 public void dispose() {
//	    	for (iCollidable e : collidableList) e.;
//	    	if(updatedEntityList != null) for (Entity e : updatedEntityList) e.dispose();
//			System.out.println("CollisionManager Resources Disposed");
	    }
	 
	
}
