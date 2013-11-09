package edu.rit.csc.butterdick.game;

import edu.rit.csc.butterdick.lambda.*;
import android.graphics.Bitmap;

import android.content.Context;

public abstract class GameGridCell
{
	private static int SUB_GRID_SIZE = 100; // TODO: Somehow make this dyanamic

	protected LambdaColor color;

	public GameGridCell(LambdaColor color)
	{
		this.color = color;
	}

	public abstract Bitmap getBitmap(Context ctxt);

	public static GameGrid convertExpressionToGameGridCells(Expression expr)
	{
		GameGrid subGrid = new GameGrid(SUB_GRID_SIZE, SUB_GRID_SIZE);
		convertExpressionToGameGridCellsHelper(expr, subGrid, 0, 0);
		return subGrid;
	}

	private static int convertExpressionToGameGridCellsHelper(Expression expr, GameGrid subGrid, int rowOffset, int colOffset)
	{
		if (expr instanceof Variable)
		{
			subGrid.set(rowOffset, colOffset, new VariableGridCell(((Variable)expr).getColor()));
			return 1;
		}
		else if (expr instanceof Lambda)
		{
			Lambda l = (Lambda)expr;
			int width = convertExpressionToGameGridCellsHelper(l.getBody(), subGrid, rowOffset+1, colOffset);
			subGrid.set(rowOffset, colOffset, new LambdaGridCell(l.getColor(), width));
		}
		else if (expr instanceof Pair)
		{
			Pair p = (Pair)expr;
			int width = convertExpressionToGameGridCellsHelper(p.getFirst(), subGrid, rowOffset, colOffset);
			width += convertExpressionToGameGridCellsHelper(p.getSecond(), subGrid, rowOffset, colOffset+width);
			return width;
		}

		// Assert false
		return 5/0;
	}
}
