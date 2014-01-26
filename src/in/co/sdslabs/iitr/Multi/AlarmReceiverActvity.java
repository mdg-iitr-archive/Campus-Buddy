package in.co.sdslabs.iitr.Multi;

import android.app.Activity;
import android.content.Context;
import android.drm.DrmStore.Playback;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmReceiverActvity extends Activity implements OnClickListener {
	private MediaPlayer mMediaPlayer;
	private PowerManager.WakeLock mWakeLock;
	//TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle gotBasket =getIntent().getExtras();
		String label = gotBasket.getString("key");
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		//textView=(TextView)findViewById(R.id.tvLabel);
		//textView.setText(label);
		mWakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Wake Lock");
		mWakeLock.acquire();
		this.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN
						| WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
						| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_FULLSCREEN
						| WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
						| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
		setContentView(R.layout.alarmtt);
		Toast.makeText(this, "one time alarm", Toast.LENGTH_LONG).show();
		Button stopAlarm = (Button) findViewById(R.id.bStopAlarm_dialog);
		stopAlarm.setOnClickListener(this);

		playSound(this, getAlarmUri());
	}

	private void playSound(Context context, Uri alert) {
	//	mMediaPlayer = MediaPlayer.create(this,R. );
	//mMediaPlayer = MediaPlayer.create(this, R.);
		mMediaPlayer = MediaPlayer.create(this, R.raw.alarm);
		mMediaPlayer.start();
		/*try {
			mMediaPlayer.setDataSource(context, alert);
			final AudioManager audioManager = (AudioManager) context
					.getSystemService(Context.AUDIO_SERVICE);
			if(audioManager.getStreamVolume(AudioManager.STREAM_ALARM)!=0)
			{
				mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
				mMediaPlayer.prepare();
				mMediaPlayer.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}*/

	}

	private Uri getAlarmUri() {
		Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
		if(alert==null)
		{
			alert=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
			if(alert==null)
			{
				alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			}
		}
		return alert;
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		mWakeLock.release();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mMediaPlayer.stop();
		finish();
	}
}
