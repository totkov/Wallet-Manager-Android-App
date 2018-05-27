package com.example.virus_000.moviesapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomCursorAdapter extends CursorAdapter implements View.OnClickListener {

    private DataBase db;
    private Cursor cursor;
    private Context context;

    public CustomCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
        db = new DataBase(context);
        cursor = c;
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).
                inflate(R.layout.activity_custom_list, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView movieName = (TextView)
                view.findViewById(R.id.nameTextView);
        TextView movieGenre = (TextView)
                view.findViewById(R.id.genreTextView);

        int columNameIndex = cursor.getColumnIndex
                (DataBase.MOVIE_NAME);
        String movieNameTxt = cursor.getString(columNameIndex);
        movieName.setText(movieNameTxt);

        movieGenre.setText(cursor.getString(
                cursor.getColumnIndex(DataBase.GENRE)));

        ImageView editImage = (ImageView)
                view.findViewById(R.id.editImageView);
        ImageView deleteImage = (ImageView)
                view.findViewById(R.id.deleteImageView);

        editImage.setOnClickListener(this);
        deleteImage.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.deleteImageView) {
            db.deleteMovie(cursor.getInt(
                    cursor.getColumnIndex(DataBase.MOVIE_ID)));
        } else {
            int id = cursor.getInt(
                    cursor.getColumnIndex(DataBase.MOVIE_ID));

            Intent intent = new Intent(context, AddMovieActivity.class);
            intent.putExtra("id", id);
            context.startActivity(intent);

        }

        cursor = db.getAllMovies();
        changeCursor(cursor);
    }
}