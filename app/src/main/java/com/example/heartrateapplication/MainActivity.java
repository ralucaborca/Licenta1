package com.example.heartrateapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        register = (TextView) findViewById(R.id.signup);
        register.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.signup:
            startActivity(new Intent(this,SignUp.class));
            break;
        }
    }
}