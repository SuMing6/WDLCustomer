package com.example.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.bean.DianDanBean;
import com.example.customer.view.homepage.HomePageDingDan;

import java.util.List;

public class HomeDianDanAdapter extends RecyclerView.Adapter<HomeDianDanAdapter.holder> {

    private final Context context;
    private final List<DianDanBean.DataBean> dataBeans;

    public HomeDianDanAdapter(Context context, List<DianDanBean.DataBean> dataBeans) {
        this.context = context ;
        this.dataBeans = dataBeans ;
    }

    @NonNull
    @Override
    public HomeDianDanAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_home_diandan, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeDianDanAdapter.holder holder, int position) {
        holder.name.setText(dataBeans.get(position).getTitle());
        holder.qishou.setText(dataBeans.get(position).getMsg());
        holder.time.setText(dataBeans.get(position).getAdd_time());
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    class holder extends RecyclerView.ViewHolder {
        TextView name , time , qishou ;
        public holder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.adapter_home_diandan_name);
            time = itemView.findViewById(R.id.adapter_home_diandan_time);
            qishou = itemView.findViewById(R.id.adapter_home_diandan_qishou);
        }
    }
}
