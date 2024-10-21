package com.example.szakdoghozkell;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.szakdoghozkell.databinding.ActivityEditnameBinding;
import com.example.szakdoghozkell.databinding.ActivityEditpasswordemailadminBinding;

public class AdminEditActivity extends AppCompatActivity {
    ActivityEditpasswordemailadminBinding binding;

    DatabaseHelper databaseHelper;

    TextView Email;
    Button editbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditpasswordemailadminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);
        Email = findViewById(R.id.edite_emailadmin);
        editbutton = findViewById(R.id.editbutton3);

        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                Boolean checkemail = databaseHelper.checkAdminEmail(email);
                if(checkemail){
                    Intent intent = new Intent(getApplicationContext(),EditPasswordActivity.class);
                    intent.putExtra("email",email);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(AdminEditActivity.this, "Rosz emailcímet adtál meg", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
