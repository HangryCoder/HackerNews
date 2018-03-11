package hackernews.mobile.com.hackernews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hackernews.mobile.com.hackernews.R;
import hackernews.mobile.com.hackernews.activites.MainActivity;
import hackernews.mobile.com.hackernews.activites.NewsDetailsActivity;
import hackernews.mobile.com.hackernews.model.News;

/**
 * Created by soniawadji on 10/03/18.
 */

public class NewsListingAdapter extends RecyclerView.Adapter<NewsListingAdapter.NewsHolder> {

    private Context context;
    private ArrayList<News> newsArrayList = new ArrayList<>();

    public NewsListingAdapter(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item_layout, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        News news = newsArrayList.get(position);

        holder.newsIdTV.setText("" + news.getNewsId());
        holder.newsTitleTV.setText(news.getNewsTitle());
        holder.newsUrlTV.setText(news.getNewsUrl());
        holder.newsTimestampTV.setText(news.getNewsTimestamp());
        holder.commentsCountTV.setText("" + news.getCommentsCount());
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.newsTitleTV)
        TextView newsTitleTV;
        @BindView(R.id.newsUrlTV)
        TextView newsUrlTV;
        @BindView(R.id.newsIdTV)
        TextView newsIdTV;
        @BindView(R.id.newsTimestampTV)
        TextView newsTimestampTV;
        @BindView(R.id.commentsCountTV)
        TextView commentsCountTV;
        @BindView(R.id.mainLayout)
        ConstraintLayout mainLayout;

        public NewsHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            mainLayout.setOnClickListener(view -> {
                Intent intent = new Intent(context, NewsDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            });
        }
    }
}
