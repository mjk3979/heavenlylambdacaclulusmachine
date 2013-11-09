package edu.rit.csc.butterdick.test;

import edu.rit.csc.butterdick.lambda.*;
import edu.rit.csc.butterdick.game.*;

public class LambdaConvertTest
{
	public boolean test1()
	{
		Expression expr = new Lambda(LambdaColor.BLUE, new Variable(LambdaColor.BLUE));
		GameGrid result = GameGridCell.convertExpressionToGameGrid(expr);
		GameGrid correct = new GameGrid(100, 100);
		correct.set(0,0,new LambdaGridCell(LambdaColor.BLUE, 1));
		correct.set(1,0, new VariableGridCell(LambdaColor.BLUE));
		return correct.equals(result);
	}
}
