package com.example.musicity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;


public class register extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private EditText conpassword;
    private Button reg, rlogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        conpassword = findViewById(R.id.conpassword);
        reg = (Button) findViewById(R.id.register);
        rlogin = findViewById(R.id.rlogin);
        mAuth = FirebaseAuth.getInstance();

        rlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register.this, login.class));
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createuser();
            }
        });
    }

    private void createuser() {
        String newemail = email.getText().toString();
        String pass = password.getText().toString();
        String conpass = conpassword.getText().toString();
        if(!newemail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(newemail).matches()){
            if(!pass.isEmpty() && pass.equals(conpass)) {
                mAuth.createUserWithEmailAndPassword(newemail, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(register.this, "Registered Successfully !!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(register.this, login.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(register.this, "Registration Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else if(!pass.equals(conpass)) Toast.makeText(register.this,"Passwords does not match!!!",Toast.LENGTH_SHORT).show();
            else password.setError("Empty Fields Are not Allowed!!");
        }
        else if(newemail.isEmpty()) email.setError("Empty Fields Are not Allowed!!");
        else email.setError("Improper email address!!!");
    }
}