package com.example.heartrateapplication;

import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.CharArrayWriter;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    private EditText mail, password;
    private Button login;
    private RadioGroup radioGroup;
    private RadioButton user_select;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        databaseReference= database.getReference();

        mail=(EditText) findViewById(R.id.mailTxt);
        password=(EditText) findViewById(R.id.passTxt);
        radioGroup=findViewById(R.id.radioGroup);



    }



    private void userLogIn() {
        String emails = mail.getText().toString().trim();
        String passwords = password.getText().toString().trim();

        if(emails.isEmpty()){
            mail.setError("Introduceti adresa de email!");
            mail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emails).matches()){
            mail.setError("Introduceti un email valid!");
            mail.requestFocus();
            return;
        }

        if(passwords.isEmpty()){
            password.setError("Introduceti parola!");
            password.requestFocus();
            return;
        }

        if(passwords.length() < 6){
            password.setError("Introduceti o parola de minim 6 caractere!");
            password.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

         mAuth.signInWithEmailAndPassword(emails, passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //redirect to user profile
                    startActivity(new Intent(LogIn.this, PacientClass.class));

                } else {
                    Toast.makeText(LogIn.this, "Inregistrarea nu a avut loc! Incercati din nou!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }



        });
    }


    @Override
    public void onClick(View v) {
        int u_select= radioGroup.getCheckedRadioButtonId();
        user_select=findViewById(R.id.radio_medic);
        StringBuffer result = new StringBuffer();
        result.append("Rez ales!");
        if(user_select != null){
            progressBar.setVisibility(View.GONE);
            result.append("\nProfesie: \n").append(user_select.getText().toString());
        }
    }

}
