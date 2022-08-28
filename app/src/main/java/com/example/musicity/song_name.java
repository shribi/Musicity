package com.example.musicity;

public class song_name {
    private String song,songurl;

    public song_name(){

    }

    public song_name(String song, String songurl) {
        this.song = song;
        this.songurl=songurl;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }
    public String getSongurl() {
        return songurl;
    }

    public void setSongurl(String songurl ) {
        this.songurl = songurl;
    }
}

