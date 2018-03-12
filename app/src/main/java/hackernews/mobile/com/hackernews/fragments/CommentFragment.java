package hackernews.mobile.com.hackernews.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hackernews.mobile.com.hackernews.R;
import hackernews.mobile.com.hackernews.adapter.CommentsAdapter;
import hackernews.mobile.com.hackernews.model.Comments;
import hackernews.mobile.com.hackernews.utils.RestClient;
import hackernews.mobile.com.hackernews.utils.Utils;
import hackernews.mobile.com.hackernews.utils.VerticalSpaceItemDecoration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static hackernews.mobile.com.hackernews.utils.Constants.API_SUCCESS;
import static hackernews.mobile.com.hackernews.utils.Constants.INTENT_COMMENT_IDS;
import static hackernews.mobile.com.hackernews.utils.Utils.logd;
import static hackernews.mobile.com.hackernews.utils.Utils.showToast;

/**
 * Created by soniawadji on 11/03/18.
 */

public class CommentFragment extends Fragment {

    private static final String TAG = CommentFragment.class.getSimpleName();
    @BindView(R.id.recyclerViewLayout)
    RecyclerView recyclerView;
    private CommentsAdapter commentsAdapter;
    private ArrayList<Comments> commentsArrayList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<String> commentsIdsArrayList = new ArrayList<>();
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);

        ButterKnife.bind(this, view);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getResources().getString(R.string.loading_message));

        commentsIdsArrayList = getArguments().getStringArrayList(INTENT_COMMENT_IDS);
        int commentsIdSize = 0;
        if (commentsIdsArrayList != null) {
            commentsIdSize = commentsIdsArrayList.size();
        }
        Utils.logd(TAG, "size " + commentsIdSize);

        for (int i = 0; i < commentsIdSize; i++) {
            getComments(commentsIdsArrayList.get(i));
        }

        commentsAdapter = new CommentsAdapter(getActivity(), commentsArrayList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(10));
        recyclerView.setAdapter(commentsAdapter);

        return view;
    }

    private void getComments(String id) {

        progressDialog.show();

        RestClient.getRestClient().getCommentDetails(id).enqueue(new Callback<Comments>() {
            @Override
            public void onResponse(Call<Comments> call, Response<Comments> response) {
                progressDialog.dismiss();
                if (response.code() == API_SUCCESS) {
                    commentsArrayList.add(response.body());
                    commentsAdapter.notifyDataSetChanged();
                } else {
                    showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
                }
            }

            @Override
            public void onFailure(Call<Comments> call, Throwable t) {
                progressDialog.dismiss();
                logd(TAG, "Error " + t.getMessage());
                if (t instanceof SocketTimeoutException) {
                    showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
                } else if (t instanceof IOException) {
                    showToast(getActivity(), getResources().getString(R.string.no_internet_connection));
                } else {
                    showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
                }
            }
        });
    }
}
