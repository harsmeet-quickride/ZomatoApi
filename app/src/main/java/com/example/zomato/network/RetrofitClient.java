package com.example.zomato.network;

import com.example.zomato.BuildConfig;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rajat Sangrame on 22/04/20.
 * http://github.com/rajatsangrame
 */
public class RetrofitClient {

    private static final String NEWS_API_URL = "https://developers.zomato.com/api/v2.1/";

    private static final Object LOCK = new Object();
    private static RetrofitApi sNewsApi;
    private static RetrofitClient sInstance;

    public static RetrofitApi getInstance() {

        if (sInstance == null || sNewsApi == null) {
            synchronized (LOCK) {

                Interceptor interceptor = new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        HttpUrl originalHttpUrl = original.url();

                        HttpUrl url = originalHttpUrl.newBuilder()
                                .addQueryParameter("api_key", BuildConfig.ApiKey)
                                .build();

                        Request.Builder requestBuilder = original.newBuilder()
                                .url(url);

                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                };

                // Building OkHttp client
                OkHttpClient client = new OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .build();


                // Retrofit Builder
                Retrofit.Builder builder =
                        new Retrofit
                                .Builder()
                                .baseUrl(NEWS_API_URL)
                                .client(client)
                                .addConverterFactory(GsonConverterFactory.create(new Gson()));

                // Set NewsApi instance
                sNewsApi = builder.build().create(RetrofitApi.class);
                sInstance = new RetrofitClient();
            }
        }
        return sNewsApi;
    }


    // Make sure this class never initialize
    private RetrofitClient() {
    }

}
