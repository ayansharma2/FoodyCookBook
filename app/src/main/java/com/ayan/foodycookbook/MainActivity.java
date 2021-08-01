package com.ayan.foodycookbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;

import com.ayan.foodycookbook.Application.FoodyCookApplicationClass;
import com.ayan.foodycookbook.Dialog.ExitDialog;
import com.ayan.foodycookbook.Fragments.FavouriteFragment;
import com.ayan.foodycookbook.Fragments.MainFragment;
import com.ayan.foodycookbook.ViewModels.MainActivityViewModel;
import com.ayan.foodycookbook.ViewModels.MainActivityViewModelFactory;
import com.ayan.foodycookbook.ViewModels.MainFragmentViewModel;
import com.ayan.foodycookbook.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements LoadingComplete, CloseActivity {
    ActivityMainBinding binding;
    MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.parentLayout.setVisibility(View.INVISIBLE);
        MainActivityViewModelFactory viewModelFactory = new MainActivityViewModelFactory(this);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel.class);
        getSupportFragmentManager().setFragmentFactory(viewModel.fragmentFactory);
        checkInternetConnection();
    }

    private void checkInternetConnection() {
        binding.bottomNav.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.parentLayout.setVisibility(View.INVISIBLE);
        boolean connected ;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;

        if(!connected){
            binding.refreshLayout.setRefreshing(false);
            binding.progressBar.setVisibility(View.GONE);
            Snackbar.make(binding.bottomNav,"You are not connected to internet but you can still access Favourites",Snackbar.LENGTH_LONG).show();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, fragmentManager.getFragmentFactory()
                    .instantiate(this.getClassLoader(), FavouriteFragment.class.getName()));
            transaction.commit();
            binding.bottomNav.setVisibility(View.GONE);
            binding.parentLayout.setVisibility(View.VISIBLE);
            //binding.progressBar.setVisibility(View.VISIBLE);
            binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    checkInternetConnection();
                }
            });
        }else{
            initTransaction();
            binding.refreshLayout.setEnabled(false);
            binding.bottomNav.inflateMenu(R.menu.bottom_nav);
            binding.bottomNav.setOnItemSelectedListener(item -> {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (item.getItemId() == R.id.home) {
                    transaction.replace(R.id.frame_layout, fragmentManager.getFragmentFactory()
                            .instantiate(this.getClassLoader(), MainFragment.class.getName()));
                } else if (item.getItemId() == R.id.favourite) {
                    transaction.replace(R.id.frame_layout, fragmentManager.getFragmentFactory()
                            .instantiate(this.getClassLoader(), FavouriteFragment.class.getName()));
                }
                //transaction.addToBackStack(null);
                transaction.commit();
                return true;
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        FoodyCookApplicationClass applicationClass=((FoodyCookApplicationClass)this.getApplication());
        if(applicationClass.fragment==2){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, fragmentManager.getFragmentFactory()
                    .instantiate(this.getClassLoader(), FavouriteFragment.class.getName()));
            transaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        ExitDialog ed=new ExitDialog(this,this);
        ed.show();
    }

    private void initTransaction() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragmentManager.getFragmentFactory()
                .instantiate(this.getClassLoader(), MainFragment.class.getName()));
        transaction.commit();
    }

    @Override
    public void onLoadingComplete() {
        binding.progressBar.setVisibility(View.GONE);
        binding.bottomNav.setVisibility(View.VISIBLE);
        binding.parentLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onExit() {
        finish();
    }
}


