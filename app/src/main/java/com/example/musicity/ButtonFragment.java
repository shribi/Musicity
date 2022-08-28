package com.example.musicity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ButtonFragment extends Fragment {
    ImageView bg, bgplayer;
    ListView listview;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    song_name song;
    Query refer;
    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_button, container, false);
        song = new song_name();
        bg = (ImageView) view.findViewById(R.id.bg);
        bgplayer = (ImageView) view.findViewById(R.id.bgplayer);
        listview = view.findViewById(R.id.mylist);
        JcPlayerView jcplayer = (JcPlayerView) view.findViewById(R.id.jcplayer);
        ArrayList<JcAudio> jcAudios = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getContext(), R.layout.song_name, R.id.sname, list);
        ref = database.getReference("Musicity");
        //------------------------------------------------------------------------------------------
        if(getArguments().getString("sonu")=="sonu")
            refer = ref.orderByChild("artist").equalTo("Sonu Nigam");
        if(getArguments().getString("hari")=="hari")
            refer = ref.orderByChild("artist").equalTo("Hariharan");
        if(getArguments().getString("shankar")=="shankar")
            refer = ref.orderByChild("artist").equalTo("Shankar Mahadevan");
        if(getArguments().getString("shreya")=="shreya")
            refer = ref.orderByChild("artist").equalTo("Shreya Ghoshal");
        if(getArguments().getString("arijith")=="arijith")
            refer = ref.orderByChild("artist").equalTo("Arijith Singh");
        if(getArguments().getString("rrr")=="rrr")
            refer = ref.orderByChild("album").equalTo("RRR");
        if(getArguments().getString("bandish")=="bandish")
            refer = ref.orderByChild("album").equalTo("Bandish Bandits");
        if(getArguments().getString("bajirao")=="bajirao")
            refer = ref.orderByChild("album").equalTo("Bajirao Mastani");
        if(getArguments().getString("kalhonaho")=="kalhonaho")
            refer = ref.orderByChild("album").equalTo("Kal Ho Na Ho");
        if(getArguments().getString("t")=="t")
            refer = ref.orderByChild("label").equalTo("T Series");
        if(getArguments().getString("zeeM")=="zeeM")
            refer = ref.orderByChild("label").equalTo("Zee Music");
        if(getArguments().getString("sony")=="sony")
            refer = ref.orderByChild("label").equalTo("Sony Music");
        if(getArguments().getString("lahari")=="lahari")
            refer = ref.orderByChild("label").equalTo("Lahari Music");
        if(getArguments().getString("class")=="class")
            refer = ref.orderByChild("genre").equalTo("Classical");
        if(getArguments().getString("dance")=="dance")
            refer = ref.orderByChild("genre").equalTo("Dance");
        if(getArguments().getString("party")=="party")
            refer = ref.orderByChild("genre").equalTo("Party");
        if(getArguments().getString("sad")=="sad")
            refer = ref.orderByChild("genre").equalTo("Sad");
        if(getArguments().getString("love")=="love")
            refer = ref.orderByChild("genre").equalTo("Romantic");
        if(getArguments().getString("hit")=="hit")
            refer = ref.orderByChild("artist").equalTo("Arijith Singh");
        if(getArguments().getString("love")=="love")
            refer = ref.orderByChild("genre").equalTo("Romantic");

        //------------------------------------------------------------------------------------------

        refer.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    song = ds.getValue(song_name.class);
                    list.add(song.getSong());
                    jcAudios.add(JcAudio.createFromURL(song.getSong(),song.getSongurl()));
                }
                jcplayer.initPlaylist(jcAudios,null);
                jcplayer.createNotification();
                listview.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "SOmething went wrong. Try again!!!", Toast.LENGTH_LONG).show();
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                jcplayer.playAudio(jcAudios.get(position));
                jcplayer.setVisibility(View.VISIBLE);
                bg.setVisibility(View.VISIBLE);
                bgplayer.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }
}