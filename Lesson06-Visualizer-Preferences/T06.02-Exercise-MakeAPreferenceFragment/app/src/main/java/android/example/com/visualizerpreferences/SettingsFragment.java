package android.example.com.visualizerpreferences;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * Created by Angel on 2/13/18.
 */

// TODO (2) Create a class called SettingsFragment that extends PreferenceFragmentCompat
// TODO (3) In res->xml create a file called pref_visualizer


// TODO (5) In SettingsFragment's onCreatePreferences method add the preference file using the
// addPreferencesFromResource method


public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_visualizer);
    }
}
