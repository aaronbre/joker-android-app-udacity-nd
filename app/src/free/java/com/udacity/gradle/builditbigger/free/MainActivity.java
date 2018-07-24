package com.udacity.gradle.builditbigger.free;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.androidjoker.JokeActivity;
import com.example.jokerlib.Joke;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.EndpointAsyncTask;
import com.udacity.gradle.builditbigger.JokeReturnHandler;
import com.udacity.gradle.builditbigger.R;


public class MainActivity extends AppCompatActivity implements JokeReturnHandler {
    private InterstitialAd mInterstitialAd;
    private ImageView mLoadingImage;
    private ViewGroup mFragContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoadingImage = findViewById(R.id.loading_image);
        mFragContainer = findViewById(R.id.fragment_container);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                loadJokeActivity();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        if(mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
        else{
            loadJokeActivity();
        }
    }

    private void loadJokeActivity() {
        showLoadingImage();
        new EndpointAsyncTask().execute(this);
    }

    private void showLoadingImage() {
        mFragContainer.setVisibility(View.GONE);
        mLoadingImage.setVisibility(View.VISIBLE);
        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        mLoadingImage.startAnimation(rotate);
    }

    public void hideLoadingImage(){
        mFragContainer.setVisibility(View.VISIBLE);
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
