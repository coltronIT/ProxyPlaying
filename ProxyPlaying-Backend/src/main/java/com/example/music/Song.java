package com.example.music;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Song {
   @JsonProperty
    String id;

    @JsonProperty
    String songDisplay;

    public Song() {};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSongDisplay() {
        return songDisplay;
    }

    public void setSongDisplay(String songDisplay) {
        this.songDisplay = songDisplay;
    }
}
