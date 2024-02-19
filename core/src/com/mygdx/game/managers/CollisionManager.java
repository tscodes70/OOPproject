package com.mygdx.game.managers;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.interfaces.iCollidable;
import com.mygdx.game.models.Entity;

public class CollisionManager {
	private List<Entity> collidableList;
	
	public CollisionManager(List<Entity> entityList) {
		
		collidableList = new ArrayList<Entity>();
		
		for(Entity entity : entityList) {
			if(entity.isCollidable()) collidableList.add(entity);
		}
	}
	
	 public void checkCollisions() {
		 for (int i = 0; i < collidableList.size() - 1; i++) {
	            for (int j = i + 1; j < collidableList.size(); j++) {

	                if (collidableList.get(i).collidesWith(collidableList.get(j))) {
	                	collidableList.get(i).onCollision(collidableList.get(j));
	                	collidableList.get(j).onCollision(collidableList.get(i));
	                }
	            }
	        }
	    }
	 
	
}
