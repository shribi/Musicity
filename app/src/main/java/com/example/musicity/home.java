package com.example.musicity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.musicity.databinding.ActivityHomeBinding;

public class home extends AppCompatActivity {

    private long backPressedTime = 0;
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replacefragment(new homeFragment());
        binding.bottomNavigationView.setOnItemSelectedListener( item -> {
            switch(item.getItemId()){
                case R.id.homeicon:
                    replacefragment(new homeFragment());
                    break;
                case R.id.searchicon:
                    Intent i = new Intent(this, search.class);
                    startActivity(i);
                    break;
                case R.id.playicon:
                    replacefragment(new PlayerFragment());
                    break;

                case R.id.playlisticon:
                    replacefragment(new playlistFragment());
                    break;
                case R.id.profileicon:
                    replacefragment(new profileFragment());
                    break;
            }

            return true;
        });
    }
    @Override
    public void onBackPressed() {
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000) {
            backPressedTime = t;
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
    }

    private void replacefragment(Fragment fragment){
        FragmentManager fmangaer = getSupportFragmentManager();
        FragmentTransaction ftransac = getSupportFragmentManager().beginTransaction();
        ftransac.replace(R.id.flayout,fragment);
        ftransac.commit();
    }
}