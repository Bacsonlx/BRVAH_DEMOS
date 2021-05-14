package com.hikvision.rvdemo.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.hikvision.rvdemo.bean.Img;
import com.hikvision.rvdemo.contents.Contents;
import com.hikvision.rvdemo.server.MyServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ImgRepository {
    private static String TAG = "TagOfImgRepository";
    private static String IMG_NAME = "img_name";
    private static String IMG_URL = "img_url";
    private static ImgRepository mImgRepository;

    public static ImgRepository getImgRepository(){

        if (mImgRepository == null) {
            mImgRepository = new ImgRepository();
        }
        return mImgRepository;
    }

    /**
     * 从服务端获取图片
     * @param callback
     */
    @SuppressLint("StaticFieldLeak")
    public void getImgsFromServer(final Context context, final Callback<HashMap<String, List<Img>>> callback){

        // todo 从服务器请求数据 将结果保存为ImgList对象返回
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(MyServer.baseURL)
//                .build();
//        MyServer myServer = retrofit.create(MyServer.class);
//        //GET
//        Call<ResponseBody> call = myServer.getData("1","2");
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                // 数据请求成功
//                try {
//                    assert response.body() != null;
//                    String result = response.body().string();
//                    Log.e("-----", "onResponse: "+result);
//                    //不用切换主线程了,因为Retrofit帮我们切过了
//                    //okHttpClient需要自己切换主线程
//                    //tv.setText(result);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                // 数据请求失败
//                Log.e("-----", "onFailure: "+t.getMessage());
//            }
//        });




        // 假的请求
//        List<Img> imgsList = new ArrayList<>();
//        for (int i = 0; i < 2 ; i++) {
//            // 产生一张随机图片
//            Img img = new Img("test", "https://pic.netbian.com/uploads/allimg/210505/233804-1620229084e5fa.jpg");
//            imgsList.add(0, img);
//        }
//
//        // 回调函数返回值
//        if (imgsList.isEmpty()) {
//            callback.onFailed("数据加载失败");
//        }

        HashMap<String, List<Img>> results = new HashMap<>();
        List<Img> my_collection_imgsList = new ArrayList<>();
        List<Img> my_share_imgsList = new ArrayList<>();
        List<Img> my_like_imgsList = new ArrayList<>();
        Img mycollection_img = new Img("img1", Contents.ImgsUrl.get("Img1"));
        Img myshare_img = new Img("img2", Contents.ImgsUrl.get("Img2"));
        Img mylike_img = new Img("img3", Contents.ImgsUrl.get("Img3"));
        // 添加我的收藏图片
        my_collection_imgsList.add(mycollection_img);
        my_collection_imgsList.add(mycollection_img);
        my_collection_imgsList.add(mycollection_img);
        results.put(Contents.MY_COLLECTION_KEY, my_collection_imgsList);

        // 添加我的分享图片
        my_share_imgsList.add(myshare_img);
        my_share_imgsList.add(myshare_img);
        my_share_imgsList.add(myshare_img);
        my_share_imgsList.add(myshare_img);
        my_share_imgsList.add(myshare_img);
        my_share_imgsList.add(myshare_img);
        my_share_imgsList.add(myshare_img);
        results.put(Contents.MY_SHARE_KEY, my_share_imgsList);

        // 添加我的点赞图片
        my_like_imgsList.add(mylike_img);
        my_like_imgsList.add(mylike_img);
        my_like_imgsList.add(mylike_img);
        my_like_imgsList.add(mylike_img);
        my_like_imgsList.add(mylike_img);
        my_like_imgsList.add(mylike_img);
        my_like_imgsList.add(mylike_img);
        results.put(Contents.MY_LIKE_KEY, my_like_imgsList);

        // 回调函数返回值
        if (results.isEmpty()) {
            callback.onFailed("数据加载失败");
        }
        callback.onSuccess(results);
    }


}
