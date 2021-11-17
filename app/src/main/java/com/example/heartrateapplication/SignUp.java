package com.example.heartrateapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SignUp extends AppCompatActivity{
    Button button_signup;
    TextView signup;
    EditText emails, passwords;
    //FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // firebaseAuth=FirebaseAuth.getInstance();
        passwords=findViewById(R.id.edittext2);
        emails=findViewById(R.id.edittext);
        button_signup=findViewById(R.id.buttons);
        signup=findViewById(R.id.signup);

        button_signup.setOnClickListener(new View.OnClickListener() {

            //@android.support.annotation.RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View v) {
                String email=emails.getText().toString();
                String password=passwords.getText().toString();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                    if(email.isEmpty()){
                        emails.setError("Va rog sa introduceti email-ul dumneavoastra!");
                        emails.requestFocus();
                    }
                    else
                        if(password.isEmpty()){
                            emails.setError("Va rog sa introduceti parola dumneavoastra!");
                            emails.requestFocus();
                        }
                        else
                            if(email.isEmpty() && password.isEmpty()){
                                Toast.makeText(getApplicationContext(), "Completati cele 2 campuri!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            if(email.isEmpty() && password.isEmpty()){
                                //facut dupa ce rezolv cu firebaseAuth
                            }
                            else{
                                Toast.makeText(signup.getContext(), "A aparut o eroare!", Toast.LENGTH_SHORT).show();
                            }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
            }
        });


    }
}
