package com.test.sportsnewsdemo.bean;

/**
 * Created by liuchang05 on 2016-1-15.
 */
public class News {


    //标题
    private String title;
    //内容简述
    private String detail;
    //图片url
    private String imgStr;


    public String getImgStr() {
        return imgStr;
    }

    public void setImgStr(String imgStr) {
        this.imgStr = imgStr;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
