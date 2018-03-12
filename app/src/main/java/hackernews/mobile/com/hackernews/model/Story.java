package hackernews.mobile.com.hackernews.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by soniawadji on 10/03/18.
 */

public class Story extends RealmObject implements Parcelable {

    @PrimaryKey
    @SerializedName("id")
    private String storyId;
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
    private RealmList<String> commentsIds;

    public Story() {
    }

    protected Story(Parcel in) {
        storyId = in.readString();
        commentsCount = in.readInt();
        storyTitle = in.readString();
        storyUrl = in.readString();
        storyTimestamp = in.readString();
        storyVotes = in.readInt();
        storyBy = in.readString();
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

    public RealmList<String> getCommentsIds() {
        return commentsIds;
    }

    public void setCommentsIds(RealmList<String> commentsIds) {
        this.commentsIds = commentsIds;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
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
        parcel.writeString(storyId);
        parcel.writeInt(commentsCount);
        parcel.writeString(storyTitle);
        parcel.writeString(storyUrl);
        parcel.writeString(storyTimestamp);
        parcel.writeInt(storyVotes);
        parcel.writeString(storyBy);
        parcel.writeStringList(commentsIds);
    }
}
