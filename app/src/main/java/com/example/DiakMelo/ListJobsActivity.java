package com.example.DiakMelo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.DiakMelo.databinding.ActivityListjobssBinding;

public class ListJobsActivity  extends AppCompatActivity {

    TextView Email;
    ActivityListjobssBinding binding;

    DatabaseHelper databaseHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_listjobss);
        ListView jobslist = findViewById(R.id.list_jobs);
        Email = findViewById(R.id.textView7);
        Intent intent = getIntent();
        Email.setText(intent.getStringExtra("email"));
        String email = Email.getText().toString();
        int adminid = databaseHelper.getAdminId(email);
        final SimpleCursorAdapter simpleCursorAdapter = databaseHelper.getDatas(adminid);
        jobslist.setAdapter(simpleCursorAdapter);

        jobslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListJobsActivity.this,ListApplicantsActivity.class);
                intent.putExtra("jobid", (int) id);
                startActivity(intent);
            }
        });
    }
}
