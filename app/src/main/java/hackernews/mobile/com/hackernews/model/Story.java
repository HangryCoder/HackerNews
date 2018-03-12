package hackernews.mobile.com.hackernews.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by soniawadji on 10/03/18.
 */

public class Story implements Parcelable{

    @SerializedName("id")
    private int storyId;
    @SerializedName("descendants")
    private int commentsCount;
    @SerializedName("title")
    private String storyTitle;
    @SerializedName("url")
    private String storyUrl;
    @SerializedName("time")
    private String storyTimestamp;
    @SerializedName("score")
    private int storyVotes;
    @SerializedName("by")
    private String storyBy;
    @SerializedName("kids")
    private ArrayList<String> commentsIds;

    public Story(int storyId, String storyTitle, String storyUrl, String storyTimestamp, int commentsCount) {
        this.storyId = storyId;
        this.commentsCount = commentsCount;
        this.storyTitle = storyTitle;
        this.storyUrl = storyUrl;
        this.storyTimestamp = storyTimestamp;
    }

    public Story(int storyId, int commentsCount, String storyTitle, String storyUrl, String storyTimestamp,
                 int storyVotes, String storyBy, ArrayList<String> commentsIds) {
        this.storyId = storyId;
        this.commentsCount = commentsCount;
        this.storyTitle = storyTitle;
        this.storyUrl = storyUrl;
        this.storyTimestamp = storyTimestamp;
        this.storyVotes = storyVotes;
        this.storyBy = storyBy;
        this.commentsIds = commentsIds;
    }

    protected Story(Parcel in) {
        storyId = in.readInt();
        commentsCount = in.readInt();
        storyTitle = in.readString();
        storyUrl = in.readString();
        storyTimestamp = in.readString();
        storyVotes = in.readInt();
        storyBy = in.readString();
        commentsIds = in.createStringArrayList();
    }

    public static final Creator<Story> CREATOR = new Creator<Story>() {
        @Override
        public Story createFromParcel(Parcel in) {
            return new Story(in);
        }

        @Override
        public Story[] newArray(int size) {
            return new Story[size];
        }
    };

    public int getStoryVotes() {
        return storyVotes;
    }

    public void setStoryVotes(int storyVotes) {
        this.storyVotes = storyVotes;
    }

    public String getStoryBy() {
        return storyBy;
    }

    public void setStoryBy(String storyBy) {
        this.storyBy = storyBy;
    }

    public ArrayList<String> getCommentsIds() {
        return commentsIds;
    }

    public void setCommentsIds(ArrayList<String> commentsIds) {
        this.commentsIds = commentsIds;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public String getStoryUrl() {
        return storyUrl;
    }

    public void setStoryUrl(String storyUrl) {
        this.storyUrl = storyUrl;
    }

    public String getStoryTimestamp() {
        return storyTimestamp;
    }

    public void setStoryTimestamp(String storyTimestamp) {
        this.storyTimestamp = storyTimestamp;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(storyId);
        parcel.writeInt(commentsCount);
        parcel.writeString(storyTitle);
        parcel.writeString(storyUrl);
        parcel.writeString(storyTimestamp);
        parcel.writeInt(storyVotes);
        parcel.writeString(storyBy);
        parcel.writeStringList(commentsIds);
    }
}
