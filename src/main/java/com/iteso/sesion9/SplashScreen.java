package com.iteso.sesion9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    User usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                SharedPreferences sharedPreferences = getSharedPreferences("com.example.preferences.PREFERENCES", MODE_PRIVATE);

                usuario = new User();

                usuario.setName(sharedPreferences.getString("USERNAME", null));
                usuario.setPassword(sharedPreferences.getString("PASSWORD", null));
                usuario.setLogged(sharedPreferences.getBoolean("LOGGED", false));

                if(usuario.isLogged() == false ||usuario.getPassword() == null || usuario.getName() == null){
                    //open login activity

                    Intent intent = new Intent(SplashScreen.this, LogIn.class);
                    startActivity(intent);
                    finish();
                }
                else{//open main activity

                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }

            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, 2000);


    }
}
