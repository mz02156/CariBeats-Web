package edu.georgiasouthern.csci5530;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

public class PlaybackActivity extends AppCompatActivity implements SongAdapter.OnSongClickListener {

    private TextView songTitle, artistName, tempoView, moodLabel;
    private RecyclerView queueRecyclerView;
    private SongAdapter queueAdapter;
    private Button addToPlaylistButton;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playback);

        songTitle = findViewById(R.id.songTitle);
        artistName = findViewById(R.id.artistName);
        tempoView = findViewById(R.id.tempoView);
        moodLabel = findViewById(R.id.moodLabel);
        queueRecyclerView = findViewById(R.id.queueRecyclerView);
        addToPlaylistButton = findViewById(R.id.addToPlaylistButton);

        Song selectedSong = (Song) getIntent().getSerializableExtra("selectedSong");

        if (selectedSong != null) {
            songTitle.setText(selectedSong.getTitle());
            artistName.setText(selectedSong.getArtists());
            tempoView.setText("Tempo: " + (int) selectedSong.getTempo() + " BPM");

            String mood = BPMClusterHelper.getMoodLabel(selectedSong.getTempo());
            moodLabel.setText("Mood: " + mood);

            // 🔊 Play audio if preview available
            String previewUrl = selectedSong.getPreviewUrl();
            if (previewUrl != null && !previewUrl.isEmpty()) {
                playSong(previewUrl);
            } else {
                Toast.makeText(this, "Preview not available for this song.", Toast.LENGTH_SHORT).show();
            }

            List<Song> allSongs = DatasetLoader.loadSongs(this);
            List<Song> queue = DatasetLoader.findSimilarTempoSongs(allSongs, selectedSong.getTempo(), selectedSong.getGenre());

            queueAdapter = new SongAdapter(this, queue, this);
            queueRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            queueRecyclerView.setAdapter(queueAdapter);

            addToPlaylistButton.setOnClickListener(v -> {
                PlaylistManager.addToPlaylist(this, "MyPlaylist", selectedSong);
                Toast.makeText(this, "Added to MyPlaylist", Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void playSong(String previewUrl) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(previewUrl);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Toast.makeText(this, "Error playing preview", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onSongClick(Song song) {
        finish();
        startActivity(getIntent().putExtra("selectedSong", song));
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}








