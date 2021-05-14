package com.hikvision.rvdemo.view;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hikvision.rvdemo.R;
import com.hikvision.rvdemo.bean.Img;

/**
 *  所有的子RecyclerView 都采用该Adapter
 */
public class ChildRVAdapter extends BaseQuickAdapter<Img, BaseViewHolder> {
    private Context context;

    public ChildRVAdapter(int layoutResId, Context context) {
        super(layoutResId);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Img img) {
        ImageView imageView = helper.getView(R.id.img_rv_item_id);
        if (img.getImg_url() == null) { // url不存在
            helper.setBackgroundResource(R.id.img_rv_item_id, R.mipmap.ic_launcher); // 加载一张默认图片
        } else { // 是从url中加载的数据
            String img_url = img.getImg_url();
            // 使用Glide加载图片
            Glide.with(context)
                    .load(img_url)
                    .into(imageView);
        }
    }
}
