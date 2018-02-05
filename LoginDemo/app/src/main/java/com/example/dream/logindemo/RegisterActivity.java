package com.example.dream.logindemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.ByteArrayOutputStream;

import static android.R.attr.data;

public class RegisterActivity extends AppCompatActivity implements IPickResult   {

    EditText txtName,txtEmail,txtPass,txtPass2;
    Button btnb1;
    CircularImageView image2;
    String userimage="";

    DatabaseReference reference;
    FirebaseDatabase database;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtName=(EditText)findViewById(R.id.txtName);
        txtEmail=(EditText)findViewById(R.id.txtEmail);
        txtPass=(EditText)findViewById(R.id.txtPass);
        txtPass2=(EditText)findViewById(R.id.txtPass2);
        btnb1=(Button)findViewById(R.id.btnb1);
        image2=(CircularImageView)findViewById(R.id.image2);

        prefs= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        database=FirebaseDatabase.getInstance();
        reference=database.getReference("users");                         //reference here is oblect

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PickImageDialog.build(new PickSetup()).show(RegisterActivity.this);
            }
        });

        btnb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = txtName.getText().toString();
                String s2 = txtEmail.getText().toString();
                String s3 = txtPass.getText().toString();
                String s4 = txtPass2.getText().toString();
                    if(s3.equals(s4))
                {
                    String userid=reference.push().getKey();
                    reference.child(userid).setValue(new Users(s1,s2,s3,userimage,prefs.getString("KEY_FCMTOKEN","")));

                    Toast.makeText(RegisterActivity.this,"Successfully Register", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"Check Password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            //If you want the Uri.
            //Mandatory to refresh image from Uri.
            //getImageView().setImageURI(null);

            //Setting the real returned image.
            //getImageView().setImageURI(r.getUri());

            //If you want the Bitmap.
            image2.setImageBitmap(r.getBitmap());
            Bitmap bimg=r.getBitmap();
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            bimg.compress(Bitmap.CompressFormat.PNG,100,bos);                 //100 is quality
            byte[] byteimg=bos.toByteArray();
            userimage= Base64.encodeToString(byteimg,Base64.DEFAULT);
            //image2.setImageURI(r.getUri());

            //Image path
            //r.getPath();
        } else {
            //Handle possible errors
            //TODO: do what you have to do with r.getError();
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
