package com.example.musicity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.example.musicity.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class playlistFragment extends Fragment {
    ImageView bg, bgplayer;
    ListView listview;
    FirebaseDatabase database;
    DatabaseReference refer;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    song_name song;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        song = new song_name();
        bg = (ImageView) view.findViewById(R.id.bg);
        bgplayer = (ImageView) view.findViewById(R.id.bgplayer);
        listview = view.findViewById(R.id.mylist1);
        JcPlayerView jcplayer = (JcPlayerView) view.findViewById(R.id.jcplayer);
        ArrayList<JcAudio> jcAudios = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getContext(), R.layout.song_name, R.id.sname, list);
        refer = database.getReference("Musicity");
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