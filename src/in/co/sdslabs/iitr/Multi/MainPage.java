package in.co.sdslabs.iitr.Multi;

import java.io.IOException;
import java.io.InputStream;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainPage extends Activity {

	ImageView iitr, calendar, timeTable;
	ImageView instructions, aboutus;
	SharedPreferences somedata;
	public static String filename = "MySharedString";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainpage);
		
		
		iitr = (ImageView) findViewById(R.id.ivActivity1);
		timeTable = (ImageView) findViewById(R.id.ivActivity2);
		calendar = (ImageView) findViewById(R.id.ivActivity3);
		instructions = (ImageView) findViewById(R.id.ivActivity4);
		aboutus = (ImageView) findViewById(R.id.ivCredits);
		AssetManager assetImages = getAssets();
		int scrX=getWindowManager().getDefaultDisplay().getWidth();
		try {
			InputStream img1 = assetImages.open("aboutus_disabled.png");
			Bitmap bmp1=BitmapFactory.decodeStream(img1);
			int newXDim=(int)( scrX*0.4f);
			int newYDim=(int)(175.0f*newXDim/45.0f); //(bmp1.getHeight()*newXDim/bmp1.getWidth());
			Bitmap bmp1Temp=Bitmap.createScaledBitmap(bmp1,newXDim,newYDim,true);
			img1.close();
			
			InputStream img2 = assetImages.open("ins_disabled.png");
			Bitmap bmp2=BitmapFactory.decodeStream(img2);
			int newXDim2=(int)( getWindowManager().getDefaultDisplay().getWidth()*0.4f);
			int newYDim2=(int)(175.0f*newXDim2/45.0f);//(bmp2.getHeight()*newXDim2/bmp2.getWidth());
			Bitmap bmp1Temp2=Bitmap.createScaledBitmap(bmp2,newXDim,newYDim,true);
			img2.close();
			instructions.setImageBitmap(bmp1Temp2);
			aboutus.setImageBitmap(bmp1Temp);
			instructions.setPadding((int)(0.05f*scrX),0,(int)(0.1f*scrX),0);
			aboutus.setPadding((int)(0.05f*scrX),0,0,0);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		iitr.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(
						"in.co.sdslabs.iitr.Multi.TABPAGE");
				startActivity(intent);
			}
		});

		instructions.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(
						"in.co.sdslabs.iitr.Multi.INSTRUCTIONS");
				startActivity(intent);
			}
		});
		
		timeTable.setOnClickListener(new OnClickListener() {

			/// Ankit time table intergration.
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				somedata = getSharedPreferences(filename, 0);
				Boolean datareturned = somedata.getBoolean("flag", false);
				if(datareturned == false)
				{
				Intent openStartingPoint = new Intent(MainPage.this,Own.class);
					startActivity(openStartingPoint);

				}
				else
				{
					Intent openStartingPoint = new Intent(
							MainPage.this,TimeTableActivity.class);
					startActivity(openStartingPoint);
					
				}
/*
				Intent intent = new Intent(
						"in.co.sdslabs.iitr.Multi.TIMETABLE");
				startActivity(intent);
*/			}
		});
		
		calendar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent("in.co.sdslabs.iitr.Multi.CALENDAR");
				startActivity(intent);
			}
		});

		aboutus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Dialog d = new Dialog(MainPage.this, R.style.DialogTheme);
				
				d.setTitle("Campus Buddy");
				
				ScrollView sv = (ScrollView) findViewById(R.id.aboutLayout);
				if (sv != null) {
					d.addContentView(sv, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				}
//				d.setContentView(sv);
				d.setContentView(R.layout.aboutactivity);
				d.show();
				
				Intent intent = new Intent(MainPage.this, Aboutus.class);
//				startActivity(intent);
			}
		});
	}
}
