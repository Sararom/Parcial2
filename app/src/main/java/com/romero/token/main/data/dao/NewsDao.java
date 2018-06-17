package com.romero.token.main.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.romero.token.main.data.model.New;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NewsDao {
    @Query("SELECT * FROM news_table ORDER BY created_date DESC")
    LiveData<List<New>> getAllNews();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(New news);

}
