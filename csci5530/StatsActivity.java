package edu.georgiasouthern.csci5530;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class StatsActivity extends AppCompatActivity {

    private TextView moodStatsView, genreStatsView, avgTempoView;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        moodStatsView = findViewById(R.id.moodStats);
        genreStatsView = findViewById(R.id.genreStats);
        avgTempoView = findViewById(R.id.avgTempo);
        resetButton = findViewById(R.id.resetStatsButton);

        // Load and display mood stats
        Map<String, Integer> moods = UserStatsManager.getMoodStats(this);
        StringBuilder moodText = new StringBuilder("\n");
        moods.forEach((k, v) -> moodText.append(String.format(Locale.US, "%s: %d\n", k, v)));
        moodStatsView.setText("Mood Usage:" + moodText);

        // Load and display genre stats
        Map<String, Integer> genres = UserStatsManager.getGenreStats(this);
        StringBuilder genreText = new StringBuilder("\n");
        genres.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .forEach(entry -> genreText.append(String.format(Locale.US, "%s: %d\n", entry.getKey(), entry.getValue())));
        genreStatsView.setText("Top Genres:" + genreText);

        // Show average tempo
        float avgTempo = UserStatsManager.getAverageTempo(this);
        avgTempoView.setText(String.format(Locale.US, "Average Tempo: %.2f BPM", avgTempo));

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserStatsManager.resetStats(StatsActivity.this);
                Toast.makeText(StatsActivity.this, "Stats Reset!", Toast.LENGTH_SHORT).show();
                recreate();
            }
        });
    }
}