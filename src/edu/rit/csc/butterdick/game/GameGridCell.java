package edu.rit.csc.butterdick.game;

import edu.rit.csc.butterdick.lambda.LambdaColor;
import android.graphics.Bitmap;

import android.content.Context;

public abstract class GameGridCell
{
	protected LambdaColor color;

	public GameGridCell(LambdaColor color)
	{
		this.color = color;
	}

	public abstract Bitmap getBitmap(Context ctxt);
}
