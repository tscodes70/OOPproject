package com.mygdx.game.managers;

import java.util.List;

import com.mygdx.game.interfaces.iCollidable;

public class CollisionManager implements iCollidable {
	private List<iCollidable> collidableList;
	
	public CollisionManager(List<iCollidable> collidableList) {
		this.setCollidableList(collidableList);
	}
	
	public List<iCollidable> getCollidableList(){
		return collidableList;
	}
	
	public void setCollidableList(List<iCollidable> collidableList) {
		this.collidableList = collidableList;
	}
	
	public CollisionManager() {
			
	}

	@Override
	public boolean checkCollision() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resolveCollision() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCollision() {
		// TODO Auto-generated method stub
		
	}
	
}
