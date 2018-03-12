package hackernews.mobile.com.hackernews.model;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by soniawadji on 12/03/18.
 */

public class TopStories extends RealmObject {

    /***
     * Might delete this later
     * */
    @PrimaryKey
    private String id;
    private Story story;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    /* public RealmList<Story> getStoryArrayList() {
        return storyArrayList;
    }

    public void setStoryArrayList(RealmList<Story> storyArrayList) {
        this.storyArrayList = storyArrayList;
    }*/
}
