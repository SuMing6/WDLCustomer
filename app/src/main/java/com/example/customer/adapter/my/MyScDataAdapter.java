package com.example.customer.adapter.my;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.adapter.SubmissionTimeRightAdapter;
import com.example.customer.bean.MyScDataBean;
import com.example.customer.view.my.MyScActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyScDataAdapter extends RecyclerView.Adapter<MyScDataAdapter.holder> {

    private final Context context;
    private final List<MyScDataBean.DataBean> dataBeans;

    MyScDataAdapter.setOnClickItem setOnClickItem;

    public MyScDataAdapter(Context context, List<MyScDataBean.DataBean> dataBeans) {
        this.context = context ;
        this.dataBeans = dataBeans ;
    }

    @NonNull
    @Override
    public MyScDataAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_my_sc_data, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyScDataAdapter.holder holder, final int position) {
        if (dataBeans!=null){
            holder.simpleDraweeView.setImageURI(dataBeans.get(position).getShop().getShop_logo());
            holder.name.setText(dataBeans.get(position).getShop().getShop_name()+"");
            holder.money.setText("￥"+dataBeans.get(position).getDistance().getRice());
            holder.qianmi.setText(dataBeans.get(position).getDistance().getDistance());
            holder.shouchu.setText("已售"+dataBeans.get(position).getShop().getVolume());

            if (MyScActivity.sc){
                holder.radioButton.setVisibility(View.VISIBLE);
            }else {
                holder.radioButton.setVisibility(View.GONE);
                holder.radioButton.setChecked(false);
            }

            holder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    setOnClickItem.onGreat(dataBeans.get(position).getMid(),position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (dataBeans!=null){
            return dataBeans.size();
        }
        return 0;
    }

    //点击事件，接口回调
    public void setSetOnClickItem(MyScDataAdapter.setOnClickItem item){
        setOnClickItem = item;
    }
    public interface setOnClickItem{
        void onGreat(int mid,int position);
    }

    class holder extends RecyclerView.ViewHolder {
        TextView name , money , qianmi , shouchu ;
        SimpleDraweeView simpleDraweeView ;
        RadioButton radioButton ;
        public holder(View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.adapter_my_sc_data_SimpleDraweeView);
            name = itemView.findViewById(R.id.adapter_my_sc_data_name);
            money = itemView.findViewById(R.id.adapter_my_sc_data_money);
            qianmi = itemView.findViewById(R.id.adapter_my_sc_data_qianmi);
            shouchu = itemView.findViewById(R.id.adapter_my_sc_data_shouchu);
            radioButton = itemView.findViewById(R.id.adapter_my_sc_data_rb);
        }
    }
}
