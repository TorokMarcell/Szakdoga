package com.example.DiakMelo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.DiakMelo.databinding.ActivityApplytoajobBinding;
import com.google.android.material.snackbar.Snackbar;

public class ApplyActivity extends AppCompatActivity {

    ActivityApplytoajobBinding binding;

    DatabaseHelper databaseHelper;

    TextView email;

    TextView jobid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityApplytoajobBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);
        Intent intent = getIntent();
        email = findViewById(R.id.textView9);
        email.setText(intent.getStringExtra("email"));
        String Email = email.getText().toString();
        int jobid = intent.getIntExtra("jobid",0);
        String studentid = databaseHelper.getStudentId(Email);

        binding.applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = binding.applyDescription.getText().toString();
                if(allFieldNotEmpty(description)) {
                    if (CheckIfRegistered(studentid, jobid)) {
                        if (insertToDb(studentid, jobid, description)) {
                            Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                            intent.putExtra("email", Email);
                            startActivity(intent);
                        }
                    }
                }else{
                    Intent intent = new Intent(getApplicationContext(), ListJobsForUserActivity.class);
                    intent.putExtra("email",Email);
                    startActivity(intent);
                }
            }
        });


    }
    public boolean allFieldNotEmpty(String descreption){
        if(descreption.equals("")){
            Snackbar.make(findViewById(android.R.id.content), "Kérlek töltsd ki az összes mezőt", Snackbar.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }
    public boolean insertToDb(String studentid,int jobid,String description){
            Boolean insert = databaseHelper.insertDataTojobsAcceptence(studentid, jobid, description);
            if (insert) {
                Snackbar.make(findViewById(android.R.id.content), "Sikeresen Jelentkeztél", Snackbar.LENGTH_SHORT).show();
                return true;
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Valami hiba történt kérlek próbáld meg újra", Snackbar.LENGTH_SHORT).show();
                return false;
            }
    }
    public boolean CheckIfRegistered(String studentid,int jobid){
        Boolean checkifalreadyRegistered = databaseHelper.checkIfRegisteredAlready(studentid,jobid);
        if(checkifalreadyRegistered){
            Snackbar.make(findViewById(android.R.id.content), "Erre a Munkára már jelentkeztél!", Snackbar.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }
}
