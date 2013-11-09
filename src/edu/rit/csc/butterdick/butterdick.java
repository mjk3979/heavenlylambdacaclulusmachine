package edu.rit.csc.butterdick;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import edu.rit.csc.butterdick.ui.BallView;

public class butterdick extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

		int w=getWindowManager().getDefaultDisplay().getWidth()-25;
		int h=getWindowManager().getDefaultDisplay().getHeight()-25;

		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		BallView view = new BallView(this, bitmap, w, h);
		setContentView(view);
    }
}
