package edu.georgiasouthern.csci5530;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    public interface OnSongClickListener {
        void onSongClick(Song song);
    }

    private List<Song> songs;
    private OnSongClickListener listener;
    private Context context;

    public SongAdapter(Context context, List<Song> songs, OnSongClickListener listener) {
        this.context = context;
        this.songs = songs;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_item, parent, false);
        return new ViewHolder(view);
    }

    private Song selectedSong = null;

    public void setSelectedSong(Song song) {
        selectedSong = song;
        notifyDataSetChanged(); // Refresh list to show highlight
    }

    public void updateSongs(List<Song> newSongs) {
        this.songs = newSongs;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Song song = songs.get(position);
        holder.titleTextView.setText(song.getTitle());
        holder.artistTextView.setText(song.getArtists());
        holder.tempoTextView.setText("Tempo: " + song.getTempo() + " BPM");

        String mood = song.getTempo() < 90 ? "Chill" : song.getTempo() < 120 ? "Moderate" : "Hype";
        holder.moodTextView.setText("Mood: " + mood);

        // Highlight selected song
        if (song.equals(selectedSong)) {
            holder.itemView.setBackgroundColor(0xFF2E7D32); // Green highlight
        } else {
            holder.itemView.setBackgroundColor(0xFF1B1B1B); // Default background
        }

        holder.itemView.setOnClickListener(v -> listener.onSongClick(song));
    }


    @Override
    public int getItemCount() {
        return songs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, artistTextView, tempoTextView, moodTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            artistTextView = itemView.findViewById(R.id.artistTextView);
            tempoTextView = itemView.findViewById(R.id.tempoTextView);
            moodTextView = itemView.findViewById(R.id.moodTextView);
        }
    }
}





