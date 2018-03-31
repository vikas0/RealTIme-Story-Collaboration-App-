package com.vikaspandey.caavomobdevvikaspandey;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

public class StartNewStoryActivity extends BaseActivity {

    EditText storyTitleEditText,storyLineEditText;
    Button submitButton;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_new_story);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Start New Story");

        storyLineEditText = findViewById(R.id.storyline_edit_text);
        storyTitleEditText = findViewById(R.id.story_title_edit_text);
        submitButton  = findViewById(R.id.submit_button);
        progressBar = findViewById(R.id.progress_bar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }


    public void submit(View view) {
        String storyLine = storyLineEditText.getText().toString();
        int countWOrds = storyLine.split(" ").length;
        if(storyTitleEditText.getText().length() == 0)
        {
            showToast("Please Enter TItle");
            return;
        }
       else if(countWOrds>=50)
        {
            showToast("storyline by single user can not exceed 50 words");
            return;
        }
        else
        {
//
//            String storyId;
//            String title;
//            String ownerId;
//            long startTime;
submitButton.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
           String storyId = mDatabase.child("Stories").push().getKey();
            HashMap<String ,Object> storyHashMap = new HashMap<>();
            HashMap<String ,Object> updateHashMap = new HashMap<>();
            storyHashMap.put("storyId",storyId);
            storyHashMap.put("title",storyTitleEditText.getText().toString());
            storyHashMap.put("ownerId",App.account.getEmail());

            updateHashMap.put("Stories/"+storyId,storyHashMap);

            HashMap<String ,Object> storyLineHashMap = new HashMap<>();
//            private String storyId;
//
//            private String storyLineId;
//            private   String storyLine;
//            private  String writtenBy;
            String storyLineId = mDatabase.child("StoryLines/"+storyId).push().getKey();
            storyLineHashMap.put("storyId",storyId);
            storyLineHashMap.put("storyLineId",storyLineId);
            storyLineHashMap.put("storyLine",storyLineEditText.getText().toString());
            storyLineHashMap.put("writtenBy",App.account.getEmail());


updateHashMap.put("StoryLines/"+storyId+"/"+storyLineId,storyLineHashMap);

            mDatabase.updateChildren(updateHashMap, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    onBackPressed();

                }
            });



        }

    }
}
