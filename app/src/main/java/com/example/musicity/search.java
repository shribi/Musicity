package com.example.musicity;

import static android.view.View.VISIBLE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.example.musicity.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class search extends AppCompatActivity {
    ListView listView;
    DatabaseReference ref,ref1;
    FirebaseDatabase database;
    JcPlayerView jcplayer;
    private AutoCompleteTextView txtsearch;
    ArrayAdapter<String> adapter,adapter1;
    ArrayList<String> list;
    ArrayList<JcAudio> jcAudios;
    String sname,surl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        jcplayer=(JcPlayerView)findViewById(R.id.jcplayer);
        ref = FirebaseDatabase.getInstance().getReference("Musicity");
        listView = findViewById(R.id.searchlist);
        jcAudios=new ArrayList<>();
        list=new ArrayList<String>();
        txtsearch = findViewById(R.id.txtsearch);
        ValueEventListener event = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                populateSearch(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        ref.addListenerForSingleValueEvent(event);
    }

    private void populateSearch(DataSnapshot snapshot) {
        ArrayList<String> names=new ArrayList<String>();
        if(snapshot.exists())
        {
            for(DataSnapshot ds:snapshot.getChildren())
            {
                String name=ds.child("song").getValue(String.class);
                names.add(name);
            }
            adapter=new ArrayAdapter(getBaseContext(),R.layout.song_name,R.id.sname,names);
            txtsearch.setAdapter(adapter);

        }
        else
        {
            Log.d("Songs","No songs found");
        }
        txtsearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }


}