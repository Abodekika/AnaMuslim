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

import com.example.anamuslim.R;
import com.example.anamuslim.pojo.alsibha.Alsibha;
import com.example.anamuslim.pojo.azkar.ContentRoom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AlSibhaPageAdapter extends RecyclerView.Adapter<AlSibhaPageAdapter.ViewHolder> {



    List<Alsibha> alsibha_text = new ArrayList<>();

    final private Context context;


    private static final String TAG = "DetailPagerAdapter";


    public AlSibhaPageAdapter(Context context, List<Alsibha> alsibha_text
    ) {
        this.context = context;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.alsibha_one_item, parent, false);


        return new ViewHolder(view);
    }

    @SuppressLint({"ResourceAsColor", "ClickableViewAccessibility"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.az_title.setText(alsibha_text.get(position).getText());
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public List<Alsibha> getAlsibha_text() {
        return alsibha_text;
    }

    public void setAlsibha_text(List<Alsibha> alsibha_text) {
        this.alsibha_text = alsibha_text;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return getAlsibha_text().size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView az_title;


        @SuppressLint("ClickableViewAccessibility")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            az_title = itemView.findViewById(R.id.alsibha_text);


        }


    }


}


