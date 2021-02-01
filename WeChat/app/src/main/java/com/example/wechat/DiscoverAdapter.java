package com.example.wechat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DiscoverAdapter extends ArrayAdapter<Discover> {
    private int resourceID;
    public DiscoverAdapter(Context context, int textViewResourceID, List<Discover> objects){
        super(context,textViewResourceID,objects);
        resourceID=textViewResourceID;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Discover discover=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        ImageView imageView1=(ImageView)view.findViewById(R.id.discover_img);
        ImageView arrow=(ImageView)view.findViewById(R.id.discover_arrow);
        TextView textView1=(TextView)view.findViewById(R.id.discover_text);
        imageView1.setImageResource(discover.getImg());
        arrow.setImageResource(discover.getArrow());
        textView1.setText(discover.getText());
        return view;
    }
}
