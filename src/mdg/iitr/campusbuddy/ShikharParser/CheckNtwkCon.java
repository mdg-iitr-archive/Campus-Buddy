package mdg.iitr.campusbuddy.ShikharParser;

import android.content.Context;
import android.net.ConnectivityManager;

public class CheckNtwkCon
{
	public boolean CheckInternet(Context context) 
	{
	    ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    android.net.NetworkInfo wifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	    android.net.NetworkInfo mobile = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
	
	    // Here if condition check for wifi and mobile network is available or not.
	    // If anyone of them is available or connected then it will return true, otherwise false;
	
	    if (wifi.isConnected() || mobile.isConnected()) {
	        return true;
	    }
	    return false;
/*	    else if (!mobile.isConnected()) {
	        return false;
	    } else if (mobile.isConnected()) {
	        return true;
	    }
*/	    
	}
}