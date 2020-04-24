package com.example.zomato.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomato.R;
import com.example.zomato.databinding.RestaurantItemBinding;
import com.example.zomato.db.Restaurant;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    private RestaurantAdapterListener mListener;
    private List<Restaurant> mRestaurants;
    private boolean mIsSavedFragment = false;

    public RestaurantAdapter() {
    }

    public void setListener(RestaurantAdapterListener mListener) {
        this.mListener = mListener;
    }

    public void setIsSavedFragment(boolean isSavedFragment) {
        this.mIsSavedFragment = isSavedFragment;
    }

    public void setRestaurants(List<Restaurant> mRestaurants) {
        if (mRestaurants != null) {
            this.mRestaurants = mRestaurants;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RestaurantItemBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.restaurant_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.binding.setRestaurant(mRestaurants.get(i));
        if (mRestaurants.get(i).isSaved()) {
            viewHolder.binding.ivSave.setImageResource(R.drawable.ic_saved);
        } else {
            viewHolder.binding.ivSave.setImageResource(R.drawable.ic_save);
        }
        if (mIsSavedFragment) {
            viewHolder.binding.tvCuisine.setVisibility(View.VISIBLE);
        }
        viewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mRestaurants == null ? 0 : mRestaurants.size();
    }


    public interface RestaurantAdapterListener {
        void onItemClicked(Restaurant restaurant);

        void onItemOptionsClicked(Restaurant restaurant);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final RestaurantItemBinding binding;

        public ViewHolder(final RestaurantItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
            this.binding.btnSave.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener == null) return;
            int index = this.getAdapterPosition();
            if (v instanceof FrameLayout) {
                mListener.onItemOptionsClicked(mRestaurants.get(index));
            } else {
                mListener.onItemClicked(mRestaurants.get(index));
            }
        }
    }
}
