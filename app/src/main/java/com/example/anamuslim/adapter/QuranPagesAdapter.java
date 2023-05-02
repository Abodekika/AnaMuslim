package com.example.anamuslim.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.anamuslim.ui.QuranPageFragment;



public class QuranPagesAdapter extends FragmentStateAdapter {
    private static final int NUM_PAGES = 604;

    public QuranPagesAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @Override
    public Fragment createFragment(int position) {
        return new QuranPageFragment(NUM_PAGES - position);
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
