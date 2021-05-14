package com.hikvision.rvdemo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hikvision.rvdemo.R;
import com.hikvision.rvdemo.entity.HomeEntity;
import com.hikvision.rvdemo.bean.Img;
import com.hikvision.rvdemo.contents.Contents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.hikvision.rvdemo.utils.ImageUtils.photoViewer;

public class MultiAdapter extends BaseMultiItemQuickAdapter<HomeEntity, BaseViewHolder>  implements OnItemClickListener {

    public static String TAG = "MultiAdapter";
    private Context mContext;
    private HashMap<String, List<Img>> imgMap;


    public MultiAdapter(Context context, HashMap<String, List<Img>> imgMap) {
        super();
        this.imgMap = imgMap;
        this.mContext = context;
        addItemType(HomeEntity.MY_COLLECTION_SECTION, R.layout.mycollection_rv_layout); // 我的收藏
        addItemType(HomeEntity.MY_SHARE_SECTION, R.layout.myshare_rv_layout); // 我的分享
        addItemType(HomeEntity.MY_LIKE_SECTION, R.layout.mylike_rv_layout); // 我的点赞

        addChildClickViewIds(R.id.more);
    }


    @Override
    protected void convert(BaseViewHolder helper, HomeEntity homeEntity) {
        List<Img> imgDatas = new ArrayList<Img>();
        switch (helper.getItemViewType()) { // 这里要对 imgDatas 分别进行处理
            case HomeEntity.MY_COLLECTION_SECTION:
                // 将 子RecyclerView 实例化
                RecyclerView myCollectionRV = helper.getView(R.id.mycollection_rv_view);
                // 线性布局
                myCollectionRV.setLayoutManager(new LinearLayoutManager(mContext,
                        LinearLayoutManager.HORIZONTAL,false));
                myCollectionRV.setHasFixedSize(true);
                // 实例化 Adpter
                ChildRVAdapter mycollection_rv_adapter = new ChildRVAdapter(R.layout.img_rv_item, mContext);
                // 将数据丢进去
                if (imgMap != null) {
                    imgDatas = imgMap.get(Contents.MY_COLLECTION_KEY);
                    assert imgDatas != null;
                    for (Img img : imgDatas) { // 加载成功则添加结果
                        mycollection_rv_adapter.addData(img);
                    }
                } else { // 如果加载的数据结果为 null，则加载失败页面
                    onRetry(myCollectionRV, mycollection_rv_adapter);
                }
                // 设置 Adapter 的监听事件
                if (myCollectionRV.getAdapter() == null) {
                    mycollection_rv_adapter.setOnItemClickListener(this);
                    myCollectionRV.setAdapter(mycollection_rv_adapter);
                }

                break;

            case HomeEntity.MY_SHARE_SECTION:
                // 将 子RecyclerView 实例化
                RecyclerView myShareRV = helper.getView(R.id.myshare_rv_view);
                myShareRV.setHasFixedSize(true);
                // 实例化 Adapter
                ChildRVAdapter myshare_rv_adapter = new ChildRVAdapter(R.layout.img_rv_item, mContext);
                // 将数据丢进去
                if (imgMap != null) {
                    imgDatas = imgMap.get(Contents.MY_SHARE_KEY);
                    assert imgDatas != null;
                    for (Img img : imgDatas) { // 加载成功则添加结果
                        myshare_rv_adapter.addData(img);
                    }
                } else { // 如果加载的数据结果为 null，则加载失败页面
                    onRetry(myShareRV, myshare_rv_adapter);
                }
                // 设置 RecyclerView 布局
                if (myShareRV.getLayoutManager() == null) {
                    myShareRV.setLayoutManager(new GridLayoutManager(mContext, 2, LinearLayoutManager.HORIZONTAL, false));
                }
                // 设置 Adapter 的监听事件
                if (myShareRV.getAdapter() == null) {
                    myshare_rv_adapter.setOnItemClickListener(this);
                    myShareRV.setAdapter(myshare_rv_adapter);
                }

                break;
            case HomeEntity.MY_LIKE_SECTION:
                RecyclerView myLikeRV = helper.getView(R.id.mylike_rv_view);
                myLikeRV.setLayoutManager(new GridLayoutManager(mContext, 2,
                        LinearLayoutManager.HORIZONTAL,false));
                myLikeRV.setHasFixedSize(true);
                // 实例化Adapter
                ChildRVAdapter mylike_rv_adapter = new ChildRVAdapter(R.layout.img_rv_item, mContext);
                // 将数据丢进去
                if (imgMap != null) {
                    imgDatas = imgMap.get(Contents.MY_LIKE_KEY);
                    assert imgDatas != null;
                    for (Img img : imgDatas) { // 加载成功则添加结果
                        mylike_rv_adapter.addData(img);
                    }
                }
                else { // 如果加载的数据结果为 null，则加载重试页面
                    onRetry(myLikeRV, mylike_rv_adapter);
                }

                // 设置 Adapter 的监听事件
                if (myLikeRV.getAdapter() == null) {
                    mylike_rv_adapter.setOnItemClickListener(this);
                    myLikeRV.setAdapter(mylike_rv_adapter);
                }

            default:
                break;
        }

    }

    /**
     *  点击嵌套RecyclerView的Item事件
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        Toast.makeText(mContext, "嵌套RecycleView item 收到: " + "点击了第 " + position + " 一次", Toast.LENGTH_LONG).show();
        // todo 设置图片查看器
//        Img item = (Img) adapter.getItem(position);
//
//        List<Img> imgList = (List<Img>) adapter.getData();
//        List<String> urlList = new ArrayList<String>();
//        for (Img img : imgList) {
//            urlList.add(img.getImg_url());
//        }
//
//        // 设置图片查看器
//        photoViewer(mContext, position, urlList);
    }

    /**
     *  重新加载
     */
    private void onRetry(RecyclerView recyclerView, final ChildRVAdapter adapter) {
        View loading_view = LayoutInflater.from(mContext).inflate(R.layout.loading_view, null);
        adapter.setEmptyView(loading_view);
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.setEmptyView(getErrorView());
//                if (mError) {
//                    // 方式二：传入View
//                    mAdapter.setEmptyView(getErrorView());
//                    mError = false;
//                } else {
//                    if (mNoData) {
//                        mAdapter.setEmptyView(getEmptyDataView());
//                        mNoData = false;
//                    } else {
//                        mAdapter.setList(DataServer.getSampleData(10));
//                    }
//                }
            }
        }, 1000);
    }

    /**
     *  返回错误页面
     */
    private View getErrorView() {
        View errorView = LayoutInflater.from(mContext).inflate(R.layout.error_view, null);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onRefresh();
                Toast.makeText(mContext, "Click_Error", Toast.LENGTH_LONG).show();
            }
        });
        return errorView;
    }


}
