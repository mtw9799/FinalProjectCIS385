package com.example.finalprojectcis385;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {RepRecords.class}, version = 1)
public abstract class AppDatabaseReps extends RoomDatabase {
    public abstract RecordDAO recordDAO();
}
