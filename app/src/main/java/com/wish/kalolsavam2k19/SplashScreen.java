package com.wish.kalolsavam2k19;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Make activity in fullScreen

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();


        final Thread welcomeScreen =new Thread() {
            @Override
            public void run() {
                try {
                    super.run();
                    sleep(2000);
                }
                catch (Exception e){}
                finally {
                    Intent intentToWelcomeSlide =new Intent(getApplicationContext(),IntroSlideActivity.class);
                    startActivity(intentToWelcomeSlide);
                    finish();
                }
            }

        };


        welcomeScreen.start();

    }


}
