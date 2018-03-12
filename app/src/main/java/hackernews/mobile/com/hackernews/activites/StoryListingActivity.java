package hackernews.mobile.com.hackernews.activites;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hackernews.mobile.com.hackernews.R;
import hackernews.mobile.com.hackernews.adapter.StoryListingAdapter;
import hackernews.mobile.com.hackernews.model.Story;
import hackernews.mobile.com.hackernews.utils.RestClient;
import hackernews.mobile.com.hackernews.utils.VerticalSpaceItemDecoration;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_listing);

        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.loading_message));

        getStories();

        storyListingAdapter = new StoryListingAdapter(this, storyArrayList);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(10));
        recyclerView.setAdapter(storyListingAdapter);

    }

    private void getStories() {

        progressDialog.show();

        RestClient.getRestClient().fetchTopStoriesID().enqueue(new Callback<JSONArray>() {
            @Override
            public void onResponse(Call<JSONArray> call, Response<JSONArray> response) {
                progressDialog.dismiss();
                if (response.code() == API_SUCCESS) {
                    // logd(TAG, response.body().toString());
                    int topStoriesLength = response.body().length();
                    JSONArray topStoriesArray = response.body();
                    for (int i = 0; i < topStoriesLength; i++) {
                        try {
                            getTopStoryDetails(topStoriesArray.getString(i));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    showToast(getApplicationContext(), getResources().getString(R.string.something_went_wrong));
                }
            }

            @Override
            public void onFailure(Call<JSONArray> call, Throwable t) {
                progressDialog.dismiss();
                logd(TAG, "Error " + t.getMessage());
                if (t instanceof SocketTimeoutException) {
                    showToast(getApplicationContext(), getResources().getString(R.string.no_internet_connection));
                }
            }
        });

        Story story = new Story(1, "Blah Blah Blah", "www.w3c.org",
                "12 mins ago", 10);
        storyArrayList.add(story);

        story = new Story(2, "Blah Blah Blah", "www.w3c.org",
                "12 mins ago", 10);
        storyArrayList.add(story);

        story = new Story(3, "Blah Blah Blah", "www.w3c.org",
                "12 mins ago", 10);
        storyArrayList.add(story);

        story = new Story(4, "Blah Blah Blah", "www.w3c.org",
                "12 mins ago", 10);
        storyArrayList.add(story);

        story = new Story(5, "Blah Blah Blah", "www.w3c.org",
                "12 mins ago", 10);
        storyArrayList.add(story);

        story = new Story(6, "Blah Blah Blah", "www.w3c.org",
                "12 mins ago", 10);
        storyArrayList.add(story);
    }

    private void getTopStoryDetails(String id) {

        progressDialog.show();

        RestClient.getRestClient().getStoryDetails(id).enqueue(new Callback<Story>() {
            @Override
            public void onResponse(Call<Story> call, Response<Story> response) {
                progressDialog.dismiss();
                if (response.code() == API_SUCCESS) {
                    logd(TAG, response.body().getStoryTitle());
                } else {
                    showToast(getApplicationContext(), getResources().getString(R.string.something_went_wrong));
                }
            }

            @Override
            public void onFailure(Call<Story> call, Throwable t) {
                progressDialog.dismiss();
                logd(TAG, "Error " + t.getMessage());
                if (t instanceof SocketTimeoutException) {
                    showToast(getApplicationContext(), getResources().getString(R.string.no_internet_connection));
                }
            }
        });
    }
}
