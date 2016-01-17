package com.test.sportsnewsdemo.model;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;

import com.test.sportsnewsdemo.NewsListener;
import com.test.sportsnewsdemo.bean.News;
import com.test.sportsnewsdemo.utils.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CHAMPION on 2016/1/17.
 */
public class NewsModel {

    public final String TAG = this.getClass().getSimpleName() + ":================";
    public final String URL = "http://www.dongqiudi.com/";

    private List<News> mNewsList = new ArrayList<>();

    private NewsListener mNewsListener;


    public NewsModel(NewsListener mNewsListener) {
        this.mNewsListener = mNewsListener;
    }

    /**
     * 从网络获取数据
     * 新开启一个子线程
     */
    public void getNewsFromInternet() {

        new Thread("CrawlHtml") {
            @Override
            public void run() {
                Document doc = null;
                try {
                    doc = Jsoup.connect(URL).userAgent("Mozilla").get();
                    Elements mElements = doc.getElementsByAttributeValue("class", "img_box");

                    for (Element element : mElements) {

                        //图片url
                        String imgSrc = element.child(0).attr("src");

                        //得到下一个同级元素
                        Element titleElement = element.nextElementSibling();
                        //标题
                        String title = titleElement.text();

                        //内容简介
                        Element detailElement = titleElement.nextElementSibling();
                        String detail = detailElement.text();

                        News news = new News();
                        news.setTitle(title);
                        news.setDetail(detail);
                        news.setImgStr(imgSrc);
                        mNewsList.add(news);

//                        Message msg = Message.obtain();
//                        msg.obj = mDatas;
//                        msg.what = 222;
//                        mHandler.sendMessage(msg);
                        Logger.show(TAG, imgSrc);
                        Logger.show(TAG, title);
                        Logger.show(TAG, detail);
                    }

                    /**
                     * 这里调用应该在线程里
                     * 由于是多线程，在线程外的话就在还没有数据的时候就
                     * 执行onComplete()了
                     *
                     */
                    mNewsListener.onComplete(mNewsList);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }

    /**
     * 根据图片URL从网络中下载图片
     * 新开启了一个线程
     * 下载为Bitmap类型
     *
     * @param path
     */
    public Bitmap getBitmapFromInternet(final String path) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Bitmap bitmap = null;
                    URLConnection conn = null;
                    java.net.URL url = new URL(path);

                    conn = url.openConnection();

                    //设置超时时间
                    conn.setConnectTimeout(10000);
                    conn.connect();
                    InputStream in;
                    in = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(in);
                    return bitmap

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


}
