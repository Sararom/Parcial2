package com.romero.token.main.data.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.romero.token.main.data.repository.NewRepository;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends AndroidViewModel {

    private NewRepository mRepository;
    private LiveData<List<New>> mAllNews;

    public NewsViewModel(Application application){
        super(application);
        mRepository = new NewRepository(application);
        mAllNews= mRepository.getmAllNews();
    }

    public LiveData<List<New>> getAllNews() { return mAllNews; }

    public void insert(New news){ mRepository.insert(news);}

}
