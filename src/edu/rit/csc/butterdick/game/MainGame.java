package edu.rit.csc.butterdick.game;

import edu.rit.csc.butterdick.lambda.LambdaColor;

public class MainGame
{
	private GameGrid grid;

	public MainGame()
	{
		grid = new GameGrid(5,5);
		grid.set(0, 4, new LambdaGridCell(LambdaColor.BLUE, 1));
		grid.set(1, 4, new VariableGridCell(LambdaColor.BLUE));
	}

	public GameGrid getGrid()
	{
		return grid;
	}
}
