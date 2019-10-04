package com.example.mvcdemo.model;

import android.util.Log;

import com.example.mvcdemo.bean.News;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsModelImpl implements NewsModel{

    private final String baseUrl = "http://v.juhe.cn/toutiao/";

    private final String key = "3593f48dd34486e85d9fc451315c56c8";

    @Override
    public void getNews(String type, final NewsModel.OnNewsListener mlistener) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client.build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyService service = retrofit.create(MyService.class);
        Call<News> call = service.getNews(type,key);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                News news = response.body();
                if (news.getReason().equals("成功的返回")) {
                    Log.e("news", "成功");
                    mlistener.onSuccess(news);
                } else {
                    Log.e("news", "失败");
                    mlistener.onError();
                }
            }
            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.d("aaa", "onFailure: " + t.getMessage());
                t.getStackTrace();
                mlistener.onError();
            }
        });
    }

}
