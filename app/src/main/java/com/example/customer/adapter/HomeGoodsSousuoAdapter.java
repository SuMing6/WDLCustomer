package com.example.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.bean.HomePageGoodSousuoBean;
import com.example.customer.view.homegoods.HomepageGoodssousuoActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class HomeGoodsSousuoAdapter extends RecyclerView.Adapter<HomeGoodsSousuoAdapter.holder> {

    private final Context context;
    private final List<HomePageGoodSousuoBean.DataBean> dataBeans;

    HomeGoodsSousuoAdapter.setOnClickItem setOnClickItem;

    HomeGoodsSousuoAdapter.setOnClickIteme setOnCarClickIteme;

    public HomeGoodsSousuoAdapter(Context context, List<HomePageGoodSousuoBean.DataBean> dataBeans) {
        this.context = context ;
        this.dataBeans = dataBeans ;
    }

    @NonNull
    @Override
    public HomeGoodsSousuoAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_home_goods_sousuo, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeGoodsSousuoAdapter.holder holder, final int position) {
        holder.name.setText(dataBeans.get(position).getTitle());
        holder.shou.setText(dataBeans.get(position).getSpec().get(position).getSpec_norms());
        holder.money.setText("￥"+dataBeans.get(position).getSpec().get(position).getSpec_money());

        if (dataBeans.get(position).getSpec()!=null){
            holder.okx.setVisibility(View.VISIBLE);
            holder.okx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setOnClickItem.onGreat(position,dataBeans.get(position).getId());
                }
            });

        }else {
            holder.gwcar.setVisibility(View.VISIBLE);
            holder.gwcar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setOnCarClickIteme.onGreat(position,dataBeans.get(position).getId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    //点击事件，接口回调
    public void setSetOnClickItem(HomeGoodsSousuoAdapter.setOnClickItem item){
        setOnClickItem = item;
    }
    public interface setOnClickItem{
        void onGreat(int id,int gid);
    }
    //点击事件，接口回调
    public void setSetOnCArClickItem(HomeGoodsSousuoAdapter.setOnClickIteme item){
        setOnCarClickIteme = item;
    }
    public interface setOnClickIteme{
        void onGreat(int id,int gid);
    }

    class holder extends RecyclerView.ViewHolder {

        SimpleDraweeView simpleDraweeView ;
        TextView name ,shou , money , okx , gwcar;

        public holder(View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.adapter_home_goods_sousuo_SimpleDraweeView);
            name = itemView.findViewById(R.id.adapter_home_goods_sousuo_name);
            shou = itemView.findViewById(R.id.adapter_home_goods_sousuo_shou);
            money = itemView.findViewById(R.id.adapter_home_goods_sousuo_money);
            okx = itemView.findViewById(R.id.adapter_home_goods_sousuo_okx);
            gwcar = itemView.findViewById(R.id.adapter_home_goods_sousuo_gwcar);
        }
    }
}
