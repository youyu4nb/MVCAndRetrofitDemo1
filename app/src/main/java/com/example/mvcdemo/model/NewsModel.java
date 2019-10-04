package com.example.mvcdemo.model;

import com.example.mvcdemo.bean.News;

public interface NewsModel {
    void getNews(String name, OnNewsListener mlistener);

    interface OnNewsListener {

        void onSuccess(News news);

        void onError();
    }
}
