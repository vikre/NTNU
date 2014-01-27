package com.example.helicopter;

import sheep.game.Sprite;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;

public class Helicopter extends Sprite{
	
	private Bitmap bitmap;
	private Rect src_rect;
	private int currentFrame;
	private int frameCount;
	private int width;
	private int height;
	private long time;
	private float x;// float so i can use the speed @see draw()
	private float y;// float so i can use the speed @see draw()
	private int fps;
	
	public Helicopter(Bitmap bitmap, float x, float y, int fps, int framenr){
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		currentFrame = 0;//startframe
		this.frameCount = framenr;
		width = bitmap.getWidth()/frameCount;
		height = bitmap.getHeight();
		this.fps = 1000/fps;
		this.src_rect = new Rect(0,0,width,height);
		time = 0l;//its a long
		turnHelicopter();
		
	}
	@Override
	public void draw(Canvas canvas){
		this.x = x + getSpeed().getX()/100;
		this.y = y+getSpeed().getY()/100;
		
		//need to create a destination rectangle
		Rect dest = new Rect((int)x,(int)y, (int)x+width, (int)y+height);
		canvas.drawBitmap(bitmap, src_rect,dest,null);
	}
	
	//flip the helicopter
	public void turnHelicopter(){
		Matrix mirrorMatrix = new Matrix();
        mirrorMatrix.preScale(-1, 1);
        Bitmap turnMap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mirrorMatrix, false);
        turnMap.setDensity(DisplayMetrics.DENSITY_DEFAULT);
        bitmap = new BitmapDrawable(turnMap).getBitmap();
		
	}
	
	public void updateFrame(long time){
		if(time > this.time + fps){
			//set the new updated time
			this.time = time;
			//change the frame
			currentFrame++;
			
			//reset framecounter
			if(currentFrame >= frameCount){
				currentFrame=0;
			}
		}
		
		this.src_rect.left = currentFrame*width;
		this.src_rect.right = this.src_rect.left+width;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getWidth(){return width;}
	public float getHeight(){return height;}
	
	public Rect getHelicopterRect(){
		// Rect(int left, int top, int right, int bottom);
		return new Rect((int)x, (int)y, (int)x+width, (int)y+height);
	}
}
