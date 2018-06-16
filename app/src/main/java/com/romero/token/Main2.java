package com.romero.token;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Switch;

import com.romero.token.main.data.model.New;
import com.romero.token.main.data.model.Post;
import com.romero.token.main.data.remote.APIService;
import com.romero.token.main.data.remote.ApiUtils;
import com.romero.token.main.fragments.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2 extends AppCompatActivity{

    private APIService mService;
    //private TextView mResponseTv;

    RecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;
    Post[] aNews;
    ArrayList<New> noticias = new ArrayList<>();
    private static Context sContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String s = getIntent().getStringExtra("TOKEN_ID");
        mService = ApiUtils.getAPIService();
        loadAnswers(s);

        sContext=getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.recyclerV);
        final GridLayoutManager gridMngr = new GridLayoutManager(this,2);

        gridMngr.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {
                switch (position%3){
                    case 0:
                        return 2;
                    default:
                        return 1;
                }
            }
        });
        mRecyclerView.setLayoutManager(gridMngr);


    }

    public void loadAnswers(String s) {
            mService.get(s).enqueue(new Callback<Post[]>() {
                @Override
                public void onResponse(Call<Post[]> call, Response<Post[]> response) {

                    if(response.isSuccessful()) {
                        aNews = response.body();

                        for (int i=0;i<aNews.length;i++){
                            New noticia = new New(aNews[i].getId(),aNews[i].getTitle(),
                                    aNews[i].getBody(),aNews[i].getGame(),aNews[i].getCoverImage(),
                                    aNews[i].getCreatedDate(),aNews[i].getV(),aNews[i].getDescription());
                            noticias.add(noticia);
                        }
                        adapter =new NewsAdapter(noticias);
                        mRecyclerView.setAdapter(adapter);

                        Log.d("MainActivity", "posts loaded from API");
                    }else {
                        int statusCode  = response.code();
                        Log.d("RESPONSE CODE: ", ""+statusCode);
                        // handle request errors depending on status code
                    }
                }

                @Override
                public void onFailure(Call<Post[]> call, Throwable t) {
                    Log.d("MainActivity 2", "error loading from API");
                    Log.d("Message ",t.toString());

                }
            });
        }

        public static Context getmain2Context(){
            return sContext;
        }
}
