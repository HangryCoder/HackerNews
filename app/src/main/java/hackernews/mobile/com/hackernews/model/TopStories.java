package hackernews.mobile.com.hackernews.model;

import java.util.ArrayList;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by soniawadji on 12/03/18.
 */

public class TopStories /*extends RealmObject*/ {

    /***
     * Might delete this later
     * */
    @PrimaryKey
    private String id;
    private ArrayList<Story> storyArrayList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Story> getStoryArrayList() {
        return storyArrayList;
    }

    public void setStoryArrayList(ArrayList<Story> storyArrayList) {
        this.storyArrayList = storyArrayList;
    }
}
