package com.example.zomato.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.zomato.R;
import com.example.zomato.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private final FragmentManager mFragmentManager = getSupportFragmentManager();
    private ActivityMainBinding mBinding;
    private HomeFragment mHomeFragment;
    private SavedFragment mSavedFragment;
    private int mCurrentFragment = 0;

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            if (mCurrentFragment == menuItem.getItemId()) {
                return false;
            }
            mCurrentFragment = menuItem.getItemId();

            switch (menuItem.getItemId()) {

                case R.id.navigation_home:
                    if (mHomeFragment == null) {
                        mHomeFragment = HomeFragment.newInstance();
                    }
                    mFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, mHomeFragment)
                            .commit();
                    return true;

                case R.id.navigation_fav:
                    if (mSavedFragment == null) {
                        mSavedFragment = SavedFragment.newInstance();
                    }
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, mSavedFragment)
                            .commit();

                    return true;
            }
            return false;
        }
    };

    private void setupToolbar() {

        setSupportActionBar(mBinding.toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.app_name));

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setupToolbar();

        if (savedInstanceState == null) {
            // Add a default fragment
            mHomeFragment = HomeFragment.newInstance();
            mFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, mHomeFragment)
                    .commit();
        }

    }
}
