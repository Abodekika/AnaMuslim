package com.example.anamuslim.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.anamuslim.R;
import com.example.anamuslim.pojo.quran.QuranSurah;
import com.example.anamuslim.ui.SoraListFragmentDirections;
import com.example.anamuslim.utils.Converter;

import java.util.List;

public class    SoraListAdapter extends RecyclerView.Adapter<SoraListAdapter.ViewHolder> {
    private Context context;
    private List<QuranSurah> soraList;
    Fragment fragment;

    public SoraListAdapter(Context context, List<QuranSurah> soraList, Fragment fragment) {
        this.context = context;
        this.soraList = soraList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sora_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Typeface font_1 = Typeface.createFromAsset(context.getAssets(), "fonts/surah_font.ttf");
        // holder.sora_name.setTypeface(font_1);
        //  holder.sora_number.setTypeface(font_1);

        holder.sora_name.setText(soraList.get(position).getSurah_name());


        int surah_number = Integer.parseInt(soraList.get(position).getSurah_number())+1;
        holder.sora_number.setText(Converter.convertNumberType(context, String.valueOf(surah_number)));

        holder.sora_name.setOnClickListener(v -> {

            NavHostFragment.findNavController
                    (fragment).navigate(SoraListFragmentDirections.
                    actionQuranFragmentToQuranContainerFragment(Integer.parseInt(soraList.get(position).getSurah_start_page()) + 1));


        });

    }


    @Override
    public int getItemCount() {
        return soraList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView sora_name;
        TextView sora_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sora_name = itemView.findViewById(R.id.sora_name);
            sora_number = itemView.findViewById(R.id.sora_number);
        }


    }
}
