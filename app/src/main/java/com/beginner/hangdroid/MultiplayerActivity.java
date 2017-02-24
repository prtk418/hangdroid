package com.beginner.hangdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MultiplayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
    }
    public void startMultiGame(View v) {
        EditText editTextWord = (EditText) findViewById(R.id.editTextWord);
        String wordToGuess = editTextWord.getText().toString();
        editTextWord.setText("");
        Intent myIntent = new Intent(MultiplayerActivity.this,GameMultiActivity.class);
        myIntent.putExtra("WORD_IDENTIFIER",wordToGuess);
        startActivity(myIntent);

    }
}
