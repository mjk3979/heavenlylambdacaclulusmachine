package edu.rit.csc.butterdick.ui;

import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.content.Context;
import android.view.MotionEvent;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Rect;

import edu.rit.csc.butterdick.game.*;

public class BallView extends SurfaceView implements SurfaceHolder.Callback
{
	private static class TouchInfo
	{
		public DragAndDropGrid<GameGridCell> grid;
		public int row, col;
	}

	// The cell we are currently moving
    private GameGridCell cell;
	private MainGame game;
	private boolean shouldDraw;
    private int width, height;
	private int x, y;
	private Rect mainGridRect, invRect;

    public BallView(Context context, MainGame game) {
        super(context);

		this.game = game;

		width = -1;
		height = -1;

		shouldDraw = false;

        getHolder().addCallback(this);
        setFocusable(true);

		updateBall();
    }

	private void calcDim()
	{
        width=getWidth();
        height=getHeight();

		mainGridRect = new Rect(0, 0, width, (height * 2) / 3);
		invRect = new Rect(0, (height * 3) / 4, width, height);
	}

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
		if (canvas == null)
			return;
		
		calcDim();

		canvas.drawColor(Color.BLACK);
		game.getGrid().draw(canvas, mainGridRect.width(), mainGridRect.height(), getContext());
		game.getInventory().draw(canvas, getContext(), invRect.width(), invRect.height(), invRect.left, invRect.top);

		if (shouldDraw && height > 0 && width > 0)
		{
			Bitmap bitmap = cell.getBitmap(getContext());
			float size = width / game.getGrid().getWidth();
			float l = x - size/4;
			float ri = x + size/4;
			float t = y - size/4;
			float b = y + size/4;
			RectF dst = new RectF(l, t, ri, b);
			canvas.drawBitmap(bitmap,null,dst,null);
		}
    }

	private TouchInfo convertToGridCoords(int x, int y)
	{
		TouchInfo retval = new TouchInfo();
		int col, row;
		if (mainGridRect.contains(x, y))
		{
			GameGrid grid = game.getGrid();
			col = (x * grid.getWidth())/mainGridRect.width();
			row = (y * grid.getHeight())/mainGridRect.height();

			retval.grid = grid;
		}
		else if (invRect.contains(x, y))
		{
			InventoryGrid inv = game.getInventory();
			row = 0;
			col = ((x - invRect.left)  * inv.getWidth())/invRect.width();

			retval.grid = inv;
		}
		else
			return null;

		if (col >= retval.grid.getWidth())
			col = retval.grid.getWidth() - 1;
		if (row >= retval.grid.getHeight())
			row = retval.grid.getHeight() -1;

		retval.col = col;
		retval.row = row;

		return retval;
	}

	private void handleFirstTouch(int x, int y)
	{
		calcDim();

		TouchInfo info = convertToGridCoords(x, y);
		if (info == null)
			return; 

		int row = info.row;
		int col = info.col;

		DragAndDropGrid<GameGridCell> grid = info.grid;
		if (grid.notEmpty(row, col))
		{
			cell = grid.remove(row, col);
			game.setMovingCell(cell);
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
		TouchInfo info = convertToGridCoords(x, y);
		int row = info.row;
		int col = info.col;

		DragAndDropGrid<GameGridCell> grid = info.grid;
		grid.set(row, col, cell);
		cell = null;
		game.setMovingCell(null);
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
