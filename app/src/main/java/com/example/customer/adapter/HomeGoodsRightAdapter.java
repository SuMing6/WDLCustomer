package com.example.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.bean.HomeGoodsListBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class HomeGoodsRightAdapter extends RecyclerView.Adapter<HomeGoodsRightAdapter.holder> {
    private final Context context;
    private final List<HomeGoodsListBean.DataBean> dataBeans;

    public HomeGoodsRightAdapter(Context context, List<HomeGoodsListBean.DataBean> dataBeans) {
        this.context = context ;
        this.dataBeans = dataBeans ;
    }

    @NonNull
    @Override
    public HomeGoodsRightAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_home_goods_right, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeGoodsRightAdapter.holder holder, int position) {
        holder.simpleDraweeView.setImageURI(dataBeans.get(position).getImg());
        holder.name.setText(dataBeans.get(position).getTitle()
        );
        for (int i = 0; i <dataBeans.get(position).getSpec().size() ; i++) {
            holder.shou.setText(dataBeans.get(position).getSpec().get(i).getSpec_norms());
            holder.money.setText(dataBeans.get(position).getSpec().get(i).getSpec_money());
        }
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    class holder extends RecyclerView.ViewHolder {

        TextView name , shou , money;
        SimpleDraweeView simpleDraweeView ;
        public holder(View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.adapter_home_goods_right_SimpleDraweeView);
            name = itemView.findViewById(R.id.adapter_home_goods_right_name);
            shou = itemView.findViewById(R.id.adapter_home_goods_right_shou);
            money = itemView.findViewById(R.id.adapter_home_goods_right_money);
        }
    }

}
