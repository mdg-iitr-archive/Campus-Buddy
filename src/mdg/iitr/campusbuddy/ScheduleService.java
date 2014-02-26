package mdg.iitr.campusbuddy;


import java.util.Calendar;
import mdg.iitr.campusbuddy.R;
import mdg.iitr.campusbuddy.pkr.AlarmTask;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class ScheduleService extends Service {

	/**
	 * Class for clients to access
	 */
	public class ServiceBinder extends Binder {
		public ScheduleService getService() {
			return ScheduleService.this;
		}
	}

	private static final String TAG = "Alarm on";
	private NotificationManager notificationMgr;

	@Override
	public void onDestroy() {
		// notificationMgr.cancelAll();
		Log.v("ScheduleService", "destroy");
		// stopForeground(true);
		super.onDestroy();
	}

	@Override
	public void onCreate() {
		super.onCreate();
		notificationMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("ScheduleService", "Received start id " + startId + ": " + intent);

		// We want this service to continue running until it is explicitly
		// stopped, so return sticky.
		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {

		return mBinder;
	}

	// This is the object that receives interactions from clients. See
	private final IBinder mBinder = new ServiceBinder();

	private void displayNotificationMessage(String message) {
		Notification notification = new Notification(R.drawable.icon, message,
				System.currentTimeMillis());

		notification.flags |= Notification.FLAG_ONGOING_EVENT
				| Notification.FLAG_NO_CLEAR;

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, MainActivity.class)
						.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

		notification.setLatestEventInfo(this, TAG, message, contentIntent);

		notificationMgr.notify(22, notification);
		// startForeground(23, notification);
		Log.v("ScheduleService", "display");
	}

	public void stopNotificationMessage() {
		notificationMgr.cancelAll();
	}

	/**
	 * Show an alarm for a certain date when the alarm is called it will pop up
	 * a notification
	 */
	public void setAlarm(Calendar c) {
		// This starts a new thread to set the alarm
		// You want to push off your tasks onto a new thread to free up the UI
		// to carry on responding
		String apm = "am";
		if (c.get(Calendar.AM_PM) != 0)
			apm = "pm";
		displayNotificationMessage("Alarm set for " + c.get(Calendar.HOUR)
				+ ":" + String.format("%02d", c.get(Calendar.MINUTE)) + apm
				+ "\n Click if you want to stop alarm");

		new AlarmTask(this, c).run();
	}
}