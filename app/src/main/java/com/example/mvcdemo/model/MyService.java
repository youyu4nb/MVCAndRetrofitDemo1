package com.example.mvcdemo.model;

import com.example.mvcdemo.bean.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyService {
    @GET("index?")
    Call<News> getNews(@Query("type") String type,@Query("key") String key);
}
