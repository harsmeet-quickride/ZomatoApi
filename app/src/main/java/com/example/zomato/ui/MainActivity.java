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
import com.example.zomato.utils.SortRestaurant;
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
    private SortBottomSheet mBottomSheet;
    private SortBottomSheet.SortBottomSheetListener mBottomSheetListener
            = new SortBottomSheet.SortBottomSheetListener() {
        @Override
        public void processSort(SortRestaurant sort) {
            if (sort != null) {
                mHomeFragment.setSort(sort);
            }
        }
    };

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

                    toggleHome();
                    if (mHomeFragment == null) {
                        mHomeFragment = HomeFragment.newInstance(QUERY);
                    }
                    mFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, mHomeFragment)
                            .commit();

                    return true;

                case R.id.navigation_search:

                    toggleSearch();
                    mFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, mSearchFragment)
                            .commit();

                    return true;

                case R.id.navigation_saved:

                    toggleSaved();
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
        setupToolbar();
        mBinding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mBinding.navigation.setSelectedItemId(R.id.navigation_search);
        mBottomSheet = SortBottomSheet.getInstance();
        mBottomSheet.setListener(mBottomSheetListener);

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
        mBinding.fabSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mHomeFragment != null) {
                    mBottomSheet.show(getSupportFragmentManager(), mBottomSheet.getTag());
                }
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

            mHomeFragment = HomeFragment.newInstance(QUERY);
            mFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, mHomeFragment)
                    .commit();

            toggleSearchQuery();

        }
    }

    private void toggleHome() {
        mBinding.fragmentContainer.setVisibility(View.VISIBLE);
        mBinding.searchContainer.setVisibility(View.GONE);
        mBinding.fabSort.setVisibility(View.VISIBLE);
    }

    private void toggleSearch() {
        mBinding.fragmentContainer.setVisibility(View.GONE);
        mBinding.searchContainer.setVisibility(View.VISIBLE);
        mBinding.fabSort.setVisibility(View.GONE);

        mBinding.etSearch.setText("");

    }

    private void toggleSaved() {
        mBinding.fragmentContainer.setVisibility(View.VISIBLE);
        mBinding.searchContainer.setVisibility(View.GONE);
        mBinding.fabSort.setVisibility(View.GONE);
    }

    private void toggleSearchQuery() {

        mBinding.fragmentContainer.setVisibility(View.VISIBLE);
        mBinding.searchContainer.setVisibility(View.GONE);
        mBinding.navigation.setSelectedItemId(R.id.navigation_home);
        Helper.hideKeyboard(this);

    }

}
