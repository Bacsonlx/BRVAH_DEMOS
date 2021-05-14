package com.hikvision.rvdemo.model;

public interface Callback<T> {
    public void onSuccess(T t);
    public void onFailed(String msg);
}
