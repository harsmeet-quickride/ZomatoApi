package com.example.zomato.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.zomato.R;
import com.example.zomato.adapter.ViewPagerAdapter;
import com.example.zomato.databinding.FragmentHomeBinding;
import com.example.zomato.utils.SortRestaurant;
import com.google.android.material.tabs.TabLayout;

import static com.example.zomato.utils.Constant.CUISINE_DATA;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding mBinding;
    private static final String PARAM_1 = "QUERY";
    private String QUERY = "";
    private ViewPagerAdapter mPagerAdapter;

    public HomeFragment() {

        CUISINE_DATA.put(50, "North Indian");
        CUISINE_DATA.put(35, "Continental");
        CUISINE_DATA.put(85, "South Indian");
        CUISINE_DATA.put(25, "Chinese");
        CUISINE_DATA.put(143, "Healthy");
        CUISINE_DATA.put(49, "Hyderabadi");

    }

    public static HomeFragment newInstance(String query) {

        HomeFragment fragment = new HomeFragment();
        if (query == null) {
            return fragment;
        }
        Bundle args = new Bundle();
        args.putString(PARAM_1, query);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            QUERY = getArguments().getString(PARAM_1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_home, container, false);

        if (getActivity() != null) {
            mPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), QUERY, CUISINE_DATA);
            mBinding.viewpager.setAdapter(mPagerAdapter);
            mBinding.viewpager.setOffscreenPageLimit(1);
            mBinding.tablayout.setupWithViewPager(mBinding.viewpager);
            setupTabIcons();
        }
        return mBinding.getRoot();
    }

    private void setupTabIcons() {
        TabLayout.Tab tab;
        for (int i = 0; i < CUISINE_DATA.size(); i++) {
            tab = mBinding.tablayout.getTabAt(i);
            if (tab != null) {
                String value = CUISINE_DATA.valueAt(i);
                tab.setText(value);
            }
        }
    }

    public void setSort(SortRestaurant sort) {
        if (mPagerAdapter != null) {
            mPagerAdapter.setSort(sort);
        }
    }

}
