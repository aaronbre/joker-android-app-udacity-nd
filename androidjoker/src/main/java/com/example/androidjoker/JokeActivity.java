package com.example.androidjoker;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jokerlib.Joke;

public class JokeActivity extends AppCompatActivity {
    public static final String JOKE_EXTRA = "joke";
    private TextView mJokeAnswer;
    private ImageView mDelaySpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        TextView jokeQuestion = findViewById(R.id.joke_question);
        mJokeAnswer = findViewById(R.id.joke_answer);
        mDelaySpinner = findViewById(R.id.delay_spinner);

        Intent intent = getIntent();
        if(intent.hasExtra(JOKE_EXTRA)){
            Joke joke =(Joke)intent.getSerializableExtra(JOKE_EXTRA);
            jokeQuestion.setText(joke.getJokeQuestion());
            setUpAnswer(joke.getJokeAnswer());
        }
    }

    private void setUpAnswer(String answer) {
        mJokeAnswer.setText(answer);
        mJokeAnswer.setVisibility(View.GONE);
        mDelaySpinner.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mJokeAnswer.setVisibility(View.VISIBLE);
                mDelaySpinner.setVisibility(View.GONE);
            }
        }, 3000);
    }
}
