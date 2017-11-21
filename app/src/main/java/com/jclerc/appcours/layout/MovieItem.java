package com.jclerc.appcours.layout;

import android.view.View;

import com.jclerc.appcours.R;
import com.jclerc.appcours.request.MovieSearchItem;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

public class MovieItem extends AbstractItem<MovieItem, MovieList> {

    private MovieSearchItem movie;

    public MovieSearchItem getMovie() {
        return movie;
    }

    public void setMovie(MovieSearchItem movie) {
        this.movie = movie;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.row_movie;
    }

    @Override
    public MovieList getViewHolder(View v) {
        return new MovieList(v);
    }

    @Override
    public void bindView(MovieList holder, List<Object> payloads) {
        super.bindView(holder, payloads);

        holder.fill(movie);
    }
}
