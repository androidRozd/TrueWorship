package com.example.user.trueworship;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class MainAdpter extends ArrayAdapter <Song> {
    private final Context mContext;
    private final List<Song> mSongs;

    public MainAdpter(Context context, List<Song> objects) {
        super(context,R.layout.my_list_item, objects);
        this.mContext = context;
        this.mSongs = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.my_list_item, null);
        }
        Song song = mSongs.get (position);
        Log.e("name", song.getName());

        TextView nameView = (TextView) convertView.findViewById(R.id.textView1);

        nameView.setText(song.getName());

        return convertView;
    }
}
