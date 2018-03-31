package com.vikaspandey.caavomobdevvikaspandey.pojo;

/**
 * Created by 0vika on 31-03-2018.
 */

public class StoryLine {
    private String storyId;

    private String storyLineId;
    private   String storyLine;
    private  String writtenBy;

    public String getStoryLineId() {
        return storyLineId;
    }

    public void setStoryLineId(String storyLineId) {
        this.storyLineId = storyLineId;
    }

    public StoryLine() {
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public StoryLine(String storyLine, String writtenBy) {
        this.storyLine = storyLine;
        this.writtenBy = writtenBy;
    }

    public String getStoryLine() {
        return storyLine;
    }

    public void setStoryLine(String storyLine) {
        this.storyLine = storyLine;
    }

    public String getWrittenBy() {
        return writtenBy;
    }

    public void setWrittenBy(String writtenBy) {
        this.writtenBy = writtenBy;
    }
}
