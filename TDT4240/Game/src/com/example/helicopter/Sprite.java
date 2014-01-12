package com.example.helicopter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {
	//Bitmap columns and rows
	private static final int COLUMNS = 3;
	private static final int ROWS = 4;
	
	//position x and y
	private int x = 0;
	private int y = 0;
	
	private int speedX = 5;
	private int speedY;
	private GameView gameView;
	private Bitmap bitmap;
	private int currentFrame = 0;
	private int width;
	private int height;
	
	public Sprite(GameView gameView, Bitmap bitmap){
		this.gameView = gameView;
		this.bitmap = bitmap;
		this.width = bitmap.getWidth()/COLUMNS; //Bitmap piece width
		this.height = bitmap.getHeight()/ROWS; //bitmap piece height
	}
	
	public void onDraw(Canvas canvas){
		update();
		int mapx = currentFrame*width;
		int mapy = 1*height;
		Rect startpkt = new Rect(mapx,mapy,mapx+width,mapy+height);
		Rect endpkt = new Rect(x,y,x+width,y+height);
		canvas.drawBitmap(bitmap, mapx, mapy, null);
	}
	
	private void update(){
		if(x > gameView.getWidth() - width-speedX){
			speedX = -5;
		}
		if(x + speedX < 0){
			speedX = 5;
		}
		x = x + speedX;
		currentFrame = ++currentFrame %COLUMNS;//get the next frame
	}
}
