package com.example.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.bean.SubmissionYHBean;
import com.example.customer.view.homegoods.SubmissionYHActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class SubmissionYHAdapter extends RecyclerView.Adapter<SubmissionYHAdapter.holder> {

    private final Context context;
    private final List<SubmissionYHBean.DataBean> dataBeans;

    SubmissionHBAdapter.setOnClickItem setOnClickItem;

    public SubmissionYHAdapter(Context context, List<SubmissionYHBean.DataBean> dataBeans) {
        this.context = context ;
        this.dataBeans = dataBeans ;
    }

    @NonNull
    @Override
    public SubmissionYHAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_submission_yh_, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubmissionYHAdapter.holder holder, final int position) {
        holder.time.setText(dataBeans.get(position).getTime_end());
        holder.money.setText(dataBeans.get(position).getReduce());
        holder.naem.setText(dataBeans.get(position).getTitle());

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
        SimpleDraweeView  simpleDraweeView ;
        TextView naem , time , money ;
        LinearLayout linearLayout ;
        public holder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.activity_submission_yh_LinearLayout);
            simpleDraweeView = itemView.findViewById(R.id.activity_submission_yh_SimpleDraweeView);
            naem = itemView.findViewById(R.id.activity_submission_yh_text);
            time = itemView.findViewById(R.id.activity_submission_yh_time);
            money = itemView.findViewById(R.id.activity_submission_yh_money);
        }
    }
}
