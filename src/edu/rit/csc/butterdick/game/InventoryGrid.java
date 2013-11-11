package edu.rit.csc.butterdick.game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.content.Context;
import android.graphics.Color;
import java.util.ArrayList;
import edu.rit.csc.butterdick.lambda.LambdaColor;

public class InventoryGrid
{
	public static LambdaColor[] COLORS_TO_CONSIDER = new LambdaColor[] {LambdaColor.BLUE, LambdaColor.RED};
	private GameGrid mainGrid;

	private int lineWidth = GameGrid.lineWidth;

	public InventoryGrid(GameGrid grid)
	{
		this.mainGrid = grid;
	}

	public ArrayList<LambdaGridCell> getLambdaCellsToDraw()
	{
		ArrayList<LambdaGridCell> lambdaCells = new ArrayList<LambdaGridCell>();
		for (LambdaColor color : COLORS_TO_CONSIDER)
			lambdaCells.add(new LambdaGridCell(color, 1));
		for (int r=0;r<mainGrid.getHeight();++r)
		{
			for (int c=0;c<mainGrid.getWidth();++c)
			{
				if (mainGrid.get(r,c) instanceof LambdaGridCell)
					lambdaCells.set(lambdaCells.indexOf(mainGrid.get(r,c)), null);
			}
		}
		return lambdaCells;
	}

	public void draw(Canvas canvas, Context ctxt, int width, int height, int startX, int startY)
	{
		canvas.save();
		canvas.translate(startX, startY);
		ArrayList<LambdaGridCell> lambdaCells = getLambdaCellsToDraw();
		int gridWidth = COLORS_TO_CONSIDER.length;

		height = Math.min((width / gridWidth), height);
		width = height * gridWidth;

		Paint linePaint = new Paint();
		linePaint.setColor(Color.WHITE);
		linePaint.setStrokeWidth(GameGrid.lineWidth);
		
		canvas.drawLine(0, 0, width, 0, linePaint);
		canvas.drawLine(0, height, width, height, linePaint);
		for (int r=0;r<gridWidth;++r)
		{
			float l = r * width / (float)gridWidth;
			float ri = (r+1) * width / (float)gridWidth;
			float t = 0;
			float b = height;
			canvas.drawLine(l, t, l, b, linePaint);

			RectF dst = new RectF(l+lineWidth, t+lineWidth, ri-lineWidth, b-lineWidth);
			if (lambdaCells.get(r) != null)
				canvas.drawBitmap(lambdaCells.get(r).getBitmap(ctxt), null, dst, null);
		}
		float l = (float)(width-lineWidth/2.0);
		float t = 0;
		float r = (float)(width-lineWidth/2.0);
		float b = height;
		canvas.drawLine(l, t, r, b, linePaint);
		canvas.restore();
	}
}
