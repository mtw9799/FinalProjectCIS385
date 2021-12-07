package com.example.finalprojectcis385;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class RepRecords {
    @PrimaryKey
    public int repRecordId;

    @ColumnInfo(name = "Exercise")
    public String exercise;

    @ColumnInfo(name = "Weight")
    public Integer weight;

    @ColumnInfo(name = "Reps")
    public Integer reps;
}
