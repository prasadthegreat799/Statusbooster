package com.prasadthegreat.statusbooster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button mEdituser;
    private Button mphonenumberslist;
    private Button mWhatsappgroup;
    private Button mEditwhatsappgroup;
    private Button mShare;
    private Button mContact;


    private FirebaseRemoteConfig mFirebaseRemoteConfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdituser = (Button) findViewById(R.id.editprofilebtn);
        mphonenumberslist = (Button) findViewById(R.id.phonenumberlistbtn);
        mWhatsappgroup = (Button) findViewById(R.id.whatsappgrouplistbtn);
        mEditwhatsappgroup = (Button) findViewById(R.id.addwhatsappgroupbtn);
        mContact = (Button) findViewById(R.id.contactusbtn);
        mShare = (Button) findViewById(R.id.sharebtn);


        HashMap<String, Object> defaultsRate = new HashMap<>();
        defaultsRate.put("new_version_code", String.valueOf(getVersionCode()));

        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(10) // change to 3600 on published app
                .build();

        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.setDefaultsAsync(defaultsRate);

        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {
                if (task.isSuccessful()) {
                    final String new_version_code = mFirebaseRemoteConfig.getString("new_version_code");

                    if(Integer.parseInt(new_version_code) > getVersionCode())
                        showTheDialog("com.prasadthegreat.statusbooster", new_version_code );
                }
                else Log.e("MYLOG", "mFirebaseRemoteConfig.fetchAndActivate() NOT Successful");

            }
        });


        mphonenumberslist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PhonenumberActivity.class));
            }
        });

        mWhatsappgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, WhatsgroupActivity.class));
            }
        });

        mEditwhatsappgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UpdateWhatsappgroupActivity.class));
            }
        });

        mEdituser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        mContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"geniusdeveloper799@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Please,Enter Your Feedback or Complaint");
                intent.setPackage("com.google.android.gm");
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);
                else
                    Toast.makeText(MainActivity.this, "Gmail App is not installed", Toast.LENGTH_SHORT).show();
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
        if (currentUser == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }


    private void showTheDialog(final String appPackageName, String versionFromRemoteConfig){
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Update")
                .setMessage("This version is absolete, please update to version: "+versionFromRemoteConfig)
                .setPositiveButton("UPDATE", null)
                .show();

        dialog.setCancelable(false);

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://statusboosterpro.blogspot.com/")));
                }
                catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://statusboosterpro.blogspot.com/")));
                }
            }
        });
    }

    private PackageInfo pInfo;
    public int getVersionCode() {
        pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.i("MYLOG", "NameNotFoundException: "+e.getMessage());
        }
        return pInfo.versionCode;
    }
}