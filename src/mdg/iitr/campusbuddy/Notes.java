package mdg.iitr.campusbuddy;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Notes {

	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "person_name";
	public static final String KEY_HOTNESS = "person_hotness";
	public static final String KEY_ATTEND = "person_attendance"; 
	public static final String KEY_DESC = "person_desc"; 
	 
	private static final String DATABASE_NAME = "Notesdb";
	private static final String DATABASE_TABLE = "peopleTable";
	
	private static final int DATABASE_VERSION = 1;

	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;

	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);

		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
					+ " TEXT, " + KEY_HOTNESS + " TEXT, " + KEY_ATTEND + " TEXT, " + KEY_DESC + " TEXT);");
			

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
			/*db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
					+ " TEXT, " + KEY_HOTNESS + " TEXT);");*/
		}

	}

	public Notes(Context c) {
		ourContext = c;
	}

	

	public Notes open() throws Exception {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		ourHelper.close();
	}

	public long createEntry(String name, String hotness,String attend,String desc) {
		ContentValues cv = new ContentValues();
		
		cv.put(KEY_NAME, name);
		cv.put(KEY_HOTNESS, hotness);
		cv.put(KEY_ATTEND,attend);
		cv.put(KEY_DESC,desc);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);

	}
	public int number() {
		int j = 0;
		
		try{
			String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_HOTNESS,KEY_ATTEND,KEY_DESC };
			Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,
					null, null);
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				j++;
					}
			//return j;
			    }catch(Exception e){
            //Toast.makeText(getApplicationContext(), "Error encountered while dropping.", Toast.LENGTH_LONG);
        }
		//int i =  ourDatabase.delete(DATABASE_TABLE,null,null);
		//System.out.println("Effected rows"+ i);
		return j;
		
	}

	public String getData() {
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_HOTNESS,KEY_ATTEND,KEY_DESC };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,
				null, null);
		String result = "";
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iHotness = c.getColumnIndex(KEY_HOTNESS);
		int iAttend = c.getColumnIndex(KEY_ATTEND);
		int idesc = c.getColumnIndex(KEY_DESC);
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = result + c.getString(iRow) + " " + c.getString(iName)
					+ " " + c.getString(iHotness)+ " " + c.getString(iAttend) + " " + c.getString(idesc) + "\n";
		}
		return result;
	}

	public String getName(long l)throws SQLException {
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_HOTNESS,KEY_ATTEND,KEY_DESC};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "="
				+ l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String name = c.getString(1);
			return name;
			
			
		}
		return null;
	}
	
	public String getDesc(long l)throws SQLException {
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_HOTNESS,KEY_ATTEND,KEY_DESC};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "="
				+ l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String name = c.getString(4);
			return name;
			
			
		}
		return null;
	}

	public String getHotness(long l) throws SQLException{
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_HOTNESS,KEY_ATTEND,KEY_DESC };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "="
				+ l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String hotness = c.getString(2);
			return hotness;
		}
		return null;
	}
	
	public String getAttend(long l) throws SQLException{
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_HOTNESS,KEY_ATTEND,KEY_DESC };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "="
				+ l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String hotness = c.getString(3);
			return hotness;
		}
		return null;
	}

	public void updateEntry(long lRow, String name, String hotness,String attend,String desc)throws SQLException {
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_NAME,name);
		cvUpdate.put(KEY_HOTNESS,hotness);
		cvUpdate.put(KEY_ATTEND,attend);
		cvUpdate.put(KEY_DESC,desc);
		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID+"="+lRow, null);
	}

	public void deleteEntry(long lRow1)throws SQLException {
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID +"="+lRow1,null);
	}
	public void drop() {
		try{
			//String sql = "TRUNCATE table" + DATABASE_TABLE;
			ourDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			ourDatabase.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
					+ " TEXT, " + KEY_HOTNESS + " TEXT, " + KEY_ATTEND + " TEXT, " + KEY_DESC + " TEXT);");
			
			
			//ourDatabase.execSQL(sql);
            }catch(Exception e){
            //Toast.makeText(getApplicationContext(), "Error encountered while dropping.", Toast.LENGTH_LONG);
        }
		//int i =  ourDatabase.delete(DATABASE_TABLE,null,null);
		//System.out.println("Effected rows"+ i);
		
	}
}
