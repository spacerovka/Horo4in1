package com.spacerovka.horo4in1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("");

        setContentView(R.layout.activity_main);
    }

    private void showBigmirData(View view){
//               Toast.makeText(MainActivity.this, "showBigmirData", Toast.LENGTH_SHORT).show();

    }

    private void showMailRuData(View view){
        Toast.makeText(MainActivity.this, "showMailRuData", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, MailRuActivity.class);
        startActivity(intent);
    }

    private void showHyraxData(View view){
        Toast.makeText(MainActivity.this, "showHyraxData", Toast.LENGTH_SHORT).show();

    }

    private void showIgnioData(View view){
        Toast.makeText(MainActivity.this, "showIgnioData", Toast.LENGTH_SHORT).show();

    }
}
