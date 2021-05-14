package com.hikvision.rvdemo.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class HomeEntity implements MultiItemEntity {

    // 分别对应三种不同的布局
    public static final int MY_COLLECTION_SECTION = 1; // 我的收藏
    public static final int MY_SHARE_SECTION = 2; // 我的分享
    public static final int MY_LIKE_SECTION = 3; // 我的点赞

    private int itemType;

    public HomeEntity(int itemType) {
        this.itemType = itemType;
    }
    @Override
    public int getItemType() {
        return itemType;
    }
}
