package edu.rit.csc.butterdick.game;

import edu.rit.csc.butterdick.lambda.*;

public class LambdaGridConverter
{
	private static int SUB_GRID_SIZE = 100; // TODO: Somehow make this dyanamic

	public static GameGrid convertExpressionToGameGrid(Expression expr)
	{
		GameGrid subGrid = new GameGrid(SUB_GRID_SIZE, SUB_GRID_SIZE);
		convertExpressionToGameGridHelper(expr, subGrid, 0, 0);
		return subGrid;
	}

	private static int convertExpressionToGameGridHelper(Expression expr, GameGrid subGrid, int rowOffset, int colOffset)
	{
		if (expr instanceof Variable)
		{
			subGrid.set(rowOffset, colOffset, new VariableGridCell(((Variable)expr).getColor()));
			return 1;
		}
		else if (expr instanceof Lambda)
		{
			Lambda l = (Lambda)expr;
			int width = convertExpressionToGameGridHelper(l.getBody(), subGrid, rowOffset+1, colOffset);
			subGrid.set(rowOffset, colOffset, new LambdaGridCell(l.getColor(), width));
			return width;
		}
		else if (expr instanceof Pair)
		{
			Pair p = (Pair)expr;
			int width = convertExpressionToGameGridHelper(p.getFirst(), subGrid, rowOffset, colOffset);
			width += convertExpressionToGameGridHelper(p.getSecond(), subGrid, rowOffset, colOffset+width);
			return width;
		}

		// Assert false
		return 5/0;
	}

	private static int[] findTopLeft(GameGrid grid)
	{
		int r, c;
		r = c = -1;
		boolean found = false;

		outer:
		for (r=0;r<grid.getHeight();++r)
		{
			for (c=0;c<grid.getWidth();++c)
			{
				if (grid.get(r, c) != null)
				{
					found = true;
					break outer;
				}
			}
		}

		if (found)
			return new int[] {r, c};
		return null;
	}

	public static Expression convertGameGridToExpression(GameGrid grid) throws GameGridConversionException
	{
		// The top-left of the board is where the expression starts
		int[] topLeft = findTopLeft(grid);

		// If the grid is empty, then there will be no top-left
		if (topLeft == null)
			return null;

		return convertGameGridToExpressionHelper(grid, topLeft[0], topLeft[1]).expr;
	}

	private static class ExpressionAndWidth
	{
		public Expression expr;
		public int width;

		public ExpressionAndWidth(Expression expr, int width)
		{
			this.expr = expr;
			this.width = width;
		}
	}

	private static ExpressionAndWidth convertGameGridToExpressionHelper(GameGrid grid, int r, int c) throws GameGridConversionException
	{
		GameGridCell cell = grid.get(r, c);

		if (cell == null)
			throw new GameGridConversionException(String.format("Cell at position (%d, %d) should not be empty", r, c));
		
		if (cell instanceof LambdaGridCell)
		{
			LambdaGridCell lambdaCell = (LambdaGridCell)cell;
			
			ExpressionAndWidth first = convertGameGridToExpressionHelper(grid, r+1, c);
			while (first.width < lambdaCell.getWidth())
			{
				ExpressionAndWidth second = convertGameGridToExpressionHelper(grid, r+1, c+first.width);
				first.width += second.width;
				first.expr = new Pair(first.expr, second.expr);
			}

			if (first.width > lambdaCell.getWidth())
				throw new GameGridConversionException(String.format("Lambda at position(%d, %d) is too small for its contents", r, c));

			return first;
		}
		else if (cell instanceof VariableGridCell)
		{
			return new ExpressionAndWidth(new Variable(cell.getColor()), 1);
		}
		throw new GameGridConversionException("WTF?");
	}
}
