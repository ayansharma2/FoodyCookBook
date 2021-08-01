package com.ayan.foodycookbook;

import com.ayan.foodycookbook.Model.Meal;
import com.ayan.foodycookbook.Model.MealsObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIS {

    @GET("random.php")
    Call<MealsObject>getRandomMeal();

    @GET("search.php")
    Call<MealsObject>getSearchResults(@Query("s")String keyword);
}
