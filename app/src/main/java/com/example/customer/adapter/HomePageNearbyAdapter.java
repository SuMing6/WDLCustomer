package com.example.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.bean.NearbyBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class HomePageNearbyAdapter extends RecyclerView.Adapter<HomePageNearbyAdapter.holder> {


    private final Context context;
    private final List<NearbyBean.DataBean> nearbyBean;
    HomePageNearbyAdapter.setOnClickItem setOnClickItem;

    public HomePageNearbyAdapter(Context context, List<NearbyBean.DataBean> nearbyBean) {
        this.context = context ;
        this.nearbyBean = nearbyBean ;
    }

    @NonNull
    @Override
    public HomePageNearbyAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_homepagefragmen_nearby, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomePageNearbyAdapter.holder holder, final int position) {
        holder.simpleDraweeView.setImageURI(nearbyBean.get(position).getShop_logo());
        holder.shop_name.setText(nearbyBean.get(position).getShop_name());
        holder.distance.setText(nearbyBean.get(position).getDistance().getDistance());
        holder.score.setText(nearbyBean.get(position).getScore()+"");
        holder.volume.setText("已售出"+nearbyBean.get(position).getVolume()+"");
        holder.rise_give.setText("起送"+nearbyBean.get(position).getRise_give()+"");
        holder.match_give.setText("配送"+nearbyBean.get(position).getMatch_give());
        holder.service_time.setText(nearbyBean.get(position).getService_time()+"分钟内");

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnClickItem.onGreat(nearbyBean.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return nearbyBean.size();
    }

    //点击事件，接口回调
    public void setSetOnClickItem(HomePageNearbyAdapter.setOnClickItem item){
        setOnClickItem = item;
    }
    public interface setOnClickItem{
        void onGreat(int id);
    }

    class holder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView ;
        TextView shop_name,distance,score,volume,rise_give,match_give,service_time;
        LinearLayout linearLayout ;
        public holder(View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.homepage_nearby_SimpleDraweeView);
            shop_name = itemView.findViewById(R.id.homepage_nearby_name);
            distance = itemView.findViewById(R.id.homepage_nearby_distance);
            score = itemView.findViewById(R.id.homepage_nearby_score);
            volume = itemView.findViewById(R.id.homepage_nearby_volume);
            rise_give = itemView.findViewById(R.id.homepage_nearby_rise_give);
            match_give = itemView.findViewById(R.id.homepage_nearby_match_give);
            service_time = itemView.findViewById(R.id.homepage_nearby_service_time);
            linearLayout = itemView.findViewById(R.id.homepage_nearby_LinearLayout);

        }
    }
}
