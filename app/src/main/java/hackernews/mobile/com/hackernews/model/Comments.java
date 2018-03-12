package hackernews.mobile.com.hackernews.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by soniawadji on 11/03/18.
 */

public class Comments {

    @SerializedName("time")
    private String commentTime;
    @SerializedName("by")
    private String commentUser;
    @SerializedName("text")
    private String commentString;

    public Comments(String commentTime, String commentUser, String commentString) {
        this.commentTime = commentTime;
        this.commentUser = commentUser;
        this.commentString = commentString;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(String commentUser) {
        this.commentUser = commentUser;
    }

    public String getCommentString() {
        return commentString;
    }

    public void setCommentString(String commentString) {
        this.commentString = commentString;
    }
}
