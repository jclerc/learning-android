package com.jclerc.appcours.layout;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jclerc.appcours.request.MovieSearchItem;
import com.jclerc.appcours.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieList extends RecyclerView.ViewHolder {

    @BindView(R.id.titleText)
    TextView titleText;

    @BindView(R.id.scoreText)
    TextView scoreText;

    public MovieList(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void fill(MovieSearchItem movie) {
        titleText.setText(movie.getTitle());
        scoreText.setText(String.valueOf(movie.getVote_average()));
    }

}
