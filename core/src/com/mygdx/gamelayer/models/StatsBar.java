package com.mygdx.gamelayer.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class StatsBar {
	private float positionX;
	private float positionY;
	private Color barColor;
	private Color barBackgroundColor;
	private float barWidth;
	private float barHeight;
	private float currentValue;
	private float maxValue;

	public StatsBar(
			float positionX, 
			float positionY, 
			Color barColor, 
			Color barBackgroundColor, 
			float barWidth,
			float barHeight, 
			float currentValue, 
			float maxValue) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.barColor = barColor;
		this.barBackgroundColor = barBackgroundColor;
		this.barWidth = barWidth;
		this.barHeight = barHeight;
		this.currentValue = currentValue;
		this.maxValue = maxValue;
	}
	
	public void draw(ShapeRenderer shape) {
		// Draw background
		shape.setColor(barBackgroundColor);
		shape.rect(positionX,positionY,barWidth,barHeight);

        // Draw fill
		shape.setColor(barColor);
        float fillWidth = barWidth * (currentValue / maxValue);
        shape.rect(positionX,positionY, fillWidth, barHeight);

	}

	public float getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(float currentValue) {
		this.currentValue = currentValue;
	}

	public float getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
	}

	public Color getBarColor() {
		return barColor;
	}

	public void setBarColor(Color barColor) {
		this.barColor = barColor;
	}

	public float getBarWidth() {
		return barWidth;
	}

	public void setBarWidth(float barWidth) {
		this.barWidth = barWidth;
	}

	public float getBarHeight() {
		return barHeight;
	}

	public void setBarHeight(float barHeight) {
		this.barHeight = barHeight;
	}

	public float getPositionX() {
		return positionX;
	}

	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	public float getPositionY() {
		return positionY;
	}

	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}

	public Color getBarBackgroundColor() {
		return barBackgroundColor;
	}

	public void setBarBackgroundColor(Color barBackgroundColor) {
		this.barBackgroundColor = barBackgroundColor;
	}
	
	
	
}
