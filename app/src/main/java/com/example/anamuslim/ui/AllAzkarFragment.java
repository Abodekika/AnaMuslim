package com.example.anamuslim.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anamuslim.R;
import com.example.anamuslim.adapter.AllAzkarAdapter;
import com.example.anamuslim.databinding.FragmentAllAzkarBinding;
import com.example.anamuslim.pojo.azkar.Azker;
import com.example.anamuslim.viewModel.AllAzkarViewModel;

import java.util.ArrayList;
import java.util.List;


public class AllAzkarFragment extends Fragment {

    private AllAzkarViewModel allAzkarViewModel;
    public AllAzkarAdapter adapter;
    public static List<Azker> azkerList;
    FragmentAllAzkarBinding binding;
    Toolbar toolbar;
    RecyclerView az_rec;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        allAzkarViewModel =
                new ViewModelProvider(this).get(AllAzkarViewModel.class);
       binding = FragmentAllAzkarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        toolbar = binding.toolbar;

        az_rec = binding.azRec;
        azkerList = new ArrayList<>();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        az_rec.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AllAzkarAdapter(getContext(), allAzkarViewModel.azkarList(requireContext()), this);
        toolbar.setTitle(R.string.all_azkar_toolbat_title);

        az_rec.setAdapter(adapter);

        toolbar.setOnMenuItemClickListener(item -> {


            if (item.getItemId() == R.id.favorite) {
                NavHostFragment.findNavController(AllAzkarFragment.this
                ).navigate(AllAzkarFragmentDirections.actionAzkarFragmentToFavoriteAzkarFragment());

                return true;
            }
            return true;
        });


    }

}