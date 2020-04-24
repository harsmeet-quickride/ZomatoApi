package com.example.zomato.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.zomato.R;
import com.example.zomato.databinding.ActivityMainBinding;
import com.example.zomato.db.RestaurantRepository;
import com.example.zomato.utils.Helper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private final FragmentManager mFragmentManager = getSupportFragmentManager();
    private ActivityMainBinding mBinding;
    private HomeFragment mHomeFragment;
    private SavedFragment mSavedFragment;
    private int mCurrentFragment = 0;
    private String QUERY = "";
    private Fragment mSearchFragment = new Fragment();

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
                    mBinding.fragmentContainer.setVisibility(View.VISIBLE);
                    mBinding.searchContainer.setVisibility(View.GONE);
                    if (mHomeFragment == null) {
                        mHomeFragment = HomeFragment.newInstance(QUERY);
                    }
                    mFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, mHomeFragment)
                            .commit();
                    return true;

                case R.id.navigation_search:
                    mBinding.fragmentContainer.setVisibility(View.GONE);
                    mBinding.searchContainer.setVisibility(View.VISIBLE);
                    mBinding.etSearch.setText("");

                    mFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, mSearchFragment)
                            .commit();
                    return true;

                case R.id.navigation_saved:
                    mBinding.fragmentContainer.setVisibility(View.VISIBLE);
                    mBinding.searchContainer.setVisibility(View.GONE);
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
        mBinding.navigation.setSelectedItemId(R.id.navigation_search);
        setupToolbar();

        mBinding.etSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    String query = mBinding.etSearch.getText().toString();
                    handleSearch(query);
                    return true;
                }

                return false;
            }
        });

    }

    private void handleSearch(String query) {

        if (query.isEmpty()) {
            Toast.makeText(this, "Field can't be empty", Toast.LENGTH_SHORT).show();
        } else if (!query.matches("[a-zA-Z.? ]*")) {
            Toast.makeText(this, "Please avoid special characters", Toast.LENGTH_SHORT).show();
        } else {

            QUERY = query;
            RestaurantRepository db = RestaurantRepository.getInstance(this);
            db.deleteUnsaved();

            mBinding.fragmentContainer.setVisibility(View.VISIBLE);
            mBinding.searchContainer.setVisibility(View.GONE);

            mHomeFragment = HomeFragment.newInstance(QUERY);
            mFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, mHomeFragment)
                    .commit();

            mBinding.navigation.setSelectedItemId(R.id.navigation_home);

            Helper.hideKeyboard(this);

        }
    }
}
