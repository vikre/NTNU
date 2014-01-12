package com.example.helicopter;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

@SuppressLint("WrongCall") public class GameThread extends Thread{
	
	static final long FPS = 10;
	private GameView gameview;
	private boolean runs = false;
	
	public GameThread(GameView gameview){
		this.gameview = gameview;
	}
	
	public void setRuns(boolean run){
		this.runs = run;
	}
	
	@Override
	public void run(){
		long startTime;
		long sleepTime;
		long tps = 1000/FPS;
		
		while(runs){
			Canvas c = null;
			startTime = System.currentTimeMillis();
			try {
				c = gameview.getHolder().lockCanvas();
				//needs to be asure that the thread is synced
				synchronized (gameview.getHolder()) {
					gameview.onDraw(c);
				}
			} finally {
				if(c!=null){
					gameview.getHolder().unlockCanvasAndPost(c);
				}
			} 
			sleepTime = tps - (System.currentTimeMillis() - startTime);
			try {
				if(sleepTime > 0) sleep(sleepTime);
				else sleep(10);
			} catch (Exception e) {}
		}
	}
}
