package com.example.szakdoghozkell;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.szakdoghozkell.databinding.ActivityEditnameBinding;
import com.example.szakdoghozkell.databinding.ActivityEditpasswordforuserBinding;


public class EditPasswordForUserActivity extends AppCompatActivity {

    ActivityEditpasswordforuserBinding binding;
    DatabaseHelper databaseHelper;
    TextView email;
    Button editbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditpasswordforuserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);

        email = findViewById(R.id.email_text_user);
        editbutton = findViewById(R.id.editbutton_user);

        Intent intent = getIntent();
        email.setText(intent.getStringExtra("email"));
        String stremail = email.getText().toString();
        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (databaseHelper.checkEmail(stremail)) {
                    String password = binding.editPasswordUser.getText().toString();
                    String confirmPassword = binding.editRepasswordUser.getText().toString();
                    if (password.equals(confirmPassword)) {
                        Boolean checkpassword = databaseHelper.updatePasswordUser(stremail, password);
                        if (checkpassword) {
                            Toast.makeText(EditPasswordForUserActivity.this, "Sikeres jelszóváltoztatás", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(EditPasswordForUserActivity.this, "Rosz", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(EditPasswordForUserActivity.this, "Nem egyezik a két Jelszó", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
