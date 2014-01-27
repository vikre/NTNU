package com.example.pingpong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.input.TouchListener;

public class PingPong extends State implements TouchListener{
	private Font font;
	
	public PingPong(){
		font = new Font(100, 100, 100, 30, Typeface.SANS_SERIF, Typeface.BOLD);
		font.setTextAlign(Align.CENTER);
	}
	//draw the text on the screen
	public void draw(Canvas c){
		c.drawColor(Color.BLACK);
		c.drawText("Tap the screen to start", c.getWidth()/2, 300, font);
	}
	
	//begin a new game
	public boolean onTouchUp(MotionEvent event){
		getGame().popState();
		getGame().pushState(new States());
		return false;
		
	}
	
}
