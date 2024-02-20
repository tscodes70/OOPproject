package com.mygdx.game.managers;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.interfaces.iCollidable;
import com.mygdx.game.models.Entity;

public class CollisionManager {
	private List<Entity> collidableList;
	private List<Entity> updatedEntityList;
	
	public CollisionManager(List<Entity> entityList) {
		
		collidableList = new ArrayList<Entity>();
		
		for(Entity entity : entityList) {
			if(entity.isCollidable()) collidableList.add(entity);
		}
	}
	
    public void update(List<Entity> entityList) {
    	updatedEntityList = new ArrayList<Entity>();
        for (Entity entity : entityList) {
            if (entity.isCollidable()) {
                updatedEntityList.add(entity);
            }
        }
        this.collidableList = updatedEntityList;
    }
	
	 public void checkCollisions(EntityManager entityManager) {
		 for (int i = 0; i < collidableList.size() - 1; i++) {
	            for (int j = i + 1; j < collidableList.size(); j++) {

	                if (collidableList.get(i).collidesWith(collidableList.get(j))) {
	                	handleCollisions(entityManager,collidableList.get(i),collidableList.get(j));
	                	
	                }
	            }
	        }
	    }
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
		 
		 
     	//else entityManager.removeEntity(y);
     	

	 }
	 
	
}
