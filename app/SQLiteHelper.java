package edu.georgiasouthern.csci5530;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "caribbeats.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "songs";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                "title TEXT, artist TEXT, bpm REAL, url TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", song.title);
        values.put("artist", song.artist);
        values.put("bpm", song.bpm);
        values.put("url", song.url);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Song> getSongs() {
        List<Song> songs = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(0);
                String artist = cursor.getString(1);
                double bpm = cursor.getDouble(2);
                String url = cursor.getString(3);
                songs.add(new Song(title, artist, bpm, url));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }
}
