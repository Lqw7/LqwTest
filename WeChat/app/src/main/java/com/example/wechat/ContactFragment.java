package com.example.wechat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactFragment extends Fragment {
    final List<Contact> contactList = new ArrayList<>();
    private List<Contact> contactMemberList = new ArrayList<>();
    private List<Contact> contactMemberList1 = new ArrayList<>();
    private List<Contact> contactMemberList2 = new ArrayList<>();
    private List<Contact> contactMemberList3 = new ArrayList<>();
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.contact_fragment, container, false);
        ContactAdapter adapter = new ContactAdapter(view.getContext(), R.layout.contact_listview, contactList);
        ContactAdapter adapter1=new ContactAdapter(view.getContext(), R.layout.contact_listview, contactMemberList);
        ContactAdapter adapter2=new ContactAdapter(view.getContext(), R.layout.contact_listview, contactMemberList1);
        ContactAdapter adapter3=new ContactAdapter(view.getContext(), R.layout.contact_listview, contactMemberList2);
        ContactAdapter adapter4=new ContactAdapter(view.getContext(), R.layout.contact_listview, contactMemberList3);
        ListView listView = (ListView) view.findViewById(R.id.contact_listview);
        ListView listView1=(ListView) view.findViewById(R.id.contact_member_list);
        ListView listView2=(ListView) view.findViewById(R.id.contact_member_list1);
        ListView listView3=(ListView) view.findViewById(R.id.contact_member_list2);
        ListView listView4=(ListView) view.findViewById(R.id.contact_member_list3);
        listView.setAdapter(adapter);
        listView1.setAdapter(adapter1);
        listView2.setAdapter(adapter2);
        listView3.setAdapter(adapter3);
        listView4.setAdapter(adapter4);
        setListViewHeightBasedOnChildren(listView,view,adapter);
        setListViewHeightBasedOnChildren(listView1,view,adapter1);
        setListViewHeightBasedOnChildren(listView2,view,adapter2);
        setListViewHeightBasedOnChildren(listView3,view,adapter3);
        setListViewHeightBasedOnChildren(listView4,view,adapter4);
        //显示联系人的个数
        TextView textView=(TextView)view.findViewById(R.id.member_num);//显示数量的TextView
        int num=adapter1.getCount()+adapter2.getCount()+adapter3.getCount()+adapter4.getCount();//从适配器获取数量
        textView.setText(num+"位联系人");
        return view;
    }

    private void init() {
        Contact firend = new Contact(R.drawable.newfriend, "新的朋友");
        contactList.add(firend);
        Contact group = new Contact(R.drawable.groupchat, "群聊");
        contactList.add(group);
        Contact sign = new Contact(R.drawable.sign, "标签");
        contactList.add(sign);
        Contact publicnum = new Contact(R.drawable.publicnum, "公众号");
        contactList.add(publicnum);
        //通讯录

            Contact kobe = new Contact(R.drawable.kobe, "Kobe Bryant");
            contactMemberList.add(kobe);
            Contact kun = new Contact(R.drawable.kun, "kun");
            contactMemberList.add(kun);
            Contact lue = new Contact(R.drawable.tyronn, "Tyronn Lue");
            contactMemberList1.add(lue);
            Contact zc = new Contact(R.drawable.zc, "陈展翅");
            contactMemberList2.add(zc);
            Contact pony = new Contact(R.drawable.pony, "Pony");
            contactMemberList3.add(pony);

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    /**
     * 动态设置ListView的高度，与ScrollView适配
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView,View view,ContactAdapter adapter) {
        if(listView == null) return;
        if (adapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


}