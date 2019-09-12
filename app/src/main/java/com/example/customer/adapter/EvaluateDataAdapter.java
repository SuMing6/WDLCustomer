package com.example.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.bean.EvaluateDataBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class EvaluateDataAdapter extends RecyclerView.Adapter<EvaluateDataAdapter.holder> {

    private final Context context;
    private final List<EvaluateDataBean.DataBean.ListBean> dataBeans;

    public EvaluateDataAdapter(Context context, List<EvaluateDataBean.DataBean.ListBean> dataBeans) {
        this.context = context ;
        this.dataBeans = dataBeans ;
    }

    @NonNull
    @Override
    public EvaluateDataAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_evaluate_data, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EvaluateDataAdapter.holder holder, int position) {
        holder.name.setText(dataBeans.get(position).getUser_id());
        holder.time.setText(dataBeans.get(position).getAdd_time());
        holder.neirong.setText(dataBeans.get(position).getContent());
        //holder.simpleDraweeView.setImageURI();
        holder.ratingBar.setRating(dataBeans.get(position).getScore());
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    class holder extends RecyclerView.ViewHolder {

        TextView name , time , neirong ;
        RatingBar ratingBar ;
        SimpleDraweeView simpleDraweeView ;
        public holder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.adapter_evaluate_data_name);
            time = itemView.findViewById(R.id.adapter_evaluate_data_time);
            neirong = itemView.findViewById(R.id.adapter_evaluate_data_neirong);
            ratingBar = itemView.findViewById(R.id.adapter_evaluate_data_RatingBar);
            simpleDraweeView = itemView.findViewById(R.id.adapter_evaluate_data_SimpleDraweeView);
        }
    }
}
