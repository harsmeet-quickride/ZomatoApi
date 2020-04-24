package com.example.zomato.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomato.R;
import com.example.zomato.databinding.RestaurantItemBinding;
import com.example.zomato.db.Restaurant;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    private RestaurantAdapterListener listener;
    private List<Restaurant> restaurants;

    public RestaurantAdapter() {
    }

    public void setListener(RestaurantAdapterListener listener) {
        this.listener = listener;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        if (restaurants != null) {
            this.restaurants = restaurants;
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
        viewHolder.binding.setRestaurant(restaurants.get(i));
        if (restaurants.get(i).isSaved()) {
            viewHolder.binding.ivSave.setImageResource(R.drawable.ic_saved);
        } else {
            viewHolder.binding.ivSave.setImageResource(R.drawable.ic_save);
        }
        viewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return restaurants == null ? 0 : restaurants.size();
    }


    public interface RestaurantAdapterListener {
        void onItemClicked(Restaurant restaurant);

        void onItemOptionsClicked(Restaurant restaurant, int position);
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
            if (listener == null) return;
            int index = this.getAdapterPosition();
            if (v instanceof FrameLayout) {
                listener.onItemOptionsClicked(restaurants.get(index), index);
            } else {
                listener.onItemClicked(restaurants.get(index));
            }
        }
    }
}
