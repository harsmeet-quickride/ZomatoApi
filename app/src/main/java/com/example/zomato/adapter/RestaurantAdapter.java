package com.example.zomato.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomato.R;
import com.example.zomato.databinding.RestaurantItemBinding;
import com.example.zomato.db.Restaurant;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    private final RestaurantAdapterListener listener;
    private List<Restaurant> restaurants;
    private LayoutInflater layoutInflater;

    public RestaurantAdapter(List<Restaurant> articles, RestaurantAdapterListener listener) {
        this.restaurants = articles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        RestaurantItemBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.restaurant_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.binding.setRestaurant(restaurants.get(i));
        viewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return restaurants == null ? 0 : restaurants.size();
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        if (restaurants != null) {
            this.restaurants = restaurants;
            notifyDataSetChanged();
        }
    }

    public interface RestaurantAdapterListener {
        void onItemClicked(Restaurant article);

        void onItemOptionsClicked(Restaurant article);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final RestaurantItemBinding binding;

        public ViewHolder(final RestaurantItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener == null) return;
            int index = this.getAdapterPosition();
            if (v instanceof ImageView) {
                listener.onItemOptionsClicked(restaurants.get(index));
            } else {
                listener.onItemClicked(restaurants.get(index));
            }
        }
    }
}
