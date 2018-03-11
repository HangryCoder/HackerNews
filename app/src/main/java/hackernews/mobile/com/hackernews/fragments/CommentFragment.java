package hackernews.mobile.com.hackernews.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hackernews.mobile.com.hackernews.R;
import hackernews.mobile.com.hackernews.adapter.CommentsAdapter;
import hackernews.mobile.com.hackernews.model.Comments;
import hackernews.mobile.com.hackernews.utils.VerticalSpaceItemDecoration;

/**
 * Created by soniawadji on 11/03/18.
 */

public class CommentFragment extends Fragment {

    @BindView(R.id.recyclerViewLayout)
    RecyclerView recyclerView;
    private CommentsAdapter commentsAdapter;
    private ArrayList<Comments> commentsArrayList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);

        getComments();

        ButterKnife.bind(this,view);

        commentsAdapter = new CommentsAdapter(getActivity(), commentsArrayList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(10));
        recyclerView.setAdapter(commentsAdapter);

        return view;
    }

    private void getComments() {
        Comments comments = new Comments("4 Sept,2017", "16:43", "user01",
                "This is a top level message", getResources().getString(R.string.dummy_text));
        commentsArrayList.add(comments);

        comments = new Comments("4 Sept,2017", "16:43", "user01",
                "This is a top level message", getResources().getString(R.string.dummy_text));
        commentsArrayList.add(comments);

        comments = new Comments("4 Sept,2017", "16:43", "user01",
                "This is a top level message", getResources().getString(R.string.dummy_text));
        commentsArrayList.add(comments);

        comments = new Comments("4 Sept,2017", "16:43", "user01",
                "This is a top level message", getResources().getString(R.string.dummy_text));
        commentsArrayList.add(comments);

        comments = new Comments("4 Sept,2017", "16:43", "user01",
                "This is a top level message", getResources().getString(R.string.dummy_text));
        commentsArrayList.add(comments);

        comments = new Comments("4 Sept,2017", "16:43", "user01",
                "This is a top level message", getResources().getString(R.string.dummy_text));
        commentsArrayList.add(comments);
    }
}
