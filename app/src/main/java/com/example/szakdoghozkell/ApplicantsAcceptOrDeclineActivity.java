package com.example.szakdoghozkell;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.szakdoghozkell.databinding.ActivityAddjobsBinding;

public class ApplicantsAcceptOrDeclineActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    TextView studentid;

    TextView user_description;

    Button accept;

    Button decline;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_jobapplication);
        Intent intent = getIntent();
        studentid = findViewById(R.id.applyjob_studentid);
        studentid.setText(intent.getStringExtra("studentid"));
        String StudentId = studentid.getText().toString();
        int jobid = intent.getIntExtra("jobid",0);
        user_description = findViewById(R.id.applyjob_description);
        String userDescription = databaseHelper.getUserDescreption(StudentId,jobid);
        user_description.setText(userDescription);
        accept = findViewById(R.id.accept_button);
        decline = findViewById(R.id.declinebutton_button);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean accepted = databaseHelper.updateAcceptenceAccepted(StudentId,jobid);
                if (accepted) {
                    Boolean evrybodyelsedeclined = databaseHelper.updateAcceptencedeclinedv(jobid);
                    Boolean deletejob = databaseHelper.deletejob(jobid);
                    Toast.makeText(ApplicantsAcceptOrDeclineActivity.this, "Elfogadtad", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ApplicantsAcceptOrDeclineActivity.this, "fosvagy el baztad", Toast.LENGTH_SHORT).show();
                }
            }
        });
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean declined = databaseHelper.updateAcceptenceDeclined(StudentId,jobid);
                if (declined) {
                    Toast.makeText(ApplicantsAcceptOrDeclineActivity.this, "Eutasítotad", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ApplicantsAcceptOrDeclineActivity.this, "fosvagy el baztad a declient", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}