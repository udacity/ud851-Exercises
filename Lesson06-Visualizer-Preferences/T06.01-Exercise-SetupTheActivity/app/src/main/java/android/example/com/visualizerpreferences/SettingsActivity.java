package android.example.com.visualizerpreferences;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    private TextView mDisplayText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mDisplayText = findViewById(R.id.test_text);

        Intent fromMain = getIntent();

        if(fromMain.hasExtra(Intent.EXTRA_TEXT)){
            String text = fromMain.getStringExtra(Intent.EXTRA_TEXT);
            mDisplayText.setText(text);
        }
    }
}
