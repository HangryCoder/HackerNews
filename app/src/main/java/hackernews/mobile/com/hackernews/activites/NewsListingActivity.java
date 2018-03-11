package hackernews.mobile.com.hackernews.activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hackernews.mobile.com.hackernews.R;
import hackernews.mobile.com.hackernews.adapter.NewsListingAdapter;
import hackernews.mobile.com.hackernews.model.News;
import hackernews.mobile.com.hackernews.utils.VerticalSpaceItemDecoration;

/**
 * Created by soniawadji on 10/03/18.
 */

public class NewsListingActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbarTitleTV)
    TextView toolbarTitleTV;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<News> newsArrayList = new ArrayList<>();
    private NewsListingAdapter newsListingAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_listing);

        ButterKnife.bind(this);

        getNews();

        newsListingAdapter = new NewsListingAdapter(this, newsArrayList);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(10));
        recyclerView.setAdapter(newsListingAdapter);

    }

    private void getNews() {

        News news = new News(1, "Blah Blah Blah", "www.w3c.org",
                "12 mins ago", 10);
        newsArrayList.add(news);

        news = new News(2, "Blah Blah Blah", "www.w3c.org",
                "12 mins ago", 10);
        newsArrayList.add(news);

        news = new News(3, "Blah Blah Blah", "www.w3c.org",
                "12 mins ago", 10);
        newsArrayList.add(news);

        news = new News(4, "Blah Blah Blah", "www.w3c.org",
                "12 mins ago", 10);
        newsArrayList.add(news);

        news = new News(5, "Blah Blah Blah", "www.w3c.org",
                "12 mins ago", 10);
        newsArrayList.add(news);

        news = new News(6, "Blah Blah Blah", "www.w3c.org",
                "12 mins ago", 10);
        newsArrayList.add(news);
    }
}
