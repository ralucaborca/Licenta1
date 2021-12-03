package com.example.heartrateapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Script;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private Button button_signup;
    private TextView signup;
    private EditText names = findViewById(R.id.edittextnume), emails, passwords;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth=FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        databaseReference= database.getReference();

        names=findViewById(R.id.edittextnume);
        passwords=findViewById(R.id.edittextmail);
        emails=findViewById(R.id.edittextpass);

        signup=findViewById(R.id.signup);
        signup.setOnClickListener(this);
        button_signup=findViewById(R.id.buttons);
        button_signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttons:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.signup:
                registerUser();
                break;
        }
    }

    public void registerUser() {
            String email = emails.getText().toString().trim();
            String password = passwords.getText().toString().trim();
            String name = names.getText().toString().trim();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            if(email.isEmpty()){
               emails.setError("Introduceti adresa de e-mail!");
               emails.requestFocus();
               return;
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
               emails.setError("Introduceti adresa de e-mail corecta!");
               emails.requestFocus();
               return;
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            if(password.isEmpty()){
                    passwords.setError("Introducti parola corecta!");
                    passwords.requestFocus();
                    return;
                 }
        }
        if(password.length() < 6){
                 passwords.setError("Prola trebuie sa contina minim 6 caractere!");
                 passwords.requestFocus();
                 return;
             }


            if(name.isEmpty()){
               names.setError("Introduceti numele complet!");
               names.requestFocus();
               return;
            }


        button_signup.setVisibility(View.VISIBLE);
             mAuth.createUserWithEmailAndPassword(email,password)
                     .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if(task.isSuccessful()){
                                 User user=new User(name,email);

                                 FirebaseDatabase.getInstance().getReference("Users")
                                         .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                          .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                     @Override
                                     public void onComplete(@NonNull Task<Void> task) {
                                         if(task.isSuccessful()){
                                             Toast.makeText(SignUp.this,"User-ul a fost inregistrat cu succes!", Toast.LENGTH_LONG).show();
                                             progressBar.setVisibility(View.VISIBLE);
                                         }else{
                                             Toast.makeText(SignUp.this, "Inregistrarea nu a avut loc! Incercati din nou!", Toast.LENGTH_LONG).show();
                                             progressBar.setVisibility(View.GONE);
                                         }
                                     }
                                 });

                             }else{
                                 Toast.makeText(SignUp.this, "Inregistrarea nu a avut loc! Incercati din nou!", Toast.LENGTH_LONG).show();
                                 progressBar.setVisibility(View.GONE);
                             }
                         }
                     });
    }


}
