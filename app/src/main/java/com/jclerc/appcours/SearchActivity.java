package com.jclerc.appcours;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.jclerc.appcours.layout.MovieItem;
import com.jclerc.appcours.request.MovieSearchItem;
import com.jclerc.appcours.request.MovieSearchList;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.neopixl.spitfire.listener.RequestListener;
import com.neopixl.spitfire.request.BaseRequest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.searchInput)
    EditText searchInput;
    @BindView(R.id.cancelButton)
    Button cancelButton;
    @BindView(R.id.searchButton)
    Button searchButton;
    @BindView(R.id.resultsView)
    RecyclerView resultsView;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        requestQueue = Volley.newRequestQueue(this);
    }

    @OnClick(R.id.cancelButton)
    public void cancelSearch() {
        Log.d("click", "cancel");
        searchInput.setText("");
    }

    @OnClick(R.id.searchButton)
    public void startSearch() {
        String search = searchInput.getText().toString();
        Log.d("click", search);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("api_key", "c1ac741d5dd740f9861e794c5363b0c2");
        parameters.put("query", search);

        BaseRequest<MovieSearchList> request = new BaseRequest.Builder<>(
                Request.Method.GET,
                "https://api.themoviedb.org/3/search/movie",
                MovieSearchList.class
        )
                .parameters(parameters)
                .listener(new RequestListener<MovieSearchList>() {
                    @Override
                    public void onSuccess(Request request, NetworkResponse response, MovieSearchList result) {
                        Toast.makeText(SearchActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                        displayResult(result);
                    }

                    @Override
                    public void onFailure(Request request, NetworkResponse response, VolleyError error) {
                        Toast.makeText(SearchActivity.this, "FAIL", Toast.LENGTH_SHORT).show();
                    }
                }).build();

        requestQueue.add(request);
    }

    private void displayResult(MovieSearchList list) {
        Toast.makeText(this, "Total: " + list.getTotal_results(), Toast.LENGTH_SHORT).show();

        ItemAdapter<MovieItem> itemAdapter = new ItemAdapter<>();
        FastAdapter fastAdapter = FastAdapter.with(itemAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false);

        resultsView.setAdapter(fastAdapter);
        resultsView.setLayoutManager(layoutManager);

        TreeMap<Double, MovieSearchItem> map = new TreeMap<>(Collections.reverseOrder());

        for (MovieSearchItem movie : list.getResults()) {
            map.put(movie.getVote_average(), movie);
        }

        for (Map.Entry<Double, MovieSearchItem> entry : map.entrySet()) {
            MovieItem item = new MovieItem();
            item.setMovie(entry.getValue());
            itemAdapter.add(item);
        }
    }

}
