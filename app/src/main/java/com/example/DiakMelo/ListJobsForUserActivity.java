package com.example.DiakMelo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListJobsForUserActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    TextView Email;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_listjobsforuser);
        ListView jobslist = findViewById(R.id.list_jobs_for_user);
        Email = findViewById(R.id.textView8);
        Intent intent = getIntent();
        Email.setText(intent.getStringExtra("email"));
        String email = Email.getText().toString();
        final SimpleCursorAdapter simpleCursorAdapter = databaseHelper.getDatasforUser();
        jobslist.setAdapter(simpleCursorAdapter);

        jobslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListJobsForUserActivity.this,ApplyActivity.class);
                intent.putExtra("jobid", (int) id);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
    }
}
