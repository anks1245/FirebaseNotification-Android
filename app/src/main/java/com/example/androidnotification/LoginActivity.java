package com.example.androidnotification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ProgressBar progressBarlogin;
    EditText editTextemail;
    EditText editTextpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextemail=(EditText)findViewById(R.id.editTextEmaillogin) ;
        editTextpassword=(EditText)findViewById(R.id.editTextPasswordlogin);
        mAuth=FirebaseAuth.getInstance();
    progressBarlogin=(ProgressBar)findViewById(R.id.Progressbarlogin);
        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email=editTextemail.getText().toString().trim();
                final String password=editTextpassword.getText().toString().trim();
                userLogin(email,password);
            }
        });

        findViewById(R.id.registerationTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
    private void userLogin(String email,String password){
        progressBarlogin.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBarlogin.setVisibility(View.INVISIBLE);
                        if (task.isSuccessful()){
                            Intent intent=new Intent(LoginActivity.this,ProfileActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }else{
                            progressBarlogin.setVisibility(View.INVISIBLE);
                            Toast.makeText(LoginActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT);
                        }

                    }
                });
    }
}