package com.ayan.foodycookbook.Room;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.ayan.foodycookbook.Model.Meal;
import com.ayan.foodycookbook.Room.MealDatabase;
import com.ayan.foodycookbook.Room.MealsDao;

import java.util.List;

public class MealsRepository {

    private MealsDao dao;
    private List<Meal> meals;

    public MealsRepository(Application application){
        MealDatabase database=MealDatabase.getDatabase(application.getApplicationContext());
        dao=database.dao();
    }

    public List<Meal> getFavourites(){
        meals=dao.getFavourites();
        return meals;
    }

    public void insert(Meal meal){
        Log.e("ReceivedInsert","Request");
        dao.addToFavourite(meal);
    }

    public void delete(Meal meal){
        dao.deleteFavourite(meal);
    }
}
