package com.prasadthegreat.statusbooster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText mUsername;
    private  EditText mEmail;
    private EditText mPassword;
    private EditText mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        mUsername=(EditText)findViewById(R.id.regusername);
        mEmail=(EditText)findViewById(R.id.regemail);
        mPassword=(EditText)findViewById(R.id.regpassword);
        mPhone=(EditText)findViewById(R.id.regmobilenumber);
        register_user(mUsername,mEmail,mPassword,mPhone);

    }

    private void register_user(EditText mUsername, EditText mEmail, EditText mPassword, EditText mPhone) {
        String name=mUsername.getText().toString();
        String mail=mEmail.getText().toString();
        String password=mPassword.getText().toString();
        String phonenumber=mPhone.getText().toString();

        mAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(RegisterActivity.this,"Registred Successful",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this,"Enter Correct Details",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}