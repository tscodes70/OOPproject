package com.mygdx.game.managers;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.interfaces.iCollidable;
import com.mygdx.game.models.Entity;

public class CollisionManager {
	private List<Entity> collidableList;
	private List<Entity> updatedEntityList;
	
	/**
	 * Constructor that takes a list of entities, extracts each entity
     * that has variable collidable=true.
	 * @param entityList
	 */
	public CollisionManager(List<Entity> entityList) {
		
		collidableList = new ArrayList<Entity>();
		
		// Retrieve entities that are collidable
		for(Entity entity : entityList) {
			if(entity.isCollidable()) collidableList.add(entity);
		}
	}
	
	/**
	 * Retrieves updated entityList when entityList is modified
	 * @param entityList
	 */
    public void update(List<Entity> entityList) {
    	updatedEntityList = new ArrayList<Entity>();
        for (Entity entity : entityList) {
            if (entity.isCollidable()) {
                updatedEntityList.add(entity);
            }
        }
        this.collidableList = updatedEntityList;
    }
	
    /**
     * Method to check and detect collisions between entities,
     * Calls handleCollisions method to then resolve these detected collisions.
     * @param entityManager
     */
	 public void checkCollisions(EntityManager entityManager) {
		 for (int i = 0; i < collidableList.size() - 1; i++) {
	            for (int j = i + 1; j < collidableList.size(); j++) {

	                if (collidableList.get(i).getBoundingBox().overlaps(collidableList.get(j).getBoundingBox())) {
	                	handleCollisions(entityManager,collidableList.get(i),collidableList.get(j));
	                	
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
	 public void handleCollisions(EntityManager entityManager, Entity x, Entity y) {
		 if(x.isAiControl() && y.isAiControl()) {
//			 System.out.println("Collision Between 2 AIs - Allowed");
		 }else if(!x.isAiControl() && !y.isAiControl()){
//			 System.out.println("Collision Between 2 Players - Allowed");
		 }else {
			 if(x.isAiControl()) {
				 collidableList.remove(x);	//update collidableList to remove entity
				 entityManager.removeEntity(x);	//update entityManager to remove entity
				 System.out.println("Collision Between Player & AI - AI Entity Removed");
			 }
			 else {
				 collidableList.remove(y);	//update collidableList to remove entity
				 entityManager.removeEntity(y);	//update entityManager to remove entity
				 System.out.println("Collision Between Player & AI - AI Entity Removed");
			 }
		 }

	 }
	 
	 /**
	  * Disposal of CollisionManager Resources
	  */
	 
	 public void dispose() {
	    	for (Entity e : collidableList) e.dispose();
	    	if(updatedEntityList != null) for (Entity e : updatedEntityList) e.dispose();
			System.out.println("CollisionManager Resources Disposed");
	    }
	 
	
}
