package com.example.szakdoghozkell;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.szakdoghozkell.databinding.ActivityAddjobsBinding;
import com.example.szakdoghozkell.databinding.ActivityApplytoajobBinding;

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
                if(allFieldNotEmpty(description)){
                    if(insertToDb(studentid,jobid,description)){
                        Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                        intent.putExtra("email",Email);
                        startActivity(intent);
                    }
                }
            }
        });


    }
    public boolean allFieldNotEmpty(String descreption){
        if(descreption.equals("")){
            Toast.makeText(ApplyActivity.this, "Kérlek töltsd ki az összes mezőt", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }
    public boolean insertToDb(String studentid,int jobid,String description){
        Boolean insert = databaseHelper.insertDataTojobsAcceptence(studentid,jobid,description);
        if (insert) {
            Toast.makeText(ApplyActivity.this, "Sikeresen Jelentkeztél", Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            Toast.makeText(ApplyActivity.this, "Valami hiba történt kérlek próbáld meg újra", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
