package com.example.szakdoghozkell;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.szakdoghozkell.databinding.ActivityEditBinding;
import com.example.szakdoghozkell.databinding.ActivitySignupBinding;

public class EditActivity extends AppCompatActivity {

    ActivityEditBinding binding;

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);
        binding.editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.signupEmail.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String studentid = binding.signupStudentId.getText().toString();
                String confirmPassword = binding.signupConfirm.getText().toString();
                String firstname = binding.signupFirstname.getText().toString();
                String lastname = binding.signupLastname.getText().toString();
                boolean checking = true;
                if(email.equals("")||password.equals("")||confirmPassword.equals("")||studentid.equals("")||firstname.equals("")||lastname.equals(""))
                    Toast.makeText(EditActivity.this, "Kérlek töltsd ki az összes mezőt", Toast.LENGTH_SHORT).show();
                if(!email.matches("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")){
                    Toast.makeText(EditActivity.this, "Nem helyes formátumban adtad meg az emailed", Toast.LENGTH_SHORT).show();
                    checking = false;
                }
                if((!studentid.matches("^[0-9]*$"))){
                    Toast.makeText(EditActivity.this, "Kérlek ide csak számot adj be", Toast.LENGTH_SHORT).show();
                    checking = false;
                }
                if(!(studentid.length() == 10))
                {
                    Toast.makeText(EditActivity.this, "10 karakterből kell áljon az azonosítód", Toast.LENGTH_SHORT).show();
                    checking = false;

                }
                if (firstname.matches("^[0-9]*$")){
                    Toast.makeText(EditActivity.this, "Kérlek ide csak betűt írj", Toast.LENGTH_SHORT).show();
                    checking = false;
                }
                if (lastname.matches("^[0-9]*$")){
                    Toast.makeText(EditActivity.this, "Kérlek ide csak betűt írj", Toast.LENGTH_SHORT).show();
                    checking = false;
                }
                else {
                    if (checking) {
                            Boolean checkUserId = databaseHelper.checkStudentID(studentid);
                            firstname=firstname.toUpperCase();
                            lastname=lastname.toUpperCase();
                            if (!checkUserId) {
                                Boolean update = databaseHelper.updatePassword(email, password);
                                if (update) {
                                    Toast.makeText(EditActivity.this, "Sikeres adatmódositás", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), LgoinActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(EditActivity.this, "Sikertelen adatmódositás", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(EditActivity.this, "Ez az email-cím már használatban van kérlek probálj meg egy másikat", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(EditActivity.this, "Helytelen Jelszó", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        });
    }
}
