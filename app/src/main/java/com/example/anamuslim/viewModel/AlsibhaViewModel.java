package com.example.anamuslim.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.anamuslim.pojo.alsibha.Alsibha;
import com.example.anamuslim.repository.AlsibhaRepository;

import java.util.List;

public class AlsibhaViewModel extends AndroidViewModel {
    AlsibhaRepository repository;
    int current_progress = 0;
    int max_progress = 0;
    boolean equal = false;


    public AlsibhaViewModel(Application application) {
        super(application);
        repository = new AlsibhaRepository(application);
    }

    public boolean isEqual() {
        return equal;
    }

    public void setEqual(boolean equal) {
        this.equal = equal;
    }

    public int getCurrent_progress() {
        return current_progress;
    }

    public void setCurrent_progress(int current_progress) {
        this.current_progress = current_progress;
    }

    public int getMax_progress() {
        return max_progress;
    }

    public void setMax_progress(int max_progress) {
        this.max_progress = max_progress;
    }

    public boolean checkEqual() {
        return getMax_progress() == getCurrent_progress();
    }


    public void increaseProgress() {

        current_progress++;
    }

    public LiveData<List<Alsibha>> readAllData() {


        return repository.readAllData();
    }

    public List<Alsibha> getElSibha_text() {
        return repository.readAllData2();
    }

    public void updateMax(int max) {

        repository.updateMax(max);

    }

    public void updateCurrent_7(int current, int id) {

        repository.updateCurrent_7(current, id);

    }

    public void updateCurrent_33(int current, int id) {

        repository.updateCurrent_33(current, id);

    }

    public void updateCurrent_100(int current, int id) {

        repository.updateCurrent_100(current, id);

    }

    public void updateCurrent_c(int current, int id) {

        repository.updateCurrent_c(current, id);

    }

    public void updatePosition(int position) {

        repository.updatePosition(position);

    }

    public void updateCurrent(int current, int id) {

        repository.updateCurrent(current, id);

    }
}