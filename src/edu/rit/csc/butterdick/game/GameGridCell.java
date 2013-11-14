package edu.rit.csc.butterdick.game;

import edu.rit.csc.butterdick.lambda.*;
import android.graphics.Bitmap;

import android.content.Context;

public abstract class GameGridCell
{
	protected LambdaColor color;

	public GameGridCell(LambdaColor color)
	{
		this.color = color;
	}

	public LambdaColor getColor()
	{
		return color;
	}

	public abstract Bitmap getBitmap(Context ctxt);
}
