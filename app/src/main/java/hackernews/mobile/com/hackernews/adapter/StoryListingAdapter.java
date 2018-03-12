package hackernews.mobile.com.hackernews.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import hackernews.mobile.com.hackernews.utils.Utils;

import static hackernews.mobile.com.hackernews.utils.Constants.INTENT_COMMENT_IDS;
import static hackernews.mobile.com.hackernews.utils.Constants.INTENT_STORY;

/**
 * Created by soniawadji on 10/03/18.
 */

public class StoryListingAdapter extends RecyclerView.Adapter<StoryListingAdapter.StoryHolder> {

    private Context context;
    private ArrayList<Story> storyArrayList = new ArrayList<>();

    public StoryListingAdapter(Context context, ArrayList<Story> storyArrayList) {
        this.context = context;
        this.storyArrayList = storyArrayList;
    }

    @Override
    public StoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.story_item_layout, parent, false);
        return new StoryHolder(view);
    }

    @Override
    public void onBindViewHolder(StoryHolder holder, int position) {
        Story story = storyArrayList.get(position);

        holder.storyVotesTV.setText("" + story.getStoryVotes());
        holder.storyTitleTV.setText(story.getStoryTitle());
        holder.storyUrlTV.setText(story.getStoryUrl());
        holder.storyTimestampTV.setText(Utils.getStoryListingTime(
                Long.parseLong(story.getStoryTimestamp()) * 1000)
                + " . " + story.getStoryBy());
        holder.commentsCountTV.setText("" + story.getCommentsCount());
    }

    @Override
    public int getItemCount() {
        return storyArrayList.size();
    }

    public class StoryHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.storyTitleTV)
        TextView storyTitleTV;
        @BindView(R.id.storyUrlTV)
        TextView storyUrlTV;
        @BindView(R.id.storyVotesTV)
        TextView storyVotesTV;
        @BindView(R.id.storyTimestampTV)
        TextView storyTimestampTV;
        @BindView(R.id.commentsCountTV)
        TextView commentsCountTV;
        @BindView(R.id.mainLayout)
        ConstraintLayout mainLayout;

        public StoryHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            mainLayout.setOnClickListener(view -> {
                Story story = storyArrayList.get(getAdapterPosition());
                ArrayList<String> commentIdsArrayList = new ArrayList<>();
                if (story.getCommentsIds() != null) {
                    commentIdsArrayList.addAll(story.getCommentsIds());
                }
                Intent intent = new Intent(context, StoryDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle = new Bundle();
                bundle.putParcelable(INTENT_STORY, story);
                bundle.putStringArrayList(INTENT_COMMENT_IDS, commentIdsArrayList);
                intent.putExtras(bundle);
                context.startActivity(intent);

            });
        }
    }
}
