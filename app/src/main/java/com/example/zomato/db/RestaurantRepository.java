package com.example.zomato.db;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.zomato.utils.AppExecutor;
import com.example.zomato.utils.Constant;
import com.example.zomato.model.ApiResponse;
import com.example.zomato.model.RestaurantsItem;
import com.example.zomato.network.RetrofitApi;
import com.example.zomato.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */
public class RestaurantRepository {

    private static final String TAG = "RestaurantRepository";
    private RestaurantDao mRestaurantDao;
    private LiveData<List<Restaurant>> mRestaurantList;
    private static final Object LOCK = new Object();
    private static RestaurantRepository sInstance;
    private final MutableLiveData<List<Restaurant>> mRestaurantLiveData;
    private static RetrofitApi sApi;
    private final AppExecutor mExecutor;


    public synchronized static RestaurantRepository getInstance(Application context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new RestaurantRepository(context);
            }
        }
        return sInstance;
    }

    /**
     * Room Database is manged through this repository. Called form ViewModel
     *
     * @param application needed to create db instance
     */
    private RestaurantRepository(Application application) {
        RestaurantDatabase db = RestaurantDatabase.getDatabase(application);
        mRestaurantDao = db.restaurantDao();
        mRestaurantList = new MutableLiveData<>();
        mRestaurantLiveData = new MutableLiveData<>();
        mExecutor = AppExecutor.getInstance();
        sApi = RetrofitClient.getInstance();

        mRestaurantLiveData.observeForever(new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(final List<Restaurant> restaurants) {
                mExecutor.getDiskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        mRestaurantDao.bulkInsert(restaurants);
                    }
                });
            }
        });
    }

    public LiveData<List<Restaurant>> getRestaurantList(String query, String cuisinesIds) {

        sApi.search(query, Constant.start, Constant.count, Constant.lat, Constant.lon,
                Constant.radius, cuisinesIds).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                if (response.body() != null) {

                    List<RestaurantsItem> restaurantList = response.body().getRestaurants();
                    final List<Restaurant> dbList = Restaurant.getListFromResponse(restaurantList);
                    mRestaurantLiveData.setValue(dbList);

                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

                Log.i(TAG, "onFailure: ");
            }
        });
        return mRestaurantDao.getAllRestaurants();
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
