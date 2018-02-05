package com.example.dream.logindemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {


    FirebaseDatabase database;
    DatabaseReference reference;
    ListView lstView;
    ArrayList<Users> arrData=new ArrayList<>();
    MyAdapterClass adapter;
    String username="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lstView=(ListView)findViewById(R.id.lstView);
        username=getIntent().getStringExtra("K_User");
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("users");

        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),ChatActivity.class);
                intent.putExtra("KEY_USER2",arrData.get(position).getUsername());
                intent.putExtra("KEY_USER2_TOKEN",arrData.get(position).getToken());
                //intent.putExtra("KEY_NAME",arrData.get(position).getName());

                startActivity(intent);
            }
        });

        //image=(ImageView)findViewById(R.id.image);
        //txtUser=(TextView)findViewById(R.id.txtUser);

        Query q=reference.orderByChild("username");
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren())
                {
                    Users usr=data.getValue(Users.class);                  //type casting to users type
                    if(!usr.getUsername().equals(username))
                    {
                        arrData.add(usr);
                    }
                    adapter=new MyAdapterClass(arrData,getApplicationContext());
                    lstView.setAdapter(adapter);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    /*@Override
    public void onBackPressed() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Do you really want to log out");
        builder.setTitle("Login Demo");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });

        AlertDialog alert=builder.create();
        alert.show();
    }*/
}
