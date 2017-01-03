package com.spacerovka.horo4in1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("");

        setContentView(R.layout.activity_main);
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

    private void showBigmirData(View view){
//               Toast.makeText(MainActivity.this, "showBigmirData", Toast.LENGTH_SHORT).show();

    }

    /*private void showMailRuData(View view){
        Toast.makeText(MainActivity.this, "showMailRuData", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, MailRuActivity.class);
        startActivity(intent);
    }*/

    private void showHyraxData(View view){
        Toast.makeText(MainActivity.this, "showHyraxData", Toast.LENGTH_SHORT).show();

    }

    private void showIgnioData(View view){
        Toast.makeText(MainActivity.this, "showIgnioData", Toast.LENGTH_SHORT).show();

    }
}
