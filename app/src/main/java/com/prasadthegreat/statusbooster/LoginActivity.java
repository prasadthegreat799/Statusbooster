package com.prasadthegreat.statusbooster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    private EditText mEmail;
    private EditText mPassword;
    private Button mLogin;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog=new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please,wait....");
        progressDialog.setCancelable(false);

        mEmail=(EditText)findViewById(R.id.editTextEmail);
        mPassword=(EditText)findViewById(R.id.editTextPassword);
        mLogin=(Button)findViewById(R.id.cirLoginButton);

        mLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String email=mEmail.getText().toString();
                String password=mPassword.getText().toString();
                if(email.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(LoginActivity.this,"Please,Fill all fields",Toast.LENGTH_SHORT).show();
                }else
                {
                    login(email,password);
                }
            }
        });
    }

    private void login(String email, final String password)
    {
        progressDialog.show();
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    progressDialog.dismiss();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else
                {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this,"Enter Correct Details",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void viewRegisterClicked(View view)
    {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }
    public void viewForgotPAssword(View view)
    {
        startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
    }

    @Override
    public void onBackPressed()
    {
        finishAffinity();
        finish();
        super.onBackPressed();
    }
}