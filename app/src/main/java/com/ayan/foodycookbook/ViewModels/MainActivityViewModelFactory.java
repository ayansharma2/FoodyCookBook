package com.ayan.foodycookbook.ViewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ayan.foodycookbook.LoadingComplete;

import org.jetbrains.annotations.NotNull;

public class MainActivityViewModelFactory implements ViewModelProvider.Factory {
    LoadingComplete loadingComplete;
    public MainActivityViewModelFactory(LoadingComplete loadingComplete){
        this.loadingComplete=loadingComplete;
    }

    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(loadingComplete);
    }
}
