package com.example.virus_000.moviesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class AddMovieActivity extends AppCompatActivity implements View.OnClickListener {

    Button addButton;
    ImageView picture;
    EditText movieNameAddText;
    EditText genreAddText;
    EditText descAddText;
    EditText castAddText;
    Button cancelButton;

    DataBase db = new DataBase(this);
    Movie movie = null;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        movieNameAddText = (EditText) findViewById(R.id.MovieNameAddText);
        genreAddText = (EditText) findViewById(R.id.GenreAddText);
        descAddText = (EditText) findViewById(R.id.DescAddText);
        castAddText = (EditText) findViewById(R.id.CastAddText);
        picture = (ImageView) findViewById(R.id.avatarImageView);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        addButton = (Button) findViewById(R.id.AddButton);
        addButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        id = getIntent().getIntExtra("id", -1);

        if (id > 0) {
            movie = db.getMovie(id);
            movieNameAddText.setText(movie.getMname());
            genreAddText.setText(movie.getGenre());
            descAddText.setText(movie.getDesc());
            castAddText.setText(movie.getCast());
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == addButton.getId()) {
            String movieName = movieNameAddText.getText().toString();
            String movieGenre = genreAddText.getText().toString();
            String movieDesc = descAddText.getText().toString();
            String movieCast = castAddText.getText().toString();

            if (movie == null) {
                movie = new Movie(movieName, movieGenre, movieDesc, movieCast, "");
            }

            if (id > 0) {
                movie.setMname(movieName);
                movie.setGenre(movieGenre);
                movie.setDesc(movieDesc);
                movie.setCast(movieCast);

                db.updateMovie(movie);
            } else {
                db.insertNewMovie(movie);
            }
        } else {
            finish();
        }
    }
}
