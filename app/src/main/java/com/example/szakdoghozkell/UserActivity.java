package com.example.szakdoghozkell;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.szakdoghozkell.databinding.ActivityUserBinding;


public class UserActivity extends AppCompatActivity {

    ActivityUserBinding binding;

    DatabaseHelper databaseHelper;

    TextView Email;
    Button exitButton;
    Button editPasswordButton;

    Button registerforjobs;
    Button joblistuserbutton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        databaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_user);
        joblistuserbutton = findViewById(R.id.jobbforUserButton);
        registerforjobs = findViewById(R.id.joblistButtonForUsers);
        exitButton = findViewById(R.id.exitbuttonusers);
        editPasswordButton = findViewById(R.id.editPasswordbutton2);
        Email = findViewById(R.id.textView6);
        Intent intent = getIntent();
        Email.setText(intent.getStringExtra("email"));
        joblistuserbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                Intent intent = new Intent(UserActivity.this,ListJobsForUserActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        registerforjobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                Intent intent = new Intent(UserActivity.this,ListJobsForApplicantsActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
    }
}
