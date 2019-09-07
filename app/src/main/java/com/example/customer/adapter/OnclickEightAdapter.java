package com.example.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.bean.HomepageOnclickEightBean;
import com.example.customer.view.homepage.HomePageOnclickEightActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class OnclickEightAdapter extends RecyclerView.Adapter<OnclickEightAdapter.holder> {

    private final Context context;
    private final List<HomepageOnclickEightBean.DataBean.ListBean> listBeans;

    public OnclickEightAdapter(Context context, List<HomepageOnclickEightBean.DataBean.ListBean> listBeans) {
        this.context = context ;
        this.listBeans = listBeans ;
    }

    @NonNull
    @Override
    public OnclickEightAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_home_onclick_eight, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnclickEightAdapter.holder holder, int position) {
        holder.simpleDraweeView.setImageURI(listBeans.get(position).getShop_logo());
        holder.name.setText(listBeans.get(position).getShop_name());
        holder.pingfen.setText(listBeans.get(position).getScore()+"");
        holder.shouchu.setText("已售出"+listBeans.get(position).getVolume());
        holder.qianmi.setText(listBeans.get(position).getDistance().getDistance());
        holder.time.setText(listBeans.get(position).getService_time()+"分钟内");

    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    class holder extends RecyclerView.ViewHolder {
        TextView name,pingfen,shouchu,qianmi,time ;
        SimpleDraweeView simpleDraweeView ;
        public holder(View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.adapter_home_onclick_eight_SimpleDraweeView);
            name = itemView.findViewById(R.id.adapter_home_onclick_eight_name);
            pingfen = itemView.findViewById(R.id.adapter_home_onclick_eight_pingfen);
            shouchu = itemView.findViewById(R.id.adapter_home_onclick_eight_shouchu);
            qianmi = itemView.findViewById(R.id.adapter_home_onclick_eight_qianmi);
            time = itemView.findViewById(R.id.adapter_home_onclick_eight_time);

        }
    }
}
