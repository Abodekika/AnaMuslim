package com.example.anamuslim.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;


import com.example.anamuslim.MyMediaPlayer;
import com.example.anamuslim.R;
import com.example.anamuslim.pojo.azkar.ContentRoom;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DetailPageAdapter extends RecyclerView.Adapter<DetailPageAdapter.ViewHolder> {


    ViewPager2 viewPager;


    final private Context context;
    final private List<ContentRoom> contentList;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService executorService =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static final String TAG = "DetailPagerAdapter";

    String title;

    public DetailPageAdapter(Context context, List<ContentRoom> contentList, String title, ViewPager2 viewPager
    ) {
        this.context = context;
        this.contentList = contentList;
        this.title = title;
        this.viewPager = viewPager;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.azkar_details_pager_item, parent, false);


        return new ViewHolder(view);
    }

    @SuppressLint({"ResourceAsColor", "ClickableViewAccessibility"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SharedPreferences preferences = context.getSharedPreferences("font_size", Context.MODE_PRIVATE);
        int font_size = preferences.getInt("font_size", 20);
        int font_style = preferences.getInt("font_style", 1);


        holder.az_title.setText(contentList.get(position).getText());

        holder.az_count.setText(contentList.get(position).getFadl());
        holder.az_detail_position.setText(String.valueOf(position + 1));


        holder.az_detail_count.setText(String.valueOf(contentList.size()));

        holder.frame_layout_az_detail_background.setOnClickListener(
                v -> {
                    holder.increaseProgressBar(contentList, viewPager, context);

                }


        );

//        holder.az_detail_media_player_button.setOnClickListener(v -> {
//            String filepath = contentList.get(position).getAUDIO();
//            int id = contentList.get(position).getID();
//            mediaPlayer.playAudio(id, filepath);
//        });


    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public int getItemCount() {

        return contentList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "View";
        TextView az_title;
        TextView az_count, az_detail_position, az_detail_count, az_detail_count_progress_bar;
        ProgressBar az_detail_progress_count;
        int currentProgress = 0;
        int P_counter = 0;
        FrameLayout frame_layout_az_detail_background;
        ImageView az_detail_media_player_button;

        @SuppressLint("ClickableViewAccessibility")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            az_title = itemView.findViewById(R.id.az_title);
            az_count = itemView.findViewById(R.id.az_source);
            az_detail_position = itemView.findViewById(R.id.az_detail_position);
            az_detail_count = itemView.findViewById(R.id.az_detail_count);
            az_detail_progress_count = itemView.findViewById(R.id.az_detail_progress_count);
            az_detail_count_progress_bar = itemView.findViewById(R.id.az_detail_count_progress_bar);
            frame_layout_az_detail_background = itemView.findViewById(R.id.frame_layout_az_detail_background);


        }


        private void increaseProgressBar(List<ContentRoom> contentList, ViewPager2 viewPager2, Context context) {


            int position = viewPager2.getCurrentItem();
            Log.d(TAG, "pro: " + position);

            int count = Integer.parseInt(contentList.get(position).getCount());

            az_detail_progress_count.setMax(count);

            if (currentProgress < count && position >= P_counter) {
                currentProgress = currentProgress + 1;

                az_detail_count_progress_bar.setText("" + currentProgress);
                az_detail_progress_count.setProgress(currentProgress);
                executorService.execute(() -> {
                    final MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.click);
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(mp -> mediaPlayer.release());
                });

            }
            if (currentProgress == count) {

                viewPager2.setCurrentItem(position + 1);

                currentProgress = 0;
                P_counter += 50;

                // attachment();

            }

        }


    }


}


