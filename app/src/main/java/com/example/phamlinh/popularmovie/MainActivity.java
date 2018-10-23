package com.example.phamlinh.popularmovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = findViewById(R.id.movieGridView);
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
        GetMovieRequest movieRequest = new GetMovieRequest(api_key);
        movieRequest.execute();


    }
}