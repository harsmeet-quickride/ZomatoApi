package com.example.zomato.db;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */
public class RestaurantRepository {

    private RestaurantDao mRestaurantDao;
    private LiveData<List<Restaurant>> mRestaurantList;
    private static final String TAG = "RestaurantRepository";
    /**
     * Room Database is manged through this repository. Called form ViewModel
     *
     * @param application needed to create db instance
     */
    public RestaurantRepository(Application application) {
        RestaurantDatabase db = RestaurantDatabase.getDatabase(application);
        mRestaurantDao = db.restaurantDao();
        mRestaurantList = mRestaurantDao.getAllRestaurants();
    }

    public LiveData<List<Restaurant>> getmRestaurantList() {
        return mRestaurantList;
    }

    public Restaurant getRestaurantById(int id) {
        return mRestaurantDao.getRestaurant(id);
    }

    /**
     * Executes a background task to insert {@link Restaurant} in database
     *
     * @param restaurant instance of {@link Restaurant}
     */
    public void insert(Restaurant restaurant) {
        new insertAsyncTask(mRestaurantDao).execute(restaurant);
    }

    private static class insertAsyncTask extends AsyncTask<Restaurant, Void, Long> {

        private RestaurantDao mAsyncTaskDao;

        insertAsyncTask(RestaurantDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Long doInBackground(final Restaurant... params) {
            return mAsyncTaskDao.insert(params[0]);
        }

        /**
         * @param aLong id of {@link Restaurant} column. If failed return -1
         */
        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            Log.i(TAG, "onPostExecute Result: " + aLong);
        }
    }
}
