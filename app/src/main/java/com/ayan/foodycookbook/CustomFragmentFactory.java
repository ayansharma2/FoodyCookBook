package com.ayan.foodycookbook;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.ayan.foodycookbook.Fragments.FavouriteFragment;
import com.ayan.foodycookbook.Fragments.MainFragment;

import org.jetbrains.annotations.NotNull;

public class CustomFragmentFactory extends FragmentFactory {

    LoadingComplete loadingComplete;
    public CustomFragmentFactory(LoadingComplete loadingComplete){
        this.loadingComplete= loadingComplete;
    }
    @NonNull
    @NotNull
    @Override
    public Fragment instantiate(@NonNull @NotNull ClassLoader classLoader, @NonNull @NotNull String className) {

        if(className== MainFragment.class.getName()){
            return new MainFragment(loadingComplete);
        }else if(className== FavouriteFragment.class.getName()){
            return new FavouriteFragment();
        }
        return super.instantiate(classLoader, className);
    }
}
