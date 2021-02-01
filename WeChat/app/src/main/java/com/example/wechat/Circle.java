package com.example.wechat;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Circle extends AppCompatActivity {
    private ListView listView;
    private String[] name={"Pony","刘启维"};
    private int[] headimg={R.drawable.pony,R.drawable.login};
    private int[] image={R.drawable.ponyimg,R.drawable.yellowmountain};
    private String[] text={"上帝让你拥有了钱财，必然会让你失去烦恼","今天天气真好"};
    private String[] time={"4小时前","4小时前"};
    private SimpleAdapter simpleAdapter;
    private ArrayList<Map<String,Object>> arrayList;
    private Context context = null;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            //因为背景为浅色，设置通知栏字体颜色为深色

            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        //返回按钮
        ImageButton imageButton=(ImageButton)findViewById(R.id.circle_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       //内容适配器
        listView=(ListView) findViewById(R.id.circle_list);
        arrayList=new ArrayList<Map<String,Object>>();
        for(int i=0;i<name.length;i++){
            HashMap<String,Object> map=new HashMap<>();
            map.put("mingzi",name[i]);
            map.put("touxiang",headimg[i]);
            map.put("wenzi",text[i]);
            map.put("tupian",image[i]);
            map.put("shijian",time[i]);
            arrayList.add(map);
        }
        simpleAdapter=new SimpleAdapter(this,arrayList,R.layout.circle_listview,
                new String[]{"mingzi","touxiang","wenzi","tupian","shijian"},
                new int[]{R.id.circle_name,R.id.circle_headimg,R.id.circle_text,R.id.circle_img,R.id.circle_time}
        );
        listView.setAdapter(simpleAdapter);
        setListViewHeightBasedOnChildren(listView,view,simpleAdapter);


        /*context = this;
        ImageButton feed=(ImageButton)findViewById(R.id.feed);
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
            }
        });*/






    }
    //PopupWindow
    private void showPopupWindow(View view) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(context).inflate(R.layout.circlepop, null);
        // final PopupWindow popupWindow = new PopupWindow(contentView,  Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT, true);
        final PopupWindow pop = new PopupWindow(contentView,  740, 190, true);
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        pop.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        // 设置好参数之后再show
        ImageButton feed=(ImageButton)findViewById(R.id.feed);
        int[] location = new int[2];
        feed.getLocationOnScreen(location);
        pop.showAtLocation(feed, Gravity.LEFT, location[0]-pop.getWidth(), 1020 );
    }
    /**
     * 动态设置ListView的高度
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView,View view,SimpleAdapter adapter) {
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
