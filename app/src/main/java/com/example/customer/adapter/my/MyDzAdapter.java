package com.example.customer.adapter.my;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.bean.MyDzBean;

import java.util.List;

public class MyDzAdapter extends RecyclerView.Adapter<MyDzAdapter.holder> {

    private final Context context;
    private final List<MyDzBean.DataBean> dataBeans;
    private int index = -1;

    MyDzAdapter.setOnClickItem setOnClickItem;

    public MyDzAdapter(Context context, List<MyDzBean.DataBean> dataBeans) {
        this.context = context ;
        this.dataBeans = dataBeans ;

    }

    @NonNull
    @Override
    public MyDzAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_my_dz, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyDzAdapter.holder holder, final int position) {
        holder.name.setText(dataBeans.get(position).getName());
        holder.phone.setText(dataBeans.get(position).getPhone());
        holder.xx.setText(dataBeans.get(position).getAddress());
        if (dataBeans.get(position).getStatus()==1){
            holder.button.setChecked(true);
        }

        holder.button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //Toast.makeText(context,"你选择的选项是"+dataBeans.get(position).getId(),Toast.LENGTH_SHORT).show();
                    index = position;
                    //setOnClickItem.onGreat(dataBeans.get(position).getId());
                    notifyDataSetChanged();
                }
            }
        });

        if(dataBeans.get(position).getStatus()==1 || index==position){
            holder.button.setChecked(true);
        } else {
            holder.button.setChecked(false);
        }
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnClickItem.onGreat(dataBeans.get(position).getId());

            }
        });
        /*if (dataBeans.get(position).getStatus()==1){
            holder.button.setChecked(true);
        }*/

    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    //点击事件，接口回调
    public void setSetOnClickItem(MyDzAdapter.setOnClickItem item){
        setOnClickItem = item;
    }
    public interface setOnClickItem{
        void onGreat(int eid);
    }

    class holder extends RecyclerView.ViewHolder {
        TextView name,phone,xx ,bj ;
        LinearLayout linearLayout ,linearLayout1;
        RadioButton button;
        public holder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.adapter_my_dz_name);
            phone = itemView.findViewById(R.id.adapter_my_dz_phone);
            xx = itemView.findViewById(R.id.adapter_my_dz_xx);
            linearLayout = itemView.findViewById(R.id.adapter_my_dz_LinearLayout);
            button = itemView.findViewById(R.id.adapter_my_dz_moren);
            bj = itemView.findViewById(R.id.adapter_my_dz_bj);
        }
    }
}
