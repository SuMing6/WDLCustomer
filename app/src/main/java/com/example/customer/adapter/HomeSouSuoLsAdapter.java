package com.example.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.bean.HomeSousuoLsBean;

import java.util.List;

public class HomeSouSuoLsAdapter extends RecyclerView.Adapter<HomeSouSuoLsAdapter.holder> {

    private final Context context;
    private final List<HomeSousuoLsBean.ListBean> listBeans;

    public HomeSouSuoLsAdapter(Context context, List<HomeSousuoLsBean.ListBean> listBeans) {
        this.context = context ;
        this.listBeans = listBeans ;
    }

    @NonNull
    @Override
    public HomeSouSuoLsAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_home_sousuo_ls, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeSouSuoLsAdapter.holder holder, int position) {
        holder.textView.setText(listBeans.get(position).getKey());

    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    class holder extends RecyclerView.ViewHolder {

        TextView textView ;

        public holder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.adapter_home_sousuo_ls_text);
        }
    }


}
