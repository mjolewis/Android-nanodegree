package com.projectpico.popularmovies.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projectpico.popularmovies.R;
import com.projectpico.popularmovies.model.Reviews;

/**********************************************************************************************************************
 * Adapters provide a binding from an app-specific data set to views that are displayed within a RecyclerView.
 *
 * @author mlewis
 * @version May 3, 2020
 *********************************************************************************************************************/
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private Reviews reviews;
    private static final String TAG = ReviewAdapter.class.getSimpleName();

    /**
     * public ReviewAdapter(Reviews reviews)
     *  Initializes a new ReviewAdapter.
     * @param reviews
     *  A reference to the Reviews model.
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for this new ReviewAdapter.
     */
    public ReviewAdapter(Reviews reviews) {
        this.reviews = reviews;
    }

    /**
     * public ReviewAdapter.ViewHolder onCreate(@NonNull ViewGroup parent, int viewType)
     *  Creates new views (invoked by the LayoutManager).
     * @param parent
     *  The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType
     *  The view type of the new View.
     * @return ReviewAdapter.ViewHolder
     *  A new ViewHolder that holds a View of the given view type.
     * @exception OutOfMemoryError
     *  Indicates insufficient memory for this new MovieViewHolder.
     */
    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_list_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder holder, int position)
     *  Replaces the contents of a view (invoked by the LayoutManager).
     * @param holder
     *  The ViewHolder that should be updated to represent the contents of the item at the given position in the data
     *  set.
     * @param position
     *  The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "Element " + position + " set.");

        final Reviews.Review review = reviews.getResults().get(position);

        holder.reviewAuthor.setText(review.getAuthor());
        holder.reviewContent.setText(review.getContent());
    }

    /**
     * public int getItemCount()
     *  Accessor method that retrieves the number of items in this Adapter.
     * @return int
     *  The number of items in this Adapter.
     */
    @Override
    public int getItemCount() {
        return reviews.getResults().size();
    }

    /******************************************************************************************************************
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView. RecyclerViews
     * should subclass ViewHolder and add fields for caching potentially expensive View.findViewById(int) results.
     *
     * @author mlewis
     * @version May 3, 2020
     *****************************************************************************************************************/
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView reviewAuthor;
        public TextView reviewContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.reviewAuthor = itemView.findViewById(R.id.tv_review_author);
            this.reviewContent = itemView.findViewById(R.id.tv_review_content);
        }
    }
}
