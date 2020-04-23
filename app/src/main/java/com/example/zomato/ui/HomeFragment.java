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
    private final String[] CATEGORIES = {
            "A",
            "BBB",
            "CCCC",
            "D",
            "EEEEEE",
            "FFFF",
            "GGG"
    };
    private final int[] CATEGORIES_ICON = {
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background

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
            ViewPagerAdapter viewPager = new ViewPagerAdapter(getChildFragmentManager(), CATEGORIES);
            mBinding.viewpagerHeadlines.setAdapter(viewPager);
            mBinding.tablayoutHeadlines.setupWithViewPager(mBinding.viewpagerHeadlines);
            setupTabIcons();
        }
        return mBinding.getRoot();
    }

    private void setupTabIcons() {
        TabLayout.Tab tab;
        for (int i = 0; i < CATEGORIES.length; i++) {
            tab = mBinding.tablayoutHeadlines.getTabAt(i);
            if (tab != null) {
                tab.setIcon(CATEGORIES_ICON[i]).setText(CATEGORIES[i]);
            }
        }
    }


}
