package com.ayan.foodycookbook.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ViewHolder> {
    Context context;

    public SearchResultsAdapter(Context context, List<Meal> meals) {
        this.context = context;
        this.meals = meals;
    }

    List<Meal> meals;
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SearchResultsAdapter.ViewHolder holder, int position) {
        holder.type.setText(meals.get(position).strCategory);
        holder.title.setText(meals.get(position).strMeal);
        Glide.with(context)
                .load(meals.get(position).strMealThumb)
                .into(holder.image);
        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String meal= new Gson().toJson(meals.get(position));
                context.startActivity(new Intent(context, MealActivity.class).putExtra("meal",meal));
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView title,type;
        CardView parent_layout;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.dish_image);
            title=itemView.findViewById(R.id.dish_title);
            type=itemView.findViewById(R.id.dish_type);
            parent_layout=itemView.findViewById(R.id.parent_layout);
        }
    }
}
