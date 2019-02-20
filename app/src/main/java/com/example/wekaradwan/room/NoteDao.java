package com.example.wekaradwan.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by weka radwan on 1/16/2019.
 */

// Dao :- Data Access Object
    // This is the operation that we will doing it on the DB
@Dao
public interface NoteDao {

    // All of this method dose not Return anything so we make it void

    @Insert
    void insert(Note note);

    // Ctrl+B will give you full info .
    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    // This will display all table entity and it's return type as List of date and we but them in LiveData
    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();


}
