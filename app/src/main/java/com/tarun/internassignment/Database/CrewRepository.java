package com.tarun.internassignment.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.tarun.internassignment.Models.RoomCrewModel;

import java.util.List;

public class CrewRepository {

    private CrewDao crewDao;
    private LiveData<List<RoomCrewModel>> allCrewData;

    public CrewRepository(Application application){
        CrewDatabase database = CrewDatabase.getInstance(application);
        crewDao = database.crewDao();
        allCrewData = crewDao.getAlldata();
    }

    public void insert(RoomCrewModel roomCrewModel){
        new InsertCrewAsyncTask(crewDao).execute(roomCrewModel);
    }

    public void delete(){
        new DeleteCrewAsyncTask(crewDao).execute();
    }

    public LiveData<List<RoomCrewModel>> getAllCrewData(){
        return allCrewData;
    }

    private static class InsertCrewAsyncTask extends AsyncTask<RoomCrewModel, Void, Void>{

        private CrewDao crewDao;

        private InsertCrewAsyncTask(CrewDao crewDao){
            this.crewDao=crewDao;
        }
        @Override
        protected Void doInBackground(RoomCrewModel... roomCrewModels) {
                crewDao.insert(roomCrewModels[0]);
            return null;
        }
    }

    private static class DeleteCrewAsyncTask extends AsyncTask<Void, Void, Void>{

        private CrewDao crewDao;

        private DeleteCrewAsyncTask(CrewDao crewDao){
            this.crewDao=crewDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
                crewDao.deleteAll();
            return null;
        }
    }
}
