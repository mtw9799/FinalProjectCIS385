package com.example.finalprojectcis385;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Embedded;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Relation;

import java.util.List;

@Dao
public interface RecordDAO {

    @Query("SELECT * FROM reprecords")
    List<RepRecords> getAll();

    @Query("SELECT MAX(Weight) FROM reprecords WHERE Exercise = (:chosenExercise)")
    String loadAllByIds(String chosenExercise);

    @Query("INSERT INTO reprecords (Exercise, Weight, Reps) VALUES (:chosenExercise, :chosenWeight, :chosenReps)")
    void InsertNewRecord(String chosenExercise, Integer chosenWeight, Integer chosenReps);

}