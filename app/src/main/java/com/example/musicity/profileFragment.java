package com.example.musicity;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class profileFragment extends Fragment {

    TextView cur_email, username;
    ImageButton logout;
    SharedPreferences shared;
    Button chpass;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Email = "emailKey";
    private FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        cur_email = (TextView) view.findViewById(R.id.current_email);
        username = (TextView) view.findViewById(R.id.username);
        logout = (ImageButton) view.findViewById(R.id.logout);
        mAuth = FirebaseAuth.getInstance();
        SharedPreferences shared = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String email = (shared.getString(Email, ""));
        cur_email.setText(email);
        String[] emailsplit = email.split("@");
        username.setText(String.valueOf(emailsplit[0]));
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = shared.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(getActivity(),"Log out Successful",Toast.LENGTH_SHORT).show();
                Intent logout = new Intent(getActivity(), login.class);
                startActivity(logout);
            }
        });
        chpass=(Button) view.findViewById(R.id.chpass);
        chpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chpass.setEnabled(false);
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "We have sent you instructions to your email to reset your password!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), "Failed to send reset email!", Toast.LENGTH_LONG).show();
                        }
                        chpass.setEnabled(true);

                    }
                });
            }
        });
        return view;
    }

}