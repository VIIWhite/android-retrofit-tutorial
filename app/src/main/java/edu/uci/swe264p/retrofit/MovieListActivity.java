package edu.uci.swe264p.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MovieListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovieListAdapter adapter;
    private List<TopRatedResponse> movieList = new ArrayList<>();

    static final String TAG = MovieListActivity.class.getSimpleName();
    static final String BASE_URL = "https://api.themoviedb.org/3/";
    static Retrofit retrofit = null;
    final static String API_KEY = "5f7cb29d489bbbb86ca6e6d90f422eb2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        recyclerView = findViewById(R.id.rvMovieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MovieListAdapter(movieList);
        recyclerView.setAdapter(adapter);

        fetchTopRatedMovies();
    }


    private void fetchTopRatedMovies() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        Call<TopRatedResponse> call = movieApiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<TopRatedResponse>() {
            @Override
            public void onResponse(Call<TopRatedResponse> call, Response<TopRatedResponse> response) {
                movieList.clear();
                movieList.addAll(response.body().getResults());
                adapter.notifyDataSetChanged();
//                TextView tv;
//                tv = findViewById(R.id.tvTitle);
//                tv.setText(response.body().getVoteAverage().toString());
            }

            @Override
            public void onFailure(Call<TopRatedResponse> call, Throwable throwable) {
                Log.e(TAG,  throwable.toString());
            }
        });
    }


}
