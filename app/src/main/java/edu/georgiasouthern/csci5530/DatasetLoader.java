package edu.georgiasouthern.csci5530;

import android.content.Context;
import android.util.Log;
import java.io.*;
import java.util.*;

/**
 * DatasetLoader is responsible for loading and filtering songs
 * from a local CSV dataset stored in the assets folder.
 */
public class DatasetLoader {
    private static final String TAG = "DatasetLoader";

    /**
     * Loads song data from the dataset.csv file located in the assets folder.
     * Validates and skips malformed rows safely.
     *
     * @param context the Android context used to access assets
     * @return a list of Song objects parsed from the CSV
     */
    public static List<Song> loadSongs(Context context) {
        List<Song> songs = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open("dataset.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            // Skip header line
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                // Ensure the row has enough columns
                if (tokens.length < 19) continue;

                try {
                    String id = tokens[0].trim();
                    String artists = tokens[1].trim();
                    String album = tokens[2].trim();
                    String title = tokens[3].trim();
                    int popularity = Integer.parseInt(tokens[4].trim());
                    int duration = Integer.parseInt(tokens[5].trim());
                    boolean explicit = Boolean.parseBoolean(tokens[6].trim());
                    float danceability = tryParseFloat(tokens[7]);
                    float energy = tryParseFloat(tokens[8]);
                    int key = Integer.parseInt(tokens[9].trim());
                    float loudness = tryParseFloat(tokens[10]);
                    int mode = Integer.parseInt(tokens[11].trim());
                    float speechiness = tryParseFloat(tokens[12]);
                    float acousticness = tryParseFloat(tokens[13]);
                    float instrumentalness = tryParseFloat(tokens[14]);
                    float liveness = tryParseFloat(tokens[15]);
                    float valence = tryParseFloat(tokens[16]);
                    float tempo = tryParseFloat(tokens[17]);
                    String genre = tokens[18].trim();

                    // Only add songs with valid tempo
                    if (tempo > 0 && !genre.isEmpty()) {
                        Song song = new Song(id, artists, album, title, popularity, duration, explicit,
                                danceability, energy, key, loudness, mode,
                                speechiness, acousticness, instrumentalness, liveness, valence,
                                tempo, genre);
                        songs.add(song);
                    }
                } catch (Exception e) {
                    Log.w(TAG, "Skipping malformed row: " + line);
                }
            }

        } catch (IOException e) {
            Log.e(TAG, "Error reading dataset.csv", e);
        }

        return songs;
    }

    /**
     * Parses a float value safely. Returns -1 if parsing fails.
     */
    private static float tryParseFloat(String s) {
        try {
            return Float.parseFloat(s.trim());
        } catch (NumberFormatException e) {
            return -1f;
        }
    }

    public static List<Song> filterSongsByGenre(List<Song> allSongs, String genre) {
        List<Song> filtered = new ArrayList<>();
        for (Song s : allSongs) {
            if (s.getGenre().equalsIgnoreCase(genre)) {
                filtered.add(s);
            }
        }
        return filtered;
    }

    public static List<Song> findSimilarTempoSongs(List<Song> songs, float tempo, String genre) {
        List<Song> queue = new ArrayList<>();
        for (Song s : songs) {
            if (s.getGenre().equalsIgnoreCase(genre) && Math.abs(s.getTempo() - tempo) <= 10) {
                queue.add(s);
            }
        }
        return queue;
    }
}



