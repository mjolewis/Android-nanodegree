package com.projectpico.popularmovies.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projectpico.popularmovies.R;
import com.projectpico.popularmovies.model.Trailers;
import com.squareup.picasso.Picasso;

/**********************************************************************************************************************
 * Adapters provide a binding from an app-specific data set to views that are displayed within a RecyclerView.
 *
 * @author mlewis
 * @version May 3, 2020
 *********************************************************************************************************************/
public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.ViewHolder> {
    private Context context;
    private Trailers trailers;
    private static final String TAG = TrailersAdapter.class.getSimpleName();

    public TrailersAdapter(Context context, Trailers trailers) {
        this.context = context;
        this.trailers = trailers;
    }

    @NonNull
    @Override
    public TrailersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trailers_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailersAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "Element " + position + " set.");

        final Trailers.Trailer trailer = trailers.getResults().get(position);

        Picasso.get()
                .load(buildTrailerUrl(trailer.getKey()))
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.videoThumbnail);

        holder.videoTitle.setText(trailer.getName());
    }

    /**
     * public int getItemCount()
     *  Accessor method that retrieves the number of items in this Adapter.
     * @return int
     *  The number of items in this Adapter.
     */
    @Override
    public int getItemCount() {
        return trailers.getResults().size();
    }

    private String buildTrailerUrl(String trailerId) {
        return "https://img.youtube.com/vi/" + trailerId + "/hqdefault.jpg";
    }

    /******************************************************************************************************************
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView. RecyclerViews
     * should subclass ViewHolder and add fields for caching potentially expensive View.findViewById(int) results.
     *
     * @author mlewis
     * @version May 3, 2020
     *****************************************************************************************************************/
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView videoThumbnail;
        public TextView videoTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.videoThumbnail = itemView.findViewById(R.id.cv_trailer);
            this.videoTitle = itemView.findViewById(R.id.tv_trailer_title);
        }
    }
}
