package com.test.sportsnewsdemo;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.test.sportsnewsdemo.parser.ParseHtml;
import com.test.sportsnewsdemo.utils.Logger;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);

    }

//    public void TestParse(){
//        ParseHtml parseHtml = new ParseHtml();
//        String TAG = this.getClass().getSimpleName();
//        Log.i("ddd","ddddddddddddddd");
//        Logger.show(TAG,"ddd");
//        parseHtml.getDocumentFromUrl("http://www.dongqiudi.com/");
//    }

    public void test(){
        Log.v("dd","ddd");
    }
}