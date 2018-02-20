package android.example.com.visualizerpreferences;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * Created by luism on 2/20/2018.
 */

public class SettingFragment  extends PreferenceFragmentCompat{
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
       addPreferencesFromResource(R.xml.pref_visualizer);
    }
}
