package com.example.phamlinh.popularmovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    ImageView moviePoster;
    TextView movieDescription;
    TextView movieReleaseDate;
    TextView movieRating;
    TextView movieTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra(getString(R.string.grid_movie_detail));
        moviePoster = findViewById(R.id.moviePoster);
        movieDescription = findViewById(R.id.movieDescription);
        movieTitle = findViewById(R.id.movieTitle);
        movieReleaseDate = findViewById(R.id.releaseDate);
        movieRating = findViewById(R.id.voteAverage);
        Picasso.with(this).load(movie.getImageUrl()).into(moviePoster);
        movieDescription.setText(movie.getDescription());
        movieTitle.setText(movie.getTitle());
        movieRating.setText(movie.getVoteAverage().toString());
        movieReleaseDate.setText(movie.getReleaseDate());


    }
}
