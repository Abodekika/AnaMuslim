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
import com.example.anamuslim.pojo.quran.QuranPart;
import com.example.anamuslim.ui.SoraListFragmentDirections;
import com.example.anamuslim.utils.Converter;

import java.util.List;

public class PartListAdapter extends RecyclerView.Adapter<PartListAdapter.ViewHolder> {
    private Context context;
    private List<QuranPart> partList;
    Fragment fragment;

    public PartListAdapter(Context context, List<QuranPart> soraList, Fragment fragment) {
        this.context = context;
        this.partList = soraList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.part_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Typeface font_1 = Typeface.createFromAsset(context.getAssets(), "fonts/surah_font.ttf");
        //  holder.part_name.setTypeface(font_1);

        int Part_number = Integer.parseInt(partList.get(position).getPart_number()) + 1;
        holder.part_name.setText(String.valueOf(Part_number));
        System.out.println(partList.get(position).getPart_number());

//        holder.part_name.setText(Converter.convertNumberType(context, String.valueOf(partList.get(position).getSurah_number())));

        holder.part_name.setOnClickListener(v -> {

            NavHostFragment.findNavController
                    (fragment).navigate(SoraListFragmentDirections.
                    actionQuranFragmentToQuranContainerFragment(Integer.parseInt(partList.get(position).getPage_number()) + 1));


        });

    }


    @Override
    public int getItemCount() {
        return partList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView part_name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            part_name = itemView.findViewById(R.id.part_name);

        }


    }
}
