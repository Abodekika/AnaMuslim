package com.example.anamuslim.viewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.anamuslim.database.azkardatabase.Repository;
import com.example.anamuslim.pojo.azkar.FavItem;

import java.util.List;

public class FavoriteAzkarViewModel extends ViewModel {
    Repository repository;

    public List<FavItem> loadDataFav(Context context) {

        repository = new Repository(context);


        return repository.readAllFav();
    }

    public void delete_item(Context context, String id) {

        repository = new Repository(context);


    }
}