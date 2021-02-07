package com.prasadthegreat.statusbooster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
    }

    public void submitmail(View view){
        EditText mail=(EditText)findViewById(R.id.mailforgot);
        String mailid=mail.getText().toString().trim();
        FirebaseAuth.getInstance().sendPasswordResetEmail(mailid)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(ForgotPasswordActivity.this,"Mail Sent,Please,Check Your Mail",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgotPasswordActivity.this,LoginActivity.class));
                        }
                    }
                });
    }


    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(ForgotPasswordActivity.this,LoginActivity.class));
        super.onBackPressed();
    }
}