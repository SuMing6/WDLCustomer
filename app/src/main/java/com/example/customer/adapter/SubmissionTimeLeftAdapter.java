package com.example.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.bean.SubmissionTimeBean;

import java.util.List;

public class SubmissionTimeLeftAdapter extends RecyclerView.Adapter<SubmissionTimeLeftAdapter.holder> {

    private final Context context;
    private final List<SubmissionTimeBean.DataBean> dataBeans;

    SubmissionTimeLeftAdapter.setOnClickItem setOnClickItem;

    public SubmissionTimeLeftAdapter(Context context, List<SubmissionTimeBean.DataBean> dataBeans) {
        this.context = context ;
        this.dataBeans = dataBeans ;
    }

    @NonNull
    @Override
    public SubmissionTimeLeftAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_submission_time_left, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubmissionTimeLeftAdapter.holder holder, final int position) {
        holder.textView.setText(dataBeans.get(position).getText());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            setOnClickItem.onGreat(dataBeans.get(position).getTime());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }
    //点击事件，接口回调
    public void setSetOnClickItem(SubmissionTimeLeftAdapter.setOnClickItem item){
        setOnClickItem = item;
    }
    public interface setOnClickItem{
        void onGreat(List<SubmissionTimeBean.DataBean.TimeBean> money);
    }

    class holder extends RecyclerView.ViewHolder {
        TextView textView ;
        public holder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.adapter_subminssion_left_name);
        }
    }
}
