package com.example.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.bean.HomeCarInforBean;
import com.example.customer.view.homegoods.HomeGoodsSubmissionActivity;

import java.util.List;

public class homeCarInforAdapter extends RecyclerView.Adapter<homeCarInforAdapter.holder> {

    private final Context context;
    private final List<HomeCarInforBean.DataBean.GoodListBean> dataBean;

    public homeCarInforAdapter(Context context, List<HomeCarInforBean.DataBean.GoodListBean> data) {
        this.context = context ;
        dataBean = data ;
    }

    @NonNull
    @Override
    public homeCarInforAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_home_car_info, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull homeCarInforAdapter.holder holder, int position) {
        holder.name.setText(dataBean.get(position).getGood_title());
        holder.ge.setText(dataBean.get(position).getSpec_name());
        holder.money.setText(dataBean.get(position).getGood_money());
    }

    @Override
    public int getItemCount() {
        return dataBean.size();
    }

    class holder extends RecyclerView.ViewHolder {
        TextView name,ge ,money ;
        public holder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.adapter_home_car_info_name);
            ge = itemView.findViewById(R.id.adapter_home_car_info_ge);
            money = itemView.findViewById(R.id.adapter_home_car_info_money);
        }
    }
}
