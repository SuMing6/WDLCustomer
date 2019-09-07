package com.example.customer.adapter.my;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.bean.MyTicketHbBean;
import com.example.customer.bean.MyTicketKqBean;

import java.util.List;

public class MyTicketKqAdapter extends RecyclerView.Adapter<MyTicketKqAdapter.holder> {

    private final Context context;
    private final List<MyTicketKqBean.DataBean> dataBeans;

    MyTicketKqAdapter.setOnClickItem setOnClickItem;

    public MyTicketKqAdapter(Context context, List<MyTicketKqBean.DataBean> dataBeans) {
        this.context = context ;
        this.dataBeans = dataBeans ;
    }

    @NonNull
    @Override
    public MyTicketKqAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_ticket_kq, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTicketKqAdapter.holder holder, final int position) {
        holder.name.setText(dataBeans.get(position).getTitle());
        holder.time.setText(dataBeans.get(position).getTime_end());
        holder.money.setText(dataBeans.get(position).getReduce());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnClickItem.onGreat(dataBeans.get(position).getShop_id());
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }


    //点击事件，接口回调
    public void setSetOnClickItem(MyTicketKqAdapter.setOnClickItem item){
        setOnClickItem = item;
    }
    public interface setOnClickItem{
        void onGreat(int money);
    }

    class holder extends RecyclerView.ViewHolder {
        TextView name,time,money;
        Button button;
        public holder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.adapter_ticket_kq_name);
            time = itemView.findViewById(R.id.adapter_ticket_kq_time);
            money = itemView.findViewById(R.id.adapter_ticket_kq_money);
            button = itemView.findViewById(R.id.adapter_ticket_kq_button);
        }
    }
}
