package com.ayan.foodycookbook.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.ayan.foodycookbook.Model.Meal;
import com.ayan.foodycookbook.Room.MealsRepository;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FavouriteFragmentViewModel extends AndroidViewModel {
    MutableLiveData<List<Meal>> favouriteMeals=new MutableLiveData<>();
    private MealsRepository repository;
    public FavouriteFragmentViewModel(@NonNull @NotNull Application application) {
        super(application);
        repository=new MealsRepository(application);
    }

    public MutableLiveData<List<Meal>> getFavourites(){
        new Thread(){
            @Override
            public void run() {
                favouriteMeals.postValue(repository.getFavourites());
                //Log.e("FavouriteMeals",new Gson().toJson(favouriteMeals.getValue().toString()));
            }
        }.start();
        return favouriteMeals;
    }
}
