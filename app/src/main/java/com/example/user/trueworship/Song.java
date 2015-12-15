package com.example.user.trueworship;

import com.parse.ParseObject;

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
