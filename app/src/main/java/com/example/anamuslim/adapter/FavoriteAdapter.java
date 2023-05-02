package com.example.anamuslim.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anamuslim.R;
import com.example.anamuslim.database.azkardatabase.Repository;
import com.example.anamuslim.pojo.azkar.FavItem;

import com.example.anamuslim.ui.FavoriteAzkarFragmentDirections;
import com.google.gson.Gson;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private Context context;
    public List<FavItem> fav_azkar_list;


    Fragment fragment;

    public FavoriteAdapter(Context context, List<FavItem> fav_azkar_list, Fragment fragment) {
        this.context = context;
        this.fav_azkar_list = fav_azkar_list;
        this.fragment = fragment;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.azkar_one_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.tvTitle.setText(fav_azkar_list.get(position).getTitle());

        holder.tvTitle.setOnClickListener(v -> {


            FavItem fav_azkar = fav_azkar_list.get(position);



            Gson gson = new Gson();

            String azkar_jason = gson.toJson(fav_azkar);
            NavHostFragment.findNavController(fragment).
                    navigate(FavoriteAzkarFragmentDirections.
                            actionFavoriteAzkarFragmentToAzkarDetailsFragment
                                    (azkar_jason));

        });


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
        return fav_azkar_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tv_count;
        TextView tv_bookmark;
        TextView az_con;
        ImageView fave_ic;
        Repository repository = new Repository(context);


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.az_title);
            fave_ic = itemView.findViewById(R.id.fave_ic);

            fave_ic.setOnClickListener(v -> {
                int position = getAdapterPosition();
                final FavItem favItem = fav_azkar_list.get(position);
                repository.deleteFromD(favItem.getId());
                removeItem(position);

            });


        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void removeItem(int position) {
        fav_azkar_list.remove(position);
        notifyDataSetChanged();
        notifyItemRangeChanged(position, fav_azkar_list.size());

    }

}
