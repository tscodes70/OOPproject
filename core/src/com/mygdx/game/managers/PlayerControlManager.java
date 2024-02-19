package com.mygdx.game.managers;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.models.Entity;

public class PlayerControlManager extends IOManager {
	
    private List<Entity> playerEntityList;

	
	public PlayerControlManager(List<Entity> entityList){
		super();
		playerEntityList = new ArrayList<Entity>();
		
		for(Entity entity : entityList) {
        	if(!entity.isAiControl()) playerEntityList.add(entity);
        }
	}

	public void moveLeft() {
		for (Entity entity : this.playerEntityList) {
            entity.setPositionX(entity.getPositionX() - entity.getSpeed());
            entity.updateBoundingBox();
    }
//		System.out.println("Move Left");
	}
	
	public void moveRight() {
		for (Entity entity : this.playerEntityList) {
            entity.setPositionX(entity.getPositionX() + entity.getSpeed());
            entity.updateBoundingBox();
    }
//		System.out.println("Move Right");
	}
	
	@Override
	public void checkKeyEvents() {
		if(super.pollLeftKey()) moveLeft();
		if(super.pollRightKey()) moveRight();
	}
}
