package edu.rit.csc.butterdick.game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.content.Context;
import android.graphics.Color;
import java.util.ArrayList;
import edu.rit.csc.butterdick.lambda.LambdaColor;
import edu.rit.csc.butterdick.ui.DragAndDropGrid;

public class InventoryGrid implements DragAndDropGrid<GameGridCell>
{
	public static LambdaColor[] COLORS_TO_CONSIDER = new LambdaColor[] {LambdaColor.BLUE, LambdaColor.RED};
	private MainGame game;
	private GameGrid mainGrid;

	private int lineWidth = GameGrid.lineWidth;

	public InventoryGrid(MainGame game)
	{
		this.game = game;
		this.mainGrid = game.getGrid();
	}

	@Override
	public int getWidth()
	{
		return mainGrid.getWidth();
	}

	@Override
	public int getHeight()
	{
		return (int)Math.ceil((float)COLORS_TO_CONSIDER.length / mainGrid.getWidth());
	}

	@Override
	public GameGridCell get(int row, int col)
	{
		ArrayList<LambdaGridCell> cells = getLambdaCellsToDraw();
		return cells.get(col);
	}

	@Override
	public void set(int row, int col, GameGridCell cell)
	{
		return;
	}

	@Override
	public GameGridCell remove(int row, int col)
	{
		return get(row, col);
	}

	@Override
	public boolean notEmpty(int row, int col)
	{
		return get(row, col) != null;
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

		if (game.getMovingCell() instanceof LambdaGridCell)
			lambdaCells.set(lambdaCells.indexOf(game.getMovingCell()), null);

		return lambdaCells;
	}

	public void draw(Canvas canvas, Context ctxt, int width, int height, int startX, int startY)
	{
		canvas.save();
		canvas.translate(startX, startY);
		ArrayList<LambdaGridCell> lambdaCells = getLambdaCellsToDraw();
		int gridWidth = getWidth();
		int gridHeight = getHeight();

		height = (width * getHeight()) / gridWidth;

		Paint linePaint = new Paint();
		linePaint.setColor(Color.WHITE);
		linePaint.setStrokeWidth(GameGrid.lineWidth);
		
		outer:
		for (int r=0;r<gridHeight;++r)
		{
			float t = (r * height) / gridHeight;
			float b = ((r+1) * height) / gridHeight;

			for (int c=0;c<gridWidth;++c)
			{

				float l = c * width / (float)gridWidth;
				float ri = (c+1) * width / (float)gridWidth;
				canvas.drawLine(l, t, l, b, linePaint);

				int i = r * gridWidth + c;
				if (i >= lambdaCells.size())
					break outer;

				if (r==0)
					canvas.drawLine(l, t, ri, t, linePaint);
				canvas.drawLine(l, b, ri, b, linePaint);

				RectF dst = new RectF(l+lineWidth, t+lineWidth, ri-lineWidth, b-lineWidth);
				if (lambdaCells.get(i) != null)
					canvas.drawBitmap(lambdaCells.get(i).getBitmap(ctxt), null, dst, null);
			}

			float l = (float)(width-lineWidth/2.0);
			float ri = (float)(width-lineWidth/2.0);
			canvas.drawLine(l, t, ri, b, linePaint);
		}

		canvas.restore();
	}
}
