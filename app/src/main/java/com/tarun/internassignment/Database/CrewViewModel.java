package com.tarun.internassignment.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tarun.internassignment.Models.RoomCrewModel;

import java.util.List;

public class CrewViewModel extends AndroidViewModel {

    private CrewRepository repository;
    private LiveData<List<RoomCrewModel>> allData;
    public CrewViewModel(@NonNull Application application) {
        super(application);
        repository = new CrewRepository(application);
        allData = repository.getAllCrewData();
    }

    public void insert(RoomCrewModel roomCrewModel){
        repository.insert(roomCrewModel);
    }

    public void delete(){
        repository.delete();
    }

    public LiveData<List<RoomCrewModel>> getAllData(){
        return allData;
    }
}
