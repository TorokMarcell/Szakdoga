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
            Integer validated = 0;
            String role = "user";
            boolean checking = true;
            if(email.equals("")||password.equals("")||confirmPassword.equals("")||studentid.equals("")||firstname.equals("")||lastname.equals(""))
                Toast.makeText(SignupActivity.this, "Kérlek töltsd ki az összes mezőt", Toast.LENGTH_SHORT).show();
            if(!email.matches("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")){
                Toast.makeText(SignupActivity.this, "Nem helyes formátumban adtad meg az emailed", Toast.LENGTH_SHORT).show();
                checking = false;
            }
            if((!studentid.matches("^[0-9]*$"))){
                Toast.makeText(SignupActivity.this, "Kérlek ide csak számot adj be", Toast.LENGTH_SHORT).show();
                checking = false;
            }
            if(!(studentid.length() == 10))
            {
                Toast.makeText(SignupActivity.this, "10 karakterből kell áljon az azonosítód", Toast.LENGTH_SHORT).show();
                checking = false;

            }
            if (firstname.matches("^[^a-zA-Z]*$")){
                Toast.makeText(SignupActivity.this, "Kérlek ide csak betűt írj", Toast.LENGTH_SHORT).show();
                checking = false;
            }
            if (lastname.matches("^[^a-z-A-Z]*$")){
                Toast.makeText(SignupActivity.this, "Kérlek ide csak betűt írj", Toast.LENGTH_SHORT).show();
                checking = false;
            }
            else {
                if (checking) {
                    if (password.equals(confirmPassword)) {
                        Boolean checkUserEmail = databaseHelper.checkEmail(email);
                        Boolean checkUserId = databaseHelper.checkStudentID(studentid);
                        Boolean checkAdminEmail = databaseHelper.checkAdminEmail(email);
                        firstname =replaceAccents(firstname.toUpperCase());
                        lastname = replaceAccents(lastname.toUpperCase());
                        if (!checkUserEmail && !checkUserId && !checkAdminEmail) {
                            Boolean insert = databaseHelper.insertDataToUsers(email, password, studentid, validated, firstname, lastname, role);
                            if (insert) {
                                Toast.makeText(SignupActivity.this, "Sikeres Regisztráció", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LgoinActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignupActivity.this, "Sikertelen Regisztráció", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignupActivity.this, "Ez az email-cím  vagy halgatói azonosító már használatban van kérlek probálj meg egy másikat", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignupActivity.this, "Helytelen Jelszó", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(SignupActivity.this, "Valami nem stimmel.", Toast.LENGTH_SHORT).show();
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
