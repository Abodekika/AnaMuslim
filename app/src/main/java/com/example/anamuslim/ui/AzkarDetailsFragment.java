package com.example.anamuslim.ui;

import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.anamuslim.MyMediaPlayer;
import com.example.anamuslim.R;
import com.example.anamuslim.adapter.DetailPageAdapter;
import com.example.anamuslim.database.azkardatabase.Repository;
import com.example.anamuslim.databinding.FragmentAzkarDetailsBinding;
import com.example.anamuslim.pojo.azkar.Azker;
import com.example.anamuslim.utils.Converter;
import com.example.anamuslim.viewModel.AzkarDetailsViewModel;

public class AzkarDetailsFragment extends Fragment {

    private AzkarDetailsViewModel mViewModel;
    FragmentAzkarDetailsBinding binding;
    public DetailPageAdapter adapter;
    Azker azkar;
    ViewPager2 det_view_pager;
    Toolbar toolbar;
    MyMediaPlayer mediaPlayer;
    private Repository repository;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAzkarDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        det_view_pager = binding.detailViewPager;
        toolbar = binding.azkarDetailToolbar;
        mediaPlayer = MyMediaPlayer.getInstance(getContext());
        repository = new Repository(getContext());
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AzkarDetailsFragmentArgs args = AzkarDetailsFragmentArgs.fromBundle(requireArguments());

        // content = (List<Content>) args.getAzkarContent();
        azkar = Converter.fromGsonToContent(args.getAzkar());
        adapter = new DetailPageAdapter(getContext(), azkar.getContent(), azkar.getTitle(), det_view_pager);
        det_view_pager.setAdapter(adapter);
        toolbar.setTitle(azkar.getTitle());

        if (azkar.getBookmark().equals("0")) {
            toolbar.getMenu().findItem(R.id.favorite).setIcon(R.drawable.ic_baseline_favorite_border_24);

        } else {
            toolbar.getMenu().findItem(R.id.favorite).setIcon(R.drawable.ic_favorite_24);

        }
        toolbar.getMenu().findItem(R.id.favorite).setOnMenuItemClickListener(item -> {

            if (azkar.getBookmark().equals("0")) {
                azkar.setBookmark("1");
                repository.insertIntoData(azkar);
                toolbar.getMenu().findItem(R.id.favorite).setIcon(R.drawable.ic_favorite_24);
                Toast.makeText(getContext(), "تمت الاضافة", Toast.LENGTH_SHORT).show();
            } else {
                azkar.setBookmark("0");
                repository.deleteFromD(azkar.getId());
                toolbar.getMenu().findItem(R.id.favorite).setIcon(R.drawable.ic_baseline_favorite_border_24);
                Toast.makeText(getContext(), "تم الحذف", Toast.LENGTH_SHORT).show();
            }


            return true;
        });


        det_view_pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                toolbar.getMenu().findItem(R.id.az_detail_media_player_button).setOnMenuItemClickListener(item -> {

                    toolbar.getMenu().findItem(R.id.az_detail_media_paused_button).setVisible(true);
                    if (mediaPlayer.isPlay()) {
                        mediaPlayer.stop();
                    }
                    toolbar.getMenu().findItem(R.id.favorite).setVisible(true);
                    String filepath = azkar.getContent().get(position).getAUDIO();
                    int id = azkar.getContent().get(position).getID();

                    mediaPlayer.playAudio(id, filepath);
                    return true;


                });


                toolbar.getMenu().findItem(R.id.az_detail_media_paused_button).setOnMenuItemClickListener(item -> {

                    if (mediaPlayer.isPlay()) {
                        mediaPlayer.pause();
                        mediaPlayer.setPause(true);
                        toolbar.getMenu().findItem(R.id.az_detail_media_paused_button).setIcon(R.drawable.ic_play_circle_outline_24);

                    } else {
                        mediaPlayer.start();
                        mediaPlayer.setPause(false);
                        toolbar.getMenu().findItem(R.id.az_detail_media_paused_button).setIcon(R.drawable.ic_baseline_paused);

                    }
                    return true;
                });


            }

        });


    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();
    }


}