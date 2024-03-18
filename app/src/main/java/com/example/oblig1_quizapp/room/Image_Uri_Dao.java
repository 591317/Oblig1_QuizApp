package com.example.oblig1_quizapp.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Image_Uri_Dao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ImageAndUri_Entity uriandname);

    @Query("DELETE FROM imageAnduri_table WHERE id = :id")
    void deleteImage(int id);

    @Query("SELECT * FROM imageAnduri_table")
    LiveData<List<ImageAndUri_Entity>> getAllUriandNames();
}
