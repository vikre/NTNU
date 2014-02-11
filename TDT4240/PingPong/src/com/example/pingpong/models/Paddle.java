package com.example.pingpong.models;

import sheep.game.Sprite;
import sheep.graphics.Image;

public class Paddle extends Sprite {
	
	float x, y;
	
	public Paddle(Image img) {
		super(img);
	}
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
		super.setPosition(x, y);
	}
	
	
	
}
