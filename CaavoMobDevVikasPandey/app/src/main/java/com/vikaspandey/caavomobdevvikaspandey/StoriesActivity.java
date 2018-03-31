package com.vikaspandey.caavomobdevvikaspandey;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.vikaspandey.caavomobdevvikaspandey.pojo.Story;
import com.vikaspandey.caavomobdevvikaspandey.pojo.StoryLine;

import java.util.ArrayList;
import java.util.HashMap;

public class StoriesActivity extends BaseActivity {
    HashMap<String,Story> storyMap;
    ArrayList<Story> storyArrayList;


    RecyclerView storiesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(App.account.getEmail());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(activity,StartNewStoryActivity.class));
            }
        });

        storiesRecyclerView = findViewById(R.id.story_recycle_view);
        storiesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        storiesRecyclerView.setAdapter(new StoriesAdapter());
        storyMap= new HashMap<>();
        mDatabase.child("Stories").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Story story = dataSnapshot.getValue(Story.class);
                storyMap.put(dataSnapshot.getKey(),story);
                updateStoryList();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Story story = dataSnapshot.getValue(Story.class);
                storyMap.put(dataSnapshot.getKey(),story);
                updateStoryList();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Story story = dataSnapshot.getValue(Story.class);
                storyMap.remove(dataSnapshot.getKey());
                updateStoryList();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    // efresh story List
    private void updateStoryList() {


        storyArrayList = new ArrayList<>(storyMap.values());
        storiesRecyclerView.getAdapter().notifyDataSetChanged();
    }

    class StoryHolder extends RecyclerView.ViewHolder {

        TextView storyTitleTextView;
        TextView storyOwnerTextView;
        RecyclerView storylineRecyclerView;

        public StoryHolder(View itemView) {
            super(itemView);
            storyTitleTextView = itemView.findViewById(R.id.story_title);
            storyOwnerTextView = itemView.findViewById(R.id.story_owner);
            storylineRecyclerView = itemView.findViewById(R.id.storyline_recyclerview);
        }
    }

    private class StoriesAdapter extends RecyclerView.Adapter<StoryHolder> {

        @Override
        public StoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           return new StoryHolder(activity.getLayoutInflater().inflate(R.layout.story_item,null));

        }

        @Override
        public void onBindViewHolder(final StoryHolder storyHolder, int position) {
            final Story story = storyArrayList.get(position);
            storyHolder.storyTitleTextView.setText(story.getTitle());
            storyHolder.storyOwnerTextView.setText(story.getOwnerId());
            storyHolder.storylineRecyclerView.setAdapter(new StoryLinesAdapter(activity,story));

            mDatabase.child("StoryLines").child(story.getStoryId()).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    story.putStoryLine(dataSnapshot.getValue(StoryLine.class));
                    storyHolder.storylineRecyclerView.getAdapter().notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    story.putStoryLine(dataSnapshot.getValue(StoryLine.class));
                    storyHolder.storylineRecyclerView.getAdapter().notifyDataSetChanged();

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    story.removeStoryLine(dataSnapshot.getKey());
                    storyHolder.storylineRecyclerView.getAdapter().notifyDataSetChanged();

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        @Override
        public int getItemCount() {
            if(storyArrayList == null)
                return 0;
           return storyArrayList.size();

        }
    }
}
