package com.prasadthegreat.statusbooster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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

        mEdituser=(Button)findViewById(R.id.editprofilebtn);
        mphonenumberslist=(Button)findViewById(R.id.phonenumberlistbtn);
        mWhatsappgroup=(Button)findViewById(R.id.whatsappgrouplistbtn);
        mEditwhatsappgroup=(Button)findViewById(R.id.addwhatsappgroupbtn);
        mContact=(Button)findViewById(R.id.contactusbtn);
        mShare=(Button)findViewById(R.id.sharebtn);

        mphonenumberslist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,PhonenumberActivity.class));
            }
        });

        mWhatsappgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,WhatsgroupActivity.class));
            }
        });

        mEditwhatsappgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,UpdateWhatsappgroupActivity.class));
            }
        });

        mEdituser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        mContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","prasadthegreat799@gmail.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Mail From StatusBooster");
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });

        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Hey,i have found an Awesome app for increasing whatsapp status views. Dowload the app from here: https://statusboosterpro.blogspot.com/";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
    }
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null){
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
           startActivity(intent);
        }
    }
}