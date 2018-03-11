package hackernews.mobile.com.hackernews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hackernews.mobile.com.hackernews.R;
import hackernews.mobile.com.hackernews.model.Comments;

/**
 * Created by soniawadji on 11/03/18.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsHolder> {

    private Context context;
    private ArrayList<Comments> commentsArrayList = new ArrayList<>();

    public CommentsAdapter(Context context, ArrayList<Comments> commentsArrayList) {
        this.context = context;
        this.commentsArrayList = commentsArrayList;
    }

    @Override
    public CommentsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comments_item_layout, parent, false);
        return new CommentsHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentsHolder holder, int position) {
        Comments comments = commentsArrayList.get(position);

        holder.commentDetailsTV.setText(comments.getCommentDate() + " - " + comments.getCommentTime()
                + " . " + comments.getCommentUser());
        holder.commentMessage.setText(comments.getCommentString());
        holder.topLevelCommentTV.setText(comments.getTopLevelComment());
    }

    @Override
    public int getItemCount() {
        return commentsArrayList.size();
    }

    public class CommentsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.commentDetailsTV)
        TextView commentDetailsTV;
        @BindView(R.id.topLevelCommentTV)
        TextView topLevelCommentTV;
        @BindView(R.id.commentMessage)
        TextView commentMessage;

        public CommentsHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
