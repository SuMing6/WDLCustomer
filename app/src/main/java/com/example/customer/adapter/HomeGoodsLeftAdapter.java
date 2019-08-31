package com.example.customer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.bean.HomeGoodsBean;

import java.util.List;

public class HomeGoodsLeftAdapter extends RecyclerView.Adapter<HomeGoodsLeftAdapter.holder> {

    private final Context context;
    private final List<HomeGoodsBean.DataBean.CateBean> cate;
    HomePageNearbyAdapter.setOnClickItem setOnClickItem;

    /**
     * 第33-39行是：手寫點擊事件的接口回調
     * 36行是點擊事件，    38行是長按事件。根據你的需求  寫你需要的
     */
    private OnItemClickListener onRecyclerViewItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position,int cate_id);

        void onLongClick(int position);
    }

    /**
     * 第44-62行是點擊變色的邏輯
     */
    /////////////////////////////////////////////最後一步點擊變色///////////////////////////////////////////////////////
    //先声明一个int成员变量
    private int thisPosition;

    //再定义一个int类型的返回值方法
    public int getthisPosition() {
        return thisPosition;
    }

    //其次定义一个方法用来绑定当前参数值的方法
    //此方法是在调用此适配器的地方调用的，此适配器内不会被调用到
    public void setThisPosition(int thisPosition) {
        this.thisPosition = thisPosition;
    }

    public void setOnRecyclerViewItemClickListener(OnItemClickListener onItemClickListener) {
        this.onRecyclerViewItemClickListener = onItemClickListener;
    }

    public HomeGoodsLeftAdapter(Context context, List<HomeGoodsBean.DataBean.CateBean> cate) {
        this.context = context ;
        this.cate = cate ;
    }

    @NonNull
    @Override
    public HomeGoodsLeftAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_home_goods_left, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeGoodsLeftAdapter.holder holder, final int position) {



            holder.textView.setText(cate.get(position).getCate_name());
            if (position == getthisPosition()) {
                //選中的顔色就設成了  黃色
                holder.textView.setTextColor(Color.parseColor("#FF9600"));
                holder.textView.setBackgroundColor(Color.WHITE);
            } else {
                //未選中的顔色 就設成了 白色
                holder.textView.setTextColor(Color.parseColor("#333333"));
                holder.textView.setBackgroundColor(Color.parseColor("#F5F5F5"));
            }
        if (onRecyclerViewItemClickListener != null) {
            //點擊事件
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**
                     * 拿到上面暴露的接口  的點擊方法  裏面的值和點擊事件的position  相互賦值  保持一致
                     * 算了，越説越亂，自己去理解吧
                     */
                    onRecyclerViewItemClickListener.onClick(position,cate.get(position).getCate_id());
                }
            });
            //長按事件
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });
            //
        }
        //




}

    @Override
    public int getItemCount() {
        return cate.size() ;
    }
    //点击事件，接口回调
    public void setSetOnClickItem(HomePageNearbyAdapter.setOnClickItem item){
        setOnClickItem = item;
    }
    public interface setOnClickItem{
        void onGreat(int id);
    }
    class holder extends RecyclerView.ViewHolder {
        TextView textView ;
        RelativeLayout relativeLayout ;
        public holder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.home_goods_lef_naem);
            relativeLayout = itemView.findViewById(R.id.home_goods_lef_RelativeLayout);
        }
    }
}
