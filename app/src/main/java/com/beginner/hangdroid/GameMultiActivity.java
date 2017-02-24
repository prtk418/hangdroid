package com.beginner.hangdroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

public class GameMultiActivity extends AppCompatActivity {
    String mWord;
    int mCounter = 0;
    int guessedLetters = 0;
    int mPoints = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_game);

        String wordSent = getIntent().getStringExtra("WORD_IDENTIFIER");
        mWord = wordSent;
        createTextViews(wordSent);

    }

    public void createTextViews(String word) {

        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);

        for (int i=0; i<word.length(); i++) {
            TextView newTextView = (TextView) getLayoutInflater().inflate(R.layout.textview,null);
            layoutLetters.addView(newTextView);
        }

    }

    public void introduceLetter(View v) {
        EditText myEditText = (EditText) findViewById(R.id.editTextLetter);
        String letter = myEditText.getText().toString();


        if (letter.length() == 1) {
            checkLetter(letter);
        } else {
            Toast.makeText(this, "Please introduce a letter", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkLetter(String introducedLetter) {
        boolean letterGuess = false;
        char charIntroduced = introducedLetter.charAt(0);
        EditText editTextLetter = (EditText) findViewById(R.id.editTextLetter);
        editTextLetter.setText("");
        for (int i = 0; i < mWord.length(); i++) {
            char charFromTheWord = mWord.charAt(i);
            if (charFromTheWord == charIntroduced) {
                letterGuess = true;
                guessedLetters++;
                showLettersAtIndex(i, charFromTheWord);
            }
        }
        if (!letterGuess) {
            letterFailed(charIntroduced);
        }
        if (guessedLetters == mWord.length()) {
            Toast.makeText(this, "Successfully Guessed", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void clearScreen() {

        TextView textViewFailed = (TextView) findViewById(R.id.textView7);
        textViewFailed.setText("");
        mCounter = 0;
        guessedLetters = 0;
        LinearLayout layoutLetter = (LinearLayout) findViewById(R.id.layoutLetters);
        for (int i = 0; i < layoutLetter.getChildCount(); i++) {
            TextView currentTextView = (TextView) layoutLetter.getChildAt(i);
            currentTextView.setText("_");
        }
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.hangdroid0);

    }


    public void letterFailed(char failedChar) {
        TextView textViewFailed = (TextView) findViewById(R.id.textView7);
        String previousFailed = textViewFailed.getText().toString();
        textViewFailed.setText(previousFailed + Character.toString(failedChar));
        mCounter++;
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        if (mCounter == 1) {
            imageView.setImageResource(R.drawable.hangdroid1);
        } else if (mCounter == 2) {
            imageView.setImageResource(R.drawable.hangdroid2);
        } else if (mCounter == 3) {
            imageView.setImageResource(R.drawable.hangdroid3);
        } else if (mCounter == 4) {
            imageView.setImageResource(R.drawable.hangdroid4);
        } else if (mCounter == 5) {
            imageView.setImageResource(R.drawable.hangdroid5);
        } else if (mCounter == 6) {

            Intent gameOverIntent = new Intent(GameMultiActivity.this, GameOverActivity.class);
            gameOverIntent.putExtra("POINTS_IDENTIFIER", mPoints);
            startActivity(gameOverIntent);

        }

    }

    public void showLettersAtIndex(int position, char letterGuessed) {

        LinearLayout layoutLetter = (LinearLayout) findViewById(R.id.layoutLetters);
        TextView textView = (TextView) layoutLetter.getChildAt(position);
        textView.setText(Character.toString(letterGuessed));
    }


}
