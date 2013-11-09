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
		return BitmapFactory.decodeResource(ctxt.getResources(), R.drawable.ic_launcher);
	}
}
