package hackernews.mobile.com.hackernews.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by soniawadji on 11/03/18.
 */

public class Utils {

    private static final boolean DEBUG = true;

    public static void logd(String TAG, String message) {
        if (DEBUG) {
            Log.d(TAG, message);
        }
    }

    public static void showToast(Context context, String message) {
        if (DEBUG) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}
