package com.ayan.foodycookbook.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.ayan.foodycookbook.Model.Meal;
import com.ayan.foodycookbook.Room.MealsRepository;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MealsActivityViewModel extends AndroidViewModel {
    public MutableLiveData<Boolean> isFavouriteMeal=new MutableLiveData<Boolean>();
    private MealsRepository repository;
    private Meal meal=null;
    MutableLiveData<List<Meal>> favouriteMeals=new MutableLiveData<>();
    public MealsActivityViewModel(@NonNull @NotNull Application application) {
        super(application);
        repository=new MealsRepository(application);
        getFavourites();
        isFavouriteMeal.setValue(false);

        favouriteMeals.observeForever(new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                if(meals!=null && contains(meals,meal)){
                    Log.e("Favourite","true");
                    isFavouriteMeal.setValue(true);
                }else{
                    isFavouriteMeal.setValue(false);
                }

            }
        });
    }

    private boolean contains(List<Meal> meals, Meal meal) {
        Log.e("SearchedItem",new Gson().toJson(meal));
        String mealString=new Gson().toJson(meal);
        for(int i=0;i<meals.size();i++){
            String temp=new Gson().toJson(meals.get(i));
            if(temp.equals(mealString)){
                return true;
            }
            Log.e("MealItems",new Gson().toJson(meals.get(i)));
        }
        return false;
    }

    private void getFavourites() {
        new Thread(){
            @Override
            public void run() {
                favouriteMeals.postValue(repository.getFavourites());
            }
        }.start();
    }

    public void addToFavourite(){
        if(!isFavouriteMeal.getValue()){
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    repository.insert(meal);
                    getFavourites();
                }
            }.start();
        }else{
            new Thread(){
                @Override
                public void run() {
                    repository.delete(meal);
                    getFavourites();
                }
            }.start();
        }

   }

    public void setCurrentMeal(Meal meal){
        if(this.meal==null){
            this.meal=meal;
            getFavourites();
        }
    }


}
