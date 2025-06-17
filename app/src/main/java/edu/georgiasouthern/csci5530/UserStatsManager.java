package edu.georgiasouthern.csci5530;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;

public class UserStatsManager {

    private static final String PREFS_NAME = "UserStatsPrefs";
    private static final String MOOD_PREFIX = "mood_";
    private static final String GENRE_PREFIX = "genre_";
    private static final String TOTAL_TEMPO = "totalTempo";
    private static final String TEMPO_COUNT = "tempoCount";

    // Record mood selection frequency
    public static void logMood(Context context, String mood) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int count = prefs.getInt(MOOD_PREFIX + mood, 0);
        prefs.edit().putInt(MOOD_PREFIX + mood, count + 1).apply();
    }

    // Record genre selection frequency
    public static void logGenre(Context context, String genre) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int count = prefs.getInt(GENRE_PREFIX + genre, 0);
        prefs.edit().putInt(GENRE_PREFIX + genre, count + 1).apply();
    }

    // Accumulate tempo data for averaging
    public static void logTempo(Context context, float tempo) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        float total = prefs.getFloat(TOTAL_TEMPO, 0);
        int count = prefs.getInt(TEMPO_COUNT, 0);
        prefs.edit().putFloat(TOTAL_TEMPO, total + tempo).putInt(TEMPO_COUNT, count + 1).apply();
    }

    // Return average tempo
    public static float getAverageTempo(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        float total = prefs.getFloat(TOTAL_TEMPO, 0);
        int count = prefs.getInt(TEMPO_COUNT, 0);
        return count == 0 ? 0 : total / count;
    }

    // Return a map of mood counts
    public static Map<String, Integer> getMoodStats(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Map<String, Integer> moodMap = new HashMap<>();
        for (String mood : new String[]{"Chill", "Moderate", "Hype"}) {
            moodMap.put(mood, prefs.getInt(MOOD_PREFIX + mood, 0));
        }
        return moodMap;
    }

    // Return a map of genre counts
    public static Map<String, Integer> getGenreStats(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Map<String, Integer> genreMap = new HashMap<>();
        for (String genre : new String[]{
                "reggae", "dancehall", "soca", "konpa", "reggaeton",
                "hip-hop", "pop", "afrobeat", "brazil", "latin",
                "latino", "r-n-b", "salsa", "samba", "spanish",
                "soul", "edm"}) {
            genreMap.put(genre, prefs.getInt(GENRE_PREFIX + genre, 0));
        }
        return genreMap;
    }

    // Clear all saved stats
    public static void resetStats(Context context) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit().clear().apply();
    }
}
