package com.vikaspandey.caavomobdevvikaspandey;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BaseActivity extends AppCompatActivity {
   // For context
    protected Activity activity;
    protected DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    public  void  showToast(String msg)
    {
                Toast.makeText(this,msg, Toast.LENGTH_LONG).show();
}

}
