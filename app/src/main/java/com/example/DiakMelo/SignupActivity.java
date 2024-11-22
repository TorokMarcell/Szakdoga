package com.example.DiakMelo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.DiakMelo.databinding.ActivitySignupBinding;
import com.google.android.material.snackbar.Snackbar;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    DatabaseHelper databaseHelper;
    boolean checking = true;

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
            String date = binding.signupBirthdate.getText().toString();
            Integer validated = 0;
            if (allFieldNotEmpty(email, password, studentid, confirmPassword, firstname, lastname, date)) {
                if (RegexChecks(email, studentid, date)) {
                    if (checking) {
                        if (checkPassword(password, confirmPassword)) {
                            if (checkifAlreadyinUse(email, studentid, firstname, lastname)) {
                                Boolean insert = databaseHelper.insertDataToUsers(email, password, studentid, validated, firstname, lastname);
                                if (insert) {
                                    Snackbar.make(findViewById(android.R.id.content), "Sikeres Regisztráció", Snackbar.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    Snackbar.make(findViewById(android.R.id.content), "Sikertelen Regisztráció", Snackbar.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
            }
        }
    });

    binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    });

}
    public boolean checkifAlreadyinUse(String email,String studentid,String firstname,String lastname){
        Boolean checkUserEmail = databaseHelper.checkEmail(email);
        Boolean checkUserId = databaseHelper.checkStudentID(studentid);
        Boolean checkAdminEmail = databaseHelper.checkAdminEmail(email);
        firstname = replaceAccents(firstname.toUpperCase());
        lastname = replaceAccents(lastname.toUpperCase());
        if (!checkUserEmail && !checkUserId && !checkAdminEmail) {
            return true;
        }
        else {
            Snackbar.make(findViewById(android.R.id.content), "Ez az email-cím  vagy halgatói azonosító már használatban van kérlek probálj meg egy másikat", Snackbar.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean checkPassword(String password,String confirmpassword){
        if (password.equals(confirmpassword)) {
            return true;
        }
        else {
            Snackbar.make(findViewById(android.R.id.content), "A két jelszó nem egyezik meg", Snackbar.LENGTH_LONG).show();
            return false;
        }
    }
    public boolean RegexChecks(String email,String studentid,String date) {
        if (!email.matches("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")) {
            Snackbar.make(findViewById(android.R.id.content), "Nem helyes formátumban adtad meg az emailed", Snackbar.LENGTH_SHORT).show();
            checking = false;
        }
        if ((!studentid.matches("^[0-9]*$"))) {
            Snackbar.make(findViewById(android.R.id.content), "Kérlek ide csak számot adj be", Snackbar.LENGTH_SHORT).show();
            checking = false;
        }
        if (!(studentid.length() == 10)) {
            Snackbar.make(findViewById(android.R.id.content), "10 karakterből kell áljon az azonosítód", Snackbar.LENGTH_SHORT).show();
            checking = false;
        }
            if (!(date.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$"))) {
                Snackbar.make(findViewById(android.R.id.content), "Kérlek így add meg a születési dátumod: 2000-01-01", Snackbar.LENGTH_SHORT).show();
                checking = false;
            }
        return checking;
    }
    public boolean allFieldNotEmpty(String email,String password,String confirmPassword,String studentid,String firstname,String lastname,String date){
        if (email.equals("") || password.equals("") || confirmPassword.equals("") || studentid.equals("") || firstname.equals("") || lastname.equals("")|| date.equals("")) {
            Snackbar.make(findViewById(android.R.id.content), "Kérlek Töltsd ki az összes mezőt.", Snackbar.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }

    public String replaceAccents(String results){
        if(results == null){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < results.length(); i++) {
            sb.append(replaceAccent(results.charAt(i)));
        }
        return sb.toString();
    }
    public char replaceAccent(char letter) {
        switch (letter) {
            case 'Á':
                return 'A';
            case 'É':
                return 'E';
            case 'Ó':
            case 'Ő':
            case 'Ö':
                return 'O';
            case 'Ú':
            case 'Ű':
            case 'Ü':
                return 'U';
            case 'Í':
                return 'I';
            default:
                return letter;

        }
    }
}
