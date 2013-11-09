package edu.rit.csc.butterdick.ui;

import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.content.Context;
import android.view.MotionEvent;
import android.graphics.Color;

public class BallView extends SurfaceView implements SurfaceHolder.Callback {
    private Bitmap bitmap ;
    private int x=20,y=20;int width,height;

    public BallView(Context context, Bitmap bitmap, int w, int h) {
        super(context);

		this.bitmap = bitmap;
        width=w;
        height=h;
        getHolder().addCallback(this);
        setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLUE);
        canvas.drawBitmap(bitmap,x-(bitmap.getWidth()/2),y-(bitmap.getHeight()/2),null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x=(int)event.getX();
        y=(int)event.getY();

        if(x < 25)
            x = 25;
        if(x > width)   
            x = width;
        if(y < 25)
            y = 25;
        if(y > height)
            y = height;

        updateBall();

        return true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
        // TODO Auto-generated method stub
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    }

    private void updateBall() {
        Canvas canvas = null;
        try {
            canvas = getHolder().lockCanvas(null);
            synchronized (getHolder()) {
                this.onDraw(canvas);
            }
        }
        finally {
            if (canvas != null) {
                getHolder().unlockCanvasAndPost(canvas);
            }
        }
    }   
}
