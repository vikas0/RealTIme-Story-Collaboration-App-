package com.vikaspandey.caavomobdevvikaspandey.pojo;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 0vika on 31-03-2018.
 */

public class Story {
    String storyId;
  String title;
    String ownerId;
    long startTime;

    @Exclude
   private HashMap<String, StoryLine> storyLineHashMap = new HashMap<>();

    public void putStoryLine(StoryLine storyLine)
    {
        storyLineHashMap.put(storyLine.getStoryLineId(),storyLine);
        storyLineArrayList = new ArrayList<>(storyLineHashMap.values());

    }

    public void removeStoryLine(String storyLineId)
    {
        storyLineHashMap.remove(storyLineId);
        storyLineArrayList = new ArrayList<>(storyLineHashMap.values());
    }

    public HashMap<String, StoryLine> getStoryLineHashMap() {
        return storyLineHashMap;
    }

    public void setStoryLineHashMap(HashMap<String, StoryLine> storyLineHashMap) {
        this.storyLineHashMap = storyLineHashMap;
    }

    public ArrayList<StoryLine> getStoryLineArrayList() {
        return storyLineArrayList;
    }

    public void setStoryLineArrayList(ArrayList<StoryLine> storyLineArrayList) {
        this.storyLineArrayList = storyLineArrayList;
    }

    @Exclude
  private   ArrayList<StoryLine>  storyLineArrayList = new ArrayList<>();


    public Story() {
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
