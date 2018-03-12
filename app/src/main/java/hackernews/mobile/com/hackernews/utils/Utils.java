package hackernews.mobile.com.hackernews.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static hackernews.mobile.com.hackernews.utils.Constants.DEBUG;

/**
 * Created by soniawadji on 11/03/18.
 */

public class Utils {

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

    public static String getDateFormatted(long timestamp) {
        try {
            Calendar calendar = Calendar.getInstance();
            TimeZone tz = TimeZone.getDefault();
            calendar.setTimeInMillis(timestamp * 1000);
            calendar.setTimeZone(tz);
            Date calculatedDate = (Date) calendar.getTime();
            SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
            String commentDate = dayFormat.format(calculatedDate) + " " + getMonthInString(calculatedDate.getMonth())
                    + ", " + yearFormat.format(calculatedDate);
            return commentDate;
        } catch (Exception e) {
        }
        return "";
    }

    public static String getCommentDateTimeFormatted(long timestamp) {
        try {
            Calendar calendar = Calendar.getInstance();
            TimeZone tz = TimeZone.getDefault();
            calendar.setTimeInMillis(timestamp * 1000);
            calendar.setTimeZone(tz);
            Date calculatedDate = (Date) calendar.getTime();
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String commentTime = timeFormat.format(calculatedDate);
            String commentDate = getDateFormatted(timestamp);
            return commentDate + " - " + commentTime;
        } catch (Exception e) {
        }
        return "";
    }

    public static String getMonthInString(int month) {
        String monthString = "";
        switch (month) {
            case 0:
                monthString = "Jan";
                break;
            case 1:
                monthString = "Feb";
                break;
            case 2:
                monthString = "Mar";
                break;
            case 3:
                monthString = "Apr";
                break;
            case 4:
                monthString = "May";
                break;
            case 5:
                monthString = "Jun";
                break;
            case 6:
                monthString = "Jul";
                break;
            case 7:
                monthString = "Aug";
                break;
            case 8:
                monthString = "Sept";
                break;
            case 9:
                monthString = "Oct";
                break;
            case 10:
                monthString = "Nov";
                break;
            case 11:
                monthString = "Dec";
                break;
            default:
                break;
        }
        return monthString;
    }
}
