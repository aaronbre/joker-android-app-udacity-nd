package com.udacity.gradle.builditbigger;

import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

/**
 * TODO for testing only remove @production
 * code for timeout found on stackoverflow
 */

public class JokeResponseIdlingResource implements IdlingResource{
    private final long startTime;
    private final long waitingTime;

    public JokeResponseIdlingResource(long waitingTime) {
        this.startTime = System.currentTimeMillis();
        this.waitingTime = waitingTime;
    }

    @Nullable
    private volatile ResourceCallback mCallback;

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        boolean isIdle = (elapsedTime >= waitingTime);
        if(isIdle){
            mCallback.onTransitionToIdle();
        }
        return isIdle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallback = callback;
    }


}
