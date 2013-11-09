package edu.rit.csc.butterdick.game;

import edu.rit.csc.butterdick.lambda.LambdaColor;
import android.graphics.Bitmap;

import android.content.Context;

public abstract class GameGridCell
{
	private Bitmap bmp;
	protected LambdaColor color;

	public GameGridCell(LambdaColor color)
	{
		this.color = color;
	}
	
	public void setBmp(Bitmap image){
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++){
			for (int y = 0; y < 6; y++)
			image.setPixel(i, y, 0xffffff);
		}
		for (int i = 0; i < width; i++){
			for (int y = height-6; y < height; y++)
			image.setPixel(i, y, 0xffffff);
		}
		for (int i = 0; i < 6; i++){
			for (int x = 0; x < height; x++)
			image.setPixel(i, x, 0xffffff);
		}
		for (int i = width-6; i < width; i++){
			for (int x = 0; x < height; x++)
			image.setPixel(i, x, 0xffffff);
		}
		bmp = image;
	}
	
	public Bitmap getBitmap(Context ctxt){
		return bmp;
	}
}
