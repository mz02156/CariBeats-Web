package edu.georgiasouthern.csci5530;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlaylistActivity extends AppCompatActivity implements SongAdapter.OnSongClickListener {

    private RecyclerView recyclerView;
    private SongAdapter adapter;
    private Button createPlaylistButton;
    private Button deletePlaylistButton;

    private static final String DEFAULT_PLAYLIST = "MyPlaylist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        createPlaylistButton = findViewById(R.id.createPlaylistButton);
        deletePlaylistButton = findViewById(R.id.deletePlaylistButton);

        loadPlaylist(DEFAULT_PLAYLIST);

        createPlaylistButton.setOnClickListener(v -> {
            Toast.makeText(this, "Playlist already exists: " + DEFAULT_PLAYLIST, Toast.LENGTH_SHORT).show();
        });

        deletePlaylistButton.setOnClickListener(v -> {
            PlaylistManager.deletePlaylist(this, DEFAULT_PLAYLIST);
            adapter.updateSongs(List.of());
            Toast.makeText(this, "Playlist deleted.", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadPlaylist(String name) {
        List<Song> songs = PlaylistManager.getPlaylist(this, name);
        if (songs == null || songs.isEmpty()) {
            Toast.makeText(this, "No songs in playlist.", Toast.LENGTH_SHORT).show();
        }
        adapter = new SongAdapter(this, songs, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSongClick(Song selectedSong) {
        Intent intent = new Intent(this, PlaybackActivity.class);
        intent.putExtra("selectedSong", selectedSong);
        startActivity(intent);
    }
}






