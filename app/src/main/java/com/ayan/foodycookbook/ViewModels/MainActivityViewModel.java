package com.ayan.foodycookbook.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentFactory;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.ayan.foodycookbook.CustomFragmentFactory;
import com.ayan.foodycookbook.Fragments.FavouriteFragment;
import com.ayan.foodycookbook.Fragments.MainFragment;
import com.ayan.foodycookbook.LoadingComplete;

import org.jetbrains.annotations.NotNull;

public class MainActivityViewModel extends ViewModel {

    public CustomFragmentFactory fragmentFactory;
    public MainActivityViewModel(LoadingComplete loadingComplete) {
        fragmentFactory=new CustomFragmentFactory(loadingComplete);
    }

}
