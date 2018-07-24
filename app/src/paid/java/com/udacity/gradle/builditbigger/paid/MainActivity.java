package com.udacity.gradle.builditbigger.paid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.androidjoker.JokeActivity;
import com.example.jokerlib.Joke;
import com.udacity.gradle.builditbigger.EndpointAsyncTask;
import com.udacity.gradle.builditbigger.JokeReturnHandler;
import com.udacity.gradle.builditbigger.R;

public class MainActivity extends AppCompatActivity implements JokeReturnHandler{
    ViewGroup mContainer;
    ImageView mLoadingImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContainer = findViewById(R.id.main_activity_container);
        mLoadingImage = findViewById(R.id.loading_image);
    }

    public void tellJoke(View view) {
        showLoadingImage();
        new EndpointAsyncTask().execute(this);
    }

    private void showLoadingImage() {
        mContainer.setVisibility(View.GONE);
        mLoadingImage.setVisibility(View.VISIBLE);
        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        mLoadingImage.startAnimation(rotate);
    }

    private void hideLoadingImage(){
        mContainer.setVisibility(View.VISIBLE);
        mLoadingImage.clearAnimation();
        mLoadingImage.setVisibility(View.GONE);
    }

    @Override
    public void handleJokeData(String response) {
        Joke joke = Joke.jokeFromFormattedString(response);
        Intent intent = new Intent(this, JokeActivity.class);
        intent.setAction("showJoke");
        intent.putExtra(JokeActivity.JOKE_EXTRA, joke);
        hideLoadingImage();
        startActivity(intent);
    }
}
