package com.example.anamuslim.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anamuslim.R;
import com.example.anamuslim.adapter.PartListAdapter;
import com.example.anamuslim.adapter.SoraListAdapter;
import com.example.anamuslim.databinding.FragmentSoraListBinding;
import com.example.anamuslim.pojo.quran.QuranPart;
import com.example.anamuslim.pojo.quran.QuranSurah;
import com.example.anamuslim.sharedpreferences.QuranPreferences;
import com.example.anamuslim.utils.NewUtil;
import com.example.anamuslim.viewModel.SoraListViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class SoraListFragment extends Fragment {

    private FragmentSoraListBinding binding;
    SoraListViewModel soraListViewModel;
    RecyclerView quran_sora_Recycler;
    RecyclerView quran_jozz_Recycler;
    Toolbar toolbar;
    QuranPreferences quranPreferences;

    public static List<QuranSurah> quran_sora_list;
    public static List<QuranPart> quran_part_list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        soraListViewModel =
                new ViewModelProvider(this).get(SoraListViewModel.class);

        binding = FragmentSoraListBinding.inflate(inflater, container, false);
        quran_sora_Recycler = binding.quranSoraRecycler;
        quran_jozz_Recycler = binding.quranJozzRecycler;
        View root = binding.getRoot();
        toolbar = binding.soraListToolbar;
        quranPreferences = new QuranPreferences(requireContext());
        quran_sora_list = new ArrayList<>();

        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //  quran_list_Rec.setAdapter(new SoraListAdapter(getContext(), soraListViewModel.getAllSora(getContext()), this));
        //   quran_list_Rec.setLayoutManager(new LinearLayoutManager(this));


        quran_sora_list = NewUtil.get_quran_surah(requireContext());
        quran_part_list = NewUtil.get_quran_part(requireContext());

        quran_sora_Recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        quran_jozz_Recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        quran_sora_Recycler.setAdapter(new SoraListAdapter(getContext(), quran_sora_list, this));
        quran_jozz_Recycler.setAdapter(new PartListAdapter(getContext(), quran_part_list, this));

        toolbar.setTitle("القران الكريم");

        toolbar.setOnMenuItemClickListener(item -> {


            switch (item.getItemId()) {

                case R.id.last_page:
                    NavHostFragment.findNavController
                            (SoraListFragment.this).navigate(SoraListFragmentDirections.
                            actionQuranFragmentToQuranContainerFragment(quranPreferences.getLast_page()));

                    return true;
            }
            return true;
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}