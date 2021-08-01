package com.ayan.foodycookbook.Fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayan.foodycookbook.Adapters.SearchResultsAdapter;
import com.ayan.foodycookbook.Application.FoodyCookApplicationClass;
import com.ayan.foodycookbook.LoadingComplete;
import com.ayan.foodycookbook.MealActivity;
import com.ayan.foodycookbook.Model.Meal;
import com.ayan.foodycookbook.R;
import com.ayan.foodycookbook.ViewModels.MainFragmentViewModel;
import com.ayan.foodycookbook.databinding.FragmentMainBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    MainFragmentViewModel viewModel;
    SearchResultsAdapter adapter;
    LoadingComplete loadingComplete;
    Meal meal;
    public MainFragment(LoadingComplete loadingComplete) {
        this.loadingComplete=loadingComplete;

    }
    FragmentMainBinding binding;
    public MainFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Foody CookBook");
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false);
        FoodyCookApplicationClass applicationClass=(FoodyCookApplicationClass)getActivity().getApplication();
        applicationClass.fragment=1;
        viewModel= new ViewModelProvider(this).get(MainFragmentViewModel.class);
        if(loadingComplete!=null){
            viewModel.loadingComplete=loadingComplete;
        }
        adapter=new SearchResultsAdapter(getContext(),new ArrayList());

        binding.mealsRecyclerView.setAdapter(adapter);
        binding.mealsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.mealsRecyclerView.setHasFixedSize(false);
        binding.randomImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count!=0){

                    binding.mealsRecyclerView.setVisibility(View.VISIBLE);
                    viewModel.searchKeyword(s.toString());

                }else{
                    binding.mealsRecyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        viewModel.getSearchResults().observe(getViewLifecycleOwner(),
                new Observer<List<Meal>>() {
                    @Override
                    public void onChanged(List<Meal> meals) {
                        if(meals!=null){
                            adapter=new SearchResultsAdapter(getContext(),meals);
                            binding.mealsRecyclerView.setAdapter(adapter);
                        }
                    }
                });
        if(applicationClass.meal==null){
            viewModel.getRandomMeal().observe(getActivity(),randomMeal->{
                if(randomMeal.size()!=0){
                    binding.randomImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String mealString= new Gson().toJson(randomMeal.get(0));
                            startActivity(new Intent(getContext(), MealActivity.class).putExtra("meal",mealString));
                        }
                    });
                    viewModel.loadingComplete.onLoadingComplete();
                }
                applicationClass.meal=randomMeal.get(0);
                binding.origin.setText(randomMeal.get(0).strArea);
                binding.type.setText(randomMeal.get(0).strCategory);
                Glide.with(getContext())
                        .load(randomMeal.get(0).strMealThumb)
                        //.placeholder(R.drawable.ic_launcher_background)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable @org.jetbrains.annotations.Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                binding.imageProgressBar.setVisibility(View.GONE);
                                Snackbar.make(binding.dishTitle,"Error Getting data", Snackbar.LENGTH_LONG).show();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                binding.imageProgressBar.setVisibility(View.GONE);
                                //binding.randomImage.setImageDrawable(resource);
                                return false;
                            }
                        }).into(binding.randomImage);
                binding.instructions.setText(randomMeal.get(0).strInstructions);
                Log.e("Image is",randomMeal.get(0).strMealThumb);
                binding.youTube.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(randomMeal.get(0).strYoutube)));
                    }
                });
                binding.dishTitle.setText(randomMeal.get(0).strMeal);
            });
        }else{
                viewModel.loadingComplete.onLoadingComplete();
            Meal meal=applicationClass.meal;
            binding.randomImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mealString= new Gson().toJson(meal);
                    startActivity(new Intent(getContext(), MealActivity.class).putExtra("meal",mealString));
                }
            });
                binding.origin.setText(meal.strArea);
                binding.type.setText(meal.strCategory);
                Glide.with(getContext())
                        .load(meal.strMealThumb)
                        //.placeholder(R.drawable.ic_launcher_background)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable @org.jetbrains.annotations.Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                binding.imageProgressBar.setVisibility(View.GONE);
                                Snackbar.make(binding.dishTitle,"Error Getting data",Snackbar.LENGTH_LONG).show();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                binding.imageProgressBar.setVisibility(View.GONE);
                                //binding.randomImage.setImageDrawable(resource);
                                return false;
                            }
                        }).into(binding.randomImage);
                binding.instructions.setText(meal.strInstructions);
                Log.e("Image is",meal.strMealThumb);
                binding.youTube.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(meal.strYoutube)));
                    }
                });
                binding.dishTitle.setText(meal.strMeal);
        }

        return binding.getRoot();
    }

}