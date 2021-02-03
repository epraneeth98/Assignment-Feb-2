package com.epraneeth.assignmentfeb2.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.nio.file.attribute.AclEntryType;
import java.util.List;

@Dao
public interface EntryDao {
    @Query(("SELECT * FROM entry ORDER BY uid DESC"))
    List<Entry> getAllEntries();

    @Query("SELECT * FROM entry WHERE uid=:id")
    Entry getEntry(long id);

    @Insert
    void insertEntry(Entry entry);

    @Delete
    void delete(Entry entry);

    @Update
    void update(Entry entry);
}
