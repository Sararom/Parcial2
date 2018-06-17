package com.romero.token;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.romero.token.main.data.model.Post;
import com.romero.token.main.data.model.Token;
import com.romero.token.main.data.remote.APIService;
import com.romero.token.main.data.remote.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private TextView mResponseTv;
    private APIService mAPIService;
    public String token;
    private static Context sContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText titleEt = (EditText) findViewById(R.id.et_title);
        final EditText bodyEt = (EditText) findViewById(R.id.et_body);
        Button submitBtn = (Button) findViewById(R.id.btn_submit);
        mResponseTv = (TextView) findViewById(R.id.tv_response);

        mAPIService = ApiUtils.getAPIService();

        sContext=getApplicationContext();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEt.getText().toString().trim();
                String body = bodyEt.getText().toString().trim();
                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)) {
                    sendPost(title, body);
                }
            }
        });
    }

    public void sendPost(final String title, final String body) {
        mAPIService.savePost(title, body).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Post>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("SDW",body);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Post post) {
                        //showResponse(post.toString());
                        Token tokenBd = new Token(post.getToken());

                        Log.d("token: ",post.getToken());
                        initMain2(tokenBd.getToken());
                    }
                });

    }

    public void initMain2(String token){
        Intent intent =new Intent(this, Main2.class);
        intent.putExtra("TOKEN_ID",token);
        Log.d("SI OBTIENE TOKEN",token);
        startActivity(intent);
    }

    public static Context getmainContext(){
        return sContext;
    }

}

