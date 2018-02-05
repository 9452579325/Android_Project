package com.example.dream.logindemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by dream on 18-Jun-17.
 */

public class MessageAdapter extends BaseAdapter {

    ArrayList<MessageData> arrmsgs=new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    String username="";
    SharedPreferences preferences;

    public MessageAdapter(ArrayList<MessageData> msgs, Context context) {
        this.arrmsgs = msgs;
        this.context = context;
        preferences= PreferenceManager.getDefaultSharedPreferences(context);
        username=preferences.getString("KEY_USER","");
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrmsgs.size();
    }

    @Override
    public Object getItem(int position) {
        return arrmsgs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(arrmsgs.get(position).getFrom().equals(username))
        {
            convertView=inflater.inflate(R.layout.item_chat2,null);
        }
        else
        {
            convertView=inflater.inflate(R.layout.item_chat1,null);
        }

        TextView lblMessage=(TextView)convertView.findViewById(R.id.chat);
        lblMessage.setText(arrmsgs.get(position).getMessage());
        return convertView;
    }
}
