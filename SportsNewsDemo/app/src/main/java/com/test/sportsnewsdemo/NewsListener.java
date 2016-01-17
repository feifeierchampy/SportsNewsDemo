package com.test.sportsnewsdemo;


import com.test.sportsnewsdemo.bean.News;

import java.util.List;

/**
 * Created by CHAMPION on 2016/1/17.
 *
 * 数据获取监听器
 * 采取回调的方式 当Model获取数据完成后回调Presenter实现的
 * onComplete()方法，进行数据传输
 */
public interface NewsListener {
    void onComplete(List<News> mNewsList);
}
