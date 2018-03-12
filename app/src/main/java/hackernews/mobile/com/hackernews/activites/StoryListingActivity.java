package hackernews.mobile.com.hackernews.activites;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.widget.TextView;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hackernews.mobile.com.hackernews.R;
import hackernews.mobile.com.hackernews.adapter.StoryListingAdapter;
import hackernews.mobile.com.hackernews.model.Story;
import hackernews.mobile.com.hackernews.utils.RestClient;
import hackernews.mobile.com.hackernews.utils.Utils;
import hackernews.mobile.com.hackernews.utils.VerticalSpaceItemDecoration;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static hackernews.mobile.com.hackernews.utils.Constants.API_SUCCESS;
import static hackernews.mobile.com.hackernews.utils.Constants.NUMBER_OF_ITEM_TO_LOAD;
import static hackernews.mobile.com.hackernews.utils.Utils.logd;
import static hackernews.mobile.com.hackernews.utils.Utils.showToast;

/**
 * Created by soniawadji on 10/03/18.
 */

public class StoryListingActivity extends AppCompatActivity {

    private static final String TAG = StoryListingActivity.class.getSimpleName();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbarTitleTV)
    TextView toolbarTitleTV;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.updatedLastTimeTV)
    TextView updatedLastTimeTV;

    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Story> storyArrayList = new ArrayList<>();
    private StoryListingAdapter storyListingAdapter;
    private ProgressDialog progressDialog;
    private Realm realm;
    private static long currentTimestamp = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_listing);

        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(new ContextThemeWrapper(this,
                android.R.style.Theme_Holo_Light_Dialog));
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getResources().getString(R.string.loading_message));

        realm = Realm.getDefaultInstance();

        /** Fetching StoryList from Realm*/
        RealmResults<Story> storyRealmResults = realm.where(Story.class).findAll();
        storyArrayList.clear();
        storyArrayList.addAll(storyRealmResults);

        settingSwipeToRefresh();

        settingUpRecyclerView();

        getStories();
    }

    @Override
    protected void onResume() {
        super.onResume();
        /** Setting Time Accordingly*/
        if (currentTimestamp == 0) {
            updatedLastTimeTV.setText("Not Updated Yet");
        } else {
            updatedLastTimeTV.setText("Updated " + Utils.getStoryListingTime(currentTimestamp));
        }
    }

    private void setLastUpdatedTextView() {
        /** Setting Time Accordingly*/
        currentTimestamp = System.currentTimeMillis();
        updatedLastTimeTV.setText("Updated " + Utils.getStoryListingTime(currentTimestamp));
    }

    private void settingUpRecyclerView() {
        storyListingAdapter = new StoryListingAdapter(this, storyArrayList);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(10));
        recyclerView.setAdapter(storyListingAdapter);
    }

    private void settingSwipeToRefresh() {
        swipeRefreshLayout.setOnRefreshListener(this::getStories);
    }

    private void dismissProgressDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    void onItemsLoadComplete() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private void getStories() {
        if (!swipeRefreshLayout.isRefreshing()) {
            progressDialog.show();
        } else {
            swipeRefreshLayout.setRefreshing(true);
        }

        RestClient.getRestClient().fetchTopStoriesID().enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                dismissProgressDialog();
                if (response.code() == API_SUCCESS) {
                    int topStoriesLength = response.body().size();
                    logd(TAG, "Size " + topStoriesLength);
                    ArrayList<String> topStoriesArray = response.body();

                    setLastUpdatedTextView();

                    /**
                     * Loading only 5 Items per network call...
                     * Rest needs to be refreshed!!
                     * */
                    int counter = 0;
                    for (int i = 0; i < topStoriesLength; i++) {
                        Story story = realm.where(Story.class).equalTo("storyId",
                                topStoriesArray.get(i)).findFirst();
                        if (story == null) {
                            if (counter < NUMBER_OF_ITEM_TO_LOAD) {
                                counter++;
                                getTopStoryDetails(topStoriesArray.get(i));
                            } else {
                                break;
                            }
                        } else {
                            //Do Nothing.. Since Its already present!!
                           /* RealmResults<Story> storyRealmResults = realm.where(Story.class).findAll();
                            if (storyRealmResults.size() > 0) {
                                storyArrayList.clear();
                                storyArrayList.addAll(storyRealmResults);
                                storyListingAdapter.notifyDataSetChanged();
                            }*/
                        }
                    }

                } else {
                    showToast(getApplicationContext(), getResources().getString(R.string.something_went_wrong));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                dismissProgressDialog();
                onItemsLoadComplete();
                logd(TAG, "Error " + t.getMessage());
                if (t instanceof SocketTimeoutException) {
                    showToast(getApplicationContext(), getResources().getString(R.string.something_went_wrong));
                } else if (t instanceof IOException) {
                    showToast(getApplicationContext(), getResources().getString(R.string.no_internet_connection));
                } else {
                    showToast(getApplicationContext(), getResources().getString(R.string.something_went_wrong));
                }
            }
        });
    }

    private void getTopStoryDetails(String id) {
        if (!swipeRefreshLayout.isRefreshing()) {
            progressDialog.show();
        } else {
            swipeRefreshLayout.setRefreshing(true);
        }

        RestClient.getRestClient().getStoryDetails(id).enqueue(new Callback<Story>() {
            @Override
            public void onResponse(Call<Story> call, Response<Story> response) {
                dismissProgressDialog();
                onItemsLoadComplete();
                if (response.code() == API_SUCCESS) {
                    Story story = response.body();

                    try (Realm realmInstance = Realm.getDefaultInstance()) {
                        realmInstance.executeTransaction(realm -> {
                            realmInstance.insertOrUpdate(story);

                            setLastUpdatedTextView();

                            Utils.logd(TAG, "onSuccess ");
                            storyArrayList.add(0, story);
                            storyListingAdapter.notifyDataSetChanged();
                            recyclerView.smoothScrollToPosition(0);
                        });
                    }
                } else {
                    showToast(getApplicationContext(), getResources().getString(R.string.something_went_wrong));
                }
            }

            @Override
            public void onFailure(Call<Story> call, Throwable t) {
                dismissProgressDialog();
                onItemsLoadComplete();
                logd(TAG, "Error " + t.getMessage());
                if (t instanceof SocketTimeoutException) {
                    showToast(getApplicationContext(), getResources().getString(R.string.something_went_wrong));
                } else if (t instanceof IOException) {
                    showToast(getApplicationContext(), getResources().getString(R.string.no_internet_connection));
                } else {
                    showToast(getApplicationContext(), getResources().getString(R.string.something_went_wrong));
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Always close realm in onDestroy!!
        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }
}
