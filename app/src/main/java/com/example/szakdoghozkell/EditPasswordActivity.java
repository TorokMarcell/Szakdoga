//package com.example.szakdoghozkell;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.szakdoghozkell.databinding.ActivityEditBinding;
//import com.example.szakdoghozkell.databinding.ActivityEditnameBinding;
//
//public class EditPasswordActivity extends AppCompatActivity {
//
//    ActivityEditBinding binding;
//    DatabaseHelper databaseHelper;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityEditBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        databaseHelper = new DatabaseHelper(this);
//        binding.editbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String email = binding.editEmail.getText().toString();
//                String password = binding.editPassword.getText().toString();
//                String confirmPassword = binding.editConfirm.getText().toString();
//                boolean checking = true;
//                if(email.equals("")||password.equals("")||confirmPassword.equals(""))
//                    Toast.makeText(EditPasswordActivity.this, "Kérlek töltsd ki az összes mezőt", Toast.LENGTH_SHORT).show();
//                if(!email.matches("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")){
//                    Toast.makeText(EditPasswordActivity.this, "Nem helyes formátumban adtad meg az emailed", Toast.LENGTH_SHORT).show();
//                    checking = false;
//                }
//                else {
//                    if (checking) {
//                        if (password.equals(confirmPassword)) {
//                            Boolean checkUserEmail = databaseHelper.checkEmail(email);
//                            if (!checkUserEmail) {
//                                Boolean insert = databaseHelper.updatePassword(email, password);
//                                if (insert) {
//                                    Toast.makeText(EditPasswordActivity.this, "Sikeres AdatMódositás", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(getApplicationContext(), LgoinActivity.class);
//                                    startActivity(intent);
//                                } else {
//                                    Toast.makeText(EditPasswordActivity.this, "Sikertelen Adatmódositás", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        } else {
//                            Toast.makeText(EditPasswordActivity.this, "Helytelen Jelszó", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    else{
//                        Toast.makeText(EditPasswordActivity.this, "Valami nem stimmel kérlek probáld ujra", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
//    }
//}
