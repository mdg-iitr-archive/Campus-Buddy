package in.co.sdslabs.iitr.Multi;

import in.co.sdslabs.iitr.pkr.Datahelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class SimpleCalendarViewActivity extends Activity implements
		OnClickListener {
	private static final String tag = "SimpleCalendarViewActivity";
	String todaytext = "";
	int count = 0;
	private ImageView calendarToJournalButton;
	private Button selectedDayMonthYearButton;
	private Button currentMonth;
	private ImageView prevMonth;
	private TextView todayev;
	private ImageView nextMonth, weekday;
	private GridView calendarView;
	private GridCellAdapter adapter;
	private Calendar _calendar;
	private int month, year;
	private final DateFormat dateFormatter = new DateFormat();
	private static final String dateTemplate = "MMMM yyyy";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.simple_calendar_view);
		int resX;
		resX = this.getWindowManager().getDefaultDisplay().getWidth();
		_calendar = Calendar.getInstance(Locale.getDefault());
		month = _calendar.get(Calendar.MONTH) + 1;
		year = _calendar.get(Calendar.YEAR);
		Log.d(tag, "Calendar Instance:= " + "Month: " + month + " " + "Year: "
				+ year);

		selectedDayMonthYearButton = (Button) this
				.findViewById(R.id.selectedDayMonthYear);
		selectedDayMonthYearButton.setText("Selected: ");

		weekday = (ImageView) this.findViewById(R.id.calendarheader);
		BitmapDrawable drawable = (BitmapDrawable) weekday.getDrawable();
		Bitmap bitmap = drawable.getBitmap();
		Bitmap rescaledDaysOriginal = Bitmap.createScaledBitmap(bitmap, resX,
				bitmap.getHeight() * resX / bitmap.getWidth(), true);
		weekday.setImageBitmap(rescaledDaysOriginal);
		weekday.setAdjustViewBounds(true);

		prevMonth = (ImageView) this.findViewById(R.id.prevMonth);
		prevMonth.setOnClickListener(this);

		currentMonth = (Button) this.findViewById(R.id.currentMonth);
		currentMonth.setText(dateFormatter.format(dateTemplate,
				_calendar.getTime()));

		nextMonth = (ImageView) this.findViewById(R.id.nextMonth);
		nextMonth.setOnClickListener(this);

		todayev = (TextView) this.findViewById(R.id.tevent);
		todayev.append(todaytext);
		calendarView = (GridView) this.findViewById(R.id.calendar);

		// Initialised
		adapter = new GridCellAdapter(getApplicationContext(),
				R.id.calendar_day_gridcell, month, year);
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter);

		Datahelper myDbHelper = new Datahelper(this);

		try {
			myDbHelper.getInstance(this);
			myDbHelper.createDataBase();

		} catch (IOException ioe) {

			throw new Error(ioe);

		}

		try {

			// myDbHelper.openDataBase();
			myDbHelper.getInstance(this);
			myDbHelper.createDataBase();
			// String data ="test";
			// String data ="test";
			// data=myDbHelper.getEvent(2202012);

			// data=myDbHelper.getEvent(2202012);

			// Dialog d = new Dialog(SimpleCalendarViewActivity.this);
			// data=myDbHelper.getData();

			
			// sample create entry for 26 jan 2013-
			// myDbHelper.createEntry(260013," Republic day");
			//NOTE-every year change last two digit of year in code 

			myDbHelper.close();

			// Log.d("try create","q"+q );
			myDbHelper.close();
			// d.setTitle("TESTing!");
			// TextView tv = new TextView(SimpleCalendarViewActivity.this);
			// tv.setText(data);
			// d.setContentView(tv);
			// d.show();
			// d.setCanceledOnTouchOutside(true);

		} catch (SQLException sqle) {
			Log.v(tag, "unable to create dwdedededed");
			throw sqle;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setGridCellAdapterToDate(int month, int year) {
		adapter = new GridCellAdapter(getApplicationContext(),
				R.id.calendar_day_gridcell, month, year);
		_calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH));
		currentMonth.setText(dateFormatter.format(dateTemplate,
				_calendar.getTime()));
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		if (v == prevMonth) {
			if (month <= 1) {
				month = 12;
				year--;
			} else {
				month--;
			}
			Log.d(tag, "Setting Prev Month in GridCellAdapter: " + "Month: "
					+ month + " Year: " + year);
			setGridCellAdapterToDate(month, year);
		}
		if (v == nextMonth) {
			if (month > 11) {
				month = 1;
				year++;
			} else {
				month++;
			}
			Log.d(tag, "Setting Next Month in GridCellAdapter: " + "Month: "
					+ month + " Year: " + year);
			setGridCellAdapterToDate(month, year);
		}
		// v.invalidate();

	}

	@Override
	public void onDestroy() {
		Log.d(tag, "Destroying View ...");
		super.onDestroy();
	}

	// ///////////////////////////////////////////////////////////////////////////////////////
	// Inner Class
	public class GridCellAdapter extends BaseAdapter implements OnClickListener {
		private static final String tag = "GridCellAdapter";
		private final Context _context;

		private final List<String> list;
		private static final int DAY_OFFSET = 1;
		private final String[] weekdays = new String[] { "Sun", "Mon", "Tue",
				"Wed", "Thu", "Fri", "Sat" };
		private final String[] months = { "January", "February", "March",
				"April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		private final int[] daysOfMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30,
				31, 30, 31 };
		private final int month, year;
		private int daysInMonth, prevMonthDays;
		private int currentDayOfMonth;
		private int currentWeekDay;
		String data;
		int eventsPerMonthMap;
		private Button gridcell;
		private TextView num_events_per_day;
		private final SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"dd-MMM-yyyy");

		// Days in Current Month
		public GridCellAdapter(Context context, int textViewResourceId,
				int month, int year) {
			super();
			this._context = context;
			this.list = new ArrayList<String>();
			this.month = month;
			this.year = year;

			Log.d(tag, "==> Passed in Date FOR Month: " + month + " "
					+ "Year: " + year);
			Calendar calendar = Calendar.getInstance();
			setCurrentDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
			setCurrentWeekDay(calendar.get(Calendar.DAY_OF_WEEK));
			Log.d(tag, "New Calendar:= " + calendar.getTime().toString());
			Log.d(tag, "CurrentDayOfWeek :" + getCurrentWeekDay());
			Log.d(tag, "CurrentDayOfMonth :" + getCurrentDayOfMonth());

			// Print Month
			printMonth(month, year);

			// Find Number of Events
			// eventsPerMonthMap = findNumberOfEventsPerMonth(year, month);

		}

		private String getMonthAsString(int i) {
			return months[i];
		}

		private String getWeekDayAsString(int i) {
			return weekdays[i];
		}

		private int getNumberOfDaysOfMonth(int i) {
			return daysOfMonth[i];
		}

		public String getItem(int position) {
			return list.get(position);
		}
		
		@Override
		public int getCount() {
			return list.size();
		}

		private void printMonth(int mm, int yy) {
			Log.d(tag, "==> printMonth: mm: " + mm + " " + "yy: " + yy);
			// The number of days to leave blank at
			// the start of this month.
			int trailingSpaces = 0;
			int leadSpaces = 0;
			int daysInPrevMonth = 0;
			int prevMonth = 0;
			int prevYear = 0;
			int nextMonth = 0;
			int nextYear = 0;

			int currentMonth = mm - 1;
			String currentMonthName = getMonthAsString(currentMonth);
			daysInMonth = getNumberOfDaysOfMonth(currentMonth);

			Log.d(tag, "Current Month: " + " " + currentMonthName + " having "
					+ daysInMonth + " days.");

			// Gregorian Calendar : minus 1, set to FIRST OF MONTH
			// to calculate trail later on
			GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);
			// to colour today's date blue
			Calendar _calendar = Calendar.getInstance(Locale.getDefault());
			int monthnow = _calendar.get(Calendar.MONTH) + 1;

			Log.d(tag, "Gregorian Calendar:= " + cal.getTime().toString()
					+ "check" + cal.get(Calendar.MONTH));

			if (currentMonth == 11) {
				prevMonth = currentMonth - 1;
				daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
				nextMonth = 0;
				prevYear = yy;
				nextYear = yy + 1;
				Log.d(tag, "*->PrevYear: " + prevYear + " PrevMonth:"
						+ prevMonth + " NextMonth: " + nextMonth
						+ " NextYear: " + nextYear);
			} else if (currentMonth == 0) {
				prevMonth = 11;
				prevYear = yy - 1;
				nextYear = yy;
				daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
				nextMonth = 1;
				Log.d(tag, "**--> PrevYear: " + prevYear + " PrevMonth:"
						+ prevMonth + " NextMonth: " + nextMonth
						+ " NextYear: " + nextYear);
			} else {
				prevMonth = currentMonth - 1;
				nextMonth = currentMonth + 1;
				nextYear = yy;
				prevYear = yy;
				daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
				Log.d(tag, "***---> PrevYear: " + prevYear + " PrevMonth:"
						+ prevMonth + " NextMonth: " + nextMonth
						+ " NextYear: " + nextYear);
			}

			// Compute how much to leave before before the first day of the
			// month.
			// getDay() returns 0 for Sunday.
			int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
			trailingSpaces = currentWeekDay;

			Log.d(tag, "Week Day:" + currentWeekDay + " is "
					+ getWeekDayAsString(currentWeekDay));
			Log.d(tag, "No. Trailing space to Add: " + trailingSpaces);
			Log.d(tag, "No. of Days in Previous Month: " + daysInPrevMonth);

			if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 2) {
				++daysInMonth;
			}

			// Trailing Month days
			for (int i = 0; i < trailingSpaces; i++) {
				Log.d(tag,
						"PREV MONTH:= "
								+ prevMonth
								+ " => "
								+ getMonthAsString(prevMonth)
								+ " "
								+ String.valueOf((daysInPrevMonth
										- trailingSpaces + DAY_OFFSET)
										+ i) + "-" + prevMonth);
				list.add(String
						.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET)
								+ i)
						+ "-GREY"
						+ "-"
						+ getMonthAsString(prevMonth)
						+ "-"
						+ prevYear + "-" + prevMonth);
			}

			// Current Month Days
			for (int i = 1; i <= daysInMonth; i++) {
				Log.d(currentMonthName, String.valueOf(i) + " "
						+ getMonthAsString(currentMonth) + " " + yy + "-"
						+ currentMonth);
				if (i == getCurrentDayOfMonth() && mm == monthnow) {
					list.add(String.valueOf(i) + "-BLUE" + "-"
							+ getMonthAsString(currentMonth) + "-" + yy + "-"
							+ currentMonth);
					todaytext = "" + String.valueOf(i)
							+ String.format("%02d", currentMonth) + 14;
					int todayint = Integer.parseInt(todaytext);
					// To get today's event
					Datahelper myDbHelper = new Datahelper(
							getApplicationContext());

					try {

						// myDbHelper.openDataBase();
						myDbHelper.getInstance(getBaseContext());
						try {
							myDbHelper.createDataBase();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (myDbHelper != null) {
							String data = myDbHelper.getEvent(todayint);
						}
						
						if (data == null) {
							if (count == 0) {
								data = "No event today";
							} else
								data = "";
							count = 1;
						}
						// myDbHelper.close();

						todayev.append(" " + data);

					} catch (SQLException sqle) {

						throw sqle;

					}

				} else {
					list.add(String.valueOf(i) + "-WHITE" + "-"
							+ getMonthAsString(currentMonth) + "-" + yy + "-"
							+ currentMonth);
				}
			}

			// Leading Month days
			for (int i = 1; i <= list.size() % 7; i++) {
				Log.d(tag, "NEXT MONTH:= " + getMonthAsString(nextMonth) + "-"
						+ nextMonth);
				list.add(String.valueOf(i) + "-GREY" + "-"
						+ getMonthAsString(nextMonth) + "-" + nextYear + "-"
						+ nextMonth);
			}
		}

		/**
		 * NOTE: YOU NEED TO IMPLEMENT THIS PART Given the YEAR, MONTH, retrieve
		 * ALL entries from a SQLite database for that month. Iterate over the
		 * List of All entries, and get the dateCreated, which is converted into
		 * day.
		 */
		private int findNumberOfEventsPerMonth(int year, int month,
				String daysinmonth) {

			DateFormat dateFormatter2 = new DateFormat();
			int events = 0, dayint1;
			String day;
			
			

			Datahelper myDbHelperq = new Datahelper(getApplicationContext());
			try {
				
				//**changed jugaad
				day = "" + daysinmonth + String.format("%02d", month+1) + 14;
				dayint1 = Integer.parseInt(day);
				myDbHelperq.getInstance(getBaseContext());
				String f = myDbHelperq.getEvent(dayint1);
				Log.d("calculating events", "" + dayint1);
				if (f != null && f.length() == 0) {

				} else if (f != null) {
					events = 1;
					return events;
				}
			} catch (SQLException sqle) {

				throw sqle;

			}

			return events;

		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			GridView grid = (GridView) parent;
			grid.setFocusable(false);
			grid.setFocusableInTouchMode(false);
			View row = convertView;
			if (row == null) {
				LayoutInflater inflater = (LayoutInflater) _context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row = inflater.inflate(R.layout.calendar_day_gridcell, parent,
						false);
			}

			// Get a reference to the Day gridcell
			gridcell = (Button) row.findViewById(R.id.calendar_day_gridcell);
			gridcell.setOnClickListener(this);
			// gridcell.setOnTouchListener(this);
			// ACCOUNT FOR SPACING

			Log.d(tag, "Current Day: " + getCurrentDayOfMonth());
			String[] day_color = list.get(position).split("-");
			String theday = day_color[0];
			String themonth = day_color[2];
			String theyear = day_color[3];
			String monthasint = day_color[4];
			monthasint = day_color[4];

			int mymonth2 = Integer.parseInt(monthasint);
			if (year == 2014)
				eventsPerMonthMap = findNumberOfEventsPerMonth(year, mymonth2,
						theday);
			if (eventsPerMonthMap != 0) {

				num_events_per_day = (TextView) row
						.findViewById(R.id.num_events_per_day);
				Integer numEvents = eventsPerMonthMap;
				num_events_per_day.setText(numEvents.toString());

				gridcell.setBackgroundResource(R.drawable.redsqr3);

			}

			// Set the Day GridCell
			gridcell.setText(theday);
			// gridcell.setTag(theday + "-" + themonth + "-" + theyear);
			gridcell.setTag(list.get(position));
			Log.d(tag, "Setting GridCell " + theday + "-" + themonth + "-"
					+ theyear + "-" + monthasint);

			if (day_color[1].equals("WHITE")) {
				gridcell.setTextColor(Color.WHITE);
			}
			// sat and sunday

			if (position % 7 == 0) {
				gridcell.setTextColor(Color.YELLOW);
			}
			if (position % 7 == 6) {
				gridcell.setTextColor(Color.YELLOW);
			}

			if (day_color[1].equals("GREY")) {
				gridcell.setTextColor(Color.DKGRAY);
			}

			if (day_color[1].equals("BLUE")) {
				gridcell.setTextColor(Color.GREEN);
				gridcell.setBackgroundResource(R.drawable.calendar_tile1);
			}
			if (day_color[1].equals("RED")) {
				gridcell.setTextColor(Color.RED);
			}

			return row;
		}

		// //////////////////////// to do
		/*
		 * @Override public boolean onTouch(View v, MotionEvent event) { // TODO
		 * Auto-generated method stub
		 * 
		 * try { Thread.sleep(50); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * switch (event.getAction()) { case MotionEvent.ACTION_MOVE:
		 * Log.d("tt", "msg"); break; case MotionEvent.EDGE_LEFT:
		 * 
		 * break;
		 * 
		 * } return true; }
		 */
		@Override
		public void onClick(View view) {

			getdataevent(view);

		}

		public void getdataevent(View view) {

			String theday;
			String themonth;
			String theyear;
			String monthasint;
			int mymonth, myday, myyear, dayint;

			String[] day_color = (String[]) ((String) view.getTag()).split("-");
			theday = day_color[0];
			themonth = day_color[2];
			theyear = day_color[3];
			monthasint = day_color[4];

			mymonth = Integer.parseInt(monthasint);
			mymonth+=1;
			myday = Integer.parseInt(day_color[0]);
			myyear = Integer.parseInt(day_color[3]);

			String day = "" + myday + String.format("%02d", mymonth) + 14;
			Log.d("CLICkedcell", day);
			dayint = Integer.parseInt(day);
			// String date_month_year =
			selectedDayMonthYearButton.setText("Selected: " + theday + "-"
					+ themonth + "-" + theyear);

			Datahelper myDbHelper = new Datahelper(getApplicationContext());

			try {

				// myDbHelper.openDataBase();
				myDbHelper.getInstance(getBaseContext());
				// myDbHelper.createDataBase();
				String title = "Events on " + theday + "-" + themonth + "-"
						+ theyear;
				String data = myDbHelper.getEvent(dayint);
				if (data != null) {
					// First letter is converted to Upprcase over here.
					String lowerCase = (new StringBuilder(data)).toString();
					Character c = lowerCase.charAt(0);
					char f = Character.toUpperCase(c);
					StringBuilder sb = new StringBuilder();
					sb.append(f);
					data = (sb.append(lowerCase.substring(1))).toString();
					}
				if (data == null)
					data = "No event on this day";
				// myDbHelper.close();
				Dialog d = new Dialog(SimpleCalendarViewActivity.this , R.style.DialogTheme);
				// data=myDbHelper.getData();
				// long q=myDbHelper.createEntry(121321, "description");
				// Log.d("try create","q"+q );
				d.setTitle(title);
				TextView tv = new TextView(SimpleCalendarViewActivity.this);
				tv.setText(data);
				tv.setTextColor(Color.BLACK);
				tv.setTextSize(16);
				tv.setPadding(10, 5, 5, 10);
				d.setContentView(tv);
				d.show();
				d.setCanceledOnTouchOutside(true);
			} catch (SQLException sqle) {

				throw sqle;

			}

		}

		public int getCurrentDayOfMonth() {
			return currentDayOfMonth;
		}

		private void setCurrentDayOfMonth(int currentDayOfMonth) {
			this.currentDayOfMonth = currentDayOfMonth;
		}

		public void setCurrentWeekDay(int currentWeekDay) {
			this.currentWeekDay = currentWeekDay;
		}

		public int getCurrentWeekDay() {
			return currentWeekDay;
		}
	}
}
