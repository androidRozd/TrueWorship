package com.example.user.trueworship;

import com.parse.ParseClassName;
import com.parse.ParseObject;
@ParseClassName("Song")
public class Song extends ParseObject {
    public Song() {
    }
    public String getName () {
        return getString("name");
    }
    public void setName (String name) {
        put("name", name);
    }
    public String getLyrics () {
        return getString("lyrics");
    }
    public void setLyrics (String lyrics) {
        put("lyrics", lyrics);
    }
}
