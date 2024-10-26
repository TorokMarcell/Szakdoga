package com.example.szakdoghozkell;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.szakdoghozkell.databinding.ActivityAdminBinding;


public class AdminActivity extends AppCompatActivity {

    ActivityAdminBinding binding;

    DatabaseHelper databaseHelper;

    TextView Email;
    Button exitButton;
    Button editPasswordButton;
    Button editNameButton;
    Button addjobsbutton;
    Button joblistbutton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);
        joblistbutton = findViewById(R.id.joblistButton);
        addjobsbutton = findViewById(R.id.addjobbutton);
        exitButton = findViewById(R.id.exitbutton);
        editPasswordButton = findViewById(R.id.editPasswordbutton);
        editNameButton = findViewById(R.id.editNamebutton);
        Email = findViewById(R.id.textView3);
        Intent intent = getIntent();
        Email.setText(intent.getStringExtra("email"));

        addjobsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                Intent intent = new Intent(AdminActivity.this,AddJobsActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        editPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, LgoinActivity.class);
                startActivity(intent);
            }
        });
        joblistbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                Intent intent = new Intent(AdminActivity.this, ListJobsActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
    }
}
