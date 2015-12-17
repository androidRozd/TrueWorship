package com.example.user.trueworship;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.opengl.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.TextView;

public class LyricsActivity extends AppCompatActivity {

    private TextView mName;
    private TextView mLyrics;
    private TextView scaleGesture;
    private ScaleGestureDetector scaleGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);
        scaleGesture = (TextView)findViewById(R.id.lyrics);
        scaleGesture.setText("this is some text");
        scaleGestureDetector = new ScaleGestureDetector(this, new simpleOnScaleGestureListener());
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String lyrics = intent.getStringExtra("lyrics");
        mName = (TextView) findViewById(R.id.name);
        mLyrics = (TextView) findViewById(R.id.lyrics);
        mLyrics.setText(lyrics);
        setTitle(name);
    }
    public class simpleOnScaleGestureListener extends
            ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            // TODO Auto-generated method stub
            float size = scaleGesture.getTextSize();
            Log.d("TextSizeStart", String.valueOf(size));

            float factor = detector.getScaleFactor();
            Log.d("Factor", String.valueOf(factor));


            float product = size*factor;
            Log.d("TextSize", String.valueOf(product));
            scaleGesture.setTextSize(TypedValue.COMPLEX_UNIT_PX, product);

            size = scaleGesture.getTextSize();
            Log.d("TextSizeEnd", String.valueOf(size));
            return true;
        }
    }

}

