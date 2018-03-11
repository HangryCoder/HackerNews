package hackernews.mobile.com.hackernews.model;

/**
 * Created by soniawadji on 11/03/18.
 */

public class Comments {

    private String commentDate, commentTime, commentUser, topLevelComment, commentString;

    public Comments(String commentDate, String commentTime, String commentUser,
                    String topLevelComment, String commentString) {
        this.commentDate = commentDate;
        this.commentTime = commentTime;
        this.commentUser = commentUser;
        this.topLevelComment = topLevelComment;
        this.commentString = commentString;
    }


    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
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

    public String getTopLevelComment() {
        return topLevelComment;
    }

    public void setTopLevelComment(String topLevelComment) {
        this.topLevelComment = topLevelComment;
    }

    public String getCommentString() {
        return commentString;
    }

    public void setCommentString(String commentString) {
        this.commentString = commentString;
    }
}
