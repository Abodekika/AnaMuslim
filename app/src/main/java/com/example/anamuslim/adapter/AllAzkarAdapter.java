package com.example.anamuslim.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anamuslim.R;
import com.example.anamuslim.database.azkardatabase.Repository;
import com.example.anamuslim.database.azkardatabase.Valuelis;
import com.example.anamuslim.pojo.azkar.Azker;


import com.example.anamuslim.ui.AllAzkarFragmentDirections;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class AllAzkarAdapter extends RecyclerView.Adapter<AllAzkarAdapter.ViewHolder>
        implements Filterable {

    final private Context context;
    private final List<Azker> azker_list;
    List<Azker> search_azker_list;

    Fragment fragment;

    Repository repository;


    public AllAzkarAdapter(Context context, List<Azker> azker_list, Fragment fragment) {
        this.context = context;
        this.azker_list = azker_list;
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

        //  azkarDataBase = new AzkarDataBase(context);

        repository = new Repository(context);

        SharedPreferences preferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences contextSharedPreferences = context.getSharedPreferences("font_size", Context.MODE_PRIVATE);
        Azker azker = azker_list.get(position);
        boolean firstStart = preferences.getBoolean("firstStart", true);
        if (firstStart) {
            createTAbleONFirsteStart(azker_list);

        }

        readCursorData(azker, holder);
        int font_size = contextSharedPreferences.getInt("font_size", 20);
        int font_style = contextSharedPreferences.getInt("font_style", 1);

        holder.tvTitle.setText(azker_list.get(position).getTitle());

        holder.tvTitle.setTextSize(font_size);


        holder.tvTitle.setText(azker.title);
        holder.fave_ic.setOnClickListener(v -> {
            if (azker.getBookmark().equals("0")) {

                azker.setBookmark("1");

                repository.insertIntoData(azker);
                holder.fave_ic.setImageResource(R.drawable.ic_bookmark_full);
                Toast.makeText(context, "تمت الاضافة", Toast.LENGTH_SHORT).show();


            } else {

                azker.setBookmark("0");
                repository.deleteFromD(azker.getId());
                holder.fave_ic.setImageResource(R.drawable.ic_bookmark);
                Toast.makeText(context, "تم الحذف", Toast.LENGTH_SHORT).show();
            }

        });

        holder.tvTitle.setOnClickListener(v -> {

            //  List<Content> content = azker_list.get(position).getContent();
            Azker azkera = azker_list.get(position);


            Gson gson = new Gson();


            String azkar_json = gson.toJson(azkera);


            NavHostFragment.findNavController(fragment).navigate(AllAzkarFragmentDirections.
                    actionAzkarFragmentToAzkarDetailsFragment(azkar_json));


//
//            Intent intent = new Intent(context, Datails.class);
//
//            List<Content> content = azker_list.get(position).getContent();
//            intent.putExtra("tec", (Serializable) content);
//            intent.putExtra("count", azker_list.get(position).title);


            //  Log.d("AllAzkarAdapter", String.valueOf(azker_list.get(position).getContent()));
//
//
//            context.startActivity(intent);


        });

    }

    private void createTAbleONFirsteStart(List<Azker> list) {


        repository.insertEmpty(list);
        SharedPreferences preferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    private void readCursorData(Azker azker, ViewHolder viewHolder) {


        repository.readAllData(azker.getId(), (Valuelis) list -> {

            for (int i = 0; i < list.size(); i++) {
                azker.setBookmark(list.get(i).getBookmark());
                String bookmark = list.get(i).getBookmark();

                if (bookmark != null && bookmark.equals("1")) {

                    viewHolder.fave_ic.setImageResource(R.drawable.ic_bookmark_full);

                } else if (bookmark != null && bookmark.equals("0")) {

                    viewHolder.fave_ic.setImageResource(R.drawable.ic_bookmark);

                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return azker_list.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Azker> filter_azkerList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filter_azkerList.addAll(search_azker_list);

            } else {
                String filterpattern = constraint.toString().trim();
                for (Azker azker : search_azker_list) {
                    if (azker.title.contains(filterpattern))
                        filter_azkerList.add(azker);

                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filter_azkerList;
            filterResults.count = filter_azkerList.size();
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            azker_list.clear();
            //   azker_list.addAll((Collection<? extends Azker>) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        ImageView fave_ic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.az_title);
            fave_ic = itemView.findViewById(R.id.fave_ic);


        }
    }
}
