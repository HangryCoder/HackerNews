package hackernews.mobile.com.hackernews.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import hackernews.mobile.com.hackernews.R;

/**
 * Created by soniawadji on 11/03/18.
 */

public class ArticleFragment extends Fragment {

    @BindView(R.id.webView)
    WebView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);

        ButterKnife.bind(this, view);

        webView.setWebViewClient(new ArticleBrowser());
        webView.loadUrl("http://www.google.com");

        return view;
    }

    private class ArticleBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
