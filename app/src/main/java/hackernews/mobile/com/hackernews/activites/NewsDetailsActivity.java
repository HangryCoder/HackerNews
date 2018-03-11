package hackernews.mobile.com.hackernews.activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import hackernews.mobile.com.hackernews.R;

/**
 * Created by soniawadji on 11/03/18.
 */

public class NewsDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolBar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
    }
}
