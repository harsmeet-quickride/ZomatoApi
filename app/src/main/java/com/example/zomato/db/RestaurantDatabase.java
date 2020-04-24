package com.example.zomato.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Restaurant.class}, version = 1)
public abstract class RestaurantDatabase extends RoomDatabase {

    public abstract RestaurantDao restaurantDao();

    private static volatile RestaurantDatabase INSTANCE;

    static RestaurantDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RestaurantDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RestaurantDatabase.class, "restaurant_db")
                            .fallbackToDestructiveMigration()
                            .addCallback(mRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback mRoomDatabaseCallback =
            new Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final RestaurantDao mDao;

        PopulateDbAsync(RestaurantDatabase db) {
            mDao = db.restaurantDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            //mDao.deleteUnsaved();
            return null;
        }
    }
}
