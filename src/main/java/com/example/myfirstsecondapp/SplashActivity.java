package com.example.myfirstsecondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Button btnSignup = (Button)findViewById(R.id.btnStart);// identifying button id that will be on the layout screen. ids are found in XML file
        Button btnLogin = (Button)findViewById(R.id.btnLogin);

        btnSignup.setOnClickListener(new View.OnClickListener() { //detects any events related to the UI
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, SignupActivity.class));// will take us into a different activity
                finish();

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();

            }
        });
    }
}