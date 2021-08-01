package com.ayan.foodycookbook.Fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayan.foodycookbook.Adapters.FavouriteItemAdapter;
import com.ayan.foodycookbook.Application.FoodyCookApplicationClass;
import com.ayan.foodycookbook.Model.Meal;
import com.ayan.foodycookbook.R;
import com.ayan.foodycookbook.ViewModels.FavouriteFragmentViewModel;
import com.ayan.foodycookbook.databinding.FavouriteMealItemBinding;
import com.ayan.foodycookbook.databinding.FragmentFavouriteBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class FavouriteFragment extends Fragment {

    FragmentFavouriteBinding binding;
    FavouriteItemAdapter adapter;
    FavouriteFragmentViewModel viewModel;
    public FavouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Favourite");
        FoodyCookApplicationClass applicationClass=(FoodyCookApplicationClass)getActivity().getApplication();
        applicationClass.fragment=2;
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_favourite,container,false);
        viewModel= new ViewModelProvider(this).get(FavouriteFragmentViewModel.class);

        adapter=new FavouriteItemAdapter(getContext(),new ArrayList<>());
        binding.favouriteRecyclerView.setAdapter(adapter);
        binding.favouriteRecyclerView.setHasFixedSize(false);
        binding.favouriteRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel.getFavourites().observe(getViewLifecycleOwner(),new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                if(meals.size()==0){
                    binding.errorMessage.setVisibility(View.VISIBLE);
                    binding.favouriteRecyclerView.setVisibility(View.GONE);
                }
                else{
                    binding.errorMessage.setVisibility(View.GONE);
                    binding.favouriteRecyclerView.setVisibility(View.VISIBLE);
                    adapter=new FavouriteItemAdapter(getContext(),meals);
                    binding.favouriteRecyclerView.setAdapter(adapter);
                }
            }
        });
        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.refresh.setRefreshing(false);
            }
        });
        return binding.getRoot();
    }

}