package hackernews.mobile.com.hackernews.activites;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hackernews.mobile.com.hackernews.R;
import hackernews.mobile.com.hackernews.adapter.ViewPagerAdapter;
import hackernews.mobile.com.hackernews.fragments.ArticleFragment;
import hackernews.mobile.com.hackernews.fragments.CommentFragment;
import hackernews.mobile.com.hackernews.model.Story;
import hackernews.mobile.com.hackernews.utils.Utils;

import static hackernews.mobile.com.hackernews.utils.Constants.INTENT_COMMENT_IDS;
import static hackernews.mobile.com.hackernews.utils.Constants.INTENT_STORY;
import static hackernews.mobile.com.hackernews.utils.Constants.INTENT_STORY_URL;

/**
 * Created by soniawadji on 11/03/18.
 */

public class StoryDetailsActivity extends AppCompatActivity {

    private static final String TAG = StoryDetailsActivity.class.getSimpleName();
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.collapsibleToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.storyTitleTV)
    TextView storyTitleTV;
    @BindView(R.id.storyUrlTV)
    TextView storyUrlTV;
    @BindView(R.id.storyDatePlusUserTV)
    TextView storyDatePlusUserTV;

    private Story story;
    private ArrayList<String> commentsIdsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_details);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        //Fetching Story which is passed between Activities
        story = getIntent().getParcelableExtra(INTENT_STORY);
        commentsIdsArrayList = getIntent().getStringArrayListExtra(INTENT_COMMENT_IDS);
        Utils.logd(TAG, "story " + story.getCommentsIds());

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        setViews();
    }

    private void setViews() {
        storyTitleTV.setText(story.getStoryTitle());
        storyDatePlusUserTV.setText(Utils.getDateFormatted(Long.parseLong(story.getStoryTimestamp()))
                + " . " + story.getStoryBy());

        if (story.getStoryUrl() != null) {
            storyUrlTV.setText(story.getStoryUrl());
        } else {
            storyUrlTV.setVisibility(View.GONE);
        }

        //Adding Comment Count
        tabLayout.getTabAt(0).setText(story.getCommentsCount() + " "
                + getResources().getString(R.string.comments));
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Passing CommentsIds to CommentsFragment
        CommentFragment commentFragment = new CommentFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(INTENT_COMMENT_IDS, commentsIdsArrayList);
        commentFragment.setArguments(bundle);
        adapter.addFragment(commentFragment, getResources().getString(R.string.comments));

        //Passing Url to ArticleFragment
        ArticleFragment articleFragment = new ArticleFragment();
        Bundle articleBundle = new Bundle();
        articleBundle.putString(INTENT_STORY_URL, story.getStoryUrl());
        articleFragment.setArguments(articleBundle);
        adapter.addFragment(articleFragment, getResources().getString(R.string.article));

        viewPager.setAdapter(adapter);
    }
}
