package com.example.szakdoghozkell;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.szakdoghozkell.databinding.ActivityAddjobsBinding;
import com.google.android.material.snackbar.Snackbar;


public class AddJobsActivity extends AppCompatActivity {
    ActivityAddjobsBinding binding;
    DatabaseHelper databaseHelper;

    TextView email;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddjobsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);
        Intent intent = getIntent();
        email = findViewById(R.id.textView4);
        email.setText(intent.getStringExtra("email"));
        String Email = email.getText().toString();
        int adminid = databaseHelper.getAdminId(Email);

        binding.jobsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = binding.jobsTitle.getText().toString();
                String descreption = binding.jobsDescription.getText().toString();
                String tmpsalary = binding.jobsSalary.getText().toString();
                int salary = Integer.parseInt(tmpsalary);
                String location = binding.jobsLocation.getText().toString();
                if(allFieldNotEmpty(title,descreption,tmpsalary,location)){
                    if(insertToDb(title,descreption,salary,location,adminid)){
                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        intent.putExtra("email",Email);
                        startActivity(intent);
                    }
                }
            }
        });
    }
    public boolean allFieldNotEmpty(String title,String descreption, String tmpsalary, String location){
        if(title.equals("")||descreption.equals("")||tmpsalary.equals("")||location.equals("")){
            Snackbar.make(findViewById(android.R.id.content), "Kérlek töltsd ki az összes mezőt", Snackbar.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }
    public boolean insertToDb(String title,String descreption,int salary, String location,int adminid){
        Boolean insert = databaseHelper.insertDataToJobs(title,descreption,salary,location,adminid);
        if (insert) {
            Snackbar.make(findViewById(android.R.id.content), "Sikereses Feladtad ezt a munkát", Snackbar.LENGTH_LONG).show();
            return true;
        }
        else{
            Snackbar.make(findViewById(android.R.id.content), "Valami hiba történt kérlek probáld újra", Snackbar.LENGTH_LONG).show();
            return false;
        }
    }

}
