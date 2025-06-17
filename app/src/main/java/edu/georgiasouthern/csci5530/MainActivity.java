package edu.georgiasouthern.csci5530;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import edu.georgiasouthern.csci5530.PlaylistActivity;

public class MainActivity extends AppCompatActivity implements SongAdapter.OnSongClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button statsButton = findViewById(R.id.statsButton);
        statsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StatsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        Button localButton = findViewById(R.id.useLocalButton);
        localButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GenreSelectionActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                return true;
            } else if (id == R.id.nav_stats) {
                startActivity(new Intent(MainActivity.this, StatsActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                return true;
            } else if (id == R.id.nav_playlists) {
                startActivity(new Intent(MainActivity.this, PlaylistActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                return true;
            }
            return false;
        });
    }

    @Override
    public void onSongClick(Song selectedSong) {
        Intent intent = new Intent(MainActivity.this, PlaybackActivity.class);
        intent.putExtra("selectedSong", selectedSong);
        startActivity(intent);
    }
}



