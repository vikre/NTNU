package com.example.pingpong;

import com.example.pingpong.models.Ball;
import com.example.pingpong.models.Paddle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.input.TouchListener;

public class States extends State implements TouchListener{

	private int width;
	private int height;
	
//	private Sprite paddle, paddle2;
	private Paddle paddle, paddle2;
	
	private Image img,img2;
    private Ball ball;
	private int points, points2;
	private Font font;
	
	public States(){
		//loading img
		img = new Image(R.drawable.pongpaddle);
		img2 = new Image(R.drawable.pongpaddle);
		
		//creating sprites
		paddle = new Paddle(img);
		paddle2 = new Paddle(img2);
		ball = Ball.getInstance();
		
		//font and counters
		font = new Font(0, 55, 20, 20, Typeface.SERIF, Typeface.NORMAL);
		points = 0;
		points2 = 0;
		
		//placing the paddles and ball and set the ball speed
		paddle.setPosition(200, 80);
		paddle2.setPosition(200, 610);
		ball.setPosition(250, 200);
		ball.setSpeed(250, 170);
	}
	
	public void draw(Canvas c){
		height = c.getHeight();
		width = c.getWidth();
		c.drawColor(Color.WHITE);
		
		//place points on the screen
		c.drawText(""+points, 40, 310,font);
		c.drawText(""+points2, 390, 400, font);
		c.drawRect(0, 340,480,340,sheep.graphics.Color.BLACK);
		
		//draw
		paddle.draw(c);
		paddle2.draw(c);
		ball.draw(c);
	}
	
	//divide stearingpad for player 1 and 2 on the screen
	public boolean onTouchMove(MotionEvent e){
		if(e.getY() < height/2){
			paddle.setPosition(e.getX(), e.getY());
			return true;
		}
		if(e.getY()> height/2){
			paddle2.setPosition(e.getX(), e.getY());
			return true;
		}
		return false;
	}
	
	public void update(float t){
		paddle.update(t);
		paddle2.update(t);
		ball.update(t);
		
		//check if there is a winner
		if(points == 2){
			getGame().popState();
			getGame().pushState(new GameOverView(1));
		}
		if(points2 == 2){
			getGame().popState();
			getGame().pushState(new GameOverView(2));
		}
		
		//collision with paddle
		if(ball.collides(paddle)){
			ball.setSpeed(ball.getSpeed().getX(), -ball.getSpeed().getY());
		}
		if(ball.collides(paddle2)){
			ball.setSpeed(ball.getSpeed().getX(), -ball.getSpeed().getY());
		}
		
		//collision with wall
		if(ball.getY() > (height-ball.getBall_img().getHeight())|| ball.getY()< 0){
			ball.setSpeed(ball.getSpeed().getX(),-ball.getSpeed().getY());
		}
		if(ball.getX()> (width-ball.getBall_img().getWidth())|| ball.getX()<0){
			ball.setSpeed(-ball.getSpeed().getX(), ball.getSpeed().getY());
		}
		
		//check if point should be given
		if(ball.getY() < paddle.getY()){
			ball.setPosition(height/2, width/2);
			points++;
			Log.w("States", "One point for player 2: "+points2);
		}
		if(ball.getY() > paddle2.getY()){
			ball.setPosition(height/2, width/2);
			points2++;
			Log.w("States","One point for player 1: "+points);
		}
	}

}
