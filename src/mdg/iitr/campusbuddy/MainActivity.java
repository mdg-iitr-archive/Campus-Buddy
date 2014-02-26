package mdg.iitr.campusbuddy;


import java.util.Calendar;
import mdg.iitr.campusbuddy.R;
import mdg.iitr.campusbuddy.pkr.ScheduleClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;


/**
 * This is the Main Activity of our app.
 * Here we allow the user to select a date,
 * we then set a notification for that date to appear in the status bar
 *  
 * @author paul.blundell
 */
public class MainActivity extends Activity  {
	// This is a handle so that we can call methods on our service
    private ScheduleClient scheduleClient;
    // This is the date picker used to select the date for our notification
	private DatePicker datepicker;
	private TimePicker timepicker;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
       
        // Create a new service client and bind our activity to this service
        scheduleClient = new ScheduleClient(this);
        scheduleClient.doBindService();

        // Get a reference to our date picker
        datepicker = (DatePicker) findViewById(R.id.scheduleDATEPicker);
        int value=Calendar.HOUR_OF_DAY; 
        timepicker = (TimePicker) findViewById(R.id.schedultimePicker);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
             value = extras.getInt("KEY");
             if(value<6)
            	 value+=12;
             if(value==12)
            	 value=0;
        }
        
        timepicker.setCurrentHour(value);
        timepicker.setCurrentMinute(0);
    }
	
    /**
     * This is the onClick called from the XML to set a new notification 
     */
    public void onDateSelectedButtonClick(View v){
    	// Get the date from our datepicker
    	int day =datepicker.getDayOfMonth();
    	int month=datepicker.getMonth();
    	int year=datepicker.getYear();
    	// Create a new calendar set to the date chosen
    	// we set the time to midnight (i.e. the first minute of that day)
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(Calendar.YEAR, datepicker.getYear());
        calendar.set(Calendar.MONTH, datepicker.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, datepicker.getDayOfMonth());                 
        calendar.set(Calendar.HOUR_OF_DAY, timepicker.getCurrentHour());
        calendar.set(Calendar.MINUTE, timepicker.getCurrentMinute());
        calendar.set(Calendar.SECOND, 0);
//        c.setTimeInMillis(System.currentTimeMillis());
//        calendar.add(Calendar.SECOND, 5);
    	// Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
    	scheduleClient.setAlarmForNotification(calendar);
    	// Notify the user what they just did
    	Toast.makeText(this, "Notification set for: "+ day +"/"+ (month+1) +"/"+ year, Toast.LENGTH_SHORT).show();
    }
    public void onStopButtonClick(View v){
    	// Get the date from our datepicker
    	if(scheduleClient != null)
    	{
    		scheduleClient.resetAlarmForNotification(22);
    		
    		scheduleClient.doUnbindService();}
    	super.onStop();
    	
    }
    @Override
    protected void onStop() {
    	// When our activity is stopped ensure we also stop the connection to the service
    	// this stops us leaking our activity into the system *bad*
    	if(scheduleClient != null)
    		{
    		scheduleClient.doUnbindService();
    		}
    	
    	super.onStop();
    }
}