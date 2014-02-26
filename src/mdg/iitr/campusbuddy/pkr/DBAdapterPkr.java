package mdg.iitr.campusbuddy.pkr;

import java.sql.SQLException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapterPkr 
{
    public static final String KEY_ROWID = "_id";
    public static final String KEY_SUB = "subject";
    public static final String KEY_DAY = "day";
    public static final String KEY_TIME = "time";    
    private static final String TAG = "DBAdapterPkr";
    
    private static final String DATABASE_NAME = "timetable";
    private static final String DATABASE_TABLE = "timetable";
    private static final int DATABASE_VERSION = 1;

           
    private final Context context; 
    
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapterPkr(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
        
    private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (_id integer primary key autoincrement, "
                    + "subject text not null, day text not null, " 
                    + "time text not null);"
            		);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
        int newVersion) 
        {
           
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }    
    
    //---opens the database---
    public DBAdapterPkr open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---    
    public void close() 
    {
    	
    	
        DBHelper.close();
    }
    
    //---insert a day into the database---
    public long insertTitle(String subject, String day, String time) 
    {
    	boolean up = updateTitle(subject, day, time);
    	if(up==false){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_SUB, subject);
        initialValues.put(KEY_DAY, day);
        initialValues.put(KEY_TIME, time);
        return db.insert(DATABASE_TABLE, null, initialValues);
        }
    	return 0;
    }

    //---deletes a particular day---
    public boolean deleteTitle(long rowId) 
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + 
        		"=" + rowId, null) > 0;
    }


    //---retrieves a particular subject---
    public String getSubj(String day,String time) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                		KEY_ROWID,
                		KEY_SUB, 
                		KEY_DAY,
                		KEY_TIME
                		}, 
                		KEY_DAY + " like" + "'%" + day + "%' AND " + KEY_TIME + " like" + "'%" + time+ "%'", 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
         if (mCursor.moveToFirst()) {
            
            String subject = mCursor.getString(1);
            mCursor.close();
			return subject;
           }
         mCursor.close();
         return null;
    }
    
    //delete database completely
    public void deleteDB() throws SQLException{
		// TODO Auto-generated method stub
			db.delete(DATABASE_TABLE, null, null);
			DBHelper.onUpgrade(db, 1, 1);
	}

    //---updates a day---
    public boolean updateTitle(String subject, 
    String day, String time) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_SUB, subject);
        args.put(KEY_DAY, day);
        args.put(KEY_TIME, time);
        return db.update(DATABASE_TABLE, args, 
        		KEY_DAY + " like" + "'%" + day + "%' AND " + KEY_TIME + " like" + "'%" + time+ "%'", null) > 0;
    }
}