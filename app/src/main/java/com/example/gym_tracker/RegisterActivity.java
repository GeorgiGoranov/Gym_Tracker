package com.example.gym_tracker;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
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


public class RegisterActivity extends AppCompatActivity {

    EditText username,email,password;
    Button registerBtn;
    TextView signIn;

    FirebaseAuth fAuth;

    ProgressBar progressBar;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed up (non-null) and update UI accordingly.
        FirebaseUser currentUser = fAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        email = findViewById(R.id.emailRegister);
        username = findViewById(R.id.username);
        password = findViewById(R.id.passwordRegister);
        registerBtn = findViewById(R.id.registerBtn);
        signIn = findViewById(R.id.signInTxt);


        fAuth = FirebaseAuth.getInstance();

        progressBar= findViewById(R.id.progressBar);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                String emailTxt = email.getText().toString().trim();
                String passwordTxt = password.getText().toString().trim();
                String usernameTxt = username.getText().toString().trim();


                if (TextUtils.isEmpty(usernameTxt) || TextUtils.isEmpty(passwordTxt) || TextUtils.isEmpty(emailTxt)) {
                    Toast.makeText(RegisterActivity.this, "Please enter your credentials!", Toast.LENGTH_SHORT).show();
                }else{
                    progressBar.setVisibility(View.VISIBLE);

                    fAuth.createUserWithEmailAndPassword(emailTxt, passwordTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Account created!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                finish();
                                progressBar.setVisibility(View.GONE);

                            }else{
                                Toast.makeText(RegisterActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });

                }
                if (TextUtils.isEmpty(emailTxt)) {
                    email.setError("Email Is Required");
                    email.requestFocus();

                    //check weather or not the email is valid and if it has (@,.,.com)
                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches()) {
                    email.setError("Please Provide Us With A Valid Email!");
                    email.requestFocus();

                }

                if (TextUtils.isEmpty(usernameTxt)) {
                    username.setError("Full Name is Required");
                    username.requestFocus();

                }

                if (TextUtils.isEmpty(passwordTxt)) {
                    password.setError("Password Is Required!");
                    password.requestFocus();

                } else if (password.length() < 6) {
                    password.setError("Password Is Required To Be At Least 6 Characters!");
                    password.requestFocus();
                }

                progressBar.setVisibility(View.GONE);

            }


        });
    }

}