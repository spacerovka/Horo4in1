package com.spacerovka.horo4in1;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MailRuActivity extends AppCompatActivity {

    public TextView text_1;
    public TextView text_2;
    public TextView astroView;
    private SharedPreferences sPref;
    boolean isConnected;
    private String astro;
    private String tomorrowDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        astroView = (TextView) findViewById(R.id.astro);
        checkInternet();
        setTomorrowDate();
/**
// Uncomment the line to delete all preferences
//        sPref.edit().clear().commit();
**/
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        sPref = PreferenceManager.getDefaultSharedPreferences(this);



//          TO-DO add this to button

//        sPref.edit().putString("astro", "libra").commit();


    }

    @Override
    protected void onResume() {
        astro = sPref.getString("astro", null);
        astroView.setText("Знак зодиака - "+ astro);
        Log.i("main_act", "astro is " + astro);
        if(isConnected) {
//            setTomorrowDate();
//            getBigmirData();
            
            getMailRuData();
        }
            super.onResume();

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    public void getBigmirData(){
        String siteUrl = "http://goroskop.bigmir.net/bm_zodiac/daily/" + astro + "/";

        (new ParseBigmirHTML(this)).execute(new String[]{siteUrl, "text_1"});

        String siteUrlTomorrow = "http://goroskop.bigmir.net/bm_zodiac/daily/" + astro + "/"+tomorrowDate;
        Log.i("DATE", tomorrowDate);
        (new ParseBigmirHTML(this)).execute(new String[]{siteUrlTomorrow, "text_2"});
    }

    public void getMailRuData(){
        String siteUrl = "https://horo.mail.ru/prediction/" + astro + "/today/";
//        https://horo.mail.ru/prediction/libra/today/
        (new ParseBigmirHTML(this)).execute(new String[]{siteUrl, "text_1"});
//        https://horo.mail.ru/prediction/libra/tomorrow/
        String siteUrlTomorrow = "https://horo.mail.ru/prediction/" + astro + "/tomorrow/";

        (new ParseMailRuHTML(this)).execute(new String[]{siteUrlTomorrow, "text_2"});
    }

    public void getHyraxData(){
        String siteUrl = "http://hyrax.ru/rss_daily_common_" + astro + ".xml";
//       http://hyrax.ru/rss_daily_common_libra.xml
        (new ParseHyraxXML(this)).execute(new String[]{siteUrl, "text_1"});
//        http://www.hyrax.ru/cgi-bin/bn_xml.cgi
        String siteUrlTomorrow = "http://www.hyrax.ru/cgi-bin/bn_xml.cgi";

        (new ParseHyraxXML(this)).execute(new String[]{siteUrlTomorrow, "text_2"});
    }

    public void getIgnioData(){
        String siteUrl = "http://img.ignio.com/r/export/utf/xml/daily/com.xml";

        (new ParseHyraxXML(this)).execute(new String[]{siteUrl, "text_1"});

        (new ParseHyraxXML(this)).execute(new String[]{siteUrl, "text_2"});
    }

    public void checkInternet(){
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        isConnected = false;
        if(activeNetwork!= null) {
            isConnected = activeNetwork.isConnected();
        }
        Log.i("main", "connection" + isConnected+" and network is" + activeNetwork);
    }

    private void setTomorrowDate(){
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DATE, 1);
        tomorrowDate = Integer.toString(gc.get(Calendar.DAY_OF_MONTH))+"-"+ Integer.toString(gc.get(Calendar.MONTH) + 1)+"-"+ Integer.toString(gc.get(Calendar.YEAR));
    }
}
