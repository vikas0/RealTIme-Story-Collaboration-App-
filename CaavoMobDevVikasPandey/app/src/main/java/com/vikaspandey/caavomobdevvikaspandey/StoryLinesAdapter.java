package com.vikaspandey.caavomobdevvikaspandey;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vikaspandey.caavomobdevvikaspandey.pojo.Story;
import com.vikaspandey.caavomobdevvikaspandey.pojo.StoryLine;

/**
 * Created by 0vika on 01-04-2018.
 */

class StoryLinesAdapter extends RecyclerView.Adapter<StoryLinesAdapter.StoryLineHolder> {
    Activity activity;
    Story story;

    @Override
    public StoryLineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       return new StoryLineHolder(activity.getLayoutInflater().inflate(R.layout.storyline_item,null));

    }

    @Override
    public void onBindViewHolder(StoryLineHolder holder, int position) {
        StoryLine storyLine = story.getStoryLineArrayList().get(position);
         holder.storyLineTextView.setText(storyLine.getStoryLine());
    }

    @Override
    public int getItemCount() {
        return (story==null||story.getStoryLineArrayList()==null)?0:story.getStoryLineArrayList().size();
    }

    static class StoryLineHolder extends RecyclerView.ViewHolder{

        TextView storyLineTextView;
        public StoryLineHolder(View itemView) {
            super(itemView);
        }
    }
    public StoryLinesAdapter(Activity activity, Story story) {
        this.activity = activity;
        this.story = story;
    }
}
