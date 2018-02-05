package com.example.dream.logindemo;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChatActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference1,reference2;
    SharedPreferences prefs;
    String user1="";
    String user2="";
    ListView lstChat;
    EditText txtMessage;
    ImageButton btnSend;
    ArrayList<MessageData> arrdata = new ArrayList<>();
    MessageAdapter adapter;

    String userToken="";
    String message="";
    String userName="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        lstChat=(ListView)findViewById(R.id.lstChat);
        txtMessage=(EditText)findViewById(R.id.txtMessage);
        btnSend=(ImageButton)findViewById(R.id.btnSend);

        prefs= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        user1=prefs.getString("KEY_USER","");
        user2=getIntent().getStringExtra("KEY_USER2");
        userToken=getIntent().getStringExtra("KEY_USER2_TOKEN");
        userName=user1;

        database=FirebaseDatabase.getInstance();
        reference1=database.getReference(user1+"_"+user2);
        reference2=database.getReference(user2+"_"+user1);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 message=txtMessage.getText().toString();
                if (!message.trim().isEmpty())
                {
                    MessageData msg=new MessageData(0,user1,user2,"",message);
                    String userid=reference1.push().getKey();
                    reference1.child(userid).setValue(msg);

                    userid=reference2.push().getKey();
                    reference2.child(userid).setValue(msg);

                    message= message.replace(" ","%20");
                    userName=userName.replace(" ","%20");
                    new SendNotification().execute();
                }

            }
        });

        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                refreshData();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                refreshData();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    void refreshData()
    {

        Query q= reference1.orderByKey();

        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                arrdata.clear();
                for (DataSnapshot data : dataSnapshot.getChildren())
                {
                    MessageData usr=data.getValue(MessageData.class);                  //type casting to users type

                    arrdata.add(usr);
                    adapter=new MessageAdapter(arrdata,getApplicationContext());
                    lstChat.setAdapter(adapter);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    class SendNotification extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... params) {

            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder()
                    .url("https://kapiltekwanicom.000webhostapp.com/sendnotification.php?"+
                            "reg_id="+userToken+
                            "&user="+userName+
                            "&message="+message)
                    .build();

            try {

                Response response=client.newCall(request).execute();

                        String sdata=response.body().string();
                Log.d("TAG_NOTIFICATION",sdata);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

    }
}
