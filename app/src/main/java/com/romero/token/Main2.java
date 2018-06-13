package com.romero.token;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.romero.token.main.data.model.Post;
import com.romero.token.main.data.remote.APIService;
import com.romero.token.main.data.remote.ApiUtils;
import com.romero.token.main.fragments.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2 extends AppCompatActivity {

    private APIService mService;
    //private TextView mResponseTv;

    RecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;
    Post[] aNews;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String s = getIntent().getStringExtra("TOKEN_ID");
        mService = ApiUtils.getAPIService();
        loadAnswers(s);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.recyclerV);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void loadAnswers(String s) {
            mService.get(s).enqueue(new Callback<Post[]>() {
                @Override
                public void onResponse(Call<Post[]> call, Response<Post[]> response) {

                    if(response.isSuccessful()) {
                        aNews = response.body();
                        adapter =new NewsAdapter(aNews);
                        mRecyclerView.setAdapter(adapter);
                        //mResponseTv.setText(aNews[0].getTitle());

                        Log.d("MainActivity", "posts loaded from API");
                    }else {
                        int statusCode  = response.code();
                        Log.d("RESPONSE CODE: ", ""+statusCode);
                        // handle request errors depending on status code
                    }
                }

                @Override
                public void onFailure(Call<Post[]> call, Throwable t) {
                    //                showErrorMessage();
                    Log.d("MainActivity", "error loading from API");
                    Log.d("Message ",t.toString());

                }
            });
        }
}
