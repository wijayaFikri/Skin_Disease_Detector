package com.jassen.skindiseasedetector.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.jassen.skindiseasedetector.R;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText passwordEt = findViewById(R.id.password_editText);
        mAuth = FirebaseAuth.getInstance();
    }

    public void goRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void goLogin(View view) {
        EditText passwordEt = findViewById(R.id.password_editText);
        EditText emailEt = findViewById(R.id.email_editText);
        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();
        if (email.equals("")) {
            emailEt.setError("Please input your email");
            return;
        }

        if (password.equals("")) {
            passwordEt.setError("Please input your password");
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Something wrong happened, please check your email or password",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
