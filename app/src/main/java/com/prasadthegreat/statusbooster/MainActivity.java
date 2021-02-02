package com.prasadthegreat.statusbooster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button mEdituser;
    private Button mphonenumberslist;
    private Button mWhatsappgroup;
    private Button mEditwhatsappgroup;
    private Button mShare;
    private Button mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        mEdituser=(Button)findViewById(R.id.editprofilebtn
        );
        mphonenumberslist=(Button)findViewById(R.id.editprofilebtn);
        mWhatsappgroup=(Button)findViewById(R.id.whatsappgrouplistbtn);
        mEditwhatsappgroup=(Button)findViewById(R.id.addwhatsappgroupbtn);
        mContact=(Button)findViewById(R.id.contactusbtn);
        mShare=(Button)findViewById(R.id.sharebtn);

    }
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null){
           // Intent intent=new Intent(MainActivity.this,LoginActivity.class);
           // startActivity(intent);
        }
    }
}