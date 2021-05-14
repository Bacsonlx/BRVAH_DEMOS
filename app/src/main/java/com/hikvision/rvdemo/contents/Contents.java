package com.hikvision.rvdemo.contents;

import java.util.HashMap;
import java.util.Map;

public class Contents {
    // 定义Key 用于HashMap
    public static String MY_COLLECTION_KEY = "MY_COLLECTION_KEY";
    public static String MY_SHARE_KEY = "MY_SHARE_KEY";
    public static String MY_LIKE_KEY = "MY_LIKE_KEY";

    /**
     *  图片的 URL 地址
     */
    public static Map<String, String> ImgsUrl = new HashMap<String, String>();
    static {
        ImgsUrl.put("Img1", "https://pic.netbian.com/uploads/allimg/210505/233804-1620229084e5fa.jpg");
        ImgsUrl.put("Img2", "https://pic.netbian.com/uploads/allimg/180826/113958-153525479855be.jpg");
        ImgsUrl.put("Img3", "https://pic.netbian.com/uploads/allimg/170725/103840-15009503208823.jpg");
        ImgsUrl.put("Img4", "https://pic.netbian.com/uploads/allimg/160822/100046-1471831246a885.jpg");
        ImgsUrl.put("Img5", "https://pic.netbian.com/uploads/allimg/210419/172907-16188245474148.jpg");
        ImgsUrl.put("Img6", "https://pic.netbian.com/uploads/allimg/210419/203429-1618835669e1dd.jpg");
        ImgsUrl.put("Img7", "https://pic.netbian.com/uploads/allimg/210406/214108-16177164688657.jpg");
        ImgsUrl.put("Img8", "https://pic.netbian.com/uploads/allimg/210307/233312-1615131192111c.jpg");
        ImgsUrl.put("Img9", "https://pic.netbian.com/uploads/allimg/170610/174855-14970881351957.jpg");
        ImgsUrl.put("Img10", "https://pic.netbian.com/uploads/allimg/180315/110404-152108304476cb.jpg");
    }


}
