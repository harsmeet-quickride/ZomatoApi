package com.example.zomato.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.zomato.ui.RestaurantFragment;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final RestaurantFragment[] mRestaurantFragments;

    public ViewPagerAdapter(FragmentManager fm, String[] cuisine) {
        super(fm);
        mRestaurantFragments = new RestaurantFragment[cuisine.length];
        for (int i = 0; i < cuisine.length; i++) {
            mRestaurantFragments[i] = RestaurantFragment.newInstance(cuisine[i]);
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
