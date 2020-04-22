package com.example.zomato;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final RestaurantFragment[] mRestaurantFragments;


    public ViewPagerAdapter(FragmentManager fm, String[] categories) {
        super(fm);
        mRestaurantFragments = new RestaurantFragment[categories.length];
        for (int i = 0; i < categories.length; i++) {
            mRestaurantFragments[i] = RestaurantFragment.newInstance(/*categories[i]*/);
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
