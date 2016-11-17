package com.example.android.visualpolish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Four text views for four activities
        TextView colorAndFont = (TextView) findViewById(R.id.colorAndFont);
        TextView style = (TextView) findViewById(R.id.style);
        TextView responsiveLayouts = (TextView) findViewById(R.id.responsiveLayouts);
        TextView touchSelector = (TextView) findViewById(R.id.touchSelector);


        assert colorAndFont != null;
        colorAndFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numbersIntent = new Intent(MainActivity.this, ColorFontActivity.class);
                startActivity(numbersIntent);
            }
        });

        assert style != null;
        style.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent familyIntent = new Intent(MainActivity.this, StyleActivity.class);
                startActivity(familyIntent);
            }
        });

        assert responsiveLayouts != null;
        responsiveLayouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent familyIntent = new Intent(MainActivity.this, ResponsiveLayoutActivity.class);
                startActivity(familyIntent);
            }
        });

        assert touchSelector != null;
        touchSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent colorsIntent = new Intent(MainActivity.this, SelectorsActivity.class);
                startActivity(colorsIntent);
            }
        });
    }
}
