package com.mygdx.gameengine.interfaces;

import java.util.List;

public interface iManager<T> {
	public void add(T obj);
	public void remove(T obj);
	public void update(List<T> objList);
}
