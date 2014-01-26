package in.co.sdslabs.iitr.pkr;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Datahelper extends SQLiteOpenHelper{

   private static Datahelper _dbHelper;

   private static String DB_PATH = "/data/data/in.co.sdslabs.iitr.Multi/databases/";
   //database name
   private static String DB_NAME = "calender131.db";
   private static final String DATABASE_TABLE = "calender";
   private SQLiteDatabase myDb;
   public static final String KEY_DATE = "_id";
	public static final String KEY_DESCRIPTION = "eventdata";
   //
   private final Context _context;

   public Datahelper(Context context)
   {
       super(context, DB_NAME, null, 1);
       this._context = context;
   }
   public  Datahelper getInstance(Context context)
   {
       if(_dbHelper == null)
       {
           _dbHelper = new Datahelper(context);
       }
       return this;
   }
   

   public void createDataBase() throws IOException,SQLException
   {
       boolean dbExist = checkDataBase();

       if(dbExist)
       {
    	 //  Log.d("createcheck database","present" );
    	   myDb= _dbHelper.getWritableDatabase();
         //  Log.d("get database",myDb.getPath() ) ;
           //do nothing - database already exist
       }else{
    	   myDb = _dbHelper.getWritableDatabase();
    	 //  Log.d("create database","else" );

    	  
           try {
        	//   Log.d("create database","copyinggggggggg" );
               copydataBase();

           } catch (IOException e) {

               throw new Error(e);

           }
       }

   }

   private boolean checkDataBase()
   {
       SQLiteDatabase checkDB = null;

       try
       {
           String myPath = DB_PATH + DB_NAME;
           checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

       }catch(SQLiteException e)
       {
           //database does't exist yet.
       }
       if(checkDB != null)
       {
    	 //  Log.d("check database","present" );
           checkDB.close();
       }else{
    	 //  Log.d("check database","not present" );
       }

       return checkDB != null ? true : false;
   }
   
   private void copydataBase() throws IOException{  
	   try {
	   
		   
	                    InputStream input =  _context.getResources().getAssets().open(DB_NAME);
	                           String outPutFileName=  DB_PATH  +  DB_NAME ;
	                       
	                           
	                      OutputStream output = new FileOutputStream( outPutFileName); 
	                       byte[] buffer = new byte[1024];
	                    int length;
	                    while ((length = input.read(buffer))>0){
	                    output.write(buffer, 0, length);Log.v("eeeeeeeeee","gjkklllkjgfghjkjgfdfgh");
	                    }
	                   
	                    output.flush();
	                    output.close();
	                    input.close();
	         }
	                       catch (IOException e) {
	                  //   Log.v("error",e.toString());
	                    }
	   SQLiteDatabase checkDB = null;
	   try
       {
           String myPath = DB_PATH + DB_NAME;
           checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

       }catch(SQLiteException e)
       {
           //database does't exist yet.
    	 //  Log.d("errorcheck",e.toString());
       }
	     }
   
   
   long a;
   public long createEntry(int date, String description)throws SQLiteException {
		// TODO Auto-generated method stub
	  
		ContentValues cv = new ContentValues();
		cv.put(KEY_DATE, date);
		cv.put(KEY_DESCRIPTION, description);
//		if(myDb!=null){Log.d("mydb present",myDb.getPath()+myDb.getVersion()+myDb.isReadOnly());} 
//		else {
//			Log.d("error","other");
//		}
		try{
		a= myDb.insertOrThrow(DATABASE_TABLE,  description, cv);
		}catch (Exception e) {
			// TODO: handle exception
			
		}
		//myDb.close();
		return a;
		
	}
   
   public String getEvent(int l) throws SQLException {
		// TODO Auto-generated method stub
	   myDb= _dbHelper.getWritableDatabase();
		String name="";
		String[] columns = new String[] { KEY_DATE, KEY_DESCRIPTION };
		Cursor c = myDb.query(DATABASE_TABLE, columns, KEY_DATE + "="+ l, null, null, null, null);
		if (c != null&&c.moveToFirst()) {
			
				name=c.getString(c.getColumnIndex(KEY_DESCRIPTION));
			    c.close();
			    return name;
			    
			
		}c.close();
		return null;
	}
   
   public String getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[] { KEY_DATE, KEY_DESCRIPTION };
		Cursor c = myDb.query(DATABASE_TABLE, columns, null, null, null,
				null, null);
		String result = "";

		int iRow = c.getColumnIndex(KEY_DATE);
		int iHotness = c.getColumnIndex(KEY_DESCRIPTION);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			
			result = result + c.getString(iRow) + ".  "  + c.getString(iHotness) + "\n";
		}
		c.close();
		return result;
	}
   
   
   @Override
  	public void onCreate(SQLiteDatabase db) {
  		// TODO Auto-generated method stub
  		//db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_DATE+ " INTEGER PRIMARY KEY, " + KEY_DESCRIPTION + " TEXT);");
  		
      //   Log.d("create tableon",myDb.getPath() ) ;
  	}

  	@Override
  	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  		// TODO Auto-generated method stub
  	//	db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
  		//onCreate(db);
  	}
  	
  	@Override
  		public synchronized void close() {
  			// TODO Auto-generated method stub
  			super.close();
  			myDb.close();
  		}
 
}