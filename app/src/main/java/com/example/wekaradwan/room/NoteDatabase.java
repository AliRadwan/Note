package com.example.wekaradwan.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by weka radwan on 1/16/2019.
 */

// We make notation her to define the  DB.

/**
 * (entities = {table1(entity)},{table2(entity)},...., version = 1 when we chane any thing of the DB we change the version)
 */
@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    // We create object from DB class call instance to use it with the singleTune
    private static NoteDatabase instance;

    // This to access our Dao
    public abstract NoteDao noteDao();


    // Create our DB , But first we check if it was create or not
    // we doing singleTune because we want to be sure that only one object will take from this class, we don't want to create problem with DB.
    //  {synchronized } mean that: only one thread at the time can Access this method
    public static synchronized NoteDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){

        //  Call in the first time when the DB Create
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // to execute populate
            new PopulateDbAsyncTask(instance).execute();
        }

    };



    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1", "Description 1", 1));
            noteDao.insert(new Note("Title 2", "Description 2", 2));
            noteDao.insert(new Note("Title 3", "Description 3", 3));
            return null;
        }
    }
}
