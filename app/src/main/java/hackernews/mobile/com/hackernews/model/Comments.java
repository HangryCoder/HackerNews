package hackernews.mobile.com.hackernews.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by soniawadji on 11/03/18.
 */

public class Comments extends RealmObject {

    @SerializedName("id")
    private String commentId;
    @SerializedName("time")
    private String commentTime;
    @SerializedName("by")
    private String commentUser;
    @SerializedName("text")
    private String commentString;

    public Comments() {

    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
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
