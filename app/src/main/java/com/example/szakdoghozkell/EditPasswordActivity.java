package com.example.szakdoghozkell;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.szakdoghozkell.databinding.ActivityEditnameBinding;


public class EditPasswordActivity extends AppCompatActivity {

    ActivityEditnameBinding binding;
    DatabaseHelper databaseHelper;
    TextView email;
    Button editbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditnameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);

        email = findViewById(R.id.email_text);
        editbutton = findViewById(R.id.editbutton);

        Intent intent = getIntent();
        email.setText(intent.getStringExtra("email"));
        String stremail = email.getText().toString();
        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (databaseHelper.checkIfAdmin(stremail)) {
                    String password = binding.editPassword.getText().toString();
                    String confirmPassword = binding.editRepassword.getText().toString();
                    if (password.equals(confirmPassword)) {
                        Boolean checkpassword = databaseHelper.updatePasswordAdmin(stremail, password);
                        if (checkpassword) {
                            Toast.makeText(EditPasswordActivity.this, "Sikeres jelszóváltoztatás", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(EditPasswordActivity.this, "Rosz", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(EditPasswordActivity.this, "Nem egyezik Jelszó", Toast.LENGTH_SHORT).show();
                    }
                }
                if (databaseHelper.checkIfAdmin(stremail)) {
                    String password = binding.editPassword.getText().toString();
                    String confirmPassword = binding.editRepassword.getText().toString();

                    if (password.equals(confirmPassword)) {
                        Boolean checkpassword = databaseHelper.updatePasswordAdmin(stremail, password);
                        if (checkpassword) {
                            Toast.makeText(EditPasswordActivity.this, "Sikeres jelszóváltoztatás", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(EditPasswordActivity.this, "Rosz", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(EditPasswordActivity.this, "Nem egyezik Jelszó", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
