package com.spacerovka.horo4in1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by work on 24.07.16.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    public static Context mContext;

    @Override
    public void onReceive(final Context context, final Intent intent) {

        if (mContext == null) mContext = context;
        if(checkInternet(mContext))
        {
            Toast.makeText(context, "Network Available Do operations", Toast.LENGTH_LONG).show();
//            Intent i = new Intent();
//            i.setClassName("com.example.spacerovka.horochoice", "com.example.spacerovka.horochoice.MainActivity");
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(i);
        }else{

        }

    }


    private boolean checkInternet(Context context) {

        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if(activeNetwork!= null && activeNetwork.isConnected()) {
            Log.i("broadcast", "internet is on");

            return true;
        }else{
            Log.i("broadcast", "internet is off");

            return false;
        }

    }

}

