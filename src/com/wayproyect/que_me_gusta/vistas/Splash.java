package com.wayproyect.que_me_gusta.vistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.wayproyect.que_me_gusta.R;

/**
 * Created with IntelliJ IDEA.
 * User: ADMIN
 * Date: 12/10/13
 * Time: 11:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class Splash extends Activity {
    // used to know if the back button was pressed in the splash screen activity and avoid opening the next activity
    private boolean mIsBackButtonPressed;
    private static final int SPLASH_DURATION = 2000; // 2 seconds

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Handler handler = new Handler();

        // run a thread after 2 seconds to start the home screen
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                // make sure we close the splash screen so the user won't come back when it presses back key

                finish();

                if (!mIsBackButtonPressed) {
                    // start the home screen if the back button wasn't pressed already
                    Intent intent = new Intent(Splash.this, com.wayproyect.que_me_gusta.vistas.Menu.class);
                    Splash.this.startActivity(intent);
                }

            }

        }, SPLASH_DURATION); // time in milliseconds (1 second = 1000 milliseconds) until the run() method will be called

    }

    @Override
    public void onBackPressed() {

        // set the flag to true so the next activity won't start up
        mIsBackButtonPressed = true;
        super.onBackPressed();

    }
}