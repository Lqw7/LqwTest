package com.example.wechat;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pay extends AppCompatActivity {
    private GridView gridView,gridView2;
    private String[] name={"信用卡还款","手机充值","理财通","生活缴费","Q币充值","城市服务","腾讯公益","保险服务",""};
    private int[] img={R.drawable.creditcard,R.drawable.phonechare,R.drawable.manage,R.drawable.lifecharge,R.drawable.qb
    ,R.drawable.cityservice,R.drawable.benefit,R.drawable.insurance,0};
    private String[] name2={"火车票机票","滴滴出行","京东优选","美团外卖","电影演出赛事","吃喝玩乐","酒店","拼多多","蘑菇街女装","唯品会特卖","装转二手","贝壳找房"};
    private int[] img2={R.drawable.ticket,R.drawable.did,R.drawable.jd,R.drawable.meituan,R.drawable.film
            ,R.drawable.fun,R.drawable.hotel,R.drawable.ping,R.drawable.street,R.drawable.weipinhui,R.drawable.zhuangzhuang,R.drawable.beike};
    private SimpleAdapter simpleAdapter,simpleAdapter2;
    private ArrayList<Map<String,Object>> arrayList,arrayList2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay);
        //第一个GridView
        gridView=(GridView)findViewById(R.id.pay_grid1);
        arrayList=new ArrayList<Map<String,Object>>();
        for(int i=0;i<name.length;i++){
            HashMap<String,Object> map=new HashMap<>();
            map.put("app_img",img[i]);
            map.put("app_name",name[i]);
            arrayList.add(map);
        }
        simpleAdapter=new SimpleAdapter(this,arrayList,R.layout.pay_grid,
                new String[]{"app_img","app_name"},
                new int[]{R.id.pay_grid_img,R.id.pay_grid_text}
        );
        gridView.setAdapter(simpleAdapter);
        //第二个GridView
        gridView2=(GridView)findViewById(R.id.pay_grid2);
        arrayList2=new ArrayList<Map<String,Object>>();
        for(int i=0;i<name2.length;i++){
            HashMap<String,Object> map=new HashMap<>();
            map.put("app_img2",img2[i]);
            map.put("app_name2",name2[i]);
            arrayList2.add(map);
        }
        simpleAdapter2=new SimpleAdapter(this,arrayList2,R.layout.pay_grid2,
                new String[]{"app_img2","app_name2"},
                new int[]{R.id.pay_grid_img2,R.id.pay_grid_text2}
        );
        gridView2.setAdapter(simpleAdapter2);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;;
            //因为背景为浅色，设置通知栏字体颜色为深色
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        //返回键
        ImageButton imageButton=(ImageButton)findViewById(R.id.pay_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
