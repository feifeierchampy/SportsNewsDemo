package com.test.sportsnewsdemo.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
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

    private ImageLoader() {
    }

    private static ImageLoader mInstance;

    private Handler mUIHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            ImageHolder imageHolder = (ImageHolder) msg.obj;

            imageHolder.imageView.setImageBitmap(imageHolder.bitmap);

        }
    };

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            mInstance = new ImageLoader();
        }
        return mInstance;
    }

    public void loadImage(final String imgUrl, final ImageView imageView) {


        new Thread() {
            @Override
            public void run() {
                super.run();

                Bitmap bm = getBitmapFromUrl(imgUrl);
                Message msg = Message.obtain();
                ImageHolder imageHolder = new ImageHolder();
                imageHolder.imageView = imageView;
                imageHolder.bitmap = bm;
                imageHolder.url = imgUrl;
                msg.obj = imageHolder;
                mUIHandler.sendMessage(msg);
            }


        }.start();

    }


    private Bitmap getBitmapFromUrl(String imgUrl) {
        Bitmap bitmap = null;

        try {
            URLConnection conn = null;
            java.net.URL url = new URL(imgUrl);

            conn = url.openConnection();

            //设置超时时间
            conn.setConnectTimeout(10000);
            conn.connect();
            InputStream in;
            in = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(in);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public class ImageHolder {
        ImageView imageView;
        String url;
        Bitmap bitmap;
    }
}
