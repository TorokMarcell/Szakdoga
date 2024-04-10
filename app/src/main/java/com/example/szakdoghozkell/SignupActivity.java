package com.example.szakdoghozkell;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.szakdoghozkell.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    DatabaseHelper databaseHelper;



//    protected boolean Checking(String email,String password,String studentid,String confirmPassword){
//       email = binding.signupEmail.getText().toString();
//         password = binding.signupPassword.getText().toString();
//        studentid = binding.signupStudentId.getText().toString();
//        confirmPassword = binding.signupConfirm.getText().toString();
//        if(email.equals("")||password.equals("")||confirmPassword.equals("")||studentid.equals(""))
//            Toast.makeText(SignupActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
//        if(!email.matches("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")){
//            Toast.makeText(SignupActivity.this, "ROSZEMAIL", Toast.LENGTH_SHORT).show();
//
//        }
//        if((!studentid.matches("^[0-9]*$"))){
//            Toast.makeText(SignupActivity.this, "NEMSZAM", Toast.LENGTH_SHORT).show();
//
//        }
//        if(!(studentid.length() == 6))
//        {
//            Toast.makeText(SignupActivity.this, "KEVES", Toast.LENGTH_SHORT).show();
//
//
//        }
//        return true;
//    }
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivitySignupBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    databaseHelper = new DatabaseHelper(this);
    binding.signupButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String email = binding.signupEmail.getText().toString();
            String password = binding.signupPassword.getText().toString();
            String studentid = binding.signupStudentId.getText().toString();
            String confirmPassword = binding.signupConfirm.getText().toString();
            String firstname = binding.signupFirstname.getText().toString();
            String lastname = binding.signupLastname.getText().toString();
            String validated = "N";
            Boolean checking = true;
            if(email.equals("")||password.equals("")||confirmPassword.equals("")||studentid.equals("")||firstname.equals("")||lastname.equals(""))
                Toast.makeText(SignupActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
            if(!email.matches("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")){
                Toast.makeText(SignupActivity.this, "ROSZEMAIL", Toast.LENGTH_SHORT).show();
                checking = false;
            }
            if((!studentid.matches("^[0-9]*$"))){
                Toast.makeText(SignupActivity.this, "NEMSZAM", Toast.LENGTH_SHORT).show();
                checking = false;
            }
            if(!(studentid.length() == 10))
            {
                Toast.makeText(SignupActivity.this, "KEVES", Toast.LENGTH_SHORT).show();
                checking = false;

            }
            if (firstname.matches("^[0-9]*$")){
                Toast.makeText(SignupActivity.this, "SZAM", Toast.LENGTH_SHORT).show();
                checking = false;
            }
            if (lastname.matches("^[0-9]*$")){
                Toast.makeText(SignupActivity.this, "SZAM", Toast.LENGTH_SHORT).show();
                checking = false;
            }
            else {
                if (checking) {
                    if (password.equals(confirmPassword)) {
                        Boolean checkUserEmail = databaseHelper.checkEmail(email);
                        Boolean checkUserId = databaseHelper.checkStudentID(studentid);
                        if (!checkUserEmail && !checkUserId) {
                            Boolean insert = databaseHelper.insertData(email, password, studentid, validated, firstname, lastname);
                            if (insert) {
                                Toast.makeText(SignupActivity.this, "Signup Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LgoinActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignupActivity.this, "Signup Failed!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignupActivity.this, "User already exists! Please login", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignupActivity.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(SignupActivity.this, "Signup Failed! try again", Toast.LENGTH_SHORT).show();
                }
            }
        }
    });
    binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(SignupActivity.this, LgoinActivity.class);
            startActivity(intent);
        }
    });
}
}
