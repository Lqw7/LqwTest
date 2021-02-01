package com.example.wechat;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Context context = null;
    private ViewPager viewPager;
    private myFragmentPagerAdapter myFragmentPagerAdapter;
    private List<Fragment> fragmentList; //保存界面的view
    private LinearLayout wechat, contact,discover,my;
    private ImageView wechat_icon,contact_icon,discover_icon,my_icon;
    private TextView  wechat_text,contact_text,discover_text,my_text,top;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置ToolBar
        final  Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //沉浸式状态栏效果设置
       if (Build.VERSION.SDK_INT >= 21) {   //只有5.0及以上系统才支持，因此这里先进行了一层if判断
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE    //设置为全屏显示，必须这两行代码一起才能生效
                    |View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;  //因为背景为浅色，设置通知栏字体颜色为深色
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);//设置为透明
        }
        //点击加号弹出PopupWindow
        context = this;
        ImageButton imageButton=(ImageButton)findViewById(R.id.plus_icon);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
            }
        });
        initViews();
        initDatas();
        setViewPagerEvent();
        initEvents();
        }

    //PopupWindow
    private void showPopupWindow(View view) {
        // 绑定自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(context).inflate(R.layout.popupwindow, null);
        final PopupWindow popupWindow = new PopupWindow(contentView,  685, 1130, true);
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        // 设置好参数之后再show
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);//获取ToolBar，将popopWindow显示在Toorbal下方
        popupWindow.showAsDropDown(toolbar,720,0);//在ToolBar下方，并向右偏移
    }
    /**
     * 数据初始化 适配fragment
     */
    private void initDatas() {
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new WeChatFragment());
        fragmentList.add(new ContactFragment());
        fragmentList.add(new DiscoverFrament());
        fragmentList.add(new MyFragment());
        myFragmentPagerAdapter = new myFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(myFragmentPagerAdapter);
    }
    /**
     * 初始化控件
     */
    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        //底部的布局
        wechat= (LinearLayout) findViewById(R.id.wechat_main);
        contact= (LinearLayout) findViewById(R.id.wechat_contact);
        discover= (LinearLayout) findViewById(R.id.wechat_discover);
        my= (LinearLayout) findViewById(R.id.wechat_mine);
        //底部显示图标的ImageView
        wechat_icon=(ImageView)findViewById(R.id.wechat_icon);
        contact_icon=(ImageView)findViewById(R.id.contact_icon);
        discover_icon=(ImageView)findViewById(R.id.discover_icon);
        my_icon=(ImageView)findViewById(R.id.mine_icon);
        //按钮对应的文字
        wechat_text = (TextView) findViewById(R.id.wechat_text);
        contact_text = (TextView) findViewById(R.id.contact_text);
        discover_text= (TextView) findViewById(R.id.discover_text);
        my_text=(TextView) findViewById(R.id.mine_text);
        //顶部标题栏文字
        top = (TextView) findViewById(R.id.top_text);

    }
    /**
     * ViewPager切换页面的事件
     */
    private void setViewPagerEvent() {
        //设置ViewPager的page监听切换底栏按钮和文字颜色
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                int currentItem = viewPager.getCurrentItem();//获取Fragment数量
                Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
                switch (currentItem) {
                    case 0:
                        toolbar.setVisibility(View.VISIBLE);//ToolBar可见
                        top.setText(R.string.title);//设置标题
                        onWechatSelected(); //改变底栏文字和图标颜色
                        break;
                    case 1:
                        toolbar.setVisibility(View.VISIBLE);//ToolBar可见

                        top.setText(R.string.bottom2);//设置标题
                        onContactSelected(); //改变底栏文字和图标颜色
                        break;
                    case 2:
                        toolbar.setVisibility(View.VISIBLE);//ToolBar可见
                        top.setText(R.string.bottom3);//设置标题
                        onDiscoverSelected(); //改变底栏文字和图标颜色
                        break;
                    case 3:
                        toolbar.setVisibility(View.GONE);//去除Toolbar
                        top.setText(R.string.bottom4);//设置标题
                        onMySelected(); //改变底栏文字和图标颜色
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    @Override
    public void onClick(View v) {
        final  Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        switch (v.getId()){
            case R.id.wechat_main:
                viewPager.setCurrentItem(0);//如果首页 切换首页
                top.setText(R.string.title);
                onWechatSelected();
                break;
            case  R.id.wechat_contact:
                viewPager.setCurrentItem(1);
                top.setText(R.string.bottom2);
                onContactSelected();
                break;
            case  R.id.wechat_discover:
                viewPager.setCurrentItem(2);
                top.setText(R.string.bottom3);
                onDiscoverSelected();
                break;
            case  R.id.wechat_mine:
                viewPager.setCurrentItem(3);
                top.setText(R.string.bottom4);
                onMySelected();
                break;
            default:
                break;
        }
    }
    /**
     * 初始化底部导航栏事件
     */
    private void initEvents() {
            LinearLayout main,contact,discover,mine;
            main=(LinearLayout)findViewById(R.id.wechat_main);
            discover=(LinearLayout)findViewById(R.id.wechat_discover);
            contact=(LinearLayout)findViewById(R.id.wechat_contact);
            mine=(LinearLayout)findViewById(R.id.wechat_mine);
            main.setOnClickListener(this);
            discover.setOnClickListener(this);
            contact.setOnClickListener(this);
            mine.setOnClickListener(this);
    }
    //微信界面被选中底部图标，文字变化
    public void onWechatSelected(){
        //改变底栏文字和图标颜色
        wechat_icon.setBackgroundDrawable(getResources().getDrawable(R.drawable.wechat_press));
        wechat_text.setTextColor(getResources().getColor(R.color.bottom_text_press));
        contact_icon.setBackgroundDrawable(getResources().getDrawable(R.drawable.contact_normal));
        contact_text.setTextColor(getResources().getColor(R.color.bottom_text_normal));
        my_icon.setBackgroundDrawable(getResources().getDrawable(R.drawable.my_normal));
        my_text.setTextColor(getResources().getColor(R.color.bottom_text_normal));
        discover_icon.setBackgroundDrawable(getResources().getDrawable(R.drawable.discover_normal));
        discover_text.setTextColor(getResources().getColor(R.color.bottom_text_normal));
    }
    //通讯录界面被选中底部图标，文字变化
    public void onContactSelected(){
        contact_icon.setBackgroundDrawable(getResources().getDrawable(R.drawable.contact_press));
        contact_text.setTextColor(getResources().getColor(R.color.bottom_text_press));
        wechat_icon.setBackgroundDrawable(getResources().getDrawable(R.drawable.wechat_normal));
        wechat_text.setTextColor(getResources().getColor(R.color.bottom_text_normal));
        discover_icon.setBackgroundDrawable(getResources().getDrawable(R.drawable.discover_normal));
        discover_text.setTextColor(getResources().getColor(R.color.bottom_text_normal));
        my_icon.setBackgroundDrawable(getResources().getDrawable(R.drawable.my_normal));
        my_text.setTextColor(getResources().getColor(R.color.bottom_text_normal));
    }
    //发现界面被选中底部图标，文字变化
    public void onDiscoverSelected(){
        //改变底栏文字和图标颜色
        discover_icon.setBackgroundDrawable(getResources().getDrawable(R.drawable.discover_press));
        discover_text.setTextColor(getResources().getColor(R.color.bottom_text_press));
        contact_icon.setBackgroundDrawable(getResources().getDrawable(R.drawable.contact_normal));
        contact_text.setTextColor(getResources().getColor(R.color.bottom_text_normal));
        my_text.setTextColor(getResources().getColor(R.color.bottom_text_normal));
        my_icon.setBackgroundDrawable(getResources().getDrawable(R.drawable.my_normal));
        wechat_icon.setBackgroundDrawable(getResources().getDrawable(R.drawable.wechat_normal));
        wechat_text.setTextColor(getResources().getColor(R.color.bottom_text_normal));
    }
    public void onMySelected(){
        //改变底栏文字和图标颜色
        my_icon.setBackgroundDrawable(getResources().getDrawable(R.drawable.my_press));
        my_text.setTextColor(getResources().getColor(R.color.bottom_text_press));
        discover_icon.setBackgroundDrawable(getResources().getDrawable(R.drawable.discover_normal));
        discover_text.setTextColor(getResources().getColor(R.color.bottom_text_normal));
        wechat_icon.setBackgroundDrawable(getResources().getDrawable(R.drawable.wechat_normal));
        wechat_text.setTextColor(getResources().getColor(R.color.bottom_text_normal));
        contact_icon.setBackgroundDrawable(getResources().getDrawable(R.drawable.contact_normal));
        contact_text.setTextColor(getResources().getColor(R.color.bottom_text_normal));
    }

    //最小化程序
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}


