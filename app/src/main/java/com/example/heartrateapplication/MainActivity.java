package com.example.heartrateapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private TextView login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (TextView) findViewById(R.id.login);
        login.setOnClickListener(this::onClick);



    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.login:
                startActivity(new Intent(this,LogIn.class));
                break;
        }
    }



}