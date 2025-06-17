package edu.georgiasouthern.csci5530;

import java.io.Serializable;

public class Song implements Serializable {
    private String id, artists, album, title, genre, audioUrl, previewUrl;
    private int popularity, duration;
    private boolean explicit;
    private float danceability, energy, loudness, speechiness, acousticness, instrumentalness, liveness, valence, tempo;
    private int key, mode;

    public Song() {
        // Default constructor for manual field setting
    }

    public Song(String id, String artists, String album, String title, int popularity, int duration, boolean explicit,
                float danceability, float energy, int key, float loudness, int mode,
                float speechiness, float acousticness, float instrumentalness, float liveness, float valence,
                float tempo, String genre) {
        this.id = id;
        this.artists = artists;
        this.album = album;
        this.title = title;
        this.popularity = popularity;
        this.duration = duration;
        this.explicit = explicit;
        this.danceability = danceability;
        this.energy = energy;
        this.key = key;
        this.loudness = loudness;
        this.mode = mode;
        this.speechiness = speechiness;
        this.acousticness = acousticness;
        this.instrumentalness = instrumentalness;
        this.liveness = liveness;
        this.valence = valence;
        this.tempo = tempo;
        this.genre = genre;
    }

    // Getters
    public String getId() { return id; }
    public String getArtists() { return artists; }
    public String getAlbum() { return album; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getPopularity() { return popularity; }
    public int getDuration() { return duration; }
    public boolean isExplicit() { return explicit; }
    public float getDanceability() { return danceability; }
    public float getEnergy() { return energy; }
    public float getLoudness() { return loudness; }
    public float getSpeechiness() { return speechiness; }
    public float getAcousticness() { return acousticness; }
    public float getInstrumentalness() { return instrumentalness; }
    public float getLiveness() { return liveness; }
    public float getValence() { return valence; }
    public float getTempo() { return tempo; }
    public int getKey() { return key; }
    public int getMode() { return mode; }
    public String getAudioUrl() { return audioUrl; }
    public String getPreviewUrl() { return previewUrl; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setArtists(String artists) { this.artists = artists; }
    public void setAlbum(String album) { this.album = album; }
    public void setTitle(String title) { this.title = title; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setPopularity(int popularity) { this.popularity = popularity; }
    public void setDuration(int duration) { this.duration = duration; }
    public void setExplicit(boolean explicit) { this.explicit = explicit; }
    public void setDanceability(float danceability) { this.danceability = danceability; }
    public void setEnergy(float energy) { this.energy = energy; }
    public void setLoudness(float loudness) { this.loudness = loudness; }
    public void setSpeechiness(float speechiness) { this.speechiness = speechiness; }
    public void setAcousticness(float acousticness) { this.acousticness = acousticness; }
    public void setInstrumentalness(float instrumentalness) { this.instrumentalness = instrumentalness; }
    public void setLiveness(float liveness) { this.liveness = liveness; }
    public void setValence(float valence) { this.valence = valence; }
    public void setTempo(float tempo) { this.tempo = tempo; }
    public void setKey(int key) { this.key = key; }
    public void setMode(int mode) { this.mode = mode; }
    public void setAudioUrl(String audioUrl) { this.audioUrl = audioUrl; }
    public void setPreviewUrl(String previewUrl) { this.previewUrl = previewUrl; }
}
