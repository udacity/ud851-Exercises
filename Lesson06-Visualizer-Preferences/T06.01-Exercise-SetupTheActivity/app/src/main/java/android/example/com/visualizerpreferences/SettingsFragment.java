package android.example.com.visualizerpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.preference.CheckBoxPreference;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences_visualizer, rootKey);
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int prefCount = preferenceScreen.getPreferenceCount();
        for (int i = 0;i<prefCount;i++){
            Preference preference = preferenceScreen.getPreference(i);
            if(!(preference instanceof CheckBoxPreference)){
                String value = sharedPreferences.getString(preference.getKey(),"");
                setPreferenceSummary(preference,value);
            }
        }

        Preference minScalePref = findPreference(getString(R.string.pref_shape_size_key));
        minScalePref.setOnPreferenceChangeListener(this);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if (!(preference instanceof CheckBoxPreference)){
            String value = sharedPreferences.getString(key,"");
            setPreferenceSummary(preference,value);
        }
    }
    private void setPreferenceSummary(Preference preference,String value){
        if (preference instanceof ListPreference){
            ListPreference listPreference = (ListPreference)preference;
            int preIndex = listPreference.findIndexOfValue(value);
            if (preIndex >=0){
                listPreference.setSummary(listPreference.getEntries()[preIndex]);
            }
        }
        else if (preference instanceof EditTextPreference){
            preference.setSummary(value);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Toast error = Toast.makeText(getContext(),"Enter value between 0.1 and 2",Toast.LENGTH_SHORT);

        String key = getString(R.string.pref_shape_size_key);
        if (preference.getKey().equals(key)){
            String stringSize = (String)newValue;
            try {
                float size = Float.parseFloat(stringSize);
                if (size<=0 ||size>3){
                    error.show();
                    return false;
                }
            } catch (NumberFormatException ex){
                error.show();
                return false;
            }

        }

        return true;
    }
}