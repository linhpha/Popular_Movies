package com.example.phamlinh.popularmovie;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetMovieRequest extends AsyncTask<Void, Void, Movie[]> {

    private OnTaskCompleted listener;
    public static final String REQUEST_METHOD = "GET";
    public static final String RESULTS = "results";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;

    public static final String ORIGINAL_TITLE = "original_title";
    public static final String POSTER_PATH = "poster_path";
    public static final String OVERVIEW = "overview";
    public static final String VOTE_AVERAGE = "vote_average";
    public static final String RELEASE_DATE = "release_date";
    public String api_key;

    public GetMovieRequest(String api_key, OnTaskCompleted listener) {
        this.api_key = api_key;
        this.listener = listener;
    }


    @Override
    protected Movie[] doInBackground(Void... voids) {
        String result;
        String inputLine;
        try {
            //Create a URL object holding our url
            URL myUrl = getUrl();
            //Create a connection
            HttpURLConnection connection =(HttpURLConnection)
                    myUrl.openConnection();

            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.connect();

            InputStreamReader streamReader = new
                    InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }
            reader.close();
            streamReader.close();
            result = stringBuilder.toString();
            Log.e("Test", result);
            return parseMovieData(result);

        } catch(IOException e){
            e.printStackTrace();
        }

        return null;
    }


    public Movie[] parseMovieData(String movieJson) {
        JSONObject moviesJson;
        JSONArray resultsArray;
        Movie[] movies;
        try {
            moviesJson = new JSONObject(movieJson);
            resultsArray = moviesJson.getJSONArray(RESULTS);
            movies = new Movie[resultsArray.length()];
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        for (int i = 0; i < resultsArray.length(); i++) {
            try {
                JSONObject movie = resultsArray.getJSONObject(i);

                Movie currentMovie = new Movie();
                currentMovie.setTitle(movie.getString(ORIGINAL_TITLE));
                currentMovie.setImageUrl(movie.getString(POSTER_PATH));
                Log.e("Title", movie.getString(RELEASE_DATE));
                currentMovie.setDescription(movie.getString(OVERVIEW));
                currentMovie.setVoteAverage(movie.getDouble(VOTE_AVERAGE));
                currentMovie.setReleaseDate(movie.getString(RELEASE_DATE));
                movies[i] = currentMovie;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return movies;
    }

    public URL getUrl() {
        final String TMDB_BASE_URL = "https://api.themoviedb.org/3/discover/movie?";
        final String SORT_BY_PARAM = "sort_by";
        final String API_KEY_PARAM = "api_key";
        Uri movieUri = Uri.parse(TMDB_BASE_URL).buildUpon().appendQueryParameter(API_KEY_PARAM, api_key).build();

        try {
            return new URL(movieUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Movie[] movies) {
        super.onPostExecute(movies);
        Log.e("Test", movies.toString());
        listener.onTaskCompleted(movies);
    }
}
