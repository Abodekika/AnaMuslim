package com.example.anamuslim.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.anamuslim.R;
import com.example.anamuslim.adapter.QuranPagesAdapter;
import com.example.anamuslim.databinding.FragmentQuranContainerBinding;
import com.example.anamuslim.pojo.quran.Reader;
import com.example.anamuslim.utils.NewUtil;
import com.example.anamuslim.viewModel.QuranPageViewModel;

import java.util.List;

public class QuranContainerFragment extends Fragment {

    FragmentQuranContainerBinding binding;
    private ViewPager2 pager;
    Toolbar toolbar;
    private QuranContainerFragmentArgs args;
    private QuranPageViewModel quranPageViewModel;
    ArrayAdapter<CharSequence> arrayAdapter;
    Spinner spinner;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentQuranContainerBinding.inflate(inflater, container, false);
        quranPageViewModel = new ViewModelProvider(requireActivity()).get(QuranPageViewModel.class);
        init();


        return binding.getRoot();

    }


    @SuppressLint("ResourceType")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pager.setAdapter(new QuranPagesAdapter(this));
        pager.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        pager.setCurrentItem(604 - args.getStartPage(), false);


        toolbar.setOnMenuItemClickListener(item -> {


            if (item.getItemId() == R.id.last_page) {
                quranPageViewModel.toggleZoomMode(getContext());

                return true;
            } else {
                quranPageViewModel.toggleFullscreen(0);

            }


            return true;
        });
        arrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(arrayAdapter);


    }


    private void init() {
        pager = binding.quranViewPager;
        toolbar = binding.quranContainerToolbar;
        args = QuranContainerFragmentArgs.fromBundle(requireArguments());
        spinner = binding.spinner;

        String[] reader = NewUtil.get_quran_reader_only_for_spinner(getContext());

        arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, reader);
    }
}