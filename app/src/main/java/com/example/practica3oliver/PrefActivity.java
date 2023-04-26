package com.example.practica3oliver;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

import androidx.preference.SwitchPreference;

public class PrefActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
