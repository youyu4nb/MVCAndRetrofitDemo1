package com.example.mvcdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvcdemo.bean.News;
import com.example.mvcdemo.model.NewsModel;
import com.example.mvcdemo.model.NewsModelImpl;

public class MainActivity extends AppCompatActivity implements NewsModel.OnNewsListener {
    private EditText editText = null;
    private Button button = null;
    private TextView title, date, category, author_name = null;

    private NewsModelImpl newsModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsModel = new NewsModelImpl();
        initView();
    }

    public void initView() {
        editText = findViewById(R.id.edit);
        button = findViewById(R.id.button);
        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        category = findViewById(R.id.category);
        author_name = findViewById(R.id.author_name);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = editText.getText().toString();
                if (!type.equals("")) {
                    newsModel.getNews(type, MainActivity.this);
                } else {
                    Toast.makeText(MainActivity.this, "输入的新闻标题不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //设置数据
    public void setView(News news) {
        title.setText(news.getResult().getData().get(1).getTitle());
        date.setText(news.getResult().getData().get(1).getDate());
        category.setText(news.getResult().getData().get(1).getCategory());
        author_name.setText(news.getResult().getData().get(1).getAuthor_name());
    }

    @Override
    public void onSuccess(News news) {
        setView(news);
    }

    @Override
    public void onError() {
        Toast.makeText(this, "出错了哦！", Toast.LENGTH_SHORT).show();
    }
}

