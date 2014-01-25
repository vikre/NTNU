package com.example.helicopter;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.input.TouchListener;

public class States extends State implements TouchListener {
	private Helicopter main;
	private Helicopter hc1;
	private Helicopter hc2;
	private int width;
	private int height;
	
	public States(Resources res){
		main = new Helicopter(BitmapFactory.decodeResource(res, R.drawable.mange_helikoptre),
				0, 0, 100, 4);
		main.setSpeed(900,900 );//set a start speed
		main.update(System.currentTimeMillis());//update every millisec
		
		hc1 = new Helicopter(BitmapFactory.decodeResource(res, R.drawable.mange_helikoptre),
			300, 100, 100, 4);
		hc1.setSpeed(900,-900 );//set a start speed
		hc1.update(System.currentTimeMillis());//update every millisec
		
		hc2 = new Helicopter(BitmapFactory.decodeResource(res, R.drawable.mange_helikoptre),
				100, 400, 100, 4);
		hc2.setSpeed(900,900);//set a start speed
		hc2.update(System.currentTimeMillis());//update every millisec
	}
	
	@Override
	public boolean onTouchMove(MotionEvent event){
		main.setPosition(event.getX(),event.getY());
		return true;
	}
	
	@Override
	public boolean onTouchUp(MotionEvent event){//Click
		main.setPosition(event.getX(), event.getY());
		return true;
	}
	@Override
	public void update(float t){
		//updates the helicopters
		main.update(System.currentTimeMillis());
		hc1.update(System.currentTimeMillis());
		hc2.update(System.currentTimeMillis());
		
		//collision detection left right
		if(main.getX() > (width-main.getWidth())|| main.getX()<0){
			main.setSpeed(-main.getSpeed().getX(), main.getSpeed().getX());
			main.turnHelicopter();
		}
		//collision detection up down
		if(main.getY() > (height-main.getHeight())|| main.getY()<0){
			main.setSpeed(main.getSpeed().getX(),-main.getSpeed().getY());
		}
		
		//hc1
		//collision detection left right
		if(hc1.getX() > (width-hc1.getWidth())|| hc1.getX()<0){
			hc1.setSpeed(-hc1.getSpeed().getX(), hc1.getSpeed().getX());
			hc1.turnHelicopter();
		}
		//collision detection up down
		if(hc1.getY() > (height-hc1.getHeight())|| hc1.getY()<0){
			hc1.setSpeed(hc1.getSpeed().getX(),-hc1.getSpeed().getY());
		}
		
		//hc2
		//collision detection left right
		if(hc2.getX() > (width-hc2.getWidth())|| hc2.getX()<0){
			hc2.setSpeed(-hc2.getSpeed().getX(), hc2.getSpeed().getX());
			hc2.turnHelicopter();
		}
		//collision detection up down
		if(hc2.getY() > (height-hc2.getHeight())|| hc2.getY()<0){
			hc2.setSpeed(hc2.getSpeed().getX(),-hc2.getSpeed().getY());
		}
		
		//collision detection between helicopters
		if(main.getHelicopterRect().intersect(hc1.getHelicopterRect())|| 
				hc1.getHelicopterRect().intersect(main.getHelicopterRect())){
			main.setSpeed(-main.getSpeed().getX(), main.getSpeed().getY());
			main.turnHelicopter();
			hc1.setSpeed(-hc1.getSpeed().getX(),-hc1.getSpeed().getY());
			hc1.turnHelicopter();
		}
		//collision detection between helicopters
		if(main.getHelicopterRect().intersect(hc2.getHelicopterRect())|| 
				hc2.getHelicopterRect().intersect(main.getHelicopterRect())){
			main.setSpeed(-main.getSpeed().getX(), main.getSpeed().getY());
			main.turnHelicopter();
			hc2.setSpeed(-hc2.getSpeed().getX(),-hc2.getSpeed().getY());
			hc2.turnHelicopter();
		}
		//collision detection between helicopters
		if(hc2.getHelicopterRect().intersect(hc1.getHelicopterRect())|| 
				hc1.getHelicopterRect().intersect(hc2.getHelicopterRect())){
			hc2.setSpeed(-hc2.getSpeed().getX(), hc2.getSpeed().getY());
			hc2.turnHelicopter();
			hc1.setSpeed(-hc1.getSpeed().getX(),-hc1.getSpeed().getY());
			hc1.turnHelicopter();
		}
				
				
		
	}
	
	@Override
	public void draw(Canvas canvas){
		height = canvas.getHeight();
		width = canvas.getWidth();
		canvas.drawColor(Color.DKGRAY);
		//Draw the helicopters
		main.draw(canvas);
		hc1.draw(canvas);
		hc2.draw(canvas);
		
		//Config the font 
		Font font = new Font(0,60,20,30,Typeface.SERIF, Typeface.NORMAL);
		canvas.drawText("Helicopter pos x: " +main.getX() + ", y: "+ main.getY(),30,30,font);
	}
	
	
}
