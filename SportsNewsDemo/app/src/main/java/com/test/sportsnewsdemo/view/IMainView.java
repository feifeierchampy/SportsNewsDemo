package com.test.sportsnewsdemo.view;

import com.test.sportsnewsdemo.bean.News;

import java.util.List;

/**
 * Created by CHAMPION on 2016/1/17.
 */
public interface IMainView {

    //显示进度条
    void showLoading();

    //隐藏进度条
    void hideLoading();

    //显示数据
    void showNewsList(List<News> mDatas);
}
