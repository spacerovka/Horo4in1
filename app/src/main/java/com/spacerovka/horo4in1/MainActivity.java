package com.spacerovka.horo4in1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.spacerovka.horo4in1.bigmir.BigmirScreenSlideActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("");

        setContentView(R.layout.activity_main);
        ImageButton mailruButton = (ImageButton) findViewById(R.id.mailru);
        mailruButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "showMailRuData", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MailRuActivity.class);
                startActivity(intent);
            }
        });

        ImageButton bigmirButton = (ImageButton) findViewById(R.id.bigmir);
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
