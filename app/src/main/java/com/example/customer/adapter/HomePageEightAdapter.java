package com.example.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.bean.EightBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class HomePageEightAdapter extends RecyclerView.Adapter<HomePageEightAdapter.holder> {
    private final Context context;
    private final List<EightBean.DataBean> eightBean;

    public HomePageEightAdapter(Context context, List<EightBean.DataBean> eightBean) {
        this.context = context ;
        this.eightBean = eightBean ;
    }

    @NonNull
    @Override
    public HomePageEightAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_homepage_eight, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomePageEightAdapter.holder holder, int position) {
        holder.simpleDraweeView.setImageURI(eightBean.get(position).getIcon());
        holder.textView.setText(eightBean.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return eightBean.size();
    }

    class holder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView ;
        TextView textView ;
        public holder(View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.adapter_homepage_eight);
            textView = itemView.findViewById(R.id.adapter_homepage_name);
        }
    }
}
