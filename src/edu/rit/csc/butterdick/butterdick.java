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

		int w=getWindowManager().getDefaultDisplay().getWidth();
		int h=getWindowManager().getDefaultDisplay().getHeight()-100;

		MainGame game = new MainGame();
		BallView view = new BallView(this, w, h, game);
		setContentView(view);

		/*LambdaConvertTest tester = new LambdaConvertTest();
		setContentView(R.layout.main);
		TextView tv = (TextView)findViewById(R.id.tv);
		tv.setText(String.format("Test 1: %s", tester.test1() ? "PASS" : "FAIL"));*/
    }
}
