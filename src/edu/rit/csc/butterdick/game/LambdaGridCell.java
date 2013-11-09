package edu.rit.csc.butterdick.game;

import edu.rit.csc.butterdick.lambda.LambdaColor;
import edu.rit.csc.butterdick.R;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;

import android.content.Context;

public class LambdaGridCell extends GameGridCell
{
	private int width;
	
	public LambdaGridCell(LambdaColor color, int width)
	{
		super(color);
		this.width = width;
	}

	@Override
	public Bitmap getBitmap(Context ctxt)
	{
		// TODO: Return a unique bitmap for each expression
		return BitmapFactory.decodeResource(ctxt.getResources(), R.drawable.ic_launcher);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof LambdaGridCell)
		{
			LambdaGridCell l = (LambdaGridCell)obj;
			return l.width == width && l.color == color;
		}
		return false;
	}
}
