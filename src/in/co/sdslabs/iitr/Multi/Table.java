package in.co.sdslabs.iitr.Multi;

import in.co.sdslabs.iitr.pkr.DBAdapterPkr;
import in.co.sdslabs.iitr.pkr.DatabaseCreator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Table extends Activity implements OnItemSelectedListener {

	TableRow rowTitle;
	TableRow rowDayLabels;
	TableRow rowDayLabels2;
	TableRow row8to9;
	TableRow row9to10;
	TableRow row10to11;
	TableRow row11to12;
	TableRow row12to1;
	TableRow row2to3;
	TableRow row3to4;
	TableRow row4to5;
	TableRow row5to6;
	TableRow rowedit;
	private ArrayAdapter<CharSequence> adaptersub;
	Spinner spinnersub, spin;
	String aa = "hh";
	AlertDialog alert;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		TableLayout table = new TableLayout(this);
		Resources res = getResources();
		ScrollView sv = new ScrollView(this);
		sv.setVerticalFadingEdgeEnabled(true);
		ImageView springs = new ImageView(this);
		springs.setImageDrawable(res.getDrawable(R.drawable.tablesprings));
		sv.addView(table);
		setContentView(sv);

		table.setStretchAllColumns(true);
		table.setShrinkAllColumns(true);
		Drawable d = res.getDrawable(R.drawable.black_wood);
		table.setBackgroundDrawable(d);
		rowTitle = new TableRow(this);
		rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);

		rowDayLabels2 = new TableRow(this);
		rowDayLabels = new TableRow(this);
		row8to9 = new TableRow(this);
		row9to10 = new TableRow(this);
		row10to11 = new TableRow(this);
		row11to12 = new TableRow(this);
		row12to1 = new TableRow(this);
		row2to3 = new TableRow(this);
		row3to4 = new TableRow(this);
		row4to5 = new TableRow(this);
		row5to6 = new TableRow(this);
		rowedit = new TableRow(this);

		// springs
		int resX;
		resX = this.getWindowManager().getDefaultDisplay().getWidth();
		Drawable dr = getResources().getDrawable(R.drawable.tablesprings);
		Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
		Drawable dw = new BitmapDrawable(Bitmap.createScaledBitmap(bitmap,
				resX, bitmap.getHeight() * resX / bitmap.getWidth(), true));

		// params
		TableLayout.LayoutParams params = new TableLayout.LayoutParams();
		params.bottomMargin = 4;

		rowDayLabels.setBackgroundColor(Color.argb(229, 12, 9, 15));
		rowDayLabels2.setBackgroundDrawable(dw);
		row8to9.setBackgroundColor(Color.argb(158, 220, 220, 215));
		row9to10.setBackgroundColor(Color.argb(154, 220, 220, 215));
		row10to11.setBackgroundColor(Color.argb(158, 220, 220, 215));
		row11to12.setBackgroundColor(Color.argb(157, 230, 230, 225));
		row12to1.setBackgroundColor(Color.argb(156, 230, 230, 225));
		row2to3.setBackgroundColor(Color.argb(155, 230, 230, 225));
		row3to4.setBackgroundColor(Color.argb(154, 240, 240, 235));
		row4to5.setBackgroundColor(Color.argb(153, 240, 240, 235));
		row5to6.setBackgroundColor(Color.argb(152, 240, 240, 235));
		rowedit.setGravity(Gravity.CENTER);

		TextView empty = new TextView(this);

		// title column/row
		Paint paint = new Paint();
		TextView title = new TextView(this);
		float measureTextCenter = paint.measureText(title.getText().toString());
		Animation animation2 = new TranslateAnimation(0f, -measureTextCenter,
				0, 0.0f);
		animation2.setDuration(5000);
		animation2.setInterpolator(new BounceInterpolator());
		title.startAnimation(animation2);

		title.setText("TIME TABLE");
		title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 23);
		title.setGravity(Gravity.CENTER);
		title.setTextColor(Color.rgb(120, 180, 184));
		title.setTypeface(Typeface.createFromAsset(getApplicationContext()
				.getAssets(), "FOO.ttf"));

		TableRow.LayoutParams paramsT = new TableRow.LayoutParams();
		paramsT.span = 7;
		paramsT.bottomMargin = 2;
		rowTitle.addView(title, paramsT);

		// labels column
		TextView Label8to9 = new TextView(this);
		Label8to9.setText("8-9");
		Label8to9.setTextColor(Color.rgb(37, 46, 57));
		Label8to9.setTextSize(16);
		Label8to9.setTypeface(Typeface.DEFAULT_BOLD);

		TextView Label9to10 = new TextView(this);
		Label9to10.setText("9-10");
		Label9to10.setTextColor(Color.rgb(37, 46, 57));
		Label9to10.setTextSize(16);
		Label9to10.setTypeface(Typeface.DEFAULT_BOLD);

		TextView Label10to11 = new TextView(this);
		Label10to11.setText("10-11");
		Label10to11.setTextColor(Color.rgb(35, 43, 57));
		Label10to11.setTextSize(16);
		Label10to11.setTypeface(Typeface.DEFAULT_BOLD);

		TextView Label11to12 = new TextView(this);
		Label11to12.setText("11to12");
		Label11to12.setTextColor(Color.rgb(33, 40, 57));
		Label11to12.setTextSize(16);
		Label11to12.setTypeface(Typeface.DEFAULT_BOLD);

		TextView Label12to1 = new TextView(this);
		Label12to1.setText("12to1");
		Label12to1.setTextColor(Color.rgb(31, 39, 57));
		Label12to1.setTextSize(16);
		Label12to1.setTypeface(Typeface.DEFAULT_BOLD);

		TextView Label2to3 = new TextView(this);
		Label2to3.setText("2to3");
		Label2to3.setTextColor(Color.rgb(29, 36, 57));
		Label2to3.setTextSize(16);
		Label2to3.setTypeface(Typeface.DEFAULT_BOLD);

		TextView Label3to4 = new TextView(this);
		Label3to4.setText("3to4");
		Label3to4.setTextColor(Color.rgb(27, 33, 57));
		Label3to4.setTextSize(16);
		Label3to4.setTypeface(Typeface.DEFAULT_BOLD);

		TextView Label4to5 = new TextView(this);
		Label4to5.setText("4to5");
		Label4to5.setTextColor(Color.rgb(25, 30, 57));
		Label4to5.setTextSize(16);
		Label4to5.setTypeface(Typeface.DEFAULT_BOLD);

		TextView Label5to6 = new TextView(this);
		Label5to6.setText("5to6");
		Label5to6.setTextColor(Color.rgb(23, 27, 57));
		Label5to6.setTextSize(16);
		Label5to6.setTypeface(Typeface.DEFAULT_BOLD);

		TextView Labelmenuq = new TextView(this);
		TextView Labelmenu = new TextView(this);
		Labelmenu.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 8);
		Labelmenu.setGravity(Gravity.CENTER);
		Labelmenu.setText("Press menu for more options");
		Labelmenu.setWidth(45);
		Labelmenu.setTextColor(Color.rgb(225, 220, 227));
		// Labelmenu.setTextSize(8);

		Label8to9.setBackgroundColor(Color.argb(99, 55, 55, 65));
		Label9to10.setBackgroundColor(Color.argb(99, 55, 55, 65));
		Label10to11.setBackgroundColor(Color.argb(99, 55, 55, 65));
		Label11to12.setBackgroundColor(Color.argb(99, 55, 55, 65));
		Label12to1.setBackgroundColor(Color.argb(99, 55, 55, 65));
		Label2to3.setBackgroundColor(Color.argb(99, 55, 55, 65));
		Label3to4.setBackgroundColor(Color.argb(99, 55, 55, 65));
		Label4to5.setBackgroundColor(Color.argb(99, 55, 55, 65));
		Label5to6.setBackgroundColor(Color.argb(99, 55, 55, 65));

		rowDayLabels.addView(empty);
		row8to9.addView(Label8to9);
		row9to10.addView(Label9to10);
		row10to11.addView(Label10to11);
		row11to12.addView(Label11to12);
		row12to1.addView(Label12to1);
		row2to3.addView(Label2to3);
		row3to4.addView(Label3to4);
		row4to5.addView(Label4to5);
		row5to6.addView(Label5to6);
		rowedit.addView(Labelmenuq);
		rowedit.addView(Labelmenu);

		// day 1 column
		TextView day1Label = new TextView(this);
		day1Label.setText("MONDAY");
		day1Label.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);

		TextView day2Label = new TextView(this);
		day2Label.setText("TUESDAY");
		day2Label.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);

		TextView day3Label = new TextView(this);
		day3Label.setText("WEDNESDAY");
		day3Label.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);

		TextView day4Label = new TextView(this);
		day4Label.setText("THURSDAY");
		day4Label.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);

		TextView day5Label = new TextView(this);
		day5Label.setText("FRIDAY");
		day5Label.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);

		TextView day1Low = new TextView(this);
		day1Low.setText("15°F");
		day1Low.setGravity(Gravity.CENTER_HORIZONTAL);

		rowDayLabels.addView(day1Label);
		rowDayLabels.addView(day2Label);
		rowDayLabels.addView(day3Label);
		rowDayLabels.addView(day4Label);
		rowDayLabels.addView(day5Label);

		// set table subjects
		/* */
		setvalue("monday");
		setvalue("tuesday");
		setvalue("wednesday");
		setvalue("thursday");
		setvalue("friday");

		// add rows to table

		table.addView(rowTitle);
		table.addView(rowDayLabels2);
		table.addView(rowDayLabels);
		table.addView(row8to9, params);
		table.addView(row9to10, params);
		table.addView(row10to11, params);
		table.addView(row11to12, params);
		table.addView(row12to1, params);
		table.addView(row2to3, params);
		table.addView(row3to4, params);
		table.addView(row4to5, params);
		table.addView(row5to6, params);
		table.addView(rowedit, paramsT);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		super.onResume();
	}

	void setvalue(final String day) {

		TextView Label8to9 = new TextView(this);
		Label8to9.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				onClicktv(day, "8-9");
			}
		});
		String sub10 = getsubdb(day, "8-9");
		settextattributes(Label8to9, sub10);

		TextView Label9to10 = new TextView(this);
		Label9to10.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				onClicktv(day, "9-10");
			}
		});
		String sub1 = getsubdb(day, "9-10");
		settextattributes(Label9to10, sub1);

		TextView Label10to11 = new TextView(this);
		Label10to11.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				onClicktv(day, "10-11");
			}
		});
		String sub2 = getsubdb(day, "10-11");
		settextattributes(Label10to11, sub2);

		TextView Label11to12 = new TextView(this);
		Label11to12.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				onClicktv(day, "11-12");
			}
		});
		String sub3 = getsubdb(day, "11-12");
		settextattributes(Label11to12, sub3);

		TextView Label12to1 = new TextView(this);
		Label12to1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				onClicktv(day, "12-1");
			}
		});
		String sub4 = getsubdb(day, "12-1");
		settextattributes(Label12to1, sub4);

		TextView Label2to3 = new TextView(this);
		Label2to3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				onClicktv(day, "2-3");
			}
		});
		String sub5 = getsubdb(day, "2-3");
		settextattributes(Label2to3, sub5);

		TextView Label3to4 = new TextView(this);
		Label3to4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				onClicktv(day, "3-4");
			}
		});
		String sub6 = getsubdb(day, "3-4");
		settextattributes(Label3to4, sub6);

		TextView Label4to5 = new TextView(this);
		Label4to5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				onClicktv(day, "4-5");
			}
		});
		String sub7 = getsubdb(day, "4-5");
		settextattributes(Label4to5, sub7);

		TextView Label5to6 = new TextView(this);
		Label5to6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				onClicktv(day, "5-6");
			}
		});
		String sub8 = getsubdb(day, "5-6");
		settextattributes(Label5to6, sub8);

		TableRow.LayoutParams paramrow = new TableRow.LayoutParams();
		paramrow.width = 66;
		paramrow.leftMargin = 3;
		paramrow.rightMargin = 3;
		// paramrow.gravity = Gravity.CENTER;

		row8to9.addView(Label8to9, paramrow);
		row9to10.addView(Label9to10, paramrow);
		row10to11.addView(Label10to11, paramrow);
		row11to12.addView(Label11to12, paramrow);
		row12to1.addView(Label12to1, paramrow);
		row2to3.addView(Label2to3, paramrow);
		row3to4.addView(Label3to4, paramrow);
		row4to5.addView(Label4to5, paramrow);
		row5to6.addView(Label5to6, paramrow);
		/* */

	}

	private void settextattributes(TextView tv, String subject) {
		// TODO Auto-generated method stub

		// tv.setOnClickListener(this);
		tv.setTextSize(14);
		tv.setGravity(Gravity.CENTER);
		tv.setTypeface(Typeface.DEFAULT_BOLD);
		tv.setBackgroundColor(Color.argb(139, 125, 125, 125));

		if (subject.length() > 9)
			tv.setTextSize(12);

		if (subject.contains("MATHS")
				|| subject.contains("MATHS".toLowerCase()))
			tv.setTextColor(Color.rgb(11, 6, 11));
		else if (subject.contains("CHEMISTRY")
				|| subject.contains("CHEMISTRY".toLowerCase()))
			tv.setTextColor(Color.rgb(13, 31, 18));
		else if (subject.contains("PHYSICS")
				|| subject.contains("PHYSICS".toLowerCase()))
			tv.setTextColor(Color.rgb(11, 16, 18));
		else if (subject.contains("PHYSICS")
				|| subject.contains("PHYSICS".toLowerCase()))
			tv.setTextColor(Color.rgb(31, 16, 18));
		else
			tv.setTextColor(Color.rgb(17, 16, 11));

		tv.setText(subject);

	}

	String getsubdb(String day, String time) {
		DBAdapterPkr entrytt = new DBAdapterPkr(getApplicationContext());
		String newq = "error";
		try {
			entrytt.open();
			newq = entrytt.getSubj(day, time);
			if (newq == null || newq.contains("No Period")) {
				newq = "-";
			}
			entrytt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			errordialog(e);
		} catch (Exception e) {
			// TODO: handle exception
			errordialog(e);
		}
		return newq;
	}

	void errordialog(Exception e) {
		String error = e.toString();
		Dialog d = new Dialog(this);
		d.setTitle("FAILURE!");
		TextView tv = new TextView(this);
		tv.setText(error);
		d.setContentView(tv);
		d.show();
		d.setCanceledOnTouchOutside(true);
	}

	public void onClicktv(final String day, final String time) {
		// TODO Auto-generated method stub
		//this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		AlertDialog.Builder builder = new Builder(getApplicationContext());

		// aa=v.getContentDescription().toString();
		CharSequence[] SubArray = { "No Period" };
		List<CharSequence> SubList = new ArrayList<CharSequence>(
				Arrays.asList(SubArray));
		adaptersub = new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_spinner_item, SubList);

		try {

			DatabaseCreator entry = new DatabaseCreator(getApplicationContext());
			entry.open();
			int ss = entry.getCount();
			// String returnedName =entry.getName(3);
			for (int i = 0; i <= ss; i++) {
				String returnedName = entry.getName(i);
				if (returnedName != null) {
					boolean ad = true;
					for (int i2 = 1; i2 < adaptersub.getCount(); i2++) {
						if (adaptersub.getItem(i2).equals(returnedName))
							ad = false;
					}
					if (ad)
						adaptersub.add(returnedName);
				}
			}
			entry.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			errordialog(e);
		} catch (Exception e) {
			// TODO: handle exception
			errordialog(e);
		}/*
		*/

		LayoutInflater factory = LayoutInflater.from(this);
		final View textEntryView = factory.inflate(
				R.layout.alert_dialog_text_entry, null);
		final EditText etsubj2 = (EditText) textEntryView
				.findViewById(R.id.subject_edit);
		builder = new AlertDialog.Builder(this);

		etsubj2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
							InputMethodManager.HIDE_IMPLICIT_ONLY);
					imm.showSoftInput(etsubj2, InputMethodManager.SHOW_FORCED);
				}
			}
		});

		builder.setInverseBackgroundForced(true);
		builder.setTitle("Choose subject");
		builder.setView(textEntryView);

		builder.setNeutralButton("set alarm",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent openity = new Intent(
								"in.co.sdslabs.iitr.Multi.ALARM");
						String[] houralm = time.split("-");
						int YourData = Integer.parseInt(houralm[0]);
						openity.putExtra("KEY", YourData);
						startActivity(openity);
					}
				});

		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
						dialog.cancel();
						setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
					}
				});

		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// Finish activity
				// final EditText etsubj2 = (EditText)
				// textEntryView.findViewById(R.id.subject_edit);
				//
				//

				// etsubj2 = (EditText) findViewById(R.id.subject_edit);
				String newsubj = etsubj2.getText().toString();
				DBAdapterPkr entryt3 = new DBAdapterPkr(getApplicationContext());
				try {
					entryt3.open();
					entryt3.insertTitle(newsubj, day, time);
					entryt3.close();
				} catch (SQLException e) {
					errordialog(e);
				} catch (Exception e) {
					errordialog(e);
				}

				try {
					boolean ad = true;
					for (int i2 = 0; i2 < adaptersub.getCount(); i2++) {
						if (adaptersub.getItem(i2).equals(newsubj))
							ad = false;
					}
					if (ad) {
						DatabaseCreator entry = new DatabaseCreator(
								getApplicationContext());
						entry.open();
						entry.createEntry(newsubj);

						adaptersub.add(newsubj);
						adaptersub.notifyDataSetChanged();
						entry.close();
					} else {
						String error = "Subject already present in list";
						Toast.makeText(getApplicationContext(), error,
								Toast.LENGTH_SHORT).show();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO: handle exception
					errordialog(e);
				}

				finish();
				Intent table = new Intent("in.co.sdslabs.iitr.Multi.TIMETABLE");
				table.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(table);
			}
		});
		builder.setSingleChoiceItems(adaptersub, -1,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int position) {

						String newq = adaptersub.getItem(position).toString();
						DBAdapterPkr entrytt = new DBAdapterPkr(
								getApplicationContext());
						try {
							entrytt.open();
							entrytt.insertTitle(newq, day, time);
							entrytt.close();
						} catch (SQLException e) {
							errordialog(e);
						} catch (Exception e) {
							errordialog(e);
						}
						alert.dismiss();
						finish();
						Intent table = new Intent("in.co.sdslabs.iitr.Multi.TIMETABLE");

						table.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
						startActivity(table);
						setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
					}

				});

		alert = builder.create();
		alert.setCancelable(false);
		alert.show();
		alert.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		etsubj2.requestFocus();

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowmenu = getMenuInflater();
		blowmenu.inflate(R.menu.coolmenu, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.Refresh:
			DBAdapterPkr entrytt = new DBAdapterPkr(getApplicationContext());
			try {
				entrytt.open();
				entrytt.deleteDB();
				entrytt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				errordialog(e);
			} catch (Exception e) {
				// TODO: handle exception
				errordialog(e);
			}
			finish();
			Intent table = new Intent("in.co.sdslabs.iitr.Multi.TIMETABLE");
			table.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(table);
			break;
		case R.id.exit:
			finish();

			break;
		case R.id.clear_suball:
			DatabaseCreator dbc = new DatabaseCreator(getApplicationContext());
			try {
				dbc.open();
				dbc.deleteDB();
				dbc.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				errordialog(e);
			} catch (Exception e) {
				// TODO: handle exception
				errordialog(e);
			}

			break;
		default:
			break;
		}
		return false;
	}
}
