package com.beginner.hangdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void startSinglePlayerGame(View v){

      Intent myIntent = new Intent(MainActivity.this,GameActivity.class);
    startActivity(myIntent);
    }
    public void startMultiGame(View v) {
        Intent myIntent = new Intent(MainActivity.this,MultiplayerActivity.class);
        startActivity(myIntent);
    }
    public  void viewScores(View v) {
        Intent myIntent = new Intent(MainActivity.this,ScoresActivity.class);
        startActivity(myIntent);
    }
}
