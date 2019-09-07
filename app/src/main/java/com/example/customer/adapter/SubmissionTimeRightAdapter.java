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
import com.example.customer.view.homegoods.HomeGoodsSubmissionActivity;

import java.util.List;

public class SubmissionTimeRightAdapter extends RecyclerView.Adapter<SubmissionTimeRightAdapter.holder> {

    private final Context context;
    private final List<SubmissionTimeBean.DataBean.TimeBean> timeBeans;

    SubmissionTimeRightAdapter.setOnClickItem setOnClickItem;

    public SubmissionTimeRightAdapter(Context context, List<SubmissionTimeBean.DataBean.TimeBean> money) {
        this.context = context ;
        timeBeans = money ;
    }

    @NonNull
    @Override
    public SubmissionTimeRightAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_submission_time_right, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubmissionTimeRightAdapter.holder holder, final int position) {
        holder.textView.setText(timeBeans.get(position).getTime());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnClickItem.onGreat(timeBeans.get(position).getId(),timeBeans.get(position).getTime());
            }
        });
    }

    @Override
    public int getItemCount() {
        return timeBeans.size();
    }
    //点击事件，接口回调
    public void setSetOnClickItem(SubmissionTimeRightAdapter.setOnClickItem item){
        setOnClickItem = item;
    }
    public interface setOnClickItem{
        void onGreat(int timeid,String tt);
    }

    class holder extends RecyclerView.ViewHolder {
        TextView textView ;
        public holder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.adapter_subminssion_right_name);
        }
    }
}
