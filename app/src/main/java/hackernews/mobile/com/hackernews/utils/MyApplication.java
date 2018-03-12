package hackernews.mobile.com.hackernews.utils;

import android.app.Application;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import static hackernews.mobile.com.hackernews.utils.Constants.DEBUG;

/**
 * Created by soniawadji on 12/03/18.
 */

public class MyApplication extends Application {

    public static OkHttpClient okHttpClient;

    private static HttpLoggingInterceptor.Level level = DEBUG ? HttpLoggingInterceptor.Level.BODY
            : HttpLoggingInterceptor.Level.NONE;

    @Override
    public void onCreate() {
        super.onCreate();
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(level))
                    .build();
        }
    }
}
