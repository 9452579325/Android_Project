package com.example.dream.logindemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dream on 16-Jun-17.
 */

public class MyAdapterClass extends BaseAdapter {

    ArrayList<Users> arrData = new ArrayList<>();
    Context context;
    LayoutInflater inflater;

    public MyAdapterClass(ArrayList<Users> arrData, Context context) {
        this.arrData = arrData;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return arrData.size();
    }

    @Override
    public Object getItem(int position) {
        return arrData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.user_item,null);
        TextView lblName = (TextView)convertView.findViewById(R.id.txtName);
        TextView lblUser=(TextView)convertView.findViewById(R.id.txtUser);
        ImageView image2=(ImageView)convertView.findViewById(R.id.image2);
        lblUser.setText(arrData.get(position).getUsername());
        lblName.setText(arrData.get(position).getName());
        String encodedimage=arrData.get(position).getImage();
        /*if(!encodedimage.isEmpty())
        {
            byte[] decodedimage = Base64.decode(encodedimage,Base64.DEFAULT);
            Bitmap bimage= BitmapFactory.decodeByteArray(decodedimage,0,decodedimage.length);
            image2.setImageBitmap(bimage);
        }*/
        return convertView;
    }
}
