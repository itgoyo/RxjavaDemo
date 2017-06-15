package net.kotlinandroid.rxjavademo;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

/**
 * Created by admin on 2017/5/11.
 * <p>
 * 更新时间 2017/5/11
 * 更新描述 ${TODO}
 */

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PicViewHolder> {
    private  static  final String URL="http://tnfs.tngou.net/img";
    private Context mContext;
    private List<TianGouBean.TngouBean> mList;
    private LayoutInflater mInflater;

    public PictureAdapter(Context context, List<TianGouBean.TngouBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public PicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.from(mContext).inflate(R.layout.pic_item, parent, false);
        return new PicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PicViewHolder holder, int position) {

        TianGouBean.TngouBean tngouBean = mList.get(position);

        holder.setData(tngouBean);


    }

    @Override
    public int getItemCount() {
        if(null!=mList){
            return mList.size();
        }
        return 0;
    }

    public class PicViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImageView;
        private TextView mTextView;

        public PicViewHolder(View itemView) {
            super(itemView);

            mImageView= (ImageView) itemView.findViewById(R.id.iv);
            mTextView= (TextView) itemView.findViewById(R.id.tv);




        }

        public void setData(TianGouBean.TngouBean tngouBean) {
            mTextView.setText(tngouBean.getName());

            Glide.with(mContext).load(URL+tngouBean.getImg()).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    mImageView.setImageBitmap(resource);
                }
            });



            Glide.with(mContext).load(URL).into(mImageView);


        }

    }
}
