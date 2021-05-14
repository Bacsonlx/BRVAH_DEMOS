package com.hikvision.rvdemo.utils;

import android.content.Context;

import java.util.List;

import cc.shinichi.library.ImagePreview;

public class ImageUtils {
    /**
     *  设置图片查看器
     */
    public static void photoViewer(Context context, int position, List<String> urlList) {
        ImagePreview
                .getInstance()
                // 上下文，必须是activity，不需要担心内存泄漏，本框架已经处理好；
                .setContext(context)
                // 设置从第几张开始看（索引从0开始）
                .setIndex(position)
                // 设置下拉图片退出
                .setEnableDragClose(true)
                // 设置关闭按钮
                .setShowCloseButton(true)
                //=================================================================================================
                // 有三种设置数据集合的方式，根据自己的需求进行三选一：
                // 1：第一步生成的imageInfo List
//                .setImageInfoList(imageInfoList)

                // 2：直接传url List
                .setImageList(urlList)

                // 3：只有一张图片的情况，可以直接传入这张图片的url
//                .setImage(item.getImg_url())
                //=================================================================================================
                // 开启预览
                .start();

    }
}
