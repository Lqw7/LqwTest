package com.example.wechat;

import android.content.Context;

public class Contact {
    private String text;
    private int img;
    public Contact(int img,String text){
        this.img=img;
        this.text=text;
    }
    public String getText(){
        return text;
    }
    public int getImg() {
        return img;
    }
}
