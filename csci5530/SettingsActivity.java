package edu.georgiasouthern.csci5530;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREFS = "ThemePrefs";
    private static final String MODE_KEY = "nightMode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Switch themeSwitch = findViewById(R.id.themeSwitch);
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        boolean isNight = prefs.getBoolean(MODE_KEY, false);

        themeSwitch.setChecked(isNight);
        AppCompatDelegate.setDefaultNightMode(
                isNight ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);

        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AppCompatDelegate.setDefaultNightMode(
                        isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                prefs.edit().putBoolean(MODE_KEY, isChecked).apply();
            }
        });
    }
}
