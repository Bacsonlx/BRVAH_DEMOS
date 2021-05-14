package com.hikvision.rvdemo.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.hikvision.rvdemo.bean.Img;
import com.hikvision.rvdemo.model.Callback;
import com.hikvision.rvdemo.model.ImgRepository;

import java.util.HashMap;
import java.util.List;

public class ImgListViewModel extends ViewModel {

    /**
     * 用户信息
     */
    private MutableLiveData<HashMap<String, List<Img>>> imgListLiveData;


    public ImgListViewModel() {
        imgListLiveData = new MutableLiveData<>();
    }


    /**
     *  从服务器中获取图片列表信息
     */
    public void getServerImgInfo(Context context) {
        /**
         *  从服务器获取
         *  假装网络请求 2s后 返回用户信息
         */
        ImgRepository.getImgRepository().getImgsFromServer(context, new Callback<HashMap<String, List<Img>>>() {
            @Override
            public void onSuccess(HashMap<String, List<Img>> imgs) {
                imgListLiveData.setValue(imgs);
            }

            @Override
            public void onFailed(String msg) {
                imgListLiveData.setValue(null);
            }
        });
    }

    /**
     * 返回LiveData类型
     */
    public LiveData<HashMap<String, List<Img>>> getImgListLiveData() {
        return imgListLiveData;
    }


}
