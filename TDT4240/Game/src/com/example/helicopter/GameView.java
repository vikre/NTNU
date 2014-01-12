package com.example.helicopter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint("WrongCall") public class GameView extends SurfaceView {

	private Bitmap bitmap;
	private SurfaceHolder sh;
	private GameThread thread;
	private Sprite sprite;
	
	public GameView(Context context) {
		super(context);
		thread = new GameThread(this);
		sh = getHolder();
		//Adding a surfaceholder callback
		sh.addCallback(new SurfaceHolder.Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean again = true;
				thread.setRuns(false);
				while (again) {
					try {
						thread.join();
						again = false;
					} catch (InterruptedException e) {
						// TODO: handle exception
					}
				}
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				thread.setRuns(true);
				thread.start();
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
				// TODO Auto-generated method stub
				
			}
		});
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		sprite = new Sprite(this, bitmap);
	}

	@Override
	protected void onDraw(Canvas canvas){
		canvas.drawColor(Color.DKGRAY);
		sprite.onDraw(canvas);
	}
}
