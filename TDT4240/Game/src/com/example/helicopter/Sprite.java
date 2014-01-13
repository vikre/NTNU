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
		int mapy = animateDirection()*height;
		Rect startpkt = new Rect(mapx,mapy,mapx+width,mapy+height);
		Rect endpkt = new Rect(x,y,x+width,y+height);
		canvas.drawBitmap(bitmap, startpkt, endpkt, null);
	}
	private int animateDirection(){
		int[] directions = {3, 1, 0, 2};
		double formulaX = (Math.atan2(speedX, speedY) / (Math.PI / 2) + 2);
        int dir = (int) Math.round(formulaX) % ROWS;
        return directions[dir];
	}
	private void update(){
		if(x > gameView.getWidth() - width-speedX|| x + speedX < 0){
			speedX = -speedX;
		}
		x = x + speedX;
		if(y > gameView.getHeight() - height-speedY || y+speedY < 0){
			speedY = -speedY;
		}
		y = y + speedY;
		currentFrame = ++currentFrame %COLUMNS;//get the next frame
	}
}
