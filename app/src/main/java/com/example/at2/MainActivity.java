package com.example.at2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private WebView mWebView;
    private Button button;
    private Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.loadUrl("file:///android_asset/files/js_java_interaction.html");//加载本地asset下面的js_java_interaction.html文件
        //mWebView.loadUrl("https://www.baidu.com/");


        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);//打开js支持
        /**
         * 打开js接口給H5调用，参数1为本地类名，参数2为别名；h5用window.别名.类名里的方法名才能调用方法里面的内容，例如：window.android.back();
         * */
        mWebView.addJavascriptInterface(new AndroidAndJSInterface(), "Android");
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());
        //保持webView设置的编码与html设置编码一致
        mWebView.getSettings().setDefaultTextEncodingName("GBK");
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                mWebView.loadUrl("javascript:show()");
            }
        });

        button = findViewById(R.id.button);
        button.setOnClickListener((view) -> {
            Log.e("ddddd", TAG);
            String abc = "我是从Java 原生代码过来的";
            mWebView.loadUrl("JavaScript:test('" + abc + "')");

        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v -> {
            mWebView.post(()->{
                mWebView.evaluateJavascript("javascript:ev()",s->{
                    button2.setText(s);
                });
            });
        });
    }
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }
