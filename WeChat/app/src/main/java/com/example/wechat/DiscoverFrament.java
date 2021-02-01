package com.example.wechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DiscoverFrament extends Fragment {
   private View view;
   private List<Discover> discoverList1 = new ArrayList<>();
   private List<Discover> discoverList2 = new ArrayList<>();
   private List<Discover> discoverList3 = new ArrayList<>();
   private List<Discover> discoverList4 = new ArrayList<>();
   private List<Discover> discoverList5 = new ArrayList<>();
    private List<Discover> discoverList6 = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.discover_fragment, container, false);
        //适配第一个listView
        DiscoverAdapter adapter1 = new DiscoverAdapter(view.getContext(), R.layout.discover_listview, discoverList1);
        ListView listView1 = (ListView) view.findViewById(R.id.discover_listview1);
        listView1.setAdapter(adapter1);
        //适配第二个listView
        DiscoverAdapter adapter2 = new DiscoverAdapter(view.getContext(), R.layout.discover_listview, discoverList2);
        ListView listView2 = (ListView) view.findViewById(R.id.discover_listview2);
        listView2.setAdapter(adapter2);
        //适配第三个listView
        DiscoverAdapter adapter3 = new DiscoverAdapter(view.getContext(), R.layout.discover_listview, discoverList3);
        ListView listView3 = (ListView) view.findViewById(R.id.discover_listview3);
        listView3.setAdapter(adapter3);
        //适配第四个listView
        DiscoverAdapter adapter4 = new DiscoverAdapter(view.getContext(), R.layout.discover_listview, discoverList4);
        ListView listView4 = (ListView) view.findViewById(R.id.discover_listview4);
        listView4.setAdapter(adapter4);
        //适配第五个listView
        DiscoverAdapter adapter5 = new DiscoverAdapter(view.getContext(), R.layout.discover_listview, discoverList5);
        ListView listView5 = (ListView) view.findViewById(R.id.discover_listview5);
        listView5.setAdapter(adapter5);
        //适配第六个listView
        DiscoverAdapter adapter6 = new DiscoverAdapter(view.getContext(), R.layout.discover_listview, discoverList6);
        ListView listView6 = (ListView) view.findViewById(R.id.discover_listview6);
        listView6.setAdapter(adapter6);

        //为第一个ListView添加点击事件
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent=new Intent(view.getContext(),Circle.class);//启动朋友圈
                        startActivity(intent);
                        break;
                    default:
                        break;
                }

            }
        });
        return view;
    }
  //初始化参数
    private void init() {
         Discover discover1=new Discover(R.drawable.circle,"朋友圈",R.drawable.next);
         discoverList1.add(discover1);
         Discover discover2=new Discover(R.drawable.scanscan,"扫一扫",R.drawable.next);
         discoverList2.add(discover2);
        Discover discover3=new Discover(R.drawable.shark,"摇一摇",R.drawable.next);
        discoverList2.add(discover3);
        Discover discover4=new Discover(R.drawable.look,"看一看",R.drawable.next);
        discoverList3.add(discover4);
        Discover discover5=new Discover(R.drawable.searchsearch,"搜一搜",R.drawable.next);
        discoverList3.add(discover5);
        Discover discover6=new Discover(R.drawable.nearperson,"附近的人",R.drawable.next);
        discoverList4.add(discover6);
        Discover discover7=new Discover(R.drawable.restaurant,"附近的餐厅",R.drawable.next);
        discoverList4.add(discover7);
        Discover discover8=new Discover(R.drawable.shop,"购物",R.drawable.next);
        discoverList5.add(discover8);
        Discover discover9=new Discover(R.drawable.game,"游戏",R.drawable.next);
        discoverList5.add(discover9);
        Discover discover10=new Discover(R.drawable.program,"小程序",R.drawable.next);
        discoverList6.add(discover10);


    }

    //加载参数
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

}