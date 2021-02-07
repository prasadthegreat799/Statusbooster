package com.prasadthegreat.statusbooster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WhatsgroupActivity extends AppCompatActivity {

    private DatabaseReference ref;
    private RecyclerView mUserslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsgroup);

        mUserslist=(RecyclerView)findViewById(R.id.whatslist);
        mUserslist.setHasFixedSize(true);
        mUserslist.setLayoutManager(new LinearLayoutManager(this));
        ref= FirebaseDatabase.getInstance().getReference().child("userswhatsappgroups");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<users> options;
        FirebaseRecyclerAdapter<users, UsersViewholdewhats> adapter;

        options=new FirebaseRecyclerOptions.Builder<users>().setQuery(ref,users.class).build();
        adapter=new FirebaseRecyclerAdapter<users, UsersViewholdewhats>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final UsersViewholdewhats holder, final int position, @NonNull final users model) {
                holder.groupname.setText(model.getGroupname());
                holder.grouplink.setText(model.getGrouplink());
                holder.mcount.setText(String.valueOf(model.getCount()));

                holder.mcount.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String data=holder.grouplink.getText().toString().trim();
                        Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                        String url =data;
                        intentWhatsapp.setData(Uri.parse(url));
                        intentWhatsapp.setPackage("com.whatsapp");
                        startActivity(intentWhatsapp);


                    }
                });

                holder.groupname.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String data=holder.grouplink.getText().toString().trim();
                        Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                        String url =data;
                        intentWhatsapp.setData(Uri.parse(url));
                        intentWhatsapp.setPackage("com.whatsapp");
                        startActivity(intentWhatsapp);


                    }
                });

                holder.grouplink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String data=holder.grouplink.getText().toString().trim();
                        Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                        String url =data;
                        intentWhatsapp.setData(Uri.parse(url));
                        intentWhatsapp.setPackage("com.whatsapp");
                        startActivity(intentWhatsapp);


                    }
                });

            }

            @NonNull
            @Override
            public UsersViewholdewhats onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.whatslist_item,parent,false);
                return new UsersViewholdewhats(v);
            }
        };
        adapter.startListening();
        mUserslist.setAdapter(adapter);


    }

}
class UsersViewholdewhats extends RecyclerView.ViewHolder {

    TextView groupname,grouplink,mcount;

    public UsersViewholdewhats(@NonNull View itemView) {
        super(itemView);

        groupname=(TextView)itemView.findViewById(R.id.whatsgroupname);
        grouplink=(TextView)itemView.findViewById(R.id.whatsgrouplink);
        mcount=(TextView)itemView.findViewById(R.id.whatscounttxt);


    }
}
