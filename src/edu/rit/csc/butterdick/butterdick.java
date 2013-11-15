package edu.rit.csc.butterdick;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import edu.rit.csc.butterdick.ui.BallView;

import edu.rit.csc.butterdick.game.MainGame;
import edu.rit.csc.butterdick.test.*;

public class butterdick extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
    }
}
