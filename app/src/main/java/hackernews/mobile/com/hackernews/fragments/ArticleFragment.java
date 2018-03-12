package hackernews.mobile.com.hackernews.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hackernews.mobile.com.hackernews.R;

import static hackernews.mobile.com.hackernews.utils.Constants.INTENT_STORY_URL;

/**
 * Created by soniawadji on 11/03/18.
 */

public class ArticleFragment extends Fragment {

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.noArticlesAvailableTV)
    TextView noArticlesAvailableTV;
    private String url = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);

        ButterKnife.bind(this, view);

        url = getArguments().getString(INTENT_STORY_URL);

        //If Url is not empty then load url in webView
        //else display a text
        if(url!=null){
            if(!url.isEmpty()){
                noArticlesAvailableTV.setVisibility(View.INVISIBLE);
                webView.setVisibility(View.VISIBLE);
                webView.setWebViewClient(new ArticleBrowser());
                webView.loadUrl(url);
            }else{
                noArticlesAvailableTV.setVisibility(View.VISIBLE);
                webView.setVisibility(View.INVISIBLE);
            }
        }else{
            noArticlesAvailableTV.setVisibility(View.VISIBLE);
            webView.setVisibility(View.INVISIBLE);
        }

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
