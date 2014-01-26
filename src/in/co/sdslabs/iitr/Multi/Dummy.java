package in.co.sdslabs.iitr.Multi;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

public class Dummy extends Activity implements OnClickListener,
		OnTimeChangedListener {
	String gotBread, gotapples, cheery, gotAlmonds;
	int a, flag = 0, count1 = 7, i1 = 0;
	// long=;
	String ankit, temp;
	TextView textView1, textView, sub;
	EditText notes, prof, place, batch,subject;
	RadioGroup radioGroup, radioGroup1;
	Button saveChange, stopAlarm, OnTimeAlarm, save;
	DatePicker datePicker;
	TimePicker timePicker;
	LinearLayout linearLayout;
	RadioButton[] rad;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		
/*
	*/
		try {
			setContentView(R.layout.pop);
			Bundle gotBasket = getIntent().getExtras();
		/*	gotBread = gotBasket.getString("key");
			gotapples = gotBasket.getString("desc");*/
			
			gotAlmonds = gotBasket.getString("id");
			a = Integer.parseInt(gotAlmonds);
			HotOrNot entry = new HotOrNot(this);
			entry.open();
		//	Toast.makeText(this,"gotbrad-",Toast.LENGTH_LONG).show();
			gotBread = entry.getName(a);
			if(gotBread.equals(""))
			{
			//	Toast.makeText(this,"went and came",Toast.LENGTH_LONG).show();
				gotBread = "No Class";
			}
			gotapples = entry.getHotness(a);
			
			entry.close();
			WindowManager.LayoutParams params = getWindow().getAttributes();
			try {
				
				params.width = WindowManager.LayoutParams.MATCH_PARENT;

			} catch (Exception e) {
				// TODO: handle exception
			}
			
			this.getWindow().setAttributes(params);
			// ankit = gotBasket.getString("second");
			textView = (TextView) findViewById(R.id.desc);
			textView.setText(gotBread);
			textView1 = (TextView) findViewById(R.id.tv_other_batch);
			textView1.setText(gotapples);
			textView1.setVisibility(View.GONE);
			notes = (EditText) findViewById(R.id.etNotes);
			TextView tvnote = (TextView) findViewById(R.id.tvNotes);
			Notes note = new Notes(this);
			try {
				note.open();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int j = note.number();
			for (int i = 0; i < j; i++) {
				i1 = i + 1;
				if (note.getName(i1) == "") {
					notes.setVisibility(View.GONE);
					save.setVisibility(View.GONE);
					tvnote.setVisibility(View.GONE);
				}
			if (gotBread.contains(note.getName(i1))) {
				
				if (note.getHotness(i1).equals("Type Here...")) {
					notes.setHint("Type Here...");
					break;
				} else {
					notes.setText(note.getHotness(i1));
					break;
				}
			}
			}
			note.close();
			// int count1 = 7;
			
			 /* RadioButton radioButton1 = null;
			  radioButton1.findViewById(R.id.rfirst); radioButton1.setText("ie");
			 */ 
			  /*RadioButton radioButton1 = null,radioButton2 = null,radioButton3 =
			 * null,radioButton4 = null,radioButton5 = null,radioButton6 =
			 * null,radioButton7 = null; //radioButton = new RadioButton[count1];
			 * 
			 * radioButton1.findViewById(R.id.radio0);
			 * radioButton2.findViewById(R.id.radio1);
			 * radioButton3.findViewById(R.id.radio2);
			 * radioButton4.findViewById(R.id.radio3);
			 * radioButton5.findViewById(R.id.radio4);
			 * radioButton6.findViewById(R.id.radio5);
			 * radioButton7.findViewById(R.id.radio6); // String strArrtext =
			 * Subjetcs.split(","); radioButton7.setText("ie");
			 * radioButton1.setText("ie"); radioButton2.setText("ie");
			 * radioButton3.setText("ie"); radioButton4.setText("ie");
			 * radioButton5.setText("ie"); radioButton6.setText("ie"); /*
			 * radioButton[1].setText(Subjetcs[1]);
			 * radioButton[2].setText(Subjetcs[2]);
			 * radioButton[3].setText(Subjetcs[3]);
			 * radioButton[4].setText(Subjetcs[4]);
			 * radioButton[5].setText(Subjetcs[5]);
			 * radioButton[6].setText(Subjetcs[6]);
			 */
			/*
			RadioButton rad = (RadioButton) findViewById(R.id.radio3);
			rad.setText("asdasd");
			RadioButton rad1 = (RadioButton) findViewById(R.id.radio4);
			rad1.setText("asdasd1");
			*//*Notes note = new Notes(this);
			note = new Notes(this);
			try {
				note.open();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			int k = note.number();
			rad = new RadioButton[k];
			rad[0] = (RadioButton)findViewById(R.id.radio3);
			rad[1] = (RadioButton)findViewById(R.id.radio4);
			rad[2] = (RadioButton)findViewById(R.id.radio5);
			rad[3] = (RadioButton)findViewById(R.id.radio6);
			rad[4] = (RadioButton)findViewById(R.id.radio7);
			rad[5] = (RadioButton)findViewById(R.id.radio8);
			rad[6] = (RadioButton)findViewById(R.id.radio9);
			rad[7] = (RadioButton)findViewById(R.id.radio10);
			Toast.makeText(this, ""+k,Toast.LENGTH_LONG).show();
			
			for (int i = 0; i < 8; i++) {
				i1 = i + 1;
				if (note.getName(i1) == "") {
					notes.setVisibility(View.GONE);
					save.setVisibility(View.GONE);
					tvnote.setVisibility(View.GONE);
				}

				if (gotBread.contains(note.getName(i1))) {
					if (note.getHotness(i1).equals("Type Here...")) {
						notes.setHint("Type Here...");
						break;
					} else {
						notes.setText(note.getHotness(i1));
						break;
					}
				}
			}
			note.close();
	*/
			/*Notes note = new Notes(this);
			note = new Notes(this);
			try {
				note.open();
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 8; i++) {
				String name = Subjetcs[i];
				String hotness = Snotes[i];
				String attend  = Sattend[i];
				note.createEntry(name, hotness,attend);
			}
			for (int i = 0; i < 8; i++) {
				i1 = i + 1;
				if (note.getName(i1) == "") {
					notes.setVisibility(View.GONE);
					save.setVisibility(View.GONE);
					tvnote.setVisibility(View.GONE);
				}

				if (gotBread.contains(note.getName(i1))) {
					if (note.getHotness(i1).equals("Type Here...")) {
						notes.setHint("Type Here...");
						break;
					} else {
						notes.setText(note.getHotness(i1));
						break;
					}
				}
			}
			note.close();
	*/		Button button = (Button) findViewById(R.id.button1);
			button.setOnClickListener(this);
			Button button1 = (Button) findViewById(R.id.bAlarm);
			button1.setOnClickListener(this);
		//	Button button2 = (Button) findViewById(R.id.b_other_batch);
			//button2.setOnClickListener(this);
			save = (Button) findViewById(R.id.bSaveNote);
			datePicker = (DatePicker) findViewById(R.id.datePicker1);
			timePicker = (TimePicker) findViewById(R.id.timePicker1);
			linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
			OnTimeAlarm = (Button) findViewById(R.id.bOneTimeAlarm);
			stopAlarm = (Button) findViewById(R.id.bStopAlarm);
			saveChange = (Button) findViewById(R.id.saveChange);
			radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
			radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup2);
			sub = (TextView) findViewById(R.id.sub);
			subject = (EditText) findViewById(R.id.subject);
			prof = (EditText) findViewById(R.id.prof);
			place = (EditText) findViewById(R.id.place);
			batch = (EditText) findViewById(R.id.batch);
			Button done = (Button) findViewById(R.id.bDone);
			
				saveChange.setOnClickListener(this);		
				save.setOnClickListener(this);
				OnTimeAlarm.setOnClickListener(this);
				stopAlarm.setOnClickListener(this);
				done.setOnClickListener(this);
			//	Toast.makeText(this, "Button:):):)",Toast.LENGTH_SHORT).show();
				// TODO: handle exception
	
		} catch (Exception e) {
			// TODO: handle exception
		//	Toast.makeText(this, "Try again:):):)",Toast.LENGTH_SHORT).show();
			finish();
		}
			}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bSaveNote: {
			Notes note = new Notes(this);
			try {
				note.open();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			note.updateEntry(i1, note.getName(i1), notes.getText().toString(),note.getAttend(i1),note.getDesc(i1));
			note.close();
			Toast.makeText(this, "Note is Saved", Toast.LENGTH_LONG).show();
			break;
		}
		case R.id.bOneTimeAlarm: {
			int chour, cminute, cyear, cmonth, cday;
			Calendar objCalendar = Calendar.getInstance();
			cmonth = datePicker.getMonth();
			cday = datePicker.getDayOfMonth();
			cyear = datePicker.getYear(); // month = month+1;
			chour = timePicker.getCurrentHour();
			cminute = timePicker.getCurrentMinute();
			objCalendar.set(Calendar.YEAR, cyear);
			objCalendar.set(Calendar.MONTH, cmonth);
			objCalendar.set(Calendar.DAY_OF_MONTH, cday);
			objCalendar.set(Calendar.HOUR_OF_DAY, chour);
			objCalendar.set(Calendar.MINUTE, cminute);
			objCalendar.set(Calendar.SECOND, 0);
			objCalendar.set(Calendar.MILLISECOND, 0);

			Intent intent = new Intent(
					"com.example.timet.ALARMRECEIVERACTIVITY");

			PendingIntent pendingIntent = PendingIntent.getActivity(this, 2,
					intent, PendingIntent.FLAG_CANCEL_CURRENT);
			AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
			am.set(AlarmManager.RTC_WAKEUP, objCalendar.getTimeInMillis(),
					pendingIntent);
			Toast.makeText(this, "Alarm is set", Toast.LENGTH_LONG).show();
			break;
		}
		case R.id.bStopAlarm: {
			Intent intent = new Intent(
					"com.sds.timetablesds.MainActivity.ALARMRECEIVERACTIVITY");
			PendingIntent pendingIntent = PendingIntent.getActivity(this, 2,
					intent, PendingIntent.FLAG_UPDATE_CURRENT);
			AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
			am.cancel(pendingIntent);
			Toast.makeText(this, "stop Alarm", Toast.LENGTH_LONG).show();

			break;
		}
		case R.id.saveChange: {

			//Toast.makeText(this, "reached", Toast.LENGTH_LONG).show();
			cheery = "";
			switch (radioGroup.getCheckedRadioButtonId()) {
			case R.id.radio0: {
				cheery = "L";
				break;
			}
			case R.id.radio1: {
				cheery = "T";
				break;
			}
			case R.id.radio2: {
				cheery = "P";
				break;
			}

			}
			//Toast.makeText(this,"whatcame"+radioGroup1.getCheckedRadioButtonId() ,Toast.LENGTH_LONG).show();
			int flag6 = 0;
			String gotSubject = subject.getText().toString();
		//	Toast.makeText(this, "subject"+gotSubject, Toast.LENGTH_SHORT).show();
			int k = radioGroup1.getCheckedRadioButtonId();
			//Toast.makeText(this, "radio"+k, Toast.LENGTH_SHORT).show();
			if(gotSubject.equals(""))
			{
			if( k != -1)
			{
			int i = (radioGroup1.getCheckedRadioButtonId())+1;
		//	Toast.makeText(this, ""+i, Toast.LENGTH_LONG).show();
			Notes note = new Notes(this);
			try {
				note.open();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			cheery += note.getName(i);
			note.close();
			}
			
			}
			else if(!gotSubject.equals(""))
			{
				if(k == -1)
			{
				cheery = cheery + subject.getText().toString() + " ";
			}
				else
				{
					flag6 = 1;
					
				}
				
			}
			
/*			case R.id.radio3: {
				cheery += "IE";
				break;
			}
			case R.id.radio4: {
				cheery += "EC-262";
				break;
			}
			case R.id.radio5: {
				cheery += "EC-252";
				break;
			}
			case R.id.radio6: {
				cheery += "EC-202";
				break;
			}
			case R.id.radio7: {
				cheery += "EC-254";
				break;
			}
			case R.id.radio8: {
				cheery += "CE-201";
				break;
			}
			case R.id.radio9: {
				cheery += "HS-201";
				break;
			}
*/
			
			cheery = cheery + " " + batch.getText().toString() + " ";
			cheery = cheery + prof.getText().toString() + " ";
			cheery = cheery + place.getText().toString();
			flag = 1;
			//Toast.makeText(this, ""+cheery, Toast.LENGTH_LONG).show();
			if(flag6 == 0)
			{
			Toast.makeText(this, "change is saved", Toast.LENGTH_LONG).show();
			HotOrNot entry = new HotOrNot(this);
			try {
				entry.open();
			} catch (Exception e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
			entry.updateEntry(a, cheery, entry.getHotness(a));
			entry.close();
			}
			else if(flag6 == 1)
			{
				Toast.makeText(this, "Select either from radiobuttons or write in textfield", Toast.LENGTH_LONG).show();
			}
			break;
		}

		case R.id.button1: {
			radioGroup.setVisibility(View.VISIBLE);
			radioGroup1.setVisibility(View.VISIBLE);
			Notes note = new Notes(this);
			note = new Notes(this);
			try {
				note.open();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			int k = note.number();
			int i1;
			for(int i = 0 ; i<k ; i++)
			{ i1 = i+1; 
				RadioButton radi = new RadioButton(this);
				radi.setId(i);
			//	Toast.makeText(this, ""+note.getName(i1), Toast.LENGTH_LONG).show();
				String text = note.getName(i1);
				radi.setText(text);
				radi.setTextColor(Color.BLACK);
				radioGroup1.addView(radi);
			}
			note.close();
/*			radioGroup1.setVisibility(View.VISIBLE);
			Notes note = new Notes(this);
			note = new Notes(this);
			try {
				note.open();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			int k = note.number();
			for(int i = 0 ; i<k ; i++)
			{
				rad[0] = ;
				
			}
			rad = new RadioButton[k];
			rad[1] = (RadioButton)findViewById(R.id.radio4);
			rad[2] = (RadioButton)findViewById(R.id.radio5);
			rad[3] = (RadioButton)findViewById(R.id.radio6);
			rad[4] = (RadioButton)findViewById(R.id.radio7);
			rad[5] = (RadioButton)findViewById(R.id.radio8);
			rad[6] = (RadioButton)findViewById(R.id.radio9);
			rad[7] = (RadioButton)findViewById(R.id.radio10);
			Toast.makeText(this, ""+k,Toast.LENGTH_LONG).show();
		note.close();
*/
			sub.setVisibility(View.VISIBLE);
			subject.setVisibility(View.VISIBLE);
			batch.setVisibility(View.VISIBLE);
			prof.setVisibility(View.VISIBLE);
			place.setVisibility(View.VISIBLE);
			saveChange.setVisibility(View.VISIBLE);
			break;
		}
		case R.id.bAlarm: {
			datePicker.setVisibility(View.VISIBLE);
			timePicker.setVisibility(View.VISIBLE);
			linearLayout.setVisibility(View.VISIBLE);
			break;
		}
		/*case R.id.b_other_batch: {
			//textView1.setVisibility(View.VISIBLE);
			break;
		}
*/
		case R.id.bDone: {
			if (flag == 1) {
			}
			finish();
			break;
		}
		}
	}

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

	@Override
	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
		// TODO Auto-generated method stub

	}

}

