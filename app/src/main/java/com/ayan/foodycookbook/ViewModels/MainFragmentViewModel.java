package com.ayan.foodycookbook.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ayan.foodycookbook.LoadingComplete;
import com.ayan.foodycookbook.Model.Meal;
import com.ayan.foodycookbook.Model.MealsObject;
import com.ayan.foodycookbook.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragmentViewModel extends AndroidViewModel {

    private MutableLiveData<List<Meal>> randomMeal= new MutableLiveData();
    private MutableLiveData<List<Meal>> searchResults= new MutableLiveData();
    private MainFragmentViewModel instance=null;
    public LoadingComplete loadingComplete;
    boolean randomDataLoaded=false;
    public MainFragmentViewModel(@NonNull @org.jetbrains.annotations.NotNull Application application) {
        super(application);
    }
    public LiveData<List<Meal>> getRandomMeal(){
        if(!randomDataLoaded){
            new Thread(){
                @Override
                public void run() {
                    Call<MealsObject> request = RetrofitClient.getClient(getApplication().getApplicationContext())
                            .getRandomMeal();
                    request.enqueue(new Callback<MealsObject>() {
                        @Override
                        public void onResponse(Call<MealsObject> call, Response<MealsObject> response) {
                            Log.e("Response",response.body().toString());
                            randomMeal.postValue(response.body().getMeals());
                            randomDataLoaded=true;
                        }

                        @Override
                        public void onFailure(Call<MealsObject> call, Throwable t) {
                            Log.e("ErrorDat",t.getLocalizedMessage());
                            //Toast.makeText(this,"Error Getting data",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }.start();
        }
        return randomMeal;
    }

    public LiveData<List<Meal>> getSearchResults(){
        return searchResults;
    }

    public void searchKeyword(String key){
        new Thread(){
            @Override
            public void run() {
                super.run();
                Call<MealsObject> request=RetrofitClient.getClient(getApplication().getApplicationContext())
                        .getSearchResults(key);
                request.enqueue(new Callback<MealsObject>() {
                    @Override
                    public void onResponse(Call<MealsObject> call, Response<MealsObject> response) {
                        searchResults.postValue(response.body().meals);
                    }

                    @Override
                    public void onFailure(Call<MealsObject> call, Throwable t) {
                        Log.e("Error Getting Data","Yes");
                    }
                });
            }
        }.start();
    }

}
