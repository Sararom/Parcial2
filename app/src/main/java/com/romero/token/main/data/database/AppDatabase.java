package com.romero.token.main.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.romero.token.Main2;
import com.romero.token.MainActivity;
import com.romero.token.main.data.dao.NewsDao;
import com.romero.token.main.data.model.New;

@Database(entities = {New.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDao newsDao();

    private static AppDatabase INSTANCE;


    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(MainActivity.getmainContext(),
                            AppDatabase.class, "news_database")
                            .build();

                }
            }
        }
        return INSTANCE;
    }

}
