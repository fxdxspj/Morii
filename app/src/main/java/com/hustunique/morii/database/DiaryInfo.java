package com.hustunique.morii.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DiaryInfo {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String article;
    public String imagePath;
    public int musicTabId;
    public String date;
}

