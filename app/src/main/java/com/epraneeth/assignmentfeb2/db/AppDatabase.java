package com.epraneeth.assignmentfeb2.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Entry.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EntryDao entryDao();
    private static AppDatabase INSTANCE;
    public static AppDatabase getDbInstance(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "MyDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

}
