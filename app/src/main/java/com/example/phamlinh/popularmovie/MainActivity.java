package com.example.phamlinh.popularmovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.movieGridView);
        gridView.setOnItemClickListener(movieDetailsListener);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), position + "", Toast.LENGTH_LONG).show();
//                Log.e("Test", position + " ");
//            }
//        });
//        Movie[] movies = getMovies();
//        gridView.setAdapter(new MovieAdapter(this, movies));
        getMovies();
    }


    public void getMovies() {
        String api_key = getString(R.string.api_key);
        OnTaskCompleted taskCompleted = new OnTaskCompleted() {
            @Override
            public void onTaskCompleted(Movie[] movies) {
                gridView.setAdapter(new MovieAdapter(getApplicationContext(), movies));
            }
        };
        GetMovieRequest movieRequest = new GetMovieRequest(api_key, taskCompleted);
        movieRequest.execute();
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_by, menu);
        return true;
    }

    public GridView.OnItemClickListener movieDetailsListener = new GridView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Movie movie = (Movie) parent.getItemAtPosition(position);
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra(getString(R.string.grid_movie_detail), movie);
            startActivity(intent);

            Log.e("movie", movie.getTitle());

        }
    };
}