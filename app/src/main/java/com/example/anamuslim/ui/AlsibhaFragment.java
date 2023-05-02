package com.example.anamuslim.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.anamuslim.R;
import com.example.anamuslim.adapter.AlSibhaPageAdapter;
import com.example.anamuslim.databinding.BottomDialogBinding;
import com.example.anamuslim.databinding.FragmentAlsibhaBinding;
import com.example.anamuslim.pojo.alsibha.Alsibha;
import com.example.anamuslim.viewModel.AlsibhaViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import nl.bryanderidder.themedtogglebuttongroup.ThemedButton;
import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup;

public class AlsibhaFragment extends Fragment {

    private AlsibhaViewModel mViewModel;
    private FragmentAlsibhaBinding binding;

    List<Alsibha> elSibha_text = new ArrayList<>();
    ImageButton alsibha_edit_button, alsibha_button;
    TextView tv_tasbiha_count, total, creent;

    int current, position1, c;
    ViewPager2 alSibha_view_pager;
    public AlSibhaPageAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(AlsibhaViewModel.class);
        binding = FragmentAlsibhaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        alsibha_edit_button = binding.alsibhaEditButton;
        alsibha_button = binding.alsibhaButton;
        alSibha_view_pager = binding.alSibhaViewPager;
        total = binding.total;
        creent = binding.creent;
        adapter = new AlSibhaPageAdapter(getContext(), elSibha_text);
        alSibha_view_pager.setAdapter(adapter);

        mViewModel.readAllData().observe(getViewLifecycleOwner(), new Observer<List<Alsibha>>() {
            @Override
            public void onChanged(List<Alsibha> alsibhas) {
                setElSibha_text(alsibhas);
                adapter.setAlsibha_text(alsibhas);
                c = elSibha_text.get(0).getMax();
                position1 = elSibha_text.get(0).getPosition();

                //  Log.d(TAG, "onChanged: " + position1);
            }

        });

        //


        return root;

    }

    public void setElSibha_text(List<Alsibha> elSibha_text) {
        this.elSibha_text = elSibha_text;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        alSibha_view_pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                bottomDialog(position);
                mViewModel.updatePosition(position);
                update(position);
                // setUi(position);
            }


        });
        total.setText(String.valueOf(mViewModel.getElSibha_text().get(0).getMax()));
        creent.setText(String.valueOf(mViewModel.getElSibha_text().get(0).getCurrent()));

    }

    @SuppressLint("ResourceAsColor")
    private void bottomDialog(int position) {


        alsibha_edit_button.setOnClickListener(v -> {
            BottomSheetDialog sheetDialog = new BottomSheetDialog(requireContext(), R.style.bottomSheetStyle);

            LayoutInflater sheetView = LayoutInflater.from(requireContext());

            BottomDialogBinding binding = BottomDialogBinding.inflate(sheetView);

            ToggleButton bottom_dialog_alSibha_7 = binding.bottomDialogAlSibha7;
            ToggleButton bottom_dialog_alSibha_100 = binding.bottomDialogAlSibha100;
            ToggleButton bottom_dialog_alSibha_33 = binding.bottomDialogAlSibha33;
            ToggleButton bottom_dialog_alSibha_c = binding.bottomDialogAlSibhaC;
            TextView ok = binding.ok;

            ImageView colse = binding.closeButton;

            check(bottom_dialog_alSibha_7, bottom_dialog_alSibha_100, bottom_dialog_alSibha_33, bottom_dialog_alSibha_c, position, ok);


            bottom_dialog_alSibha_7.setOnClickListener(v1 -> {
                bottom_dialog_alSibha_7.setChecked(true);
                bottom_dialog_alSibha_100.setChecked(false);
                bottom_dialog_alSibha_33.setChecked(false);
                bottom_dialog_alSibha_c.setChecked(false);

                if (elSibha_text.get(position).getMax() == 7) {
                    ok.setTextColor(R.color.gray);
                } else {
                    ok.setTextColor(R.color.mainColor);
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v1) {

                            okButton(bottom_dialog_alSibha_7, bottom_dialog_alSibha_100, bottom_dialog_alSibha_33, bottom_dialog_alSibha_c, sheetDialog);

                        }
                    });
                }
            });

            bottom_dialog_alSibha_100.setOnClickListener(v12 -> {
                bottom_dialog_alSibha_7.setChecked(false);
                bottom_dialog_alSibha_100.setChecked(true);
                bottom_dialog_alSibha_33.setChecked(false);
                bottom_dialog_alSibha_c.setChecked(false);


                if (elSibha_text.get(position).getMax() == 100) {
                    ok.setTextColor(R.color.gray);


                } else {
                    ok.setTextColor(R.color.mainColor);
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v12) {
                            okButton(bottom_dialog_alSibha_7, bottom_dialog_alSibha_100, bottom_dialog_alSibha_33, bottom_dialog_alSibha_c, sheetDialog);
                        }
                    });
                }

            });
            bottom_dialog_alSibha_33.setOnClickListener(v13 -> {
                bottom_dialog_alSibha_7.setChecked(false);
                bottom_dialog_alSibha_100.setChecked(false);
                bottom_dialog_alSibha_33.setChecked(true);
                bottom_dialog_alSibha_c.setChecked(false);

                if (elSibha_text.get(position).getMax() == 33) {
                    ok.setTextColor(R.color.gray);
                } else {
                    ok.setTextColor(R.color.mainColor);
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v13) {
                            okButton(bottom_dialog_alSibha_7, bottom_dialog_alSibha_100, bottom_dialog_alSibha_33, bottom_dialog_alSibha_c, sheetDialog);


                        }
                    });
                }

            });
            bottom_dialog_alSibha_c.setOnClickListener(v14 -> {
                bottom_dialog_alSibha_7.setChecked(false);
                bottom_dialog_alSibha_100.setChecked(false);
                bottom_dialog_alSibha_33.setChecked(false);
                bottom_dialog_alSibha_c.setChecked(true);

                if (elSibha_text.get(position).getMax() == 99) {
                    ok.setTextColor(R.color.gray);
                } else {
                    ok.setTextColor(R.color.mainColor);
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v14) {

                            okButton(bottom_dialog_alSibha_7, bottom_dialog_alSibha_100, bottom_dialog_alSibha_33, bottom_dialog_alSibha_c, sheetDialog);

                        }
                    });
                }
            });

            colse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sheetDialog.cancel();
                }
            });


            sheetDialog.setContentView(binding.getRoot());
            sheetDialog.show();
        });

    }

    @SuppressLint("ResourceAsColor")
    private void check(ToggleButton bottom_dialog_alSibha_7, ToggleButton bottom_dialog_alSibha_100, ToggleButton bottom_dialog_alSibha_33, ToggleButton bottom_dialog_alSibha_c, int pois, TextView ok) {
        int c = elSibha_text.get(pois).getMax();

        switch (c) {
            case 33:
                bottom_dialog_alSibha_7.setChecked(false);
                bottom_dialog_alSibha_100.setChecked(false);
                bottom_dialog_alSibha_33.setChecked(true);
                bottom_dialog_alSibha_c.setChecked(false);
                if (elSibha_text.get(pois).getMax() == 33) {
                    ok.setTextColor(R.color.gray);
                } else {
                    ok.setTextColor(R.color.mainColor);
                }
                break;
            case 7:
                bottom_dialog_alSibha_7.setChecked(true);
                bottom_dialog_alSibha_100.setChecked(false);
                bottom_dialog_alSibha_33.setChecked(false);
                bottom_dialog_alSibha_c.setChecked(false);
                if (elSibha_text.get(pois).getMax() == 7) {
                    ok.setTextColor(R.color.gray);
                } else {
                    ok.setTextColor(R.color.mainColor);
                }
                break;

            case 100:
                bottom_dialog_alSibha_7.setChecked(false);
                bottom_dialog_alSibha_100.setChecked(true);
                bottom_dialog_alSibha_33.setChecked(false);
                bottom_dialog_alSibha_c.setChecked(false);
                if (elSibha_text.get(pois).getMax() == 100) {
                    ok.setTextColor(R.color.gray);
                } else {
                    ok.setTextColor(R.color.mainColor);
                }
                break;
            case 99:
                bottom_dialog_alSibha_7.setChecked(false);
                bottom_dialog_alSibha_100.setChecked(false);
                bottom_dialog_alSibha_33.setChecked(false);
                bottom_dialog_alSibha_c.setChecked(true);
                if (elSibha_text.get(pois).getMax() == 99) {
                    ok.setTextColor(R.color.gray);
                } else {
                    ok.setTextColor(R.color.mainColor);
                }
                break;

        }

    }

    private void okButton(ToggleButton bottom_dialog_alSibha_7, ToggleButton bottom_dialog_alSibha_100, ToggleButton bottom_dialog_alSibha_33, ToggleButton bottom_dialog_alSibha_c, BottomSheetDialog sheetDialog) {

        if (bottom_dialog_alSibha_7.isChecked()) {
            mViewModel.updateMax(7);
            creent.setText(String.valueOf(elSibha_text.get(position1).getCurrent_7()));
            total.setText(String.valueOf(7));

            sheetDialog.cancel();
        } else if (bottom_dialog_alSibha_33.isChecked()) {
            mViewModel.updateMax(33);
            creent.setText((String.valueOf(elSibha_text.get(position1).getCurrent_33())));
            total.setText(String.valueOf(33));

            sheetDialog.cancel();
        } else if (bottom_dialog_alSibha_100.isChecked()) {
            mViewModel.updateMax(100);
            creent.setText((String.valueOf(elSibha_text.get(position1).getCurrent_100())));
            total.setText(String.valueOf(100));

            sheetDialog.cancel();
        } else if (bottom_dialog_alSibha_c.isChecked()) {
            mViewModel.updateMax(99);
            creent.setText((String.valueOf(elSibha_text.get(position1).getCurrent_c())));
            total.setText(String.valueOf(99));

            sheetDialog.cancel();
        }

    }

    private void update(int poi) {
        setUi(poi);

        alsibha_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c = elSibha_text.get(poi).getMax();


                switch (c) {
                    case 33:
                        current = elSibha_text.get(poi).getCurrent_33();
                        Toast.makeText(getContext(), "nnnnnn" + current, Toast.LENGTH_SHORT).show();
                        if (elSibha_text.get(poi).getMax() == current) {
                            //  mViewModel.setCurrent_progress(0);


                            //  alsibha_progress.setProgress(0);
                            current = 1;
                            mViewModel.updateCurrent_33(current, poi);
                            // elSibha_text = repository.readAllData();
                            creent.setText(String.valueOf(current));

                        } else {

                            current++;

                            mViewModel.updateCurrent_33(current, poi);
                            // elSibha_text = repository.readAllData();
                            creent.setText(String.valueOf(current));
                            // Toast.makeText(getContext(), "dsdd" + current, Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 7:
                        current = elSibha_text.get(poi).getCurrent_7();
                        if (elSibha_text.get(poi).getMax() == current) {
                            //  mViewModel.setCurrent_progress(0);


                            //  alsibha_progress.setProgress(0);
                            current = 1;
                            mViewModel.updateCurrent_7(current, poi);
                            // elSibha_text = repository.readAllData();
                            creent.setText(String.valueOf(current));

                            Toast.makeText(getContext(), "dsdd" + current, Toast.LENGTH_SHORT).show();
                        } else {
                            current++;

                            mViewModel.updateCurrent_7(current, poi);
                            // elSibha_text = repository.readAllData();
                            creent.setText(String.valueOf(current));
                            // Toast.makeText(getContext(), "dsdd" + current, Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case 100:
                        current = elSibha_text.get(poi).getCurrent_100();
                        if (elSibha_text.get(poi).getMax() == current) {
                            //  mViewModel.setCurrent_progress(0);


                            //  alsibha_progress.setProgress(0);
                            current = 1;
                            mViewModel.updateCurrent_100(current, poi);
                            // elSibha_text = repository.readAllData();
                            creent.setText(String.valueOf(current));

                            Toast.makeText(getContext(), "dsdd" + current, Toast.LENGTH_SHORT).show();
                        } else {
                            current++;

                            mViewModel.updateCurrent_100(current, poi);
                            // elSibha_text = repository.readAllData();
                            creent.setText(String.valueOf(current));
                            // Toast.makeText(getContext(), "dsdd" + current, Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 99:
                        current = elSibha_text.get(poi).getCurrent_c();
                        if (elSibha_text.get(poi).getMax() == current) {
                            //  mViewModel.setCurrent_progress(0);


                            //  alsibha_progress.setProgress(0);
                            current = 1;
                            mViewModel.updateCurrent_c(current, poi);
                            //   elSibha_text = repository.readAllData();
                            creent.setText(String.valueOf(current));

                            Toast.makeText(getContext(), "dsdd" + current, Toast.LENGTH_SHORT).show();
                        } else {
                            current++;

                            mViewModel.updateCurrent_c(current, poi);
                            // elSibha_text = repository.readAllData();
                            creent.setText(String.valueOf(current));
                            // Toast.makeText(getContext(), "dsdd" + current, Toast.LENGTH_SHORT).show();
                        }
                        break;

                }
                mViewModel.updateCurrent(current, poi);

            }
        });

    }

    private void setUi(int position) {
        int c = elSibha_text.get(position).getMax();
        switch (c) {
            case 33:
                creent.setText(String.valueOf(elSibha_text.get(position).getCurrent_33()));


                break;
            case 7:
                creent.setText(String.valueOf(elSibha_text.get(position).getCurrent_7()));

                break;

            case 100:
                creent.setText(String.valueOf(elSibha_text.get(position).getCurrent_100()));

                break;
            case 99:
                creent.setText(String.valueOf(elSibha_text.get(position).getCurrent_c()));

                break;

        }
        total.setText(String.valueOf(elSibha_text.get(position).getMax()));
    }
}