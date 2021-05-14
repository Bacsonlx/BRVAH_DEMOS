package com.hikvision.rvdemo.server;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyServer {

    // 定义URL 必须以反斜杠结尾 Retrofit 是一个 RESTful 的 HTTP 网络请求框架的封装
    String baseURL = "http://192.168.1.112/app/";

    @GET("xxx")
    Call<ResponseBody> getData(@Query("page") String page , @Query("area") String area);

    //@POST("xxx") 在url后边拼接xxx
    //服务器接收的url是 ： http://192.168.1.112/app/xxx
//    @POST("xxx")
//    @FormUrlEncoded
//    Call<ResponseBody> postData(@FieldMap Map<String,Object> map);

}
