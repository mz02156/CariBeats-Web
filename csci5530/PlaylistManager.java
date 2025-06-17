package edu.georgiasouthern.csci5530;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaylistManager {

    private static final String PREFS_NAME = "Playlists";
    private static final String DATA_KEY = "data";
    private static final Gson gson = new Gson();

    public static void addToPlaylist(Context context, String playlistName, Song song) {
        Map<String, List<Song>> playlists = getPlaylists(context);
        List<Song> songs = playlists.getOrDefault(playlistName, new ArrayList<>());

        // Avoid duplicates (by title and artist)
        boolean exists = false;
        for (Song s : songs) {
            if (s.getTitle().equals(song.getTitle()) && s.getArtists().equals(song.getArtists())) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            songs.add(song);
            playlists.put(playlistName, songs);
            savePlaylists(context, playlists);
        }
    }

    public static List<Song> getPlaylist(Context context, String playlistName) {
        Map<String, List<Song>> playlists = getPlaylists(context);
        return playlists.getOrDefault(playlistName, new ArrayList<>());
    }

    public static void deletePlaylist(Context context, String playlistName) {
        Map<String, List<Song>> playlists = getPlaylists(context);
        if (playlists.containsKey(playlistName)) {
            playlists.remove(playlistName);
            savePlaylists(context, playlists);
        }
    }

    public static List<String> getAllPlaylistNames(Context context) {
        return new ArrayList<>(getPlaylists(context).keySet());
    }

    private static Map<String, List<Song>> getPlaylists(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(DATA_KEY, "{}");
        Type type = new TypeToken<Map<String, List<Song>>>() {}.getType();
        Map<String, List<Song>> playlists = gson.fromJson(json, type);
        return (playlists != null) ? playlists : new HashMap<>();
    }

    private static void savePlaylists(Context context, Map<String, List<Song>> playlists) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String json = gson.toJson(playlists);
        editor.putString(DATA_KEY, json);
        editor.apply();
    }
}


