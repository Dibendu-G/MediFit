package com.example.medifit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class passwordResetMessage extends AppCompatActivity {
    MediaPlayer music;
    public static int SPLASH_TIME_OUT = 3000;
    TextView emailSent,message;
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset_message);

        emailSent=findViewById(R.id.mailSent);
        message=findViewById(R.id.mailMessage);
        lottieAnimationView=findViewById(R.id.lottie2);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(passwordResetMessage.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        },SPLASH_TIME_OUT);

        Thread timer = new Thread()
        {
            @Override
            public void run()
            {
                try {
                    lottieAnimationView.animate().setDuration(1000).setStartDelay(4000);
                    music = MediaPlayer.create(passwordResetMessage.this,R.raw.successtune);
                    music.start();
                    sleep(3000);
                }
                catch (InterruptedException e)
                {

                }

            }
        };
        timer.start();
    }
    @Override protected void onPause()
    {
        super.onPause();
        music.release();
    }
}