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

    //Disregard this query, the other three are called at some point
    @Query("SELECT * FROM reprecords")
    List<RepRecords> getAll();

    @Query("SELECT MAX(Weight) FROM reprecords WHERE Exercise = (:chosenExercise)")
    Integer loadAllByIds(String chosenExercise);

    @Query("SELECT MAX(Reps) FROM reprecords WHERE Exercise = (:chosenExercise) AND Weight = :chosenWeight")
    Integer getMaxReps(String chosenExercise, Integer chosenWeight);

    @Query("INSERT INTO reprecords (Exercise, Weight, Reps) VALUES (:chosenExercise, :chosenWeight, :chosenReps)")
    void InsertNewRecord(String chosenExercise, Integer chosenWeight, Integer chosenReps);

}