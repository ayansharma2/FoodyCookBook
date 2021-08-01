package com.ayan.foodycookbook.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ayan.foodycookbook.MealActivity;
import com.ayan.foodycookbook.Model.Meal;
import com.ayan.foodycookbook.R;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FavouriteItemAdapter extends RecyclerView.Adapter<FavouriteItemAdapter.ViewHolder> {
    Context context;

    public FavouriteItemAdapter(Context context, List<Meal> meals) {
        this.context = context;
        this.meals = meals;
    }

    List<Meal> meals;
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.favourite_meal_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FavouriteItemAdapter.ViewHolder holder, int position) {
        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String meal= new Gson().toJson(meals.get(position));
                context.startActivity(new Intent(context, MealActivity.class).putExtra("meal",meal));
            }
        });
        Glide.with(context)
                .load(meals.get(position).strMealThumb)
                .into(holder.dish_image);
        holder.dish_title.setText(meals.get(position).strMeal);
        holder.dish_origin.setText(meals.get(position).strArea);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView dish_image;
        CardView parent_layout;
        TextView dish_title,dish_origin;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            dish_image=itemView.findViewById(R.id.dish_image);
            parent_layout=itemView.findViewById(R.id.parent_layout);
            dish_title=itemView.findViewById(R.id.dish_title);
            dish_origin=itemView.findViewById(R.id.dish_origin);

        }
    }
}
