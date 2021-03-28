package com.tarun.internassignment.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.tarun.internassignment.Models.RoomCrewModel;


@Database(entities = {RoomCrewModel.class},version = 6)
public abstract class CrewDatabase extends RoomDatabase {

    private static volatile CrewDatabase instance;

    public abstract CrewDao crewDao();

    public static synchronized CrewDatabase getInstance( final Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CrewDatabase.class,"crew_database")
                    .addMigrations(new Migration(6,6) {
                        @Override
                        public void migrate(@NonNull SupportSQLiteDatabase database) {

                        }
                    })
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
