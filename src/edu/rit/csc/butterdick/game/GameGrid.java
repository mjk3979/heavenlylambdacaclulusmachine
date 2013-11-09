package edu.rit.csc.butterdick.game;

import edu.rit.csc.butterdick.lambda.Expression;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Bitmap;
import android.content.Context;
import android.graphics.Color;

public class GameGrid
{
	int width;
	int height;
	private GameGridCell grid[][];

	public static final int lineWidth = 5;

	public GameGrid(int width, int height)
	{
		this.width = width;
		this.height = height;
		this.grid = new GameGridCell[width][height];
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public void set(int row, int col, GameGridCell cell)
	{
		grid[row][col] = cell;
	}

	public GameGridCell get(int row, int col)
	{
		return grid[row][col];
	}

	public GameGridCell remove(int row, int col)
	{
		GameGridCell retval = get(row, col);
		set(row, col, null);
		return retval;
	}

	public boolean notEmpty(int row, int col)
	{
		return grid[row][col] != null;
	}

	public boolean equals(Object obj)
	{
		if (obj instanceof GameGrid)
		{
			GameGrid grid = (GameGrid)obj;
			if (width != grid.getWidth() || height != grid.getHeight())
				return false;
			for (int row=0;row<height; ++row)
			{
				for (int col=0; col<width; ++col)
				{
					GameGridCell cell1 = this.grid[row][col];
					GameGridCell cell2 = grid.get(row,col);
					if (!(cell1 == null && cell2 == null) && !cell1.equals(cell2))
						return false;
				}
			}
			return true;
		}
		return false;
	}

	public void draw(Canvas canvas, int cWidth, int cHeight, Context context)
	{
		Paint linePaint = new Paint();
		linePaint.setStrokeWidth(lineWidth);
		linePaint.setColor(Color.WHITE);
		for (int r=0;r<=height;++r)
		{
			System.out.println("CANVAS:" + canvas);
			float y = r * (cHeight / height);
			canvas.drawLine(0, y, cWidth, y, linePaint);
		}
		for (int c=0;c<=width;++c)
		{
			float x = c * (cWidth / width);
			canvas.drawLine(x, 0, x, cHeight, linePaint);
		}
		for (int r=0;r<height;++r)
		{
			for (int c=0;c<width;++c)
			{
				if (notEmpty(r, c))
				{
					float t = r * (cHeight / height);
					float l = c * (cWidth / width);

					float b = (r+1) * (cHeight / height);
					float ri = (c+1) * (cWidth / width);
					Bitmap bitmap = grid[r][c].getBitmap(context);
					RectF dst = new RectF(l+lineWidth, t+lineWidth, ri-lineWidth, b-lineWidth);
					canvas.drawBitmap(bitmap, null, dst, null);
				}
			}
		}
	}
}
