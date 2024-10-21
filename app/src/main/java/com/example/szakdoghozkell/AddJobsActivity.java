package com.example.szakdoghozkell;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.szakdoghozkell.databinding.ActivityAddjobsBinding;


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
                if(title.equals("")||descreption.equals("")||tmpsalary.equals("")||location.equals("")){
                    Toast.makeText(AddJobsActivity.this, "Kérlek töltsd ki az összes mezőt", Toast.LENGTH_SHORT).show();
                }
                Boolean insert = databaseHelper.insertDataToJobs(title,descreption,salary,location,adminid);
                if (insert) {
                    Toast.makeText(AddJobsActivity.this, "Sikereses Feladtad ezt a munkát", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                    intent.putExtra("email",Email);
                    startActivity(intent);
                }
            }
        });
    }

}
