package com.example.user.trueworship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LyricsActivity extends AppCompatActivity {

    private TextView mLyrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String lyrics = intent.getStringExtra("lyrics");
        mLyrics = (TextView) findViewById(R.id.lyrics);
        mLyrics.setText(lyrics);
        setTitle(name);
    }
}
