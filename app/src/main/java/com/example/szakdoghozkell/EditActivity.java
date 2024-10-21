package com.example.szakdoghozkell;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.szakdoghozkell.databinding.ActivityEditpasswordemailBinding;

public class EditActivity extends AppCompatActivity {

    ActivityEditpasswordemailBinding binding;

    Button resetButton;
    EditText Email;

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditpasswordemailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);
        resetButton = findViewById(R.id.editbutton2);
        Email = findViewById(R.id.edite_email);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                Boolean checkemail = databaseHelper.checkEmail(email);
                if(checkemail){
                    Intent intent = new Intent(getApplicationContext(),EditPasswordActivity.class);
                    intent.putExtra("email",email);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(EditActivity.this, "Rosz emailcímet adtál meg", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
