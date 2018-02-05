package com.example.dream.logindemo;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FRegisterService extends FirebaseInstanceIdService {
    SharedPreferences prefs;
    public FRegisterService() {
    }

    @Override
    public void onTokenRefresh()
    {
        super.onTokenRefresh();
        prefs= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor edit=prefs.edit();
        String token= FirebaseInstanceId.getInstance().getToken();
        edit.putString("KEY_FCMTOKEN",token);
        Log.d("TAG_TOKEN",token);
        edit.commit();
    }
}
