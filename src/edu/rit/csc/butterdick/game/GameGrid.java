package edu.rit.csc.butterdick.game;

import edu.rit.csc.butterdick.lambda.Expression;

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
}
