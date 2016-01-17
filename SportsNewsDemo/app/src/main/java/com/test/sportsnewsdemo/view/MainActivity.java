package com.test.sportsnewsdemo.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.sportsnewsdemo.R;
import com.test.sportsnewsdemo.adapter.RecyclerViewAdpater;
import com.test.sportsnewsdemo.bean.News;
import com.test.sportsnewsdemo.parser.ParseHtml;
import com.test.sportsnewsdemo.presenter.NewsPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements IMainView {


    List<News> mDatas = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerViewAdpater mAdapter;
    private ProgressDialog mProgressDialog;

    private NewsPresenter mNewsPresenter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initData();
    }

    private void initData() {
        mNewsPresenter = new NewsPresenter(this);
        mNewsPresenter.fetchNews();
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new RecyclerViewAdpater(MainActivity.this, mDatas);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void showLoading() {
        mProgressDialog = ProgressDialog.show(this, null, "正在加载数据...");
    }

    @Override
    public void hideLoading() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showNewsList(List<News> mDatas) {
        mAdapter.setmDatas(mDatas);
    }
}
