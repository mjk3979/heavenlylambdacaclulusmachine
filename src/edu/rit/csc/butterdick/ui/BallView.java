package edu.rit.csc.butterdick.ui;

import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.content.Context;
import android.view.MotionEvent;
import android.graphics.Color;

import edu.rit.csc.butterdick.game.*;

public class BallView extends SurfaceView implements SurfaceHolder.Callback {

	// The cell we are currently moving
    private GameGridCell cell;
	private MainGame game;
	private boolean shouldDraw;
    private int width, height;
	private int x, y;

    public BallView(Context context, int w, int h, MainGame game) {
        super(context);

        width=w;
        height=h;
		this.game = game;

		shouldDraw = false;

        getHolder().addCallback(this);
        setFocusable(true);

		updateBall();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
		if (canvas == null)
			return;
		canvas.drawColor(Color.BLACK);
		game.getGrid().draw(canvas, getContext());

		if (shouldDraw)
		{
			Bitmap bitmap = cell.getBitmap(getContext());
			canvas.drawBitmap(bitmap,x-(bitmap.getWidth()/2),y-(bitmap.getHeight()/2),null);
		}
    }

	private int[] convertToGridCoords(int x, int y)
	{
		GameGrid grid = game.getGrid();
		int col = (x * grid.getWidth())/width;
		int row = (y * grid.getHeight())/height;

		if (col >= grid.getWidth())
			col = grid.getWidth() - 1;
		if (row >= grid.getHeight())
			row = grid.getHeight() -1;

		return new int[] {row, col};
	}

	private void handleFirstTouch(int x, int y)
	{
		int[] gridCoords = convertToGridCoords(x, y);
		int row = gridCoords[0];
		int col = gridCoords[1];

		System.out.printf("COORDS: (%d, %d)\n", row, col);

		GameGrid grid = game.getGrid();
		if (grid.notEmpty(row, col))
		{
			cell = grid.remove(row, col);
			shouldDraw = true;
			this.x = x;
			this.y = y;
		}
	}

	private void handleDrag(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	private void handleDrop(int x, int y)
	{
		int[] gridCoords = convertToGridCoords(x, y);
		int row = gridCoords[0];
		int col = gridCoords[1];

		GameGrid grid = game.getGrid();
		grid.set(row, col, cell);
		cell = null;
		shouldDraw = false;
	}

    @Override
    public boolean onTouchEvent(MotionEvent event)
	{
        int x=(int)event.getX();
        int y=(int)event.getY();

        if(x < 25)
            x = 25;
        if(x > width)   
            x = width;
        if(y < 25)
            y = 25;
        if(y > height)
            y = height;

		// Type of touch event
		switch (event.getAction())
		{
			// First touch
			case MotionEvent.ACTION_DOWN:
				handleFirstTouch(x, y);
				break;

			// Dragged
			case MotionEvent.ACTION_MOVE:
				handleDrag(x, y);
				break;

			// Dropped
			case MotionEvent.ACTION_UP:
				handleDrop(x, y);
				break;
		}

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
