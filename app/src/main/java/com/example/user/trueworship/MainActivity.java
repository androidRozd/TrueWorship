package com.example.user.trueworship;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
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

        Parse.initialize(this, "ajTuzd0ZdNU12NgZ50StU39Gt6Sbl5xr9f4hIFTB", "zRfNZf0rAUEFuTgEpFAO64omvdCK4iDwoqyJMUG8");

        mListView = (ListView) findViewById(R.id.list);
        mAdapter = new MainAdpter(this, new ArrayList<Song>());
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        updateData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
