package kp.enterprise.graphicsexample10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class GraphicsView extends View implements View.OnTouchListener{
    private Paint p;
    private int resImage[] = {
            R.drawable.ufo_bm_01,R.drawable.ufo_bm_02,
            R.drawable.ufo_bm_03,R.drawable.ufo_bm_04,
            R.drawable.ufo_bm_05, R.drawable.ufo_bm_06,
            R.drawable.ufo_bm_07, R.drawable.ufo_bm_08,
            R.drawable.ufo_bm_09, R.drawable.ufo_bm_10,
            R.drawable.ufo_bm_11, R.drawable.ufo_bm_12,
            R.drawable.ufo_bm_13, R.drawable.ufo_bm_14,
            R.drawable.ufo_bm_15, R.drawable.ufo_bm_16,
            R.drawable.ufo_bm_17, R.drawable.ufo_bm_18,
            R.drawable.ufo_bm_19, R.drawable.ufo_bm_20
    };
    private Bitmap image[] = new Bitmap[resImage.length];
    private int Num = 0;
    float x, y;
    private boolean Play;
    private CountDownTimer animation;
    private Random rnd = new Random();

    public GraphicsView(Context context) {
        super(context);
        p = new Paint();
        Play = false;
        for (int n = 0; n < resImage.length ; n++)
            image[n] = BitmapFactory.decodeResource( getResources(),

                    resImage[n]);

        x = rnd.nextInt(900) - image[0].getWidth()/2;
        y = rnd.nextInt(1200) - image[0].getHeight()/2;
        animation = new CountDownTimer(10000, 100){
            public void onTick(long millisUnitsFinished)
            {
                Num = (Num + 1 == resImage.length) ? 0 : Num+1;
                invalidate();
            }
            public void onFinish()
            {
                Play = false;
                Num = 0;
                invalidate();
            }
        };
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (!Play) {
            Play = !Play;
            animation.start();
            invalidate();
        }
        return(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setTextAlign(Paint.Align.CENTER);
        p.setTextSize(70);
        canvas.drawText("Animation", getWidth()/2, 100, p);
        x = getWidth()/2 - image[0].getWidth() /2;
        y = getHeight()/2 - image[0].getHeight() /2;
        if (Play) {
            p.setTextSize(50);
            canvas.drawText("PLAY", 100, getHeight()-60, p);
            canvas.drawBitmap(image[Num], x, y, null);
        }
        else {
            p.setTextSize(50);
            canvas.drawText("STOP", 100, getHeight() - 60, p);
            canvas.drawBitmap(image[Num], x, y, null);
        }
    }
}
