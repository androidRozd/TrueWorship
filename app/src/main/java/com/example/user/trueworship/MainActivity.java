package com.example.user.trueworship;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView mListView;
    private MainAdpter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseObject.registerSubclass(Song.class);
        Parse.initialize(this, "ajTuzd0ZdNU12NgZ50StU39Gt6Sbl5xr9f4hIFTB", "zRfNZf0rAUEFuTgEpFAO64omvdCK4iDwoqyJMUG8");

        mListView = (ListView) findViewById(R.id.song_list);
        mAdapter = new MainAdpter(this, new ArrayList<Song>());
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        updateData();

    }

    private void updateData() {
        ParseQuery<Song> query = ParseQuery.getQuery(Song.class);
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
        query.findInBackground(new FindCallback<Song>() {
            @Override
            public void done(List<Song> songs, ParseException error) {
                if (songs != null) {
                    mAdapter.clear();
                    mAdapter.addAll(songs);
                    mAdapter.sort(new Comparator<Song>() {
                        @Override
                        public int compare(Song lhs, Song rhs) {
                            return lhs.getName().compareTo(rhs.getName());
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Song song = mAdapter.getItem(position);
        Intent intent = new Intent(MainActivity.this, com.example.user.trueworship.LyricsActivity.class);
        intent.putExtra("name", song.getName());
        intent.putExtra("lyrics", song.getLyrics());
        startActivity(intent);
    }
}
