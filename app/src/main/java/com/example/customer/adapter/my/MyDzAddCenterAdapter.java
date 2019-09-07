package com.example.customer.adapter.my;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.bean.SanBean;

import java.util.List;

public class MyDzAddCenterAdapter extends RecyclerView.Adapter<MyDzAddCenterAdapter.holder> {

    private final Context context;
    private final List<SanBean.DataBean.ChildrenBeanX> dataBeans;

    MyDzAddCenterAdapter.setOnClickItem setOnClickItem;

    public MyDzAddCenterAdapter(Context context, List<SanBean.DataBean.ChildrenBeanX> dataBeans) {
        this.context = context ;
        this.dataBeans = dataBeans ;
    }

    @NonNull
    @Override
    public MyDzAddCenterAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_my_dz_add_center, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDzAddCenterAdapter.holder holder, final int position) {
        holder.textView.setText(dataBeans.get(position).getText());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnClickItem.onGreat(dataBeans.get(position).getChildren(),dataBeans.get(position).getValue(),dataBeans.get(position).getText());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }


    //点击事件，接口回调
    public void setSetOnClickItem(MyDzAddCenterAdapter.setOnClickItem item){
        setOnClickItem = item;
    }
    public interface setOnClickItem{
        void onGreat(List<SanBean.DataBean.ChildrenBeanX.ChildrenBean> data, int value, String text);
    }

    class holder extends RecyclerView.ViewHolder {
        TextView textView ;
        public holder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.adapter_my_dz_add_center_name);
        }
    }
}
