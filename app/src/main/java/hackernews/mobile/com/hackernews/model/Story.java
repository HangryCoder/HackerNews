package hackernews.mobile.com.hackernews.model;

/**
 * Created by soniawadji on 10/03/18.
 */

public class Story {

    private int storyId, commentsCount;
    private String storyTitle, storyUrl, storyTimestamp;

    public Story(int storyId, String storyTitle, String storyUrl, String storyTimestamp, int commentsCount) {
        this.storyId = storyId;
        this.commentsCount = commentsCount;
        this.storyTitle = storyTitle;
        this.storyUrl = storyUrl;
        this.storyTimestamp = storyTimestamp;
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
