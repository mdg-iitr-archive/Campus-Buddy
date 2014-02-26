package mdg.iitr.campusbuddy.database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.PointF;

public class DBAdapter extends SQLiteOpenHelper {

	// fields for table 1
	public static final String KEY_ROWID_VENUE = "_id_venue";
	public static final String KEY_MINX = "_minX";
	public static final String KEY_MINY = "_minY";
	public static final String KEY_MAXX = "_maxX";
	public static final String KEY_MAXY = "_maxY";
	public static final String KEY_TOUCH_VENUE = "_touch_venue";

	// fields for table 2
	public static final String KEY_ROWID_DETAILS = "_id_details";
	public static final String KEY_VENUE = "_place";
	public static final String KEY_ADVANCED = "_advanced";
	public static final String KEY_MORE = "_more";
	public static final String KEY_TEL = "_tel";
	public static final String KEY_MAIL = "_mail";

	// fields for table 3
	public static final String KEY_ROWID_INFO = "_id_info";
	public static final String KEY_PLACE = "_place";
	public static final String KEY_INFO = "_info";
	public static final String KEY_TEL_MAP = "_tel";
	public static final String KEY_MAIL_MAP = "_mail";
	public static final String KEY_IMAGES = "_images";

	public static final String DATABASE_NAME = "cognizance.db";
	public static final String DATABASE_TABLE1 = "table_venue";
	public static final String DATABASE_TABLE2 = "table_details";
	public static final String DATABASE_TABLE3 = "table_info";
	private static final int DATABASE_VERSION = 1;
	public static String DATABASE_PATH = "/data/data/mdg.iitr.campusbuddy/databases/";

	private static DBAdapter ourHelper;
	private final Context ourContext;
	private SQLiteDatabase db;

	public DBAdapter(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		ourContext = context;
		// TODO Auto-generated constructor stub
	}

