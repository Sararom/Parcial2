package com.romero.token.main.data.remote;

import com.romero.token.main.data.model.Post;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface APIService {
    @POST("/login")
    @FormUrlEncoded
    Observable<Post> savePost(@Field("user") String user,
                              @Field("password") String pass);
}

