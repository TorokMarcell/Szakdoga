package com.example.szakdoghozkell;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class ListApplicantsActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_listapplicants);
        Intent intent = getIntent();
        ListView applicantslist = findViewById(R.id.list_applicants);
        int jobid = intent.getIntExtra("jobid",0);
        final SimpleCursorAdapter simpleCursorAdapter = databaseHelper.getDatasforApplicants(jobid);
        applicantslist.setAdapter(simpleCursorAdapter);
    }
}
