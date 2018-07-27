package com.example.kamlesh.flyingfish;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

public class FlyingFishView extends View{
    private Bitmap fish[] = new Bitmap[2];
    private int pos_x = 10;
    private int pos_y;
    private int speed;
    private int canvasWidth,canvasHight;
    private int min_y,max_y;
    private boolean touch = false;
    private Bitmap background;
    private Bitmap life[] = new Bitmap[2];
    private Paint score =   new Paint();
    private int yellowX ,yellowY,yellowSpeed=10;
    private Paint yellowPaint = new Paint();
    private int greenX,greenY,greenSpeed = 15;
    private Paint greenPaint = new Paint();
    private int redX,redY,redSpeed = 15;
    private Paint redPaint = new Paint();
    private int Score;
    private int count;
    public FlyingFishView(Context context) {
        super(context);
        fish[0] = BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(),R.drawable.fish2);
        background = BitmapFactory.decodeResource(getResources(),R.drawable.background);
        life[0] = BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);
        life[1] = BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);
        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);
        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);
        score.setColor(Color.WHITE);
        score.setTextSize(70);
        score.setTypeface(Typeface.DEFAULT_BOLD);
        score.setAntiAlias(true);
        pos_y = 100;
        Score = 0;
        count = 0;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasWidth  = canvas.getWidth();
        canvasHight = canvas.getHeight();
        min_y = fish[0].getHeight();
        max_y = canvasHight - min_y*2;
        pos_y = pos_y + speed;

        if(pos_y>max_y){
            pos_y = max_y;
        }
        if(pos_y<min_y){
            pos_y=min_y;
        }
        speed = speed + 2;
        canvas.drawBitmap(background,0,0,null);
        if(touch){
            canvas.drawBitmap(fish[1],pos_x,pos_y,null);
            touch = false;
        }
        else{
            canvas.drawBitmap(fish[0],pos_x,pos_y,null);
        }

        yellowX = yellowX -yellowSpeed;
        greenX = greenX - greenSpeed;
        redX = redX -redSpeed;
        if(hitchecker(yellowX,yellowY))
        {
            Score += 10;
            yellowX = -100;
        }
        if(yellowX<0)
        {
            yellowX = canvasWidth +21;
            yellowY = (int) Math.floor(Math.random()*(max_y-min_y))+ min_y;
        }
        canvas.drawCircle(yellowX,yellowY,10,yellowPaint);
        if(hitchecker(greenX,greenY))
        {
            Score += 20;
            greenX = -100;
        }
        if(greenX < 0)
        {
            greenX = canvasWidth + 700;
            greenY = (int) Math.floor(Math.random()*(max_y-min_y))+ min_y;
        }
        canvas.drawCircle(greenX,greenY,15,greenPaint);
        if(hitchecker(redX,redY))
        {
            redX = -100;
            count +=1;
        }
        if(count==0)
        {
            canvas.drawBitmap(life[1],380,10,null);
            canvas.drawBitmap(life[1],430,10,null);
            canvas.drawBitmap(life[1],480,10,null);

        }
        if (count == 1)
        {
            canvas.drawBitmap(life[1],380,10,null);
            canvas.drawBitmap(life[1],430,10,null);
            canvas.drawBitmap(life[0],480,10,null);

        }
        if  (count == 2){
            canvas.drawBitmap(life[1],380,10,null);
            canvas.drawBitmap(life[0],430,10,null);
            canvas.drawBitmap(life[0],480,10,null);

        }
        if(count > 2)
        {


        }


        if(redX <0)
        {
            redX = canvasWidth + 400;
            redY = (int) Math.floor(Math.random()*(max_y-min_y))+ min_y;
        }
        canvas.drawCircle(redX,redY,20,redPaint);

        canvas.drawText("Score : "+Score,20,60 , score);
    }

    public boolean hitchecker(int x,int y)
    {
        if(pos_x<x && x<(pos_x+fish[0].getWidth()) && pos_y < y && y< (pos_y + fish[0].getHeight()))
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction()== MotionEvent.ACTION_DOWN)
            {
                touch = true;
                speed = -15;
            }
            return true;
    }


}

