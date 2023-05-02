package com.example.anamuslim.ui;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anamuslim.R;
import com.example.anamuslim.databinding.FragmentQuranPageBinding;
import com.example.anamuslim.handler.TouchImageView;
import com.example.anamuslim.sharedpreferences.QuranPreferences;
import com.example.anamuslim.viewModel.QuranPageViewModel;

import java.io.File;

public class QuranPageFragment extends Fragment {

    private QuranPageViewModel quranPageViewModel;

    private FragmentQuranPageBinding binding;
    QuranPreferences quranPreferences;

    private final int pageNumber;
    TouchImageView imageView;


    public QuranPageFragment(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentQuranPageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        quranPageViewModel = new ViewModelProvider(requireActivity()).get(QuranPageViewModel.class);
        imageView = binding.quranImage;

        quranPreferences = new QuranPreferences(requireContext());
        // mViewModel.downloadQuranPage(getContext());

        // QuranPageViewModel.toggleZoomMode(getContext());

        check(root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        quranPreferences.setLast_page(pageNumber);


    }

    public void check(View root) {
        File rootv = Environment.getExternalStoragePublicDirectory("");


        File dir = new File(rootv + "/AnaMuslim/pages_hd");


        String fname = "images.zip";
        File file = new File(dir, fname);

        if (file.exists()) {
            QuranPageViewModel.initialize(getActivity(), imageView, pageNumber - 1);
            QuranPageViewModel.sViews.put(this.pageNumber, root);
        } else {

            quranPageViewModel.downloadQuranPage(getContext());
        }
    }

}