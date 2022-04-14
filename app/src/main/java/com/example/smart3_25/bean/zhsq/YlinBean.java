package com.example.smart3_25.bean.zhsq;

import android.graphics.Bitmap;

import org.litepal.crud.LitePalSupport;

import java.util.List;

public class YlinBean extends LitePalSupport {
    private String name;
    private byte[] img;
    private String content;

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
