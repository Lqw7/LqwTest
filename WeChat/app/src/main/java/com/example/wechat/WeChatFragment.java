package com.example.wechat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WeChatFragment extends Fragment {
    private List<Chat> chatList=new ArrayList<>();//存放Chat对象
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.wechat_fragment, container, false);
        //聊天对话框列表的适配
        ChatAdapter adapter=new ChatAdapter(view.getContext(),R.layout.wechat_listview,chatList);//实例化适配器
        final ListView listView=(ListView)view.findViewById(R.id.chat_listview);//获取ListView
        listView.setAdapter(adapter);//适配

        //为ListView添加上下文菜单
        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {

            @Override
            public void onCreateContextMenu(android.view.ContextMenu menu, View v,
                                            ContextMenu.ContextMenuInfo menuInfo) {
                      menu.add(0, 0, 0, "标为未读");
                      menu.add(0, 1, 0, "置顶聊天");
                      menu.add(0,2,0,"删除聊天");

            }
                    });
          listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                     if (position==0){
                         Intent intent = new Intent(view.getContext(), Booking.class);//启动订阅号界面
                         startActivity(intent);
                     }
                      if(position>=1) {
                          LinearLayout layout = (LinearLayout) listView.getChildAt(position);
                          TextView textView = (TextView) layout.findViewById(R.id.chat_name);//获取ListView中显示姓名的TextView
                          String s = textView.getText().toString();//获取TextView的值
                          Intent intent = new Intent(view.getContext(), ChatView.class);//启动聊天界面
                          intent.putExtra("username", s);//将TextView的值传递给聊天界面
                          startActivity(intent);
                      }
              }
          });
        return view;

    }
    //初始化参数
    private void init(){
        Chat five=new Chat(R.drawable.booking ,"订阅号消息","广东共青团：华为注册了整本山海经？","00.12");
        chatList.add(five);
        Chat one=new Chat(R.drawable.tyronn,"Tyronn Lue","世间所有的相遇都是久别重逢","1.03");
        chatList.add(one);
        Chat zc=new Chat(R.drawable.zc,"陈展翅","6月22号交Android课设","1.27");
        chatList.add(zc);
        Chat kobe=new Chat(R.drawable.kobe,"Kobe Bryant","凌晨四点见","4.00");
        chatList.add(kobe);
        Chat kun=new Chat(R.drawable.kun ,"kun","我喜欢唱，跳，rap，篮球","7.30");
        chatList.add(kun);
        Chat van=new Chat(R.drawable.van, "VanVleet","排队给我道歉","8.30");
        chatList.add(van);
        Chat pony=new Chat(R.drawable.pony ,"Pony","该充钱了","8.52");
        chatList.add(pony);
       /*Chat two=new Chat(R.drawable.wepay ,"微信支付","微信支付凭证","9.28");
        chatList.add(two);*/
         Chat three=new Chat(R.drawable.team ,"微信团队","登录操作通知","11.52");
        chatList.add(three);
         Chat six=new Chat(R.drawable.ten ,"腾讯新闻","微视频：文明交流之道","12.08");
        chatList.add(six);
        /*Chat seven=new Chat(R.drawable.sport ,"微信运动","[应用消息]","昨天");
        chatList.add(seven);
        Chat eigth=new Chat(R.drawable.sport ,"文件传输助手","[文件]","昨天");
        chatList.add(eigth);*/


    }
    @Override
    public void onStart() {
        Log.d("TAG","onStart");
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

}