	public DBAdapter getInstance(Context context) {
		if (ourHelper == null) {
			ourHelper = new DBAdapter(context);
		}
		return this;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

	public synchronized void close() {
		super.close();
		db.close();
	}

	public void createDataBase() throws IOException, SQLException {
		boolean dbExist = checkDataBase();

		if (dbExist) {
			db = ourHelper.getWritableDatabase();
		} else {
			db = ourHelper.getWritableDatabase();

			try {
				copydataBase();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean deleteAndInsert_table_details() {
		db = ourHelper.getWritableDatabase();
		boolean success = false;
		boolean finalStatus = false;
		try {
			// db.execSQL("drop table if exists table_details");
			deleteTable(db);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * if (success) { try{ db.execSQL(
		 * "create table table_details(_id_details integer primary key autoincrement, _name text, _venue text, _time_hour integer, _time_minute integer, _time_ampm text, __time integer, _description text,_date integer)"
		 * ); finalStatus=true; return true; } catch (Exception e) { // TODO:
		 * handle exception } }
		 */
		return success;
	}

	private boolean checkDataBase() {
		SQLiteDatabase checkDB = null;
		try {
			String myPath = DATABASE_PATH + DATABASE_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READWRITE);
			checkDB.close();
		} catch (SQLiteException e) {
			// database does't exist yet.
		}
		if (checkDB != null) {
			checkDB.close();
		}
		return checkDB != null ? true : false;
	}

	private void copydataBase() throws IOException {
		try {
			InputStream input = ourContext.getResources().getAssets()
					.open(DATABASE_NAME);

			String outPutFileName = DATABASE_PATH + DATABASE_NAME;
			OutputStream output = new FileOutputStream(outPutFileName);
			byte[] buffer = new byte[1024];
			int length;

			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			output.flush();
			output.close();
			input.close();
		} catch (IOException e) {
		}
	}

	public String[] getDataForTelDir1Spinner() {
		// TODO Auto-generated method stub
		String[] columns1 = new String[] { KEY_VENUE };
		String[] columns2 = new String[] { KEY_ROWID_DETAILS, KEY_VENUE };
		Cursor c = db.query(true, DATABASE_TABLE2, columns1, null, null, null,
				null, KEY_VENUE, null);
		String result[] = new String[c.getCount() + 1];
		
		//Cursor c = db.query(true, DATABASE_TABLE2, columns2, null, null, null,
		//		null, KEY_ROWID_DETAILS, null);
		

		int iVenue = c.getColumnIndex(KEY_VENUE);
		result[0] = "Select";
		int i = 1;
		int j = 1;

		for (c.moveToFirst(); !c.isAfterLast(); i++, c.moveToNext()) {
			//if (!result[j - 1].equals(c.getString(iVenue)))
				result[i] = c.getString(iVenue);
		}
		c.close();
		return result;
	}

	public String[] getDataForMapSpinner() {
		// TODO Auto-generated method stub
		String[] columns = new String[] { KEY_PLACE };
		Cursor c = db.query(true, DATABASE_TABLE3, columns, null, null, null,
				null, KEY_PLACE, null);
		String result[] = new String[c.getCount() + 1];

		int iPlace = c.getColumnIndex(KEY_PLACE);
		result[0] = "Show Full Map";
		int i = 1;

		for (c.moveToFirst(); !c.isAfterLast(); i++, c.moveToNext()) {
			result[i] = c.getString(iPlace);
		}
		c.close();
		return result;
	}

	public String searchEntryForVenue(String x, String y) throws SQLException {
		// TODO Auto-generated method stub
		db = ourHelper.getWritableDatabase();
		String[] columns = new String[] { KEY_ROWID_VENUE, KEY_MINX, KEY_MINY,
				KEY_MAXX, KEY_MAXY, KEY_TOUCH_VENUE };

		int ix = Integer.parseInt(x) * 2;
		int iy = Integer.parseInt(y) * 2;

		Cursor c = db.query(DATABASE_TABLE1, columns, KEY_MINX + "<=" + ix
				+ " AND " + KEY_MINY + "<=" + iy + " AND " + KEY_MAXX + ">="
				+ ix + " AND " + KEY_MAXY + ">=" + iy, null, null, null, null);
		try {
			if (c != null) {
				c.moveToFirst();
				String venue = c.getString(5);
				c.close();
				return venue;
			}
		} catch (CursorIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			c.close();
			return null;
		}
		return null;
	}

	// ----------------------search database-----------
	public String[][] searchEntryForDetails(String returnedVenue,
			String advanced) {
		// TODO Auto-generated method stub
		db = ourHelper.getWritableDatabase();
		String[] columns = new String[] { KEY_ROWID_DETAILS, KEY_VENUE,
				KEY_ADVANCED, KEY_MORE, KEY_TEL, KEY_MAIL };

		Cursor c;

		if (advanced == null)
			c = db.query(DATABASE_TABLE2, columns, KEY_VENUE + " = \""
					+ returnedVenue + "\"", null, null, null, KEY_ADVANCED
					+ "," + KEY_MORE);
		else
			c = db.query(DATABASE_TABLE2, columns, KEY_VENUE + " = \""
					+ returnedVenue + "\"" + " AND " + KEY_ADVANCED + " = \""
					+ advanced + "\"", null, null, null, KEY_MORE);
		String result[][] = new String[c.getCount()][4];

		int iAdvanced = c.getColumnIndex(KEY_ADVANCED);
		int iTel = c.getColumnIndex(KEY_TEL);
		int iMail = c.getColumnIndex(KEY_MAIL);
		int iMore = c.getColumnIndex(KEY_MORE);
		int i = 0;

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext(), i++) {
			result[i][0] = c.getString(iAdvanced);
			result[i][1] = c.getString(iTel);
			result[i][2] = c.getString(iMail);
			result[i][3] = c.getString(iMore);
		}
		c.close();
		return result;
	}

	// ----------------------Add another parameter for advanced-----------
	public String[] getDataForTelDir2Spinner(String returnedVenue) {
		// TODO Auto-generated method stub
		db = ourHelper.getWritableDatabase();
		String[] columns = new String[] { KEY_ADVANCED };
		Cursor c = db.query(true, DATABASE_TABLE2, columns, KEY_VENUE + " = \""
				+ returnedVenue + "\"", null, null, null, KEY_ADVANCED, null);
		String result[] = new String[c.getCount() + 1];

		int iAdvanced = c.getColumnIndex(KEY_ADVANCED);
		result[0] = "All";
		int i = 1;

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext(), i++) {
			result[i] = c.getString(iAdvanced);
		}
		c.close();
		return result;
	}

	// ------------------to be used in wona feeds-------------
	/*
	 * public String[][] searchEntryForDetailsTodays(String returnedVenue) { //
	 * TODO Auto-generated method stub db = ourHelper.getWritableDatabase();
	 * String[] columns = new String[] { KEY_ROWID_DETAILS, KEY_VENUE,
	 * KEY_ADVANCED, KEY_TEL, KEY_MAIL }; Cursor c = db.query(DATABASE_TABLE2,
	 * columns, KEY_VENUE + " = \"" + returnedVenue + "\" AND " + KEY_DATE +
	 * "==" + Time.MONTH_DAY, null, null, null, KEY_TIME_AMPM + "," +
	 * KEY_TIME_HOUR + "," + KEY_TIME_MINUTE + "," + KEY_NAME); String
	 * result[][] = new String[c.getCount()][5];
	 * 
	 * int iName = c.getColumnIndex(KEY_NAME); int iDate =
	 * c.getColumnIndex(KEY_DATE); int iTimeHour =
	 * c.getColumnIndex(KEY_TIME_HOUR); int iTimeMinute =
	 * c.getColumnIndex(KEY_TIME_MINUTE); int iTimeAmPm =
	 * c.getColumnIndex(KEY_TIME_AMPM); int iDescription =
	 * c.getColumnIndex(KEY_DESCRIPTION); int i = 0;
	 * 
	 * for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext(), i++) {
	 * result[i][0] = c.getString(iName); result[i][1] = returnedVenue;
	 * result[i][2] = c.getString(iDate) + " March,2012"; result[i][3] =
	 * c.getString(iTimeHour) + ":"; if ("0".equals(c.getString(iTimeMinute)))
	 * result[i][3] += "00"; else result[i][3] += c.getString(iTimeMinute);
	 * result[i][3] += " " + c.getString(iTimeAmPm); result[i][4] =
	 * c.getString(iDescription); } c.close(); return result; }
	 */

	// --------------------------returns info of touched place in
	// map------------
	public String[] searchPlaceForInfo(String place) {
		// TODO Auto-generated method stub
		db = ourHelper.getWritableDatabase();
		String[] columns = new String[] { KEY_ROWID_INFO, KEY_PLACE, KEY_INFO,
				KEY_TEL_MAP, KEY_MAIL_MAP };

		Cursor c = db.query(DATABASE_TABLE3, columns, KEY_PLACE + " = \""
				+ place + "\"", null, null, null, null);

		String result[] = new String[4];

		int iId = c.getColumnIndex(KEY_ROWID_INFO);
		int iDesc = c.getColumnIndex(KEY_INFO);
		int iTel = c.getColumnIndex(KEY_TEL_MAP);
		int iMail = c.getColumnIndex(KEY_MAIL_MAP);

		if (c != null) {
			c.moveToFirst();
			result[0] = c.getString(iId);
			result[1] = c.getString(iDesc);
			result[2] = c.getString(iTel);
			result[3] = c.getString(iMail);
			c.close();
			return result;
		}
		return null;
	}

	// ------------to show "Show on Map button or not----------"
	public boolean isOnMap(String place1, String place2) {
		// TODO Auto-generated method stub
		db = ourHelper.getWritableDatabase();
		String[] columns = new String[] { KEY_PLACE };

		Cursor c = db.query(DATABASE_TABLE3, columns, KEY_PLACE + " = \""
				+ place1 + "\"" + " OR " + KEY_PLACE + " = \"" + place1 + "\"",
				null, null, null, null);

		if (c != null)
			return true;
		else
			return false;
	}

	// --------------No use of time search------------
	/*
	 * public String[][] searchEntryForTime(int date, int hours, int minutes) {
	 * // TODO Auto-generated method stub db = ourHelper.getWritableDatabase();
	 * String[] columns = new String[] { KEY_NAME, KEY_VENUE, KEY_DATE,
	 * KEY_TIME_HOUR, KEY_TIME_MINUTE, KEY_TIME_AMPM, KEY_DESCRIPTION }; Cursor
	 * c = db.query(DATABASE_TABLE2, columns, KEY_DATE + ">" + date + " OR (" +
	 * KEY_DATE + "==" + date + " AND " + KEY_TIME + ">=" + (hours * 100 +
	 * minutes) + ")", null, null, null, KEY_DATE + "," + KEY_TIME + "," +
	 * KEY_NAME + "," + KEY_VENUE); String result[][] = new
	 * String[c.getCount()][5];
	 * 
	 * int iName = c.getColumnIndex(KEY_NAME); int iDate =
	 * c.getColumnIndex(KEY_DATE); int iVenue = c.getColumnIndex(KEY_VENUE); int
	 * iTimeHour = c.getColumnIndex(KEY_TIME_HOUR); int iTimeMinute =
	 * c.getColumnIndex(KEY_TIME_MINUTE); int iTimeAmPm =
	 * c.getColumnIndex(KEY_TIME_AMPM); int iDescription =
	 * c.getColumnIndex(KEY_DESCRIPTION); int i = 0;
	 * 
	 * for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext(), i++) {
	 * result[i][0] = c.getString(iName); result[i][1] = c.getString(iVenue);
	 * result[i][2] = c.getString(iDate) + " March,2012"; result[i][3] =
	 * c.getString(iTimeHour) + ":"; if ("0".equals(c.getString(iTimeMinute)))
	 * result[i][3] += "00"; else result[i][3] += c.getString(iTimeMinute);
	 * result[i][3] += " " + c.getString(iTimeAmPm); result[i][4] =
	 * c.getString(iDescription); } c.close(); return result; }
	 */

	// -----------------Again no use of time search----------------
	/*
	 * public String[][] searchEntryForTimeTodays(int date, int hours, int
	 * minutes) { // TODO Auto-generated method stub db =
	 * ourHelper.getWritableDatabase(); String[] columns = new String[] {
	 * KEY_NAME, KEY_VENUE, KEY_DATE, KEY_TIME_HOUR, KEY_TIME_MINUTE,
	 * KEY_TIME_AMPM, KEY_DESCRIPTION }; Cursor c = db.query(DATABASE_TABLE2,
	 * columns, KEY_DATE + "==" + date + " AND " + KEY_TIME + ">=" + (hours *
	 * 100 + minutes), null, null, null, KEY_TIME + "," + KEY_NAME + "," +
	 * KEY_VENUE); String result[][] = new String[c.getCount()][5];
	 * 
	 * int iName = c.getColumnIndex(KEY_NAME); int iDate =
	 * c.getColumnIndex(KEY_DATE); int iVenue = c.getColumnIndex(KEY_VENUE); int
	 * iTimeHour = c.getColumnIndex(KEY_TIME_HOUR); int iTimeMinute =
	 * c.getColumnIndex(KEY_TIME_MINUTE); int iTimeAmPm =
	 * c.getColumnIndex(KEY_TIME_AMPM); int iDescription =
	 * c.getColumnIndex(KEY_DESCRIPTION); int i = 0;
	 * 
	 * for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext(), i++) {
	 * result[i][0] = c.getString(iName); result[i][1] = c.getString(iVenue);
	 * result[i][2] = c.getString(iDate) + " March,2012"; result[i][3] =
	 * c.getString(iTimeHour) + ":"; if ("0".equals(c.getString(iTimeMinute)))
	 * result[i][3] += "00"; else result[i][3] += c.getString(iTimeMinute);
	 * result[i][3] += " " + c.getString(iTimeAmPm); result[i][4] =
	 * c.getString(iDescription); } c.close(); return result; }
	 */

	// ------------------to be seen later where this func is used-------------
	/*
	 * public long addNewEntry(String name, String venue, String date, String
	 * time, String description) { db = ourHelper.getWritableDatabase(); // TODO
	 * Auto-generated method stub
	 * 
	 * int iTime = Integer.parseInt(time); int iDate = Integer.parseInt(date);
	 * int iMinutes = iTime % 100; int iHour = iTime / 100; String sAmPm; if
	 * (iHour > 12) { iHour -= 12; sAmPm = "PM"; } else if (iHour == 12) sAmPm =
	 * "PM"; else sAmPm = "AM";
	 * 
	 * if (time.length() == 3) { String temp = "0" + time; time = temp; }
	 * 
	 * ContentValues cv = new ContentValues(); cv.put(KEY_NAME, name);
	 * cv.put(KEY_VENUE, venue); cv.put(KEY_DATE, iDate); cv.put(KEY_TIME_HOUR,
	 * iHour); cv.put(KEY_TIME_MINUTE, iMinutes); cv.put(KEY_TIME_AMPM, sAmPm);
	 * cv.put(KEY_TIME, time); cv.put(KEY_DESCRIPTION, description);
	 * 
	 * return db.insert(DATABASE_TABLE2, null, cv); }
	 */

	/*
	 * public long updateEntry(String name, String newVenue, String newDate,
	 * String newTime, String newDescription) { db =
	 * ourHelper.getWritableDatabase(); // TODO Auto-generated method stub
	 * 
	 * int iTime = Integer.parseInt(newTime); int iDate =
	 * Integer.parseInt(newDate); int iMinutes = iTime % 100; int iHour = iTime
	 * / 100; String sAmPm; if (iHour > 12) { iHour -= 12; sAmPm = "PM"; } else
	 * if (iHour == 12) sAmPm = "PM"; else sAmPm = "AM";
	 * 
	 * if (newTime.length() == 3) { String temp = "0" + newTime; newTime = temp;
	 * }
	 * 
	 * ContentValues cv = new ContentValues(); cv.put(KEY_NAME, name);
	 * cv.put(KEY_VENUE, newVenue); cv.put(KEY_DATE, iDate);
	 * cv.put(KEY_TIME_HOUR, iHour); cv.put(KEY_TIME_MINUTE, iMinutes);
	 * cv.put(KEY_TIME_AMPM, sAmPm); cv.put(KEY_TIME, newTime);
	 * cv.put(KEY_DESCRIPTION, newDescription); return
	 * db.update(DATABASE_TABLE2, cv, KEY_NAME + " == \"" + name + "\"", null);
	 * }
	 */

	/*
	 * public void deleteEntry(String name) { db =
	 * ourHelper.getWritableDatabase(); // TODO Auto-generated method stub
	 * db.delete(DATABASE_TABLE2, KEY_NAME + " == \"" + name + "\"", null); }
	 */

	public void deleteTable() {
		db = ourHelper.getWritableDatabase();
		// TODO Auto-generated method stub
		db.delete(DATABASE_TABLE2, null, null);
	}

	public void deleteTable(SQLiteDatabase dbPrev) {
		dbPrev.delete(DATABASE_TABLE2, null, null);
	}

	public PointF searchPlaceForCoordinates(String selection) {
		// TODO Auto-generated method stub
		db = ourHelper.getWritableDatabase();
		String[] columns = new String[] { KEY_MINX, KEY_MINY, KEY_MAXX,
				KEY_MAXY, KEY_TOUCH_VENUE };
		PointF coor = new PointF();

		Cursor c = db.query(DATABASE_TABLE1, columns, KEY_TOUCH_VENUE + "==\""
				+ selection + "\"", null, null, null, null);

		int iMinX = c.getColumnIndex(KEY_MINX);
		int iMinY = c.getColumnIndex(KEY_MINY);
		int iMaxX = c.getColumnIndex(KEY_MAXX);
		int iMaxY = c.getColumnIndex(KEY_MAXY);

		try {
			if (c != null) {
				c.moveToFirst();
				coor.x = (Integer.parseInt(c.getString(iMinX)) + Integer
						.parseInt(c.getString(iMaxX))) / 4;
				coor.y = (Integer.parseInt(c.getString(iMinY)) + Integer
						.parseInt(c.getString(iMaxY))) / 4;
				c.close();
			}
		} catch (CursorIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return coor;
	}
}
