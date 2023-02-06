package com.example.webviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tv_nointernet;
    private WebView wv;
    String message="aaaaaaaaaaaaaaaaaaaaaaa55595+595+656+565+6565+6+6+5616+965";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          wv=(WebView)findViewById(R.id.webview);
          tv_nointernet=findViewById(R.id.tv_nointernet);
        if (isConnected()) {
          //  String url="https://www.amazon.in/";
            Intent intent=getIntent();
            String url=intent.getStringExtra("link");
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
/*            wv.setWebViewClient(new MyBrowser());
            wv.getSettings().setLoadsImagesAutomatically(true);
            wv.getSettings().setJavaScriptEnabled(true);
            wv.getSettings().setAppCacheEnabled(true);
            wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            wv.loadUrl(url);*/
//            wv.setWebChromeClient(new WebChromeClient());
            wv.setWebViewClient(new MywebClient());
            wv.getSettings().setLoadsImagesAutomatically(true);
            wv.getSettings().setJavaScriptEnabled(true);
            wv.getSettings().setAppCacheEnabled(true);
            wv.getSettings().setDomStorageEnabled(true);
            wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            wv.loadUrl(url);
            WebSettings webSettings = wv.getSettings();
            webSettings.setJavaScriptEnabled(true);
            tv_nointernet.setVisibility(View.GONE);
            wv.setVisibility(View.VISIBLE);
//            Toast.makeText(getApplicationContext(), "Internet Connected", Toast.LENGTH_SHORT).show();
        } else {
            tv_nointernet.setVisibility(View.VISIBLE);
            wv.setVisibility(View.GONE);
//            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }


    }
    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }
    private class MywebClient extends WebViewClient{

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

    }

    @Override
    public void onBackPressed() {
        if(wv.isFocused() && wv.canGoBack())
        {
            wv.goBack();
        }else{
            super.onBackPressed();
        }
    }
}
 /* class MyBrowser extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}*/

