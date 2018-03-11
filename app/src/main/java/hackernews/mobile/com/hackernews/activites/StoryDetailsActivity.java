package hackernews.mobile.com.hackernews.activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import hackernews.mobile.com.hackernews.R;
import hackernews.mobile.com.hackernews.adapter.ViewPagerAdapter;
import hackernews.mobile.com.hackernews.fragments.ArticleFragment;
import hackernews.mobile.com.hackernews.fragments.CommentFragment;

/**
 * Created by soniawadji on 11/03/18.
 */

public class StoryDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.collapsibleToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent)); // transperent color = #00000000

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CommentFragment(), getResources().getString(R.string.comments));
        adapter.addFragment(new ArticleFragment(), getResources().getString(R.string.article));
        viewPager.setAdapter(adapter);
    }
}
