package com.example.customer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.bean.OrderBean;
import com.example.customer.bean.SubmissionTimeBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.holder> {

    private final Context context;
    private final List<OrderBean.DataBean> dataBeans;

    OrderAdapter.setOnClickItem setOnClickItem;
    OrderAdapter.setOnClickItemt setOnClickItemt;

    public OrderAdapter(Context context, List<OrderBean.DataBean> dataBeans) {
        this.context = context ;
        this.dataBeans = dataBeans ;

    }

    @NonNull
    @Override
    public OrderAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_order, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.holder holder, final int position) {
        holder.name.setText(dataBeans.get(position).getShop().getShop_name());
        holder.simpleDraweeView.setImageURI(dataBeans.get(position).getShop().getShop_logo());
        holder.time.setText("下单时间： "+dataBeans.get(position).getUser_time());
        holder.money.setText("总价： ￥"+dataBeans.get(position).getMoney_paid());
        holder.zhuangtai.setText(dataBeans.get(position).getStatus().getBtn_txt());
        //status  0 空  1重新付款  2退款  3评价


        if (dataBeans.get(position).getStatus().getStatus()==1){
            holder.btn.setText("付款");

        }
        else if (dataBeans.get(position).getStatus().getStatus()==2){
            holder.btn.setText("退款");

        }
        else if (dataBeans.get(position).getStatus().getStatus()==3){
            holder.btn.setText("评价");

        }
        else {
            holder.btn.setVisibility(View.GONE);
        }

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnClickItem.onGreat(dataBeans.get(position).getId(),dataBeans.get(position).getStatus().getStatus());
            }
        });
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnClickItemt.onGreat(dataBeans.get(position).getId());
            }
        });


       /* if (dataBeans.get(position).getUser_status()==0){

        }*/
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    //点击事件，接口回调
    public void setSetOnClickItem(OrderAdapter.setOnClickItem item){
        setOnClickItem = item;
    }
    public interface setOnClickItem{
        void onGreat(int order, int status);
    }
    //点击事件，接口回调
    public void setSetOnClickItemt(OrderAdapter.setOnClickItemt item){
        setOnClickItemt = item;
    }
    public interface setOnClickItemt{
        void onGreat(int id);
    }

    class holder extends RecyclerView.ViewHolder {
        TextView name , zhuangtai , time ,money ,btn ;
        SimpleDraweeView simpleDraweeView ;
        LinearLayout linearLayout ;
        public holder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.adapter_order_name);
            zhuangtai = itemView.findViewById(R.id.adapter_order_zhuangtai);
            time = itemView.findViewById(R.id.adapter_order_time);
            money = itemView.findViewById(R.id.adapter_order_money);
            btn = itemView.findViewById(R.id.adapter_order_btn);
            simpleDraweeView = itemView.findViewById(R.id.adapter_order_SimpleDraweeView);
            linearLayout = itemView.findViewById(R.id.adapter_order_LinearLayout);
        }
    }
}
