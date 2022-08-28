package com.example.musicity;
import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    public EditText email;
    private EditText password;
    private Button login, lreg,fpass,xbutton;
    private ImageView logo,logo3;
    private FirebaseAuth mAuth;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Email = "emailKey";
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logo = (ImageView) findViewById(R.id.logo);
        logo3 = (ImageView) findViewById(R.id.logo3);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();
        lreg = findViewById(R.id.lregister);
        fpass = (Button) findViewById(R.id.fpass);
        xbutton = (Button) findViewById(R.id.xbut);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if(sharedPreferences.contains(Email)){
            logo3.setVisibility(View.INVISIBLE);
            logo.setVisibility(View.VISIBLE);
            email.setVisibility(View.INVISIBLE);
            password.setVisibility(View.INVISIBLE);
            login.setEnabled(false);
            login.setVisibility(View.INVISIBLE);
            lreg.setEnabled(false);
            lreg.setVisibility(View.INVISIBLE);
            fpass.setEnabled(false);
            fpass.setVisibility(View.INVISIBLE);
            xbutton.setEnabled(false);
            xbutton.setVisibility(View.INVISIBLE);
            Intent i = new Intent(login.this, home.class);
            ActivityOptions options = ActivityOptions.makeCustomAnimation(this,R.anim.slide_from_right,R.anim.slide_out_from_left);
            this.startActivity(i);
        }


        xbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, home.class));
                Toast.makeText(login.this, "Currently you are Logged in as a Guest!!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        fpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailaddress = email.getText().toString();
                fpass.setEnabled(false);
                if (emailaddress.isEmpty()) {
                    Toast.makeText(login.this, "Enter your registered email id", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.sendPasswordResetEmail(emailaddress).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(login.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(login.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                    }
                                    fpass.setEnabled(true);
                                }
                            });
                }
            }
        });

        lreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, register.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginuser();
            }
        });
    }
    private void loginuser() {
        String newemail = email.getText().toString();
        String pass = password.getText().toString();
        if(!newemail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(newemail).matches()){
            if(!pass.isEmpty()) {
                mAuth.signInWithEmailAndPassword(newemail,pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(Email, newemail);
                                editor.commit();
                                Toast.makeText(login.this, "Login Successful!!", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(login.this, home.class);
                                ActivityOptions options = ActivityOptions.makeCustomAnimation(getBaseContext(),R.anim.slide_from_right,R.anim.slide_out_from_left);
                                startActivity(i);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(login.this, "Login failed!!!Check your email or password.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else password.setError("Empty Fields Are not Allowed!!");
        }
        else if(newemail.isEmpty()) email.setError("Empty Fields Are not Allowed!!");
        else email.setError("Improper email address!!!");
    }
}