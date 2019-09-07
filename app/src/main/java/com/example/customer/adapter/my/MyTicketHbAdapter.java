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
import com.example.customer.adapter.SubmissionTimeLeftAdapter;
import com.example.customer.bean.MyTicketHbBean;
import com.example.customer.bean.SubmissionTimeBean;

import java.util.List;

public class MyTicketHbAdapter extends RecyclerView.Adapter<MyTicketHbAdapter.holder> {

    private final Context context;
    private final List<MyTicketHbBean.DataBean> dataBeans;

    MyTicketHbAdapter.setOnClickItem setOnClickItem;


    public MyTicketHbAdapter(Context context, List<MyTicketHbBean.DataBean> dataBeans) {
        this.context = context ;
        this.dataBeans = dataBeans ;
    }

    @NonNull
    @Override
    public MyTicketHbAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_ticket_hb, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTicketHbAdapter.holder holder, final int position) {
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
    public void setSetOnClickItem(MyTicketHbAdapter.setOnClickItem item){
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
            name = itemView.findViewById(R.id.adapter_ticket_hb_name);
            time = itemView.findViewById(R.id.adapter_ticket_hb_time);
            money = itemView.findViewById(R.id.adapter_ticket_hb_money);
            button = itemView.findViewById(R.id.adapter_ticket_hb_button);
        }
    }
}
