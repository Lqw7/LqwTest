package com.example.wechat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyFragment extends Fragment {
    private View view;
    private List<My> myList1 = new ArrayList<>();
    private List<My> myList2 = new ArrayList<>();
    private List<My> myList3 = new ArrayList<>();
    private Uri imageUri;
    public static final int TAKE_PHOTO=1;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_fragment, container, false);

        //适配第一个listView
        MyAdapter myAdapter1=new MyAdapter(view.getContext(),R.layout.my_listview,myList1);
        ListView listView1 = (ListView) view.findViewById(R.id.my_listview1);
        listView1.setAdapter(myAdapter1);
        //适配第二个listView
        MyAdapter myAdapter2=new MyAdapter(view.getContext(),R.layout.my_listview,myList2);
        ListView listView2 = (ListView) view.findViewById(R.id.my_listview2);
        listView2.setAdapter(myAdapter2);
        //适配第三个listView
        MyAdapter myAdapter3=new MyAdapter(view.getContext(),R.layout.my_listview,myList3);
        ListView listView3 = (ListView) view.findViewById(R.id.my_listview3);
        listView3.setAdapter(myAdapter3);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent=new Intent(view.getContext(),Pay.class);//启动支付界面
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
        //调用摄像头
        ImageButton camera=(ImageButton) view.findViewById(R.id.mycamera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                 intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                 startActivityForResult(intent,TAKE_PHOTO);
            }
        });
        return view;
    }
    //初始化数据
    private void init() {
        My my1=new My(R.drawable.mypayment,"支付",R.drawable.next);
        myList1.add(my1);
        My my2=new My(R.drawable.collect,"收藏",R.drawable.next);
        myList2.add(my2);
        My my3=new My(R.drawable.photo,"相册",R.drawable.next);
        myList2.add(my3);
        My my4=new My(R.drawable.card,"卡包",R.drawable.next);
        myList2.add(my4);
        My my5=new My(R.drawable.emotion,"表情",R.drawable.next);
        myList2.add(my5);
        My my6=new My(R.drawable.setting,"设置",R.drawable.next);
        myList3.add(my6);
    }
    //加载数据，根据生命周期
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
}
