package com.example.virus_000.moviesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataBase extends SQLiteOpenHelper {

    private static final String DB_NAME = "moviesl.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_MOVIES = "movies";
    public static final String MOVIE_ID = "_id";
    public static final String MOVIE_NAME = "mname";
    public static final String GENRE = "genre";
    public static final String MOVIE_DESC = "description";
    public static final String MOVIE_CAST = "cast";
    public static final String MOVIE_PIC = "picture";
    public static final String CREATE_TABLE_MOVIES =
            "CREATE TABLE " + TABLE_MOVIES + " (" +
                    "'" + MOVIE_ID + "' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "'" + MOVIE_NAME + "' TEXT NOT NULL, " +
                    "'" + GENRE + "' TEXT NOT NULL, " +
                    "'" + MOVIE_DESC + "' TEXT, " +
                    "'" + MOVIE_CAST + "' TEXT NOT NULL, " +
                    "'" + MOVIE_PIC + "' TEXT)";

    private static final String LOG_TAG = "DBException";
    SQLiteDatabase db;

    public DataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_MOVIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(sqLiteDatabase);
    }

    public void insertNewMovie(Movie movie) {
        try {
            db = getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put(MOVIE_NAME, movie.getMname());
            cv.put(GENRE, movie.getGenre());
            cv.put(MOVIE_DESC, movie.getDesc());
            cv.put(MOVIE_CAST, movie.getCast());

            db.insertOrThrow(TABLE_MOVIES, null, cv);
        } catch (SQLException e) {
            Log.e(LOG_TAG, e.getMessage());
        } finally {
            if (db != null)
                db.close();
        }
    }

    public Cursor getAllMovies() {

        try {
            db = this.getReadableDatabase();

            String query = "SELECT " + MOVIE_NAME + ","
                    + GENRE + ","
                    + MOVIE_ID + " FROM " + TABLE_MOVIES;

            return db.rawQuery(query, null);
        } catch (SQLException e) {
            Log.e(LOG_TAG, e.getMessage());
        }

        return null;
    }

    public Cursor getAllMoviesByGenre(String genre) {

        try {
            db = this.getReadableDatabase();

            String query = "SELECT " + MOVIE_NAME + ","
                    + GENRE + ","
                    + MOVIE_ID + " FROM " + TABLE_MOVIES + " WHERE " + GENRE + " = " + "'" + genre + "'";

            return db.rawQuery(query, null);
        } catch (SQLException e) {
            Log.e(LOG_TAG, e.getMessage());
        }

        return null;
    }

    public void deleteMovie(int id) {
        db = this.getWritableDatabase();
        db.delete(TABLE_MOVIES, MOVIE_ID + " = ?",
                new String[]{String.valueOf(id)});

        db.close();
    }

    public Movie getMovie(int id) {

        Movie result = null;

        db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_MOVIES +
                " WHERE " + MOVIE_ID + " = " + id;

        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            result = new Movie();

            result.setMname(c.getString(c.getColumnIndex(MOVIE_NAME)));
            result.setGenre(c.getString(c.getColumnIndex(GENRE)));
            result.setDesc(c.getString(c.getColumnIndex(MOVIE_DESC)));
            result.setCast(c.getString(c.getColumnIndex(MOVIE_CAST)));
            result.setPicture(c.getString(c.getColumnIndex(MOVIE_PIC)));
            result.setId(id);
        }
        return result;
    }

    public void updateMovie(Movie movie) {
        db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(MOVIE_NAME, movie.getMname());
        cv.put(GENRE, movie.getGenre());
        cv.put(MOVIE_DESC, movie.getDesc());
        cv.put(MOVIE_CAST, movie.getCast());
        cv.put(MOVIE_PIC, movie.getPicture());

        db.update(TABLE_MOVIES, cv, "_id=" + movie.getId(), null);
    }
}

