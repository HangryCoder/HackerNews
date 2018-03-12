package hackernews.mobile.com.hackernews.utils;

import org.json.JSONArray;

import java.util.ArrayList;

import hackernews.mobile.com.hackernews.model.Story;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static hackernews.mobile.com.hackernews.utils.Constants.BASE_URL;
import static hackernews.mobile.com.hackernews.utils.Constants.DEBUG;
import static hackernews.mobile.com.hackernews.utils.Constants.FETCH_TOP_STORIES;

/**
 * Created by soniawadji on 11/03/18.
 */

public class RestClient {


    public static Retrofit getRetrofitBuilder() {
        /*OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(level))
                .build();*/

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(MyApplication.okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static HackerNewsAPI getRestClient() {
        return getRetrofitBuilder().create(HackerNewsAPI.class);
    }

    public interface HackerNewsAPI {
        @GET(FETCH_TOP_STORIES)
        Call<ArrayList<String>> fetchTopStoriesID();

        @GET("item/{id}.json?print=pretty")
        Call<Story> getStoryDetails(@Path("id") String id);
    }

}
