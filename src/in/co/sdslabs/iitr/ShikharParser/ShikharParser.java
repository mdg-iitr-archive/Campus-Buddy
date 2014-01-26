package in.co.sdslabs.iitr.ShikharParser;

import in.co.sdslabs.iitr.database.DBAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import android.content.Context;
import android.os.Environment;
import android.util.Log;


public class ShikharParser {
	
	private void Download(String downloadlink,int choice)
	{
		try {
			//set the download URL, a url that points to a file on the internet
			//this is the file to be downloaded
			//URL url = new URL("http://somewhere.com/some/webhosted/file");
			String USERAGENT;
			if(choice==0)
			{
				USERAGENT ="Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_7; en-us) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Safari/530.17";
			}
			else
			{
				USERAGENT ="Mozilla/5.0 (Linux; U; Android 2.1-update1; en-us; ADR6300 Build/ERE27) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17";
			}
			System.out.println("DownloadLink for download is "+downloadlink);
			URL url = new URL(downloadlink);
			//create the new connection
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

			//set up some things on the connection
			urlConnection.setRequestProperty("User-Agent", USERAGENT);
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);

			//and connect!
			urlConnection.connect();

			//set the path where we want to save the file
			//in this case, going to save it on the root directory of the
			//sd card.
			File SDCardRoot = Environment.getExternalStorageDirectory();
			//create a new file, specifying the path, and the filename
			//which we want to save the file as.
			//File file = new File(SDCardRoot,"hmparse.tgu");
			File dir = new File (SDCardRoot.getAbsolutePath() + "/sdsLabs/events");
			if(!dir.exists())
			{
			dir.mkdirs();
			}
			File file = new File(dir, "hmPar.tgu");

			//this will be used to write the downloaded data into the file we created
			FileOutputStream fileOutput = new FileOutputStream(file);

			//this will be used in reading the data from the internet
			InputStream inputStream = urlConnection.getInputStream();

			//this is the total size of the file
			int totalSize = urlConnection.getContentLength();
			//variable to store total downloaded bytes
			int downloadedSize = 0;

			//create a buffer...
			byte[] buffer = new byte[1024];
			int bufferLength = 0; //used to store a temporary size of the buffer

			//now, read through the input buffer and write the contents to the file
			while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
				//add the data in the buffer to the file in the file output stream (the file on the sd card
				fileOutput.write(buffer, 0, bufferLength);
				//add up the size so we know how much is downloaded
				downloadedSize += bufferLength;
				//this is where you would do something to report the prgress, like this maybe
				//updateProgress(downloadedSize, totalSize);

			}
			//close the output stream when done
			fileOutput.close();
			inputStream.close();
			urlConnection.disconnect();

		//catch some possible errors...
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	public void parseShikharXML(Context context)
	{
		Download("http://sdslabs.co/android/events.xml",0);
		HtmlCleaner cleaner = new HtmlCleaner();
		File SDCardRoot = Environment.getExternalStorageDirectory();
		File dir = new File (SDCardRoot.getAbsolutePath() + "/sdsLabs/events");
		File file = new File(dir, "hmPar.tgu");
		String siteUrl = "file:";
		siteUrl=siteUrl+file.getAbsolutePath();
		 
		TagNode node=new TagNode(siteUrl);
		try {
			node = cleaner.clean(new URL(siteUrl));
		} 
		catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		try{
			//int versionNum=Integer.parseInt(node.getElementsByName("version",true)[0].getText().toString());
			TagNode[] myNodes=node.getElementsByName("event",true);
			if(myNodes.length>0)
			{
		      DBAdapter adapter = new DBAdapter(context);
		      adapter.getInstance(context);
		      boolean oldDatabaseStatus=adapter.deleteAndInsert_table_details();			      
				for(int infoNodes=0;infoNodes<myNodes.length && oldDatabaseStatus;infoNodes++)
				{
					String name=myNodes[infoNodes].getElementsByName("name",false)[0].getText().toString();
					String time=myNodes[infoNodes].getElementsByName("time",false)[0].getText().toString();
					String date=myNodes[infoNodes].getElementsByName("date",false)[0].getText().toString();
					String venue=myNodes[infoNodes].getElementsByName("venue",false)[0].getText().toString();
					String description=myNodes[infoNodes].getElementsByName("description",false)[0].getText().toString();
	/*				      DBAdapter adapter = new DBAdapter(context);
					      adapter.getInstance(context); */
				      //adapter.addNewEntry(name, venue, date, time, description);
				}
		      adapter.close();
			}
		}
		catch(Exception e)
		{	
			e.printStackTrace();
		}
	}
	
	public boolean checkExternalMedia()
	{
        boolean mExternalStorageAvailable = false;
        boolean mExternalStorageWriteable = false;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // We can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
            Log.i("","State="+state);
        } else {
            // Something else is wrong. It may be one of many other states,but all we need
            //  to know is we can neither read nor write
                Log.i("","State="+state+" Not good");
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }

        Log.i("","Available="+mExternalStorageAvailable+"Writeable="+mExternalStorageWriteable+" State"+state);
        return (mExternalStorageAvailable && mExternalStorageWriteable);
	}	
	

}
