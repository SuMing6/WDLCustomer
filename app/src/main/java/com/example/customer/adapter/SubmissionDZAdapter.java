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
import com.example.customer.bean.DzBean;
import com.example.customer.view.homegoods.SubmissionDZActivity;

import java.util.List;

public class SubmissionDZAdapter extends RecyclerView.Adapter<SubmissionDZAdapter.holder> {

    private final Context context;
    private final List<DzBean.DataBean> dataBeans;

    SubmissionDZAdapter.setOnClickItem setOnClickItem;

    public SubmissionDZAdapter(Context context, List<DzBean.DataBean> dataBeans) {
        this.context = context ;
        this.dataBeans = dataBeans ;

    }

    @NonNull
    @Override
    public SubmissionDZAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_submission_dz, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubmissionDZAdapter.holder holder, final int position) {
        holder.name.setText(dataBeans.get(position).getName());
        holder.phone.setText(dataBeans.get(position).getPhone());
        holder.xx.setText(dataBeans.get(position).getAddress());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnClickItem.onGreat(dataBeans.get(position).getId(),position,dataBeans.get(position).getProvince()+dataBeans.get(position).getCity()+dataBeans.get(position).getDistrict()+dataBeans.get(position).getAddress());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }
    //点击事件，接口回调
    public void setSetOnClickItem(SubmissionDZAdapter.setOnClickItem item){
        setOnClickItem = item;
    }
    public interface setOnClickItem{
        void onGreat(int eid,int position,String address);
    }

    class holder extends RecyclerView.ViewHolder {
        TextView name,phone,xx ;
        LinearLayout linearLayout ;
        public holder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.adapter_submission_dz_name);
            phone = itemView.findViewById(R.id.adapter_submission_dz_phone);
            xx = itemView.findViewById(R.id.adapter_submission_dz_xx);
            linearLayout = itemView.findViewById(R.id.adapter_submission_dz_LinearLayout);
        }
    }
}
