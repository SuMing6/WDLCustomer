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
import com.example.customer.bean.HomeSouSuoBean;
import com.example.customer.bean.SubmissionTimeBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class HomeSousuoAdapter extends RecyclerView.Adapter<HomeSousuoAdapter.holder> {

    private final Context context;
    private final List<HomeSouSuoBean.DataBean> dataBeans;

    HomeSousuoAdapter.setOnClickItem setOnClickItem;

    public HomeSousuoAdapter(Context context, List<HomeSouSuoBean.DataBean> dataBeans) {
        this.context = context ;
        this.dataBeans = dataBeans ;
    }

    @NonNull
    @Override
    public HomeSousuoAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adaptetr_home_sousuo, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeSousuoAdapter.holder holder, final int position) {
        holder.simpleDraweeView.setImageURI(dataBeans.get(position).getShop_logo());
        holder.name.setText(dataBeans.get(position).getShop_name());
        holder.distance.setText(dataBeans.get(position).getDistance().getDistance());
        holder.score.setText(dataBeans.get(position).getScore()+"");
        holder.volume.setText(dataBeans.get(position).getVolume()+"");
        holder.rise_give.setText(dataBeans.get(position).getRise_give());
        holder.match_give.setText(dataBeans.get(position).getMatch_give());
        holder.time.setText(dataBeans.get(position).getService_time()+"");

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnClickItem.onGreat(dataBeans.get(position).getId());
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    //点击事件，接口回调
    public void setSetOnClickItem(HomeSousuoAdapter.setOnClickItem item){
        setOnClickItem = item;
    }
    public interface setOnClickItem{
        void onGreat(int money);
    }

    class holder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView ;
        TextView name , distance , score , volume , rise_give , match_give , time ;
        LinearLayout linearLayout ;
        public holder(View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.homepage_sousuo_SimpleDraweeView);
            name = itemView.findViewById(R.id.homepage_sousuo_name);
            distance = itemView.findViewById(R.id.homepage_sousuo_distance);
            score = itemView.findViewById(R.id.homepage_sousuo_score);
            volume = itemView.findViewById(R.id.homepage_sousuo_volume);
            rise_give = itemView.findViewById(R.id.homepage_sousuo_rise_give);
            match_give = itemView.findViewById(R.id.homepage_sousuo_match_give);
            time = itemView.findViewById(R.id.homepage_sousuo_service_time);
            linearLayout = itemView.findViewById(R.id.homepage_sousuo_LinearLayout);
        }
    }
}
