package com.example.musicity;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class homeFragment extends Fragment implements View.OnClickListener {
    ImageButton sonu,hari,shankar,shreya,arijith,rrr,bandish,bajirao,kalhonaho,tseries,sony,lahari,zee,classical;
    ImageButton arijith_hits, sad, dance, party,dancenew, love,love1;
    Bundle bundle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        bundle =new Bundle();
        sonu = (ImageButton) view.findViewById(R.id.sonu);
        sonu.setOnClickListener(this);
        hari = (ImageButton) view.findViewById(R.id.hari);
        hari.setOnClickListener(this);
        shankar = (ImageButton) view.findViewById(R.id.shankar);
        shankar.setOnClickListener(this);
        shreya = (ImageButton) view.findViewById(R.id.shreya);
        shreya.setOnClickListener(this);
        arijith = (ImageButton) view.findViewById(R.id.arijith);
        arijith.setOnClickListener(this);
        rrr = (ImageButton) view.findViewById(R.id.rrr);
        rrr.setOnClickListener(this);
        bandish = (ImageButton) view.findViewById(R.id.bandish);
        bandish.setOnClickListener(this);
        bajirao = (ImageButton) view.findViewById(R.id.bajirao);
        bajirao.setOnClickListener(this);
        kalhonaho = (ImageButton) view.findViewById(R.id.kalhonaho);
        kalhonaho.setOnClickListener(this);
        tseries = (ImageButton) view.findViewById(R.id.tseries);
        tseries.setOnClickListener(this);
        sony = (ImageButton) view.findViewById(R.id.sony);
        sony.setOnClickListener(this);
        zee = (ImageButton) view.findViewById(R.id.zee);
        zee.setOnClickListener(this);
        lahari = (ImageButton) view.findViewById(R.id.lahari);
        lahari.setOnClickListener(this);
        classical = (ImageButton) view.findViewById(R.id.classical);
        classical.setOnClickListener(this);
        sad = (ImageButton) view.findViewById(R.id.sad);
        sad.setOnClickListener(this);
        arijith_hits = (ImageButton) view.findViewById(R.id.hitarijith);
        arijith_hits.setOnClickListener(this);
        dance = (ImageButton) view.findViewById(R.id.dance);
        dance.setOnClickListener(this);
        dancenew = (ImageButton) view.findViewById(R.id.dancenew);
        dancenew.setOnClickListener(this);
        party= (ImageButton) view.findViewById(R.id.danceparty);
        party.setOnClickListener(this);
        love = (ImageButton) view.findViewById(R.id.romantic);
        love.setOnClickListener(this);
        love1 = (ImageButton) view.findViewById(R.id.love);
        love1.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = new ButtonFragment();
        if(view.equals(sonu))
            bundle.putString("sonu","sonu");
        else if(view.equals(hari))
            bundle.putString("hari","hari");
        else if(view.equals(shankar))
            bundle.putString("shankar","shankar");
        else if(view.equals(shreya))
            bundle.putString("shreya","shreya");
        else if(view.equals(arijith))
            bundle.putString("arijith","arijith");
        else if(view.equals(rrr))
            bundle.putString("rrr","rrr");
        else if(view.equals(bandish))
            bundle.putString("bandish","bandish");
        else if(view.equals(bajirao))
            bundle.putString("bajirao","bajirao");
        else if(view.equals(kalhonaho))
            bundle.putString("kalhonaho","kalhonaho");
        else if(view.equals(tseries))
            bundle.putString("t","t");
        else if(view.equals(sony))
            bundle.putString("sony","sony");
        else if(view.equals(zee))
            bundle.putString("zeeM","zeeM");
        else if(view.equals(lahari))
            bundle.putString("lahari","lahari");
        else if(view.equals(classical))
            bundle.putString("class","class");
        else if(view.equals(love) || view.equals(love1))
            bundle.putString("love","love");
        else if(view.equals(arijith_hits))
            bundle.putString("hit","hit");
        else if(view.equals(dance) || view.equals(dancenew))
            bundle.putString("dance","dance");
        else if(view.equals(sad))
            bundle.putString("sad","sad");
        else if(view.equals(party))
            bundle.putString("party","party");
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slide_out_from_left);
        fragmentTransaction.replace(R.id.flayout, fragment,null).addToBackStack(null).commit();
    }
}