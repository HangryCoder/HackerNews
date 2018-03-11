package hackernews.mobile.com.hackernews.model;

/**
 * Created by soniawadji on 10/03/18.
 */

public class News {

    private int newsId, commentsCount;
    private String newsTitle, newsUrl, newsTimestamp;

    public News(int newsId, String newsTitle, String newsUrl, String newsTimestamp, int commentsCount) {
        this.newsId = newsId;
        this.commentsCount = commentsCount;
        this.newsTitle = newsTitle;
        this.newsUrl = newsUrl;
        this.newsTimestamp = newsTimestamp;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getNewsTimestamp() {
        return newsTimestamp;
    }

    public void setNewsTimestamp(String newsTimestamp) {
        this.newsTimestamp = newsTimestamp;
    }
}
