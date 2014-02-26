package mdg.iitr.campusbuddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import mdg.iitr.campusbuddy.R;

public class Splash extends Activity implements OnTouchListener {

	@Override
	protected void onCreate(Bundle totoo) {
		// TODO Auto-generated method stub
		super.onCreate(totoo);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash);

		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent openNewbostoActivity = new Intent(
							"in.co.sdslabs.iitr.Multi.MAINPAGE");
					startActivity(openNewbostoActivity);

				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		Intent openNewbostoActivity = new Intent(
				"in.co.sdslabs.iitr.Multi.MAINPAGE");
		startActivity(openNewbostoActivity);
		return false;
	}

}
