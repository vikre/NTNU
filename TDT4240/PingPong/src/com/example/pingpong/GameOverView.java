package com.example.pingpong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.renderscript.Type;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.input.TouchListener;

public class GameOverView extends State implements TouchListener {
	
	private int winner;
	private Font font, font2;
	
	public GameOverView(int player){
		this.winner = player;
		font = new Font(88,88,88,50,Typeface.SERIF,Typeface.BOLD);
		font2 = new Font(100,100,100,20, Typeface.SERIF,Typeface.NORMAL);
		
		font.setTextAlign(Align.CENTER);
		font2.setTextAlign(Align.CENTER);
	}
	
	public void draw(Canvas c){
		c.drawColor(Color.BLACK);
		c.drawText("And the winner is", c.getWidth()/2, 200, font);
		c.drawText("player "+winner+"!", c.getWidth()/2, 250, font2);
		c.drawText("Tap the screen to start a new game", c.getWidth()/2, 450, font2);
	}
	
	public boolean onTouchUp(MotionEvent e){
		getGame().popState();
		getGame().pushState(new States());
		return true;
	}
}
