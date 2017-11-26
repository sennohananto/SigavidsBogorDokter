package besidev.sigavidsbogor.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import besidev.sigavidsbogor.R;
import besidev.sigavidsbogor.helpers.AppHelpers;

public class InAppbrowser extends AppCompatActivity {
    WebView webView;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_appbrowser);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        Intent keInAppBrowser = getIntent();
        url = keInAppBrowser.getStringExtra("url");
        webView.loadUrl("http://ping.eu");
        AppHelpers.LogCat("Mengakses : "+url);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);
    }

    private class WebViewController extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); //method goback() dieksekusi untuk kembali pada halaman sebelumnya
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
