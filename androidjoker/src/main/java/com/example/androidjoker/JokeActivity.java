package com.example.androidjoker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    private void setUpAnswer(final String answer) {
        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        mDelaySpinner.startAnimation(rotate);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mJokeAnswer.setVisibility(View.VISIBLE);
                mJokeAnswer.setText(answer);
                mDelaySpinner.clearAnimation();
                mDelaySpinner.setVisibility(View.GONE);
            }
        }, 3000);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return(super.onOptionsItemSelected(item));
    }
}
