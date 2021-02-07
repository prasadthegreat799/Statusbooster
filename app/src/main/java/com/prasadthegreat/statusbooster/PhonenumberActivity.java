package com.prasadthegreat.statusbooster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.HashMap;

public class PhonenumberActivity extends AppCompatActivity {

    private DatabaseReference ref;
    private RecyclerView mUserslist;
    private int scrollPosition;
    private  int stop=0;
    private String phonenumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonenumber);



        mUserslist=(RecyclerView)findViewById(R.id.phonelist);
        mUserslist.setHasFixedSize(true);
        mUserslist.setLayoutManager(new LinearLayoutManager(this));
        ref = FirebaseDatabase.getInstance().getReference().child("phonelist");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<users> options;
        FirebaseRecyclerAdapter<users, UsersViewholde> adapter;

        options=new FirebaseRecyclerOptions.Builder<users>().setQuery(ref,users.class).build();
        adapter=new FirebaseRecyclerAdapter<users, UsersViewholde>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final UsersViewholde holder, int position, @NonNull users model) {
                holder.mname.setText(model.getName());
                holder.mphone.setText(model.getPhone());
                holder.mcount.setText(String.valueOf(++position));


                holder.mname.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scrollPosition=holder.getPosition();
                        String data=holder.mphone.getText().toString().trim();
                        Intent intentWhatsapp = new Intent("android.intent.action.MAIN");
                        intentWhatsapp.setAction(Intent.ACTION_VIEW);
                        String url = "https://api.whatsapp.com/send?phone="+data+"&text=Hi,i am from viewsbooster.Please,save my number for status views ";
                        intentWhatsapp.setData(Uri.parse(url));
                        startActivity(intentWhatsapp);
                    }
                });
                holder.mphone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scrollPosition=holder.getPosition();
                        String data=holder.mphone.getText().toString().trim();
                        Intent intentWhatsapp = new Intent("android.intent.action.MAIN");
                        intentWhatsapp.setAction(Intent.ACTION_VIEW);
                        String url = "https://api.whatsapp.com/send?phone="+data+"&text=Hi,i am from viewsbooster.Please,save my number for status views ";
                        intentWhatsapp.setData(Uri.parse(url));
                        startActivity(intentWhatsapp);
                    }
                });

            }

            @NonNull
            @Override
            public UsersViewholde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
                return new UsersViewholde(v);
            }
        };
        adapter.startListening();
        mUserslist.setAdapter(adapter);



    }

    @Override
    protected void onPause() {

        stop=scrollPosition;
        super.onPause();

    }

    @Override
    protected void onResume() {

        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mUserslist.scrollToPosition(stop);
            }
        }, 200);


    }

}

class UsersViewholde extends RecyclerView.ViewHolder {

    TextView mname,mphone,mcount;

    public UsersViewholde(@NonNull View itemView) {
        super(itemView);
        mname=(TextView)itemView.findViewById(R.id.name);
        mphone=(TextView)itemView.findViewById(R.id.phone);
        mcount=(TextView)itemView.findViewById(R.id.counttxt);
    }
}