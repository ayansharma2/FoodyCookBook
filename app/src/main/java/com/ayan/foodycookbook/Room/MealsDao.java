package com.ayan.foodycookbook.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ayan.foodycookbook.Model.Meal;

import java.util.List;

@Dao
public interface MealsDao {
    @Query("SELECT * FROM Meal")
    List<Meal> getFavourites();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addToFavourite(Meal meal);


    @Delete
    void deleteFavourite(Meal meal);
}
