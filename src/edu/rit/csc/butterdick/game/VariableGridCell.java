package edu.rit.csc.butterdick.game;

import edu.rit.csc.butterdick.lambda.LambdaColor;
import edu.rit.csc.butterdick.R;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;

import android.content.Context;

public class VariableGridCell extends GameGridCell
{
	public VariableGridCell(LambdaColor color)
	{
		super(color);
	}

	public Bitmap getBitmap(Context ctxt)
	{
		// TODO: Return a unique bitmap for each expression
		int id = 0;
		switch (color)
		{
			case BLUE:
				id = R.drawable.powerpebbleblue;
				break;
			case RED:
				id = R.drawable.powerpebblered;
				break;
		}
		return BitmapFactory.decodeResource(ctxt.getResources(), id);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof VariableGridCell)
			return color == ((VariableGridCell)obj).color;
		return false;
	}
}
