package com.example.zomato.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.zomato.R;
import com.example.zomato.adapter.ViewPagerAdapter;
import com.example.zomato.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding mBinding;
    private final String[] CUISINE = {
            "North Indian",
            "Continental",
            "South Indian",
            "Chinese",
            "Healthy",
            "Hyderabadi"
    };

    private final String[] CUISINE_ID = {
            "50",
            "35",
            "85",
            "25",
            "143",
            "49"
    };

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_home, container, false);

        if (getActivity() != null) {
            ViewPagerAdapter viewPager = new ViewPagerAdapter(getChildFragmentManager(), CUISINE_ID);
            mBinding.viewpager.setAdapter(viewPager);
            //mBinding.viewpager.setOffscreenPageLimit(1);
            mBinding.tablayout.setupWithViewPager(mBinding.viewpager);
            setupTabIcons();
        }
        return mBinding.getRoot();
    }

    private void setupTabIcons() {
        TabLayout.Tab tab;
        for (int i = 0; i < CUISINE.length; i++) {
            tab = mBinding.tablayout.getTabAt(i);
            if (tab != null) {
                tab.setText(CUISINE[i]);
            }
        }
    }


}
