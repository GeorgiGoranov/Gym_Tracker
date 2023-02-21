package com.example.gym_tracker;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText email;
    EditText password;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        registerBtn = findViewById(R.id.registerBtn);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailTxt = email.getText().toString().trim();
                String passwordTxt = password.getText().toString().trim();
                String usernameTxt = username.getText().toString().trim();


                if (TextUtils.isEmpty(usernameTxt) || TextUtils.isEmpty(passwordTxt) || TextUtils.isEmpty(emailTxt)) {
                    Toast.makeText(RegisterActivity.this, "Please enter your credentials!", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(emailTxt)) {
                    email.setError("Email Is Required");
                    email.requestFocus();
                    return;
                    //check weather or not the email is valid and if it has (@,.,.com)
                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches()) {
                    email.setError("Please Provide Us With A Valid Email!");
                    email.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(usernameTxt)) {
                    username.setError("Full Name is Required");
                    username.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(passwordTxt)) {
                    password.setError("Password Is Required!");
                    password.requestFocus();
                    return;


                } else if (password.length() < 6) {
                    password.setError("Password Is Required To Be At Least 6 Characters!");
                    password.requestFocus();


                }
            }


        });
    }


}