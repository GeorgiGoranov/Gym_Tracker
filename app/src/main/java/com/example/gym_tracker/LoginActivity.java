package com.example.gym_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText emailTxt,passwordTxt;

    Button loginBtn;
    TextView notRegistered;

    FirebaseAuth fAuth;

    ProgressBar progressBar;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = fAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailTxt = findViewById(R.id.emailLogin);
        passwordTxt = findViewById(R.id.passwordLogin);
        loginBtn = findViewById(R.id.loginBtn);
        notRegistered = findViewById(R.id.signupText);

        fAuth = FirebaseAuth.getInstance();

        progressBar= findViewById(R.id.progressBar);

        loginBtn.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            String email = emailTxt.getText().toString();
            String password = passwordTxt.getText().toString();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                Toast.makeText(LoginActivity.this, "Please enter your credentials!", Toast.LENGTH_SHORT).show();
            }else{

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Logged In!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

            if (TextUtils.isEmpty(email)){
                emailTxt.setError("Email is Required");

            }
            if (TextUtils.isEmpty(password)){
                passwordTxt.setError("Password is Required");

            }
            if (password.length() < 6){
                passwordTxt.setError("Password is Required to be At least 6 characters");

            }

            progressBar.setVisibility(View.GONE);

        });

        notRegistered.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });


    }
}