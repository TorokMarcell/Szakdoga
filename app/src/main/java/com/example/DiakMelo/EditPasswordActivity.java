package com.example.DiakMelo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.DiakMelo.databinding.ActivityEditnameBinding;
import com.google.android.material.snackbar.Snackbar;


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
                            Snackbar.make(findViewById(android.R.id.content), "Sikeres jelszóváltoztatás", Snackbar.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                            startActivity(intent);
                        } else {
                            Snackbar.make(findViewById(android.R.id.content), "Valami hiba történt kérlek próbáld újra", Snackbar.LENGTH_LONG).show();
                        }
                    } else {
                        Snackbar.make(findViewById(android.R.id.content), "Nem egyezik a két Jelszó", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
