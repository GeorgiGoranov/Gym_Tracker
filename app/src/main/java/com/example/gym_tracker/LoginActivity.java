package com.example.gym_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginBtn;
    TextView notRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        notRegistered = findViewById(R.id.signupText);

        loginBtn.setOnClickListener(view -> {
            String usernameTxt = username.getText().toString();
            String passwordTxt = username.getText().toString();

            if (TextUtils.isEmpty(usernameTxt) || TextUtils.isEmpty(passwordTxt)){
                Toast.makeText(LoginActivity.this, "Please enter your credentials!", Toast.LENGTH_SHORT).show();
            }
            if (TextUtils.isEmpty(usernameTxt)){
                username.setError("Email is Required");

            }
            if (TextUtils.isEmpty(passwordTxt)){
                password.setError("Password is Required");

            }
            if (password.length() < 6){
                password.setError("Password is Required to be At least 6 characters");

            }

        });

        notRegistered.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);


        });


    }
}