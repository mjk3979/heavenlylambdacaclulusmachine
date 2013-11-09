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

	public void draw(Canvas canvas, Context context)
	{
		Paint linePaint = new Paint();
		int lineWidth = 5;
		linePaint.setStrokeWidth(lineWidth);
		linePaint.setColor(Color.WHITE);
		for (int r=0;r<=height;++r)
		{
			System.out.println("CANVAS:" + canvas);
			float y = r * (canvas.getHeight() / height);
			canvas.drawLine(0, y, canvas.getWidth(), y, linePaint);
		}
		for (int c=0;c<=width;++c)
		{
			float x = c * (canvas.getWidth() / width);
			canvas.drawLine(x, 0, x, canvas.getHeight(), linePaint);
		}
		for (int r=0;r<height;++r)
		{
			for (int c=0;c<width;++c)
			{
				if (notEmpty(r, c))
				{
					float t = r * (canvas.getHeight() / height);
					float l = c * (canvas.getWidth() / width);

					float b = (r+1) * (canvas.getHeight() / height);
					float ri = (c+1) * (canvas.getWidth() / width);
					Bitmap bitmap = grid[r][c].getBitmap(context);
					RectF dst = new RectF(l-lineWidth, t-lineWidth, ri-lineWidth, b-lineWidth);
					canvas.drawBitmap(bitmap, null, dst, null);
				}
			}
		}
	}
}
