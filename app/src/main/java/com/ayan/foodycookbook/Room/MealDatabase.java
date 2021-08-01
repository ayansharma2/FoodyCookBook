package com.ayan.foodycookbook.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ayan.foodycookbook.Model.Meal;

@Database(entities = {Meal.class},version = 2)
public abstract class MealDatabase extends RoomDatabase {
    public abstract MealsDao dao();

    public static MealDatabase database=null;

    public static MealDatabase getDatabase(Context context){
        if(database==null){
            database= Room.databaseBuilder(context.getApplicationContext(),
                    MealDatabase.class,"Favourite Meals")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
}
