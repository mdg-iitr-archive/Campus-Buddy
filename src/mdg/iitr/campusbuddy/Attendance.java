package mdg.iitr.campusbuddy;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import mdg.iitr.campusbuddy.R;

public class Attendance extends Activity implements OnClickListener {
	int i1;
	int count1 = 14;
	String a  ="1";
	TextView[] subjects;
	LinearLayout ll;
	//TableLayout tt;
//	LinearLayout linear;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try {
			
		setContentView(R.layout.attendance);
		WindowManager.LayoutParams params = getWindow().getAttributes();
		params.width = WindowManager.LayoutParams.MATCH_PARENT;
		ll = (LinearLayout)findViewById(R.id.attend);
		//tt = (TableLayout)findViewById(R.id.table);
		Notes note = new Notes(this);
		try {
			note.open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = note.number();
		//linear = new LinearLayout[note.number()];
		for( int i  = 0  ; i< note.number() ; i++)
		{
/*			TableRow tableRow = new TableRow(this);
	         tableRow.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));

	         TextView rowText=  new TextView(this);
	         rowText.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
	      
	         rowText.setText("dyanamic text");
	         tableRow.addView(rowText);

	         TextView rowText1=  new TextView(this);
	         rowText1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
	      
	         rowText1.setText("dyanamic text1");
	      
	         rowText1.setTextColor(Color.BLACK);
ta
	         tableRow.addView(rowText1);

	         tt.addView(tableRow);*/
			LinearLayout linear = new LinearLayout(this);
			
			linear.setOrientation(LinearLayout.HORIZONTAL);
			ll.addView(linear);
			i1 = i+1;
			TextView tv = new TextView(this);
			String subject = note.getName(i1);
			if(!subject.equals("IE"))
			{
			tv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 2f));
			
			}
			else
			{
				if (getResources().getConfiguration().orientation == 1) {
				tv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 3.2f));
				}
				else
				{
					tv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 2.4f));
					
				}
			}
			//tv.setLayoutParams(new .LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
			tv.setText(subject);
			tv.setTextColor(Color.BLACK);
			
			
			TextView tv1 = new TextView(this);
			tv1.setId(i1);
			tv1.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
			//tv.setLayoutParams(new .LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
			if(!note.getAttend(i1).equals(""))
			{
				tv1.setText(note.getAttend(i1));
				
			}
			else{
				tv1.setText("start");
					
			}
			tv1.setTextColor(Color.BLACK);
			
			Button add = new Button(this);
			add.setId(i1);
			add.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
			//tv.setLayoutParams(new .LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
			add.setText("+1");
			
			Button sub = new Button(this);
			int i2 = i1*(-1);
			
			sub.setId(i2);
			sub.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
			//tv.setLayoutParams(new .LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
			sub.setText("-1");
			add.setOnClickListener(this);
			sub.setOnClickListener(this);
			
			linear.addView(tv);
			linear.addView(tv1);
			linear.addView(add);
			linear.addView(sub);
			
		}
		
		note.close();
		} catch (Exception e) {
			finish();
			// TODO: handle exception
		}
		

/*		int count = 8;

		add = new Button[count];
		sub = new Button[count];
		subjects = new TextView[count1];
		subjects[0] = (TextView) findViewById(R.id.sub1);
		subjects[7] = (TextView) findViewById(R.id.sub2);
		subjects[1] = (TextView) findViewById(R.id.sub3);
		subjects[8] = (TextView) findViewById(R.id.sub4);

		subjects[2] = (TextView) findViewById(R.id.sub5);
		subjects[9] = (TextView) findViewById(R.id.sub6);
		subjects[3] = (TextView) findViewById(R.id.sub7);

		subjects[10] = (TextView) findViewById(R.id.sub8);
		subjects[4] = (TextView) findViewById(R.id.sub9);
		subjects[11] = (TextView) findViewById(R.id.sub10);
		subjects[5] = (TextView) findViewById(R.id.sub11);

		subjects[12] = (TextView) findViewById(R.id.sub12);
		subjects[6] = (TextView) findViewById(R.id.sub13);
		subjects[13] = (TextView) findViewById(R.id.sub14);

		add[0] = (Button) findViewById(R.id.button1);
		add[1] = (Button) findViewById(R.id.button3);
		add[2] = (Button) findViewById(R.id.button5);
		add[3] = (Button) findViewById(R.id.button7);
		add[4] = (Button) findViewById(R.id.button9);
		add[5] = (Button) findViewById(R.id.button11);
		add[6] = (Button) findViewById(R.id.button13);
		sub[0] = (Button) findViewById(R.id.button2);
		sub[1] = (Button) findViewById(R.id.button4);
		sub[2] = (Button) findViewById(R.id.button6);
		sub[3] = (Button) findViewById(R.id.button8);
		sub[4] = (Button) findViewById(R.id.button10);
		sub[5] = (Button) findViewById(R.id.button12);
		sub[6] = (Button) findViewById(R.id.button14);
		Notes note;
		note = new Notes(this);
		try {
			note.open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 6; i++) {
			i1 = i + 1;
			{
				subjects[i].setText("" + note.getName(i1));
			}
		}
		for (int i = 0; i < 6; i++) {
			add[i].setOnClickListener(this);
			sub[i].setOnClickListener(this);
		}
		for (int i = 6; i < 12; i++) {
			i1 = i - 6;
			if (!note.getAttend(i1).equals("")) {

				subjects[i].setText(note.getAttend(i1));
			}
		}
		
		 * for (int i = 0; i < 7; i++) {
		 * 
		 * i2= i + 1; add[i].setOnClickListener(this);
		 * add[i].setText("Add 1 to " + note.getName(i2));
		 * 
		 * } for (int i = 0; i < 7; i++) {
		 * 
		 * i2= i + 1; sub[i].setOnClickListener(this); sub[i].setText("" +
		 * note.getName(i2));
		 * 
		 * }
		 
		note.close();
	*/}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Notes note;
		note = new Notes(this);
		try {
			note.open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(v.getId() > 0)
		{
		int i = v.getId();
		if (!note.getAttend(i).equals("")) {
			int myNum = Integer.parseInt(note.getAttend(i));
			myNum = myNum + 1;
			note.updateEntry(i, note.getName(i), note.getHotness(i),
					String.valueOf(myNum),note.getDesc(i));
		} else {
			
			note.updateEntry(i, note.getName(i), note.getHotness(i), "1",note.getDesc(i));

		}
		TextView tv  = (TextView)findViewById(i);
		tv.setText(note.getAttend(i));
	}
		else
		{
			//Toast.makeText(this, ""+v.getId(), Toast.LENGTH_LONG).show();
			int i2 = v.getId();
			int i = v.getId()*(-1);
			if (!note.getAttend(i).equals("")) {
				
			int myNum = Integer.parseInt(note.getAttend(i));
			myNum = myNum - 1;
			if (myNum < 0) {
				Toast.makeText(this, "Can't subract", Toast.LENGTH_SHORT)
						.show();
			} else {
				note.updateEntry(i, note.getName(i), note.getHotness(i),
						String.valueOf(myNum),note.getDesc(i));
				TextView tv1  = (TextView)findViewById(i);
				tv1.setText(note.getAttend(i));
			
			/*	subjects[7].setText(note.getAttend(1));
*/
			}
			}
			else{
				Toast.makeText(this, "Not Started Recording Yet", Toast.LENGTH_SHORT)
				.show();
			}
		

		}
	}

}