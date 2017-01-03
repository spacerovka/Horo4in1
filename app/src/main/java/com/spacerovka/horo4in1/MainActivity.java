package com.spacerovka.horo4in1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
                Toast.makeText(MainActivity.this, "showMailRuData", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MailruScreenSlideActivity.class);
                startActivity(intent);
            }
        });

        Button bigmirButton = (Button) findViewById(R.id.bigmir);
        bigmirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "goTo ScreenSlideActivity", Toast.LENGTH_SHORT).show();
                Intent bintent = new Intent(MainActivity.this, BigmirScreenSlideActivity.class);
                if (bintent != null) {
                    startActivity(bintent);
                }else{
                    Log.i("MainActivity on click","intent is null");
                }

            }
        });

        Button hyraxButton = (Button) findViewById(R.id.hyrax);
        hyraxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "goTo HyraxScreenSlideActivity", Toast.LENGTH_SHORT).show();
                Intent hintent = new Intent(MainActivity.this, HyraxScreenSlideActivity.class);
                if (hintent != null) {
                    startActivity(hintent);
                }else{
                    Log.i("MainActivity on click","intent is null");
                }

            }
        });

        Button ignioButton = (Button) findViewById(R.id.ignio);
        ignioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "goTo IgnioScreenSlideActivity", Toast.LENGTH_SHORT).show();
                Intent iintent = new Intent(MainActivity.this, IgnioScreenSlideActivity.class);
                if (iintent != null) {
                    startActivity(iintent);
                }else{
                    Log.i("MainActivity on click","intent is null");
                }

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

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
