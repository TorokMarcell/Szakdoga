package com.example.DiakMelo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        applicantslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) simpleCursorAdapter.getItem(position);
                String studentid = cursor.getString(1);
                int jobid = cursor.getInt(2);
                Intent intent = new Intent(ListApplicantsActivity.this,ApplicantsAcceptOrDeclineActivity.class);
                intent.putExtra("jobid", jobid);
                intent.putExtra("studentid",studentid);
                startActivity(intent);
            }
        });
    }
}
