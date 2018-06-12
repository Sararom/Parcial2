package com.romero.token;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.romero.token.main.data.model.Post;
import com.romero.token.main.data.remote.APIService;
import com.romero.token.main.data.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2 extends AppCompatActivity {

    private APIService mService;
    private TextView mResponseTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mResponseTv = (TextView) findViewById(R.id.testId);
        setSupportActionBar(toolbar);

        String s = getIntent().getStringExtra("TOKEN_ID");

        mService = ApiUtils.getAPIService();
        loadAnswers(s);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void loadAnswers(String s) {
            mService.get(s).enqueue(new Callback<Post[]>() {
                @Override
                public void onResponse(Call<Post[]> call, Response<Post[]> response) {

                    if(response.isSuccessful()) {
                        Post[] news = response.body();
                        //mResponseTv.setText(news[0].getTitle());

                        for(int i=0;i<news.length;i++){
                            mResponseTv.append(news[i].getTitle());
                            mResponseTv.append("\n");
                        }

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

    public void showResponse(Post[] response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
   }

}
