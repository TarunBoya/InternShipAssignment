package com.tarun.internassignment.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.tarun.internassignment.Models.RoomCrewModel;

import java.util.List;

@Dao
public interface CrewDao {

    @Insert
    void insert(RoomCrewModel roomCrewModel);

    @Query("SELECT * FROM crew_table ORDER BY id ASC")
    LiveData<List<RoomCrewModel>> getAlldata();

    @Query("DELETE FROM crew_table")
    void deleteAll();
}
