package com.example.helicopter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Sprite {
	private int x = 0;
	private int speedX = 5;
	private GameView gameView;
	private Bitmap bitmap;
	
	public Sprite(GameView gameView, Bitmap bitmap){
		this.gameView = gameView;
		this.bitmap = bitmap;
	}
	
	public void onDraw(Canvas canvas){
		update();
		canvas.drawBitmap(bitmap, x, 10, null);
	}
	
	private void update(){
		if(x > gameView.getWidth() - bitmap.getWidth()-speedX){
			speedX = -5;
		}
		if(x + speedX < 0){
			speedX = 5;
		}
		x = x + speedX;
	}
}
