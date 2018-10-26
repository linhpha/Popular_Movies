package com.example.phamlinh.popularmovie;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private Movie[] movies;

    public MovieAdapter(Context c, Movie[] movies) {
        this.context = c;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.length;
    }

    @Override
    public Object getItem(int position) {
        if (movies.length == 0 || movies[position] == null) {
            return null;
        }
        return movies[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        for (int i = 0; i < movies.length; i++) {
            Log.e("Testing movie", movies[i].toString());
        }
        Picasso.with(context).load(movies[position].getImageUrl()).error(R.drawable.not_found).into(imageView);
        return imageView;
    }
}
