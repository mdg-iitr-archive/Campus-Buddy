package mdg.iitr.campusbuddy.pkr;

import java.sql.SQLException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseCreator {

	private static final String DATABASE_NAME = "HotOrNotdb";
	private static final String DATABASE_TABLE = "TIMETable";
	private static final int DATABASE_VERSION = 1;
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "subject_name";
	int deleted;
	

    
	private DbSubject ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;

	private static class DbSubject extends SQLiteOpenHelper {

		public DbSubject(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
					+ " TEXT NOT NULL );");
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
	}

	public DatabaseCreator(Context c) {
		ourContext = c;
		ourHelper = new DbSubject(ourContext);
	}

	public DatabaseCreator open() throws SQLException {
		
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		ourHelper.close();
	}

	public long createEntry(String name) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	
	public int getCount() {
		// TODO Auto-generated method stub
		String[] columns = new String[] { KEY_ROWID, KEY_NAME};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,
				null, null);
		if(c.moveToLast())
		{
			int q=c.getInt(0);
			c.close();
			return q;
		}
		c.close();
		return 0;
	}
	
	public String getName(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[] { KEY_ROWID, KEY_NAME };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "="
				+ l, null, null, null, null);
		if (c.moveToFirst()) {
			
			String name = c.getString(1);
			c.close();
			return name;
		}
		c.close();
		return null;
	}
	


	public void updateEntry(long lRow, String mName, String mHotness)
			throws SQLException {
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_NAME, mName);
		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow,
				null);
	}

	public void deleteEntry(String todel) throws SQLException {
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, KEY_NAME + "='" + todel + "'", null);
	}


	public void deleteDB() throws SQLException{
		// TODO Auto-generated method stub
			ourDatabase.delete(DATABASE_TABLE, null, null);
			ourHelper.onUpgrade(ourDatabase, 1, 1);
	}
	
	

}