package com.test.sportsnewsdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.sportsnewsdemo.R;
import com.test.sportsnewsdemo.bean.News;
import com.test.sportsnewsdemo.cache.ImageLoader;

import java.util.List;

/**
 * Created by liuchang05 on 2016-1-15.
 */
public class RecyclerViewAdpater extends RecyclerView.Adapter<RecyclerViewAdpater.MyViewHolder> {


    public void setmDatas(List<News> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    private List<News> mDatas;
    private Context mContext;

    public RecyclerViewAdpater(Context context, List<News> datas) {
        mDatas = datas;
        mContext = context;
    }


    public void addItem(News mNew, int position) {
        mDatas.add(position, mNew);
        notifyItemInserted(position); //Attention!
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item,viewGroup,false));
        return holder;


    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {

//        myViewHolder.imageView.setImageResource(R.drawable.send_btn_pressed);

        ImageLoader.getInstance().loadImage(mDatas.get(i).getImgStr(), myViewHolder.imageView);

        myViewHolder.titleTextView.setText(mDatas.get(i).getTitle());
        myViewHolder.detailTextView.setText(mDatas.get(i).getDetail());

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titleTextView;
        private TextView detailTextView;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.id_item_img);
            titleTextView = (TextView) itemView.findViewById(R.id.id_item_tv_title);
            detailTextView = (TextView) itemView.findViewById(R.id.id_item_tv_detail);
        }
    }
}
