package com.example.zomato.adapter;

import android.util.SparseArray;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.zomato.ui.RestaurantFragment;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final RestaurantFragment[] mRestaurantFragments;

    public ViewPagerAdapter(FragmentManager fm, String query, SparseArray<String> cuisine) {
        super(fm);
        mRestaurantFragments = new RestaurantFragment[cuisine.size()];
        for (int i = 0; i < cuisine.size(); i++) {
            int key = cuisine.keyAt(i);
            mRestaurantFragments[i] = RestaurantFragment.newInstance(query, cuisine.get(key));
        }
    }

    @Override
    public Fragment getItem(int i) {
        return mRestaurantFragments[i];
    }

    @Override
    public int getCount() {
        return mRestaurantFragments == null ? 0 : mRestaurantFragments.length;
    }
}
