package hackernews.mobile.com.hackernews.model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by soniawadji on 10/03/18.
 */

public class Story {

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
    private JSONArray commentsIds;

    public Story(int storyId, String storyTitle, String storyUrl, String storyTimestamp, int commentsCount) {
        this.storyId = storyId;
        this.commentsCount = commentsCount;
        this.storyTitle = storyTitle;
        this.storyUrl = storyUrl;
        this.storyTimestamp = storyTimestamp;
    }

    public Story(int storyId, int commentsCount, String storyTitle, String storyUrl, String storyTimestamp,
                 int storyVotes, String storyBy, JSONArray commentsIds) {
        this.storyId = storyId;
        this.commentsCount = commentsCount;
        this.storyTitle = storyTitle;
        this.storyUrl = storyUrl;
        this.storyTimestamp = storyTimestamp;
        this.storyVotes = storyVotes;
        this.storyBy = storyBy;
        this.commentsIds = commentsIds;
    }

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

    public JSONArray getCommentsIds() {
        return commentsIds;
    }

    public void setCommentsIds(JSONArray commentsIds) {
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

}
