package com.romero.token.main.data.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.romero.token.main.data.dao.NewsDao;
import com.romero.token.main.data.database.AppDatabase;
import com.romero.token.main.data.model.New;

import java.util.ArrayList;
import java.util.List;

public class NewRepository {
    private NewsDao mNewDao;
    private LiveData<List<New>> mAllNews;
    public NewRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mNewDao = db.newsDao();
        mAllNews = mNewDao.getAllNews();
    }

    public LiveData<List<New>> getmAllNews(){
        return mAllNews;
    }

    public void insert (New news){
        new insertAsyncTask(mNewDao).execute(news);
    }

    private static class insertAsyncTask extends AsyncTask<New, Void, Void> {

        private NewsDao mAsyncTaskDao;

        insertAsyncTask(NewsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final New... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
