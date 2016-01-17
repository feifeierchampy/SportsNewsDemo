package com.test.sportsnewsdemo.presenter;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.test.sportsnewsdemo.NewsListener;
import com.test.sportsnewsdemo.bean.News;
import com.test.sportsnewsdemo.model.NewsModel;
import com.test.sportsnewsdemo.utils.Logger;
import com.test.sportsnewsdemo.view.IMainView;

import java.util.List;

/**
 * Created by CHAMPION on 2016/1/17.
 */
public class NewsPresenter implements NewsListener {
    private static final int FETCH_NEWS_SUCCESS = 0x111;
    private IMainView mMainView;
    private NewsModel mNewsModel;
    private Handler mUIHandler;

    public final String TAG = this.getClass().getSimpleName() + ":================";


    public NewsPresenter(final IMainView mMainView) {
        this.mMainView = mMainView;
        mNewsModel = new NewsModel(this);
        mUIHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == FETCH_NEWS_SUCCESS) {
                    List<News> mTemp = (List<News>) msg.obj;
                    mMainView.showNewsList(mTemp);
                }
            }
        };
    }

    public void fetchNews() {
        mMainView.showLoading();
        mNewsModel.getNewsFromInternet();
    }


    @Override
    public void onComplete(List<News> mNewsList) {
        mMainView.hideLoading();
        Logger.show(TAG, "onComplete()");

        /**
         * 这里若直接调用showNewsList()，就会发生
         * android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
         * 只能在主线程中更新UI
         * 所以就用Handler了
         */
        Message msg = Message.obtain();
        msg.what = FETCH_NEWS_SUCCESS;
        msg.obj = mNewsList;
        mUIHandler.sendMessage(msg);
    }
}
