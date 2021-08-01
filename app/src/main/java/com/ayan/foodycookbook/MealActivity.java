package com.ayan.foodycookbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ayan.foodycookbook.Model.Meal;
import com.ayan.foodycookbook.ViewModels.MealsActivityViewModel;
import com.ayan.foodycookbook.databinding.ActivityMealBinding;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

public class MealActivity extends AppCompatActivity {
    Meal meal;
    ActivityMealBinding binding;
    MealsActivityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_meal);
        meal=new Gson().fromJson(getIntent().getStringExtra("meal"),Meal.class);
        viewModel= new ViewModelProvider(this).get(MealsActivityViewModel.class);
        viewModel.isFavouriteMeal.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    binding.likeButton.setImageDrawable(getDrawable(R.drawable.heart_filled));
                }else{
                    binding.likeButton.setImageDrawable(getDrawable(R.drawable.heart));
                }
            }
        });

        binding.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addToFavourite();
            }
        });

        viewModel.setCurrentMeal(meal);
        binding.title.setText(meal.strMeal);
        Glide.with(this)
                .load(meal.strMealThumb)
                .into(binding.image);
        binding.type.setText(meal.strCategory);
        binding.origin.setText(meal.strArea);
        binding.youTube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(meal.strYoutube)));
            }
        });
        if(meal.strIngredient1!=null && meal.strIngredient1!=""){
            View view= LayoutInflater.from(MealActivity.this).inflate(R.layout.ingredient_item,null,false);
            TextView name=view.findViewById(R.id.name);
            name.setText(meal.strIngredient1);
            binding.ingredients.addView(view);

        }
        if(meal.strIngredient2!=null && meal.strIngredient2!=""){
            View view= LayoutInflater.from(MealActivity.this).inflate(R.layout.ingredient_item,null,false);
            TextView name=view.findViewById(R.id.name);
            name.setText(meal.strIngredient2);
            binding.ingredients.addView(view);
        }
        if(meal.strIngredient3!=null && !meal.strIngredient3.isEmpty()){
            View view= LayoutInflater.from(MealActivity.this).inflate(R.layout.ingredient_item,null,false);
            TextView name=view.findViewById(R.id.name);
            name.setText(meal.strIngredient3);
            binding.ingredients.addView(view);
        }
        if(meal.strIngredient4!=null && !meal.strIngredient4.isEmpty()){
            View view= LayoutInflater.from(MealActivity.this).inflate(R.layout.ingredient_item,null,false);
            TextView name=view.findViewById(R.id.name);
            name.setText(meal.strIngredient4);
            binding.ingredients.addView(view);
        }
        if(meal.strIngredient5!=null && !meal.strIngredient5.isEmpty()){
            View view= LayoutInflater.from(MealActivity.this).inflate(R.layout.ingredient_item,null,false);
            TextView name=view.findViewById(R.id.name);
            name.setText(meal.strIngredient5);
            binding.ingredients.addView(view);
        }

        if(meal.strIngredient6!=null && !meal.strIngredient6.isEmpty()){
            View view= LayoutInflater.from(MealActivity.this).inflate(R.layout.ingredient_item,null,false);
            TextView name=view.findViewById(R.id.name);
            name.setText(meal.strIngredient6);
            binding.ingredients.addView(view);
        }

        if(meal.strIngredient7!=null && !meal.strIngredient7.isEmpty()){
            View view= LayoutInflater.from(MealActivity.this).inflate(R.layout.ingredient_item,null,false);
            TextView name=view.findViewById(R.id.name);
            name.setText(meal.strIngredient7);
            binding.ingredients.addView(view);
        }
        if(meal.strIngredient8!=null && !meal.strIngredient8.isEmpty()){
            View view= LayoutInflater.from(MealActivity.this).inflate(R.layout.ingredient_item,null,false);
            TextView name=view.findViewById(R.id.name);
            name.setText(meal.strIngredient8);
            binding.ingredients.addView(view);
        }
        if(meal.strIngredient9!=null && !meal.strIngredient9.isEmpty()){
            View view= LayoutInflater.from(MealActivity.this).inflate(R.layout.ingredient_item,null,false);
            TextView name=view.findViewById(R.id.name);
            name.setText(meal.strIngredient9);
            binding.ingredients.addView(view);
        }

        if(meal.strIngredient10!=null && !meal.strIngredient10.isEmpty()){
            View view= LayoutInflater.from(MealActivity.this).inflate(R.layout.ingredient_item,null,false);
            TextView name=view.findViewById(R.id.name);
            name.setText(meal.strIngredient10);
            binding.ingredients.addView(view);
        }


        if(meal.strIngredient11!=null && !meal.strIngredient11.isEmpty()){
            View view= LayoutInflater.from(MealActivity.this).inflate(R.layout.ingredient_item,null,false);
            TextView name=view.findViewById(R.id.name);
            name.setText(meal.strIngredient11);
            binding.ingredients.addView(view);
        }

        if(meal.strIngredient12!=null && !meal.strIngredient12.isEmpty()){
            View view= LayoutInflater.from(MealActivity.this).inflate(R.layout.ingredient_item,null,false);
            TextView name=view.findViewById(R.id.name);
            name.setText(meal.strIngredient12);
            binding.ingredients.addView(view);
        }

        if(meal.strIngredient13!=null && !meal.strIngredient13.isEmpty()){
            View view= LayoutInflater.from(MealActivity.this).inflate(R.layout.ingredient_item,null,false);
            TextView name=view.findViewById(R.id.name);
            name.setText(meal.strIngredient13);
            binding.ingredients.addView(view);
        }

        if(meal.strIngredient14!=null && !meal.strIngredient14.isEmpty()){
            View view= LayoutInflater.from(MealActivity.this).inflate(R.layout.ingredient_item,null,false);
            TextView name=view.findViewById(R.id.name);
            name.setText(meal.strIngredient14);
            binding.ingredients.addView(view);
        }

        if(meal.strIngredient15!=null && !meal.strIngredient15.isEmpty()){
            View view= LayoutInflater.from(MealActivity.this).inflate(R.layout.ingredient_item,null,false);
            TextView name=view.findViewById(R.id.name);
            name.setText(meal.strIngredient15);
            binding.ingredients.addView(view);
        }

        if(meal.strIngredient16!=null && !meal.strIngredient16.isEmpty()){
            View view= LayoutInflater.from(MealActivity.this).inflate(R.layout.ingredient_item,null,false);
            TextView name=view.findViewById(R.id.name);
            name.setText(meal.strIngredient16);
            binding.ingredients.addView(view);
        }

        if(meal.strIngredient17!=null && !meal.strIngredient17.isEmpty()){
            View view= LayoutInflater.from(MealActivity.this).inflate(R.layout.ingredient_item,null,false);
            TextView name=view.findViewById(R.id.name);
            name.setText(meal.strIngredient17);
            binding.ingredients.addView(view);
        }


        if(meal.strIngredient18!=null && !meal.strIngredient18.isEmpty()){
            View view= LayoutInflater.from(MealActivity.this).inflate(R.layout.ingredient_item,null,false);
            TextView name=view.findViewById(R.id.name);
            name.setText(meal.strIngredient18);
            binding.ingredients.addView(view);
        }

        if(meal.strIngredient19!=null && !meal.strIngredient19.isEmpty()){
            View view= LayoutInflater.from(MealActivity.this).inflate(R.layout.ingredient_item,null,false);
            TextView name=view.findViewById(R.id.name);
            name.setText(meal.strIngredient19);
            binding.ingredients.addView(view);
        }

        if(meal.strIngredient20!=null && !meal.strIngredient20.isEmpty()){
            View view= LayoutInflater.from(MealActivity.this).inflate(R.layout.ingredient_item,null,false);
            TextView name=view.findViewById(R.id.name);
            name.setText(meal.strIngredient20);
            binding.ingredients.addView(view);
        }

        binding.instructions.setText(meal.strInstructions);
    }
}