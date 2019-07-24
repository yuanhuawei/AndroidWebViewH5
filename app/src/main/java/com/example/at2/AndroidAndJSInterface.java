package com.example.at2;

import android.webkit.JavascriptInterface;

public class AndroidAndJSInterface {
    @JavascriptInterface//一定要写，不然H5调不到这个方法
    public String back() {
        return "我是java里的方法返回值";
    }
}
