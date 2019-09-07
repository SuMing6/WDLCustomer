package com.example.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.bean.SubmissionHBBean;
import com.example.customer.view.homegoods.SubmissionHBActivity;

import java.util.List;

public class SubmissionHBAdapter extends RecyclerView.Adapter<SubmissionHBAdapter.holder> {

    private final Context context;
    private final List<SubmissionHBBean.DataBean> dataBeans;

    SubmissionHBAdapter.setOnClickItem setOnClickItem;

    public SubmissionHBAdapter(Context context, List<SubmissionHBBean.DataBean> dataBeans) {
        this.context = context ;
        this.dataBeans = dataBeans ;
    }

    @NonNull
    @Override
    public SubmissionHBAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_submission_hb_, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubmissionHBAdapter.holder holder, final int position) {
        holder.titl.setText(dataBeans.get(position).getTime_end());
        holder.money.setText(dataBeans.get(position).getReduce());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnClickItem.onGreat(dataBeans.get(position).getId(),position,dataBeans.get(position).getReduce());
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    //点击事件，接口回调
    public void setSetOnClickItem(SubmissionHBAdapter.setOnClickItem item){
        setOnClickItem = item;
    }
    public interface setOnClickItem{
        void onGreat(int id,int position,String money);
    }

    class holder extends RecyclerView.ViewHolder {
        TextView titl,money ;
        LinearLayout linearLayout ;
        public holder(View itemView) {
            super(itemView);
            titl = itemView.findViewById(R.id.activity_submission_hb_text);
            money = itemView.findViewById(R.id.activity_submission_hb_money);
            linearLayout = itemView.findViewById(R.id.activity_submission_hb_LinearLayout);
        }
    }
}
