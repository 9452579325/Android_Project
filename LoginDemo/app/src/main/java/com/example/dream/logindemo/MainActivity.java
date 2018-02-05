package com.example.dream.logindemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;

    SharedPreferences prefs;

    EditText txtUser;
    EditText txtPass;
    Button btnLogin;
    Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        database=FirebaseDatabase.getInstance();
        reference=database.getReference("users");

        txtUser=(EditText)findViewById(R.id.txtUser);
        txtPass=(EditText)findViewById(R.id.txtPass);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnReg=(Button)findViewById(R.id.btnReg);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String s1="admin";
                final String user = txtUser.getText().toString();
                final String pass = txtPass.getText().toString();
                if (user.isEmpty()||pass.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Empty Fields", Toast.LENGTH_LONG).show();
                }
                else {

                    Query q = reference.orderByChild("username").equalTo(user);

                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            String spass = "";
                            for (DataSnapshot data : dataSnapshot.getChildren())
                            {
                                Users usr = data.getValue(Users.class);
                                spass = usr.getPassword();
                            }

                            //type casting to users type
                            if (spass.equals(pass)) {
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString("KEY_USER", user);
                                editor.commit();
                                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                intent.putExtra("K_User", user);
                                startActivity(intent);
                            } else
                                Toast.makeText(MainActivity.this, "Check Password", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    /*@Override
    public void onBackPressed() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Do you really want to close the app");
        builder.setTitle("Login Demo");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
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
