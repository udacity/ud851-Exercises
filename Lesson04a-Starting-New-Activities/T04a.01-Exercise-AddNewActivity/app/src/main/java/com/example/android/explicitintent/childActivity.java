package com.example.android.explicitintent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by luism on 1/26/2018.
 */
// TODO (1) Use Android Studio's Activity wizard to create a new Activity called ChildActivity


// Do steps 2 - 5 in activity_child.xml
// TODO (2) Change the ConstraintLayout to a FrameLayout and make appropriate adjustments checked
// TODO (3) Give your TextView an ID of tv_display checked
// TODO (4) Set the text to something that indicates this is default text being displayed checked
// TODO (5) Make the text size a little larger checked

// Do steps 6 & 7 in ChildActivity.java
// TODO (6) Create a TextView field to display your message check
// TODO (7) Get a reference to your TextView in Java checked

public class childActivity extends AppCompatActivity {

    private TextView text;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        text = (TextView)findViewById(R.id.show_message);

    }
}
