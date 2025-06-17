package edu.georgiasouthern.csci5530;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class GenreSelectionActivity extends AppCompatActivity implements SongAdapter.OnSongClickListener {

    private Spinner genreSpinner;
    private RecyclerView songRecyclerView;
    private SongAdapter songAdapter;
    private List<Song> allSongs;
    private List<Song> filteredSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_selection);

        genreSpinner = findViewById(R.id.genreSpinner);
        songRecyclerView = findViewById(R.id.songRecyclerView);
        Button spotifyLoginButton = findViewById(R.id.spotifyLoginButton);

        spotifyLoginButton.setOnClickListener(v -> {
            try {
                String url = SpotifyAuthManager.buildAuthorizationUrl();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            } catch (Exception e) {
                Log.e("SpotifyAuth", "Failed to launch Spotify login", e);
            }
        });

        allSongs = DatasetLoader.loadSongs(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                Arrays.asList("reggae", "dancehall", "soca", "konpa", "reggaeton",
                        "hip-hop", "pop", "afrobeat", "brazil", "latin", "latino",
                        "r-n-b", "salsa", "samba", "spanish", "soul", "edm"));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(adapter);

        songAdapter = new SongAdapter(this, new ArrayList<>(), this);
        songRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        songRecyclerView.setAdapter(songAdapter);

        genreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGenre = parent.getItemAtPosition(position).toString();
                filteredSongs = DatasetLoader.filterSongsByGenre(allSongs, selectedGenre);
                songAdapter.updateSongs(filteredSongs);
            }

            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    @Override
    public void onSongClick(Song song) {
        Intent intent = new Intent(this, PlaybackActivity.class);
        intent.putExtra("selectedSong", song);
        startActivity(intent);
    }
}










