package mdg.iitr.campusbuddy;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import mdg.iitr.campusbuddy.R;

public class Seenote extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		int count = 0;
		try {
			setContentView(R.layout.seenote);
			WindowManager.LayoutParams params = getWindow().getAttributes();
			params.width = WindowManager.LayoutParams.MATCH_PARENT;
			Notes note= new Notes(this);
			try {
				note.open();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String allnotes = "";
			TextView seenote=(TextView)findViewById(R.id.tvSeeNote);
		//	Toast.makeText(this, "inside note"+note.number(), Toast.LENGTH_LONG).show();
			for(int i =1;i<=note.number();i++)
			{
				if(!note.getHotness(i).contentEquals("Type Here..."))
				{
				allnotes=allnotes+" "+note.getName(i)+":-"+note.getHotness(i)+"\n";
				}
				else
				{
					count++;
				}
			}
			if(count == note.number())
			{
				seenote.setText("No Notes are Available");
			}
			else
			{
				seenote.setText(allnotes);			
			}
		
			note.close();
		} catch (Exception e) {
			finish();
			// TODO: handle exception
		}
			}

}
