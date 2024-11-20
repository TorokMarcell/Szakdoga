package com.example.szakdoghozkell;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ListJobsForApplicantsActivity  extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    TextView Email;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_listjobforapplicants);
        ListView jobsforapplicantslist = findViewById(R.id.list_jobsforapplicants);
        Email = findViewById(R.id.textView16);
        Intent intent = getIntent();
        Email.setText(intent.getStringExtra("email"));
        String  email = Email.getText().toString();
        String studentid = databaseHelper.getStudentId(email);
        final SimpleCursorAdapter simpleCursorAdapter = databaseHelper.getDatasforApplicant(studentid);
        jobsforapplicantslist.setAdapter(simpleCursorAdapter);

        jobsforapplicantslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) simpleCursorAdapter.getItem(position);
                int accepted = cursor.getInt(4);
                String stateofyourapplication = databaseHelper.getAcceptence(accepted);
                Toast.makeText(ListJobsForApplicantsActivity.this,stateofyourapplication,Toast.LENGTH_LONG).show();
            }
        });

    }
}
