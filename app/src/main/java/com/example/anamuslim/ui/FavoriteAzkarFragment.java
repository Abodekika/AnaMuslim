package com.example.anamuslim.ui;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anamuslim.R;
import com.example.anamuslim.adapter.FavoriteAdapter;
import com.example.anamuslim.databinding.FragmentFavoriteAzkarBinding;
import com.example.anamuslim.viewModel.FavoriteAzkarViewModel;

public class FavoriteAzkarFragment extends Fragment {

    private FavoriteAzkarViewModel favoriteAzkarViewModel;
    private FragmentFavoriteAzkarBinding binding;
    FavoriteAdapter favAdapter;
    RecyclerView fav_rec;
    Toolbar toolbar;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        favoriteAzkarViewModel =
                new ViewModelProvider(this).get(FavoriteAzkarViewModel.class);

        binding = FragmentFavoriteAzkarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        fav_rec = binding.favRec;
        toolbar = binding.toolbar;
        fav_rec.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favAdapter = new FavoriteAdapter(getContext(), favoriteAzkarViewModel.loadDataFav(getContext()), this);

        fav_rec.setAdapter(favAdapter);
        toolbar.setTitle(R.string.fav_toolbar_title);
        favAdapter.notifyDataSetChanged();
    }

}