package com.example.virus_000.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addMovieButton = (Button) findViewById(R.id.addMovieButton);
        Button showMoviesButton = (Button) findViewById(R.id.showMoviesButton);

        addMovieButton.setOnClickListener(this);
        showMoviesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.addMovieButton:
                intent = new Intent(MainActivity.this,
                        AddMovieActivity.class);
                break;
            case R.id.showMoviesButton:
                intent = new Intent(MainActivity.this,
                        MovieListActivity.class);
                break;
        }
        startActivity(intent);
    }
}
