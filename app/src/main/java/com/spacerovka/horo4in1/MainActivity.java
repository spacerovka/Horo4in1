package com.spacerovka.horo4in1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.spacerovka.horo4in1.bigmir.BigmirScreenSlideActivity;
import com.spacerovka.horo4in1.hyrax.HyraxScreenSlideActivity;
import com.spacerovka.horo4in1.ignio.IgnioScreenSlideActivity;
import com.spacerovka.horo4in1.mailru.MailruScreenSlideActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("");

        setContentView(R.layout.activity_main);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        SharedPreferences sPref = PreferenceManager.getDefaultSharedPreferences(this);
        String astro = sPref.getString("astro", null);
        Log.i("astro", astro);
        if(astro==null){
            sPref.edit().putString("astro", "libra").commit();
        }

        Button mailruButton = (Button) findViewById(R.id.mailru);
        mailruButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MailruScreenSlideActivity.class);
                startActivityWithConnection(intent);
            }
        });

        Button bigmirButton = (Button) findViewById(R.id.bigmir);
        bigmirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bintent = new Intent(MainActivity.this, BigmirScreenSlideActivity.class);
                startActivityWithConnection(bintent);
            }
        });

        Button hyraxButton = (Button) findViewById(R.id.hyrax);
        hyraxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hintent = new Intent(MainActivity.this, HyraxScreenSlideActivity.class);
                startActivityWithConnection(hintent);

            }
        });

        Button ignioButton = (Button) findViewById(R.id.ignio);
        ignioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iintent = new Intent(MainActivity.this, IgnioScreenSlideActivity.class);
                startActivityWithConnection(iintent);
            }
        });
    }

    @Override
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
            Intent i = new Intent(this, HoroPreferencesActivity.class);
            startActivity(i);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean checkInternet(){
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        isConnected = false;
        if(activeNetwork!= null) {
            return activeNetwork.isConnected();
        }
        return  false;
    }

    private void startActivityWithConnection(Intent intent){
        if(checkInternet()){
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }else{

            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast,
                    (ViewGroup) findViewById(R.id.custom_toast_layout));
            TextView text = (TextView) layout.findViewById(R.id.textToShow);
            text.setText("Подключение к интернету отсутствует. Пожалуйста, повторите попытку позднее");
            Toast toast = new Toast(MainActivity.this);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setView(layout);
            toast.show();
        }
    }

}
