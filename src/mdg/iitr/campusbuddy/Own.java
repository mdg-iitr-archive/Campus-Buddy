package mdg.iitr.campusbuddy;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import mdg.iitr.campusbuddy.R;

public class Own extends Activity implements OnClickListener {
	Button subject, alldone;
	EditText subjectName;
	TextView subjectPresent;
	String subjectData;
	SharedPreferences somedata;
	public static String filename = "MySharedString";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addsubject);

		subject = (Button) findViewById(R.id.bAddSubject);
		alldone = (Button) findViewById(R.id.bDone);
		subjectName = (EditText) findViewById(R.id.etSubject);
		subjectPresent = (TextView) findViewById(R.id.tvSubjectInDB);

		subject.setOnClickListener(this);
		alldone.setOnClickListener(this);
		Notes note = new Notes(this);
		try {
			note.open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!note.getData().equals(""))
		{
			int j = note.number();
			subjectData = "Subject You added\n";
			for (int i = 0; i < j; i++) {
				int i1 = i + 1;
				subjectData = subjectData + i1 + "\t" + note.getName(i1)
						+ "\n";
				
			}
			subjectPresent.setText(subjectData);
		}
		note.close();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		if (id == R.id.bDone) {
			HotOrNot entry;
			entry = new HotOrNot(this);
			try {
				entry.open();
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (int i1 = 0; i1 < 50; i1++) {
				String name = "";
				String hotness = "";
				entry.createEntry(name, hotness);
				
				// Toast.makeText(this, "" + i, Toast.LENGTH_SHORT).show();
			}

			entry.close();
			somedata = getSharedPreferences(filename, 0);
			SharedPreferences.Editor editor = somedata.edit();
			editor.putBoolean("flag", true);
			editor.commit();

			Intent openStartingPoint = new Intent(
					this,TimeTableActivity.class);
			startActivity(openStartingPoint);
			
		} else if (id == R.id.bAddSubject) {
			String subjectString = subjectName.getText().toString();
			subjectName.setText("");
			if (!subjectString.equals("")) {
				Notes note = new Notes(this);
				try {
					note.open();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				note.createEntry(subjectString, "Type Here...", "","");    //************See this***************
				int j = note.number();
				subjectData = "Subject You added\n";
				for (int i = 0; i < j; i++) {
					int i1 = i + 1;
					subjectData = subjectData + i1 + "\t" + note.getName(i1) + " " + note.getDesc(i1)
							+ "\n";
					subjectPresent.setText(subjectData);
				}
				note.close();

			} else {
				Toast.makeText(this, "Enter Subject Name", Toast.LENGTH_LONG)
						.show();
			}

		}
		}
	}

