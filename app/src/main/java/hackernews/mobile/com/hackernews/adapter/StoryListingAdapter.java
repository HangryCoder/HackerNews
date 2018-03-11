package hackernews.mobile.com.hackernews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hackernews.mobile.com.hackernews.R;
import hackernews.mobile.com.hackernews.activites.StoryDetailsActivity;
import hackernews.mobile.com.hackernews.model.Story;

/**
 * Created by soniawadji on 10/03/18.
 */

public class StoryListingAdapter extends RecyclerView.Adapter<StoryListingAdapter.NewsHolder> {

    private Context context;
    private ArrayList<Story> storyArrayList = new ArrayList<>();

    public StoryListingAdapter(Context context, ArrayList<Story> storyArrayList) {
        this.context = context;
        this.storyArrayList = storyArrayList;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item_layout, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        Story story = storyArrayList.get(position);

        holder.storyIdTV.setText("" + story.getStoryId());
        holder.storyTitleTV.setText(story.getStoryTitle());
        holder.storyUrlTV.setText(story.getStoryUrl());
        holder.storyTimestampTV.setText(story.getStoryTimestamp());
        holder.commentsCountTV.setText("" + story.getCommentsCount());
    }

    @Override
    public int getItemCount() {
        return storyArrayList.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.storyTitleTV)
        TextView storyTitleTV;
        @BindView(R.id.storyUrlTV)
        TextView storyUrlTV;
        @BindView(R.id.storyIdTV)
        TextView storyIdTV;
        @BindView(R.id.storyTimestampTV)
        TextView storyTimestampTV;
        @BindView(R.id.commentsCountTV)
        TextView commentsCountTV;
        @BindView(R.id.mainLayout)
        ConstraintLayout mainLayout;

        public NewsHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            mainLayout.setOnClickListener(view -> {
                Intent intent = new Intent(context, StoryDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            });
        }
    }
}
