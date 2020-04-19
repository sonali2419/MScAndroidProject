package com.example.uitestingapplication.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.uitestingapplication.db.entity.Registration;
import com.example.uitestingapplication.db.entity.Report;
import com.example.uitestingapplication.db.repo.RegistrationRepo;
import com.example.uitestingapplication.db.repo.ReportRepo;

@Database(entities = {Registration.class, Report.class  },version = 1)
public abstract class MedicareAppDatabase extends RoomDatabase {

    public static volatile MedicareAppDatabase database;

    public  abstract RegistrationRepo getRegistrationRepo();
    public abstract ReportRepo getReportRepo();

    public static MedicareAppDatabase getDatabase(final Context context){
        if (database==null){
            synchronized (MedicareAppDatabase.class){
                if (database == null){
                    database = Room.databaseBuilder(context.getApplicationContext(),
                            MedicareAppDatabase.class,
                            "medicareDB").build();
                }
            }
        }
        return database;
    }

}
