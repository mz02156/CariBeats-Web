package edu.georgiasouthern.csci5530;

import java.util.List;

public class StaticSongStore {
    private static List<Song> songs;

    public static void setSongs(List<Song> s) {
        songs = s;
    }

    public static List<Song> getSongs() {
        return songs;
    }
}
