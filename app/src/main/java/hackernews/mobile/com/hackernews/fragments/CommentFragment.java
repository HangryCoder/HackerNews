package hackernews.mobile.com.hackernews.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
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
import io.realm.Realm;
import io.realm.RealmResults;
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
    private Realm realm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);

        ButterKnife.bind(this, view);

        progressDialog = new ProgressDialog(new ContextThemeWrapper(getActivity(),
                android.R.style.Theme_Holo_Light_Dialog));
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getResources().getString(R.string.loading_message));

        commentsIdsArrayList = getArguments().getStringArrayList(INTENT_COMMENT_IDS);
        int commentsIdSize = 0;
        if (commentsIdsArrayList != null) {
            commentsIdSize = commentsIdsArrayList.size();
        }

        realm = Realm.getDefaultInstance();

        /** Fetching StoryList from Realm*/
        RealmResults<Comments> commentsRealmResults = realm.where(Comments.class).findAll();
        commentsArrayList.clear();
        commentsArrayList.addAll(commentsRealmResults);

        /** Calling getComments for the particular ids
         for fetching Comments Details */
        for (int i = 0; i < commentsIdSize; i++) {
            getComments(commentsIdsArrayList.get(i));
        }

        settingUpRecyclerView();

        return view;
    }

    private void settingUpRecyclerView() {
        commentsAdapter = new CommentsAdapter(getActivity(), commentsArrayList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(10));
        recyclerView.setAdapter(commentsAdapter);
    }

    private void getComments(String id) {

        progressDialog.show();

        RestClient.getRestClient().getCommentDetails(id).enqueue(new Callback<Comments>() {
            @Override
            public void onResponse(Call<Comments> call, Response<Comments> response) {
                progressDialog.dismiss();
                if (response.code() == API_SUCCESS) {
                    Comments comments = response.body();
                    try (Realm realmInstance = Realm.getDefaultInstance()) {
                        realmInstance.executeTransaction(realm -> {
                            realmInstance.insertOrUpdate(comments);

                            Utils.logd(TAG, "onSuccess ");
                            commentsArrayList.add(comments);
                            commentsAdapter.notifyDataSetChanged();
                        });
                    }

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }
}
