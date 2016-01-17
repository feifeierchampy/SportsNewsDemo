package com.test.sportsnewsdemo.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by CHAMPION on 2016/1/17.
 */
public class ImageLoader {

    //线程池，线程数量为CPU的数量
    private ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private ImageLoader(){}

    private static ImageLoader mInstance;

    public static ImageLoader getInstance() {
        if(mInstance == null) {
            mInstance = new ImageLoader();
        }
        return mInstance;
    }

    public void getBitmapFromInternet(String path, ImageView imageView) {
        imageView.setTag(path);

        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                URLConnection conn = null;
                java.net.URL url = new URL(path);

                conn = url.openConnection();

                //设置超时时间
                conn.setConnectTimeout(10000);
                conn.connect();
                InputStream in;
                in = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(in);

            }
        })
    }


    private Bitmap getBitmapFromUrl(String imgUrl) {
        0
    }
}
