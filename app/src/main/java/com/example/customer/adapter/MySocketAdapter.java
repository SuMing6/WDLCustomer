package com.example.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.bean.MySocketBean;
import com.example.customer.view.homepage.HomePageMessageCustomer;

import java.util.List;

public class MySocketAdapter extends RecyclerView.Adapter<MySocketAdapter.holder> {

    private final Context context;
    private final List<MySocketBean.DataBean> dataBeans;

    public MySocketAdapter(Context context, List<MySocketBean.DataBean> dataBeans) {
        this.context = context ;
        this.dataBeans = dataBeans ;
    }

    @NonNull
    @Override
    public MySocketAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.a_socket_one, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MySocketAdapter.holder holder, int position) {
        if (dataBeans.get(position).getUser_id() == 19491001){
        holder.me.setVisibility(View.GONE);
        holder.nr.setText(dataBeans.get(position).getContent());
        holder.tv_name.setText(dataBeans.get(position).getUser_id()+"");
        holder.tv_time.setText(dataBeans.get(position).getAdd_time());
        }else {
            holder.kf.setVisibility(View.GONE);
            holder.nr2.setText(dataBeans.get(position).getContent());
            holder.tv_name2.setText(dataBeans.get(position).getUser_id()+"");
            holder.tv_time2.setText(dataBeans.get(position).getAdd_time());
        }
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    class holder extends RecyclerView.ViewHolder {
        LinearLayout me,kf ;
        TextView nr , nr2 ,tv_name , tv_name2 , tv_time ,tv_time2 ;
        public holder(View itemView) {
            super(itemView);
            me = itemView.findViewById(R.id.Socket_me);
            kf = itemView.findViewById(R.id.Socket_e);
            nr = itemView.findViewById(R.id.nr);
            nr2 = itemView.findViewById(R.id.nr2);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_name2 = itemView.findViewById(R.id.tv_name2);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_time2 = itemView.findViewById(R.id.tv_time2);

        }
    }
}
