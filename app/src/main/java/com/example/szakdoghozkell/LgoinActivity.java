package com.example.szakdoghozkell;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.szakdoghozkell.databinding.ActivityLgoinBinding;
import com.google.android.material.snackbar.Snackbar;

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
                    Snackbar.make(findViewById(android.R.id.content), "Kérlek töltsd ki az összes mezőt", Snackbar.LENGTH_LONG).show();
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
                                Snackbar.make(findViewById(android.R.id.content), "Sikeres Bejelentkezés!", Snackbar.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                intent.putExtra("email",email);
                                startActivity(intent);
                            }
                            else {
                                Snackbar.make(findViewById(android.R.id.content), "Sikeres Bejelentkezés!", Snackbar.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("email",email);
                                startActivity(intent);
                            }
                        }
                    }else{
                        Snackbar.make(findViewById(android.R.id.content), "Rossz adatokat adtál meg", Snackbar.LENGTH_LONG).show();
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
    public boolean allFieldNotEmpty(String email,String password){
        if(email.equals("")||password.equals("")) {
            Toast.makeText(LgoinActivity.this, "Kérlek töltsd ki az összes mezőt", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}