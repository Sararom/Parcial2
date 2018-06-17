package com.romero.token;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.romero.token.main.data.model.New;
import com.romero.token.main.data.model.NewsViewModel;
import com.romero.token.main.data.model.Post;
import com.romero.token.main.data.remote.APIService;
import com.romero.token.main.data.remote.ApiUtils;
import com.romero.token.main.data.model.NewsAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2 extends AppCompatActivity{

    private APIService mService;
    //private TextView mResponseTv;

    RecyclerView mRecyclerView;
    NewsAdapter adapter;
    Post[] aNews;
    NewsViewModel mNewsViewModel = new NewsViewModel(getApplication());
    ArrayList<New> noticias = new ArrayList<New>();
    private static Context sContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String s = getIntent().getStringExtra("TOKEN_ID");
        mService = ApiUtils.getAPIService();
        //NewsViewModel mNewsViewModel = new NewsViewModel(getApplication());
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
                            mNewsViewModel.insert(noticia);

                        }

                        adapter =new NewsAdapter(noticias);
                        mRecyclerView.setAdapter(adapter);

                        adapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                //Snackbar.make(mRecyclerView,"This is the "+position+" position", Snackbar.LENGTH_LONG).show();
                                New news = noticias.get(position);
                                Intent intent = new Intent(getmain2Context(),FullNew.class);
                                intent.putExtra("NOTICIA",news);
                                startActivity(intent);
                            }
                        });

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
