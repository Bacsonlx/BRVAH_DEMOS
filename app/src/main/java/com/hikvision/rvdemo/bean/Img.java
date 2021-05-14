package com.hikvision.rvdemo.bean;

public class Img {

    private String img_name;
    private String img_url;

    public Img(String img_name, String img_url) {
        this.img_name = img_name;
        this.img_url = img_url;
    }

    public String getImg_name() {
        return img_name;
    }

    public void setImg_name(String img_name) {
        this.img_name = img_name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

}
