package com.example.wekaradwan.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by weka radwan on 1/16/2019.
 */

// name of the table in the DB.
@Entity(tableName = "note_table")
public class Note {

    // Table Colume
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private int priority;

    // constructor
    // we did not put the id because id will be auto generate
    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;

    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
