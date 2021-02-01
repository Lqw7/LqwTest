package com.example.wechat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private int resourceID;
    public ContactAdapter(Context context, int textViewResourceID, List<Contact> objects){
        super(context,textViewResourceID,objects);
        resourceID=textViewResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        ImageView imageView=(ImageView)view.findViewById(R.id.contact_img);
        TextView textView=(TextView)view.findViewById(R.id.contact_text);
        imageView.setImageResource(contact.getImg());
        textView.setText(contact.getText());
        return view;
    }
}
