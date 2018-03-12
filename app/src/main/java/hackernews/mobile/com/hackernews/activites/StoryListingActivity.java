package hackernews.mobile.com.hackernews.activites;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hackernews.mobile.com.hackernews.R;
import hackernews.mobile.com.hackernews.adapter.StoryListingAdapter;
import hackernews.mobile.com.hackernews.model.Story;
import hackernews.mobile.com.hackernews.model.TopStories;
import hackernews.mobile.com.hackernews.utils.RestClient;
import hackernews.mobile.com.hackernews.utils.Utils;
import hackernews.mobile.com.hackernews.utils.VerticalSpaceItemDecoration;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static hackernews.mobile.com.hackernews.utils.Constants.API_SUCCESS;
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

    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Story> storyArrayList = new ArrayList<>();
    private StoryListingAdapter storyListingAdapter;
    private ProgressDialog progressDialog;
    private Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_listing);

        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.loading_message));

        realm = Realm.getDefaultInstance();

        getStories();

        storyListingAdapter = new StoryListingAdapter(this, storyArrayList);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(10));
        recyclerView.setAdapter(storyListingAdapter);

    }

    private void getStories() {

        progressDialog.show();

        RestClient.getRestClient().fetchTopStoriesID().enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                progressDialog.dismiss();
                if (response.code() == API_SUCCESS) {
                    int topStoriesLength = response.body().size();
                    logd(TAG, "Size " + topStoriesLength);
                    ArrayList<String> topStoriesArray = response.body();

                  /*  RealmResults<Story> storyRealmResults =
                            realm.where(Story.class).findAll();
                    Utils.logd(TAG, "stories " + storyRealmResults);*/

                   /* if (storyRealmResults.size() == 0) {
                        for (int i = 0; i < 2; i++) {
                            getTopStoryDetails(topStoriesArray.get(i));
                        }
                    } else {
                        for (Story story :
                                storyRealmResults) {
                            for (int i = 0; i < 2; i++) {
                                getTopStoryDetails(story.getStoryId());
                            }
                        }
                    }*/

                   /* Realm realm = null;
                    try { // I could use try-with-resources here
                        realm = Realm.getDefaultInstance();
                        Realm finalRealm = realm;
                        realm.executeTransactionAsync(realm1 -> {
                            Utils.logd(TAG, "stories ");
                            Story story = new Story();
                            story.setStoryId(topStoriesArray.get(0));
                            finalRealm.insertOrUpdate(story);
                        }, () -> {
                            //onSuccess
                            Utils.logd(TAG, "onSuccess");
                            RealmResults<Story> storyRealmResults =
                                    finalRealm.where(Story.class).findAll();
                            Utils.logd(TAG, "stories " + storyRealmResults);

                            for (Story story :
                                    storyRealmResults) {
                                // if (topStories.getStory() == null) {
                                for (int i = 0; i < 2; i++) {
                                    getTopStoryDetails(story.getStoryId());
                                }
                                //}
                            }
                        });
                    } finally {
                        if (realm != null) {
                            realm.close();
                        }
                    }
*/
                   /* try (Realm realmInstance = Realm.getDefaultInstance()) {
                        realmInstance.executeTransactionAsync(realm -> {

                            Utils.logd(TAG, "stories ");

                            for (int i = 0; i < topStoriesLength; i++) {
                                Story story = new Story();
                                story.setStoryId(topStoriesArray.get(i));
                                realmInstance.insertOrUpdate(story);
                            }
                        }, () -> {
                            //onSuccess
                            Utils.logd(TAG, "onSuccess");
                            RealmResults<Story> storyRealmResults =
                                    realmInstance.where(Story.class).findAll();
                            Utils.logd(TAG, "stories " + storyRealmResults);

                            for (Story story :
                                    storyRealmResults) {
                                // if (topStories.getStory() == null) {
                                for (int i = 0; i < 2; i++) {
                                    getTopStoryDetails(story.getStoryId());
                                }
                                //}
                            }
                        }, error -> {
                            //onError
                            Utils.logd(TAG, "onError " + error.getMessage());
                        });
                    }*/

                    for (int i = 0; i < 2; i++) {
                        Story story = realm.where(Story.class).equalTo("storyId",
                                topStoriesArray.get(i)).findFirst();
                        if (story == null) {
                            // for (int i = 0; i < 2; i++) {
                            getTopStoryDetails(topStoriesArray.get(i));
                            //}
                        } else {
                            RealmResults<Story> storyRealmResults = realm.where(Story.class).findAll();
                            if (storyRealmResults.size() > 0) {
                                storyArrayList.clear();
                                storyArrayList.addAll(storyRealmResults);
                                storyListingAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                    /*for (int i = 0; i < 2; i++) {
                        getTopStoryDetails(topStoriesArray.get(i));
                    }*/
                } else {
                    showToast(getApplicationContext(), getResources().getString(R.string.something_went_wrong));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                progressDialog.dismiss();
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

        progressDialog.show();

        RestClient.getRestClient().getStoryDetails(id).enqueue(new Callback<Story>() {
            @Override
            public void onResponse(Call<Story> call, Response<Story> response) {
                progressDialog.dismiss();
                if (response.code() == API_SUCCESS) {
                    Story story = response.body();

                    try (Realm realmInstance = Realm.getDefaultInstance()) {
                        realmInstance.executeTransaction(realm -> {
                            realmInstance.insertOrUpdate(story);

                            Utils.logd(TAG, "onSuccess ");
                            storyArrayList.add(story);
                            storyListingAdapter.notifyDataSetChanged();
                        });
                    }
                } else {
                    showToast(getApplicationContext(), getResources().getString(R.string.something_went_wrong));
                }
            }

            @Override
            public void onFailure(Call<Story> call, Throwable t) {
                progressDialog.dismiss();
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

        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }
}
