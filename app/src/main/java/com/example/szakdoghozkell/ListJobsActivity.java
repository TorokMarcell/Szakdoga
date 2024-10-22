package com.example.szakdoghozkell;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.szakdoghozkell.databinding.ActivityListjobssBinding;

public class ListJobsActivity  extends AppCompatActivity {

    ActivityListjobssBinding binding;

    DatabaseHelper databaseHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_listjobss);
        ListView jobslist = findViewById(R.id.list_jobs);
        final SimpleCursorAdapter simpleCursorAdapter = databaseHelper.getDatas(1);
        jobslist.setAdapter(simpleCursorAdapter);
        jobslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) simpleCursorAdapter.getItem(position);
                String title = cursor.getString(1);
                Toast.makeText(ListJobsActivity.this,title,Toast.LENGTH_LONG).show();
            }
        });
    }
}
