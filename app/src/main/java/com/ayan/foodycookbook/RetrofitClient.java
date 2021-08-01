package com.ayan.foodycookbook;

import android.content.Context;
import android.util.Log;

import com.readystatesoftware.chuck.ChuckInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit=null;
    private static APIS service=null;

    public static APIS getClient(Context context){
        if(retrofit==null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new ChuckInterceptor(context)
                    .showNotification(true))
                    .build();
            retrofit =new Retrofit.Builder()
                    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            Log.e("RetofitInitialised","true");
        }
        if(service==null){
            service=retrofit.create(APIS.class);
        }
        return service;
    }

}
