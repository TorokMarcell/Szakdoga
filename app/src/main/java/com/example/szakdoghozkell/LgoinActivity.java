package com.example.szakdoghozkell;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.szakdoghozkell.databinding.ActivityLgoinBinding;

public class LgoinActivity extends AppCompatActivity {
    ActivityLgoinBinding binding;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLgoinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);
        binding.loginButton.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View view) {
                String email = binding.loginEmail.getText().toString();
                String password = binding.loginPassword.getText().toString();
                if(email.equals("")||password.equals(""))
                    Toast.makeText(LgoinActivity.this, "Kérlek töltsd ki az összes mezőt", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkCredentials = databaseHelper.checkPassword(email, password);
                    Boolean checkAdminCredentials = databaseHelper.checkAdminPassword(email, password);
                    if(checkCredentials||checkAdminCredentials){
                        if(databaseHelper.checkValidated(email)){
                            Intent intent = new Intent(LgoinActivity.this, UserActivity.class);
                            intent.putExtra("email",email);
                            startActivity(intent);
                        }
                        else {
                            if(databaseHelper.checkIfAdmin(email)) {
                                Toast.makeText(LgoinActivity.this, "Sikeres Bejelentkezés!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                intent.putExtra("email",email);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(LgoinActivity.this, "Sikeres Bejelentkezés!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("email",email);
                                startActivity(intent);
                            }
                        }
                    }else{
                        Toast.makeText(LgoinActivity.this, "Rosz adatokat adtál meg", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
       binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LgoinActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}