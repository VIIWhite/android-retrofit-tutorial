package edu.uci.swe264p.retrofit;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private List<TopRatedResponse> mData;

    MovieListAdapter(List<TopRatedResponse> data) {
        this.mData = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, releaseDate, overview, vote;
        //public Float vote;
        public ImageView poster;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            releaseDate = itemView.findViewById(R.id.tvReleaseDate);
            overview = itemView.findViewById(R.id.tvOverview);
            vote = itemView.findViewById(R.id.tvVote);
            poster = itemView.findViewById(R.id.ivMovie);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TopRatedResponse movie = mData.get(position);
        holder.title.setText(movie.getTitle());
        holder.releaseDate.setText(movie.getReleaseDate());
        holder.overview.setText(movie.getOverview());
        holder.vote.setText(movie.getVoteAverage().toString());
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
