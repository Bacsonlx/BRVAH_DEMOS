package com.hikvision.rvdemo.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hikvision.rvdemo.R;
import com.hikvision.rvdemo.entity.HomeEntity;
import com.hikvision.rvdemo.bean.Img;
import com.hikvision.rvdemo.viewmodel.ImgListViewModel;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;

public class MainFragment extends Fragment {

    private String TAG = "TagOfMainFragment";

    private SmartRefreshLayout refreshLayout;
    private RecyclerView rvContainer;
    private MultiAdapter multiAdapter;
    private ImgListViewModel mImgListViewModel;
    private Context mContext;
    // 设置一个 noDataFlag 表示加载失败
    private Boolean noDataFlag = true;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mContext = getContext();

        // 初始化RV_container
        initView(view);
        // 下拉刷新请求
        RefreshRequest();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
        // 初始化ViewModel
        initViewModel();
        // 观察ViewModel的数据
        observeLivaData();
        // 初始数据
        requestServerData();

    }


    /**
     *  初始化View
     */
    private void initView(View view) {
        //  刷新框
        refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(mContext));

        // RecyclerView
        rvContainer = view.findViewById(R.id.rv_container);
        rvContainer.setLayoutManager(new LinearLayoutManager(mContext));
    }

    /**
     *  初始化Adapter
     */
    private void initAdapter(HashMap<String, List<Img>> responce) {

        // todo 做一次请求 将数据初始化
        // 初始的数据
        multiAdapter = new MultiAdapter(mContext, responce);
        multiAdapter.setAnimationEnable(true);
        // 给 RecyclerView 中添加 Section
        multiAdapter.addData(new HomeEntity(HomeEntity.MY_COLLECTION_SECTION));
        multiAdapter.addData(new HomeEntity(HomeEntity.MY_SHARE_SECTION));
        multiAdapter.addData(new HomeEntity(HomeEntity.MY_LIKE_SECTION));

        // 设置点击事件
        ClickEvent();
        rvContainer.setAdapter(multiAdapter);
    }

    /**
     *  下拉刷新 上拉加载
     */
    private void RefreshRequest() {

        //设置刷新的时候监听，2秒钟之后添加数据完毕
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //模拟网络请求数据
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //请求数据
                        requestServerData();
                        //recyclerView回到最上面
                        rvContainer.scrollToPosition(0);
                    }
                }, 2000);

                // 结束刷新
                refreshLayout.finishRefresh(2000/*,false*/);
//                refreshLayout.finishRefresh(1000, false, true); // 刷新失败
            }
        });
    }


    /**
     *  初始化 ViewModel
     */
    private void initViewModel() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        mImgListViewModel = viewModelProvider.get(ImgListViewModel.class);
    }


    /**
     *  假装获取网络请求数据
     */
    private void requestServerData() {
        if (!isNetworkConnected(mContext)) {
            initAdapter(null);
        }else {
            mImgListViewModel.getServerImgInfo(mContext);
        }
    }

    /**
     * 观察ViewModel的数据，且此数据 是 View 直接需要的，不需要再做逻辑处理
     */
    private void observeLivaData() {
        mImgListViewModel.getImgListLiveData().observe(getViewLifecycleOwner(), new Observer<HashMap<String, List<Img>>>() {
            @Override
            public void onChanged(HashMap<String,List<Img>> response) {
                if (response == null) {
                    Toast.makeText(mContext, "获取图片失败！", Toast.LENGTH_SHORT).show();
                    return;
                }
                // todo 待改善 : 这里是重新初始化了Adapter实例 原因是为了用Adapter的构造函数进行参数传递
                initAdapter(response);

            }
        });
    }

    /**
     *  点击事件
     */
    public void ClickEvent() {
        // 点击整个Section的事件
        multiAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Toast.makeText(mContext, "onItem_Click" + position, Toast.LENGTH_LONG).show();
            }
        });
        // 点击子控件的事件
        multiAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                switch (view.getId()) {
                    case R.id.more: // 点击更多按钮
                        Toast.makeText(mContext, "Click_More" + position, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

}
