package com.prasadthegreat.statusbooster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateWhatsappgroupActivity extends AppCompatActivity {

    private Button mSubmitlink;
    private EditText mGroupname,mLink;
    private DatabaseReference mDatabase;
    private long count=0;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_whatsappgroup);

        progressDialog=new ProgressDialog(UpdateWhatsappgroupActivity.this);
        progressDialog.setTitle("Uploading Data");
        progressDialog.setMessage("Please,wait....");
        progressDialog.setCancelable(false);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("userswhatsappgroups");
        mSubmitlink=(Button)findViewById(R.id.submitwhatsbtn);
        mGroupname=(EditText)findViewById(R.id.whatsgroupnameedit);
        mLink=(EditText)findViewById(R.id.whatsappgrouplinkedit);
        final users user=new users();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    count=dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mSubmitlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String groupname= mGroupname.getText().toString().trim();
                String grouplink=mLink.getText().toString().trim();
                if(TextUtils.isEmpty(groupname) | TextUtils.isEmpty(grouplink)){
                    Toast.makeText(UpdateWhatsappgroupActivity.this,"Please,fill enter values",Toast.LENGTH_LONG).show();
                }else{
                    progressDialog.show();
                    user.setCount((int) ++count);
                    user.setGrouplink(grouplink);
                    user.setGroupname(groupname);
                    mDatabase.child(String.valueOf(count)).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){

                                mLink.setText("");
                                mGroupname.setText("");
                                Toast.makeText(UpdateWhatsappgroupActivity.this,"Data uploaded success fully..",Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();

                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(UpdateWhatsappgroupActivity.this,"Please,try again..",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });


    }
}
