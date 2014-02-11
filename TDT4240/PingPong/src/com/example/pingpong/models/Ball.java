package com.example.pingpong.models;

import com.example.pingpong.R;
import com.example.pingpong.R.drawable;

import android.graphics.Canvas;
import sheep.graphics.Image;
import sheep.game.Sprite;

/**
 * Created by vikre on 07/02/14.
 *
 * Singleton!!
 */
public class Ball extends Sprite {

    private static final Ball ball = new Ball();
    private Image ball_img;

    public Ball(){
        if (ball != null){
            throw new IllegalStateException("Allready been instansiated");
        }
        ball_img = new sheep.graphics.Image(R.drawable.ball);
        ball.setPosition(250, 200);
        ball.setSpeed(250, 170);
    }

    public static Ball getInstance(){
        return Ball.ball;
    }

    public Image getBall_img(){
        return ball_img;
    }

    public void draw(Canvas c){
        ball.draw(c);
    }

}
