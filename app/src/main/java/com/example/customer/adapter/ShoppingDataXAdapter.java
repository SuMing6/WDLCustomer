package com.example.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.bean.ShoppingBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ShoppingDataXAdapter extends RecyclerView.Adapter<ShoppingDataXAdapter.holder> {


    private final List<ShoppingBean.DataBean.GoodBean> goodBeans;
    private final Context context;

    public ShoppingDataXAdapter(Context context, List<ShoppingBean.DataBean.GoodBean> good) {
        this.context = context ;
        goodBeans = good ;

    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_shopping_x, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.simpleDraweeView.setImageURI(goodBeans.get(position).getGood_img());
        holder.textView.setText("￥"+goodBeans.get(position).getGood_money());
    }

    @Override
    public int getItemCount() {
        return goodBeans.size();
    }

    class holder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView ;
        TextView textView ;
        public holder(View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.adapter_shopping_x_SimpleDraweeView);
            textView = itemView.findViewById(R.id.adapter_shopping_x_text);
        }
    }
}
