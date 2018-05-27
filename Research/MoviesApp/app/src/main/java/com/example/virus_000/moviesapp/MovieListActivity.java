package com.example.virus_000.moviesapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class MovieListActivity extends AppCompatActivity implements View.OnClickListener {

    ListView MovieListView;
    DataBase db = new DataBase(this);
    CustomCursorAdapter adapter;
    Cursor cursor;
    Button filterButton;
    EditText filterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        MovieListView = (ListView) findViewById(R.id.MovieListView);
        filterText = (EditText) findViewById(R.id.filter_text);
        filterButton = (Button) findViewById(R.id.filter_button);
        filterButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAllMovies();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == filterButton.getId()){
            String genre = filterText.getText().toString();

            if(!genre.isEmpty()){
                cursor = db.getAllMoviesByGenre(genre);
                adapter = new CustomCursorAdapter(this, cursor);
                MovieListView.setAdapter(adapter);
            }else {
                loadAllMovies();
            }
        }
    }

    private void loadAllMovies(){
        cursor = db.getAllMovies();
        adapter = new CustomCursorAdapter(this, cursor);
        MovieListView.setAdapter(adapter);
    }
}
