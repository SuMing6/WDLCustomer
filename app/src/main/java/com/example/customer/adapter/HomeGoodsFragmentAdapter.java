package com.example.customer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.bean.HomeGoodsListBean;

import java.util.List;

public class HomeGoodsFragmentAdapter extends RecyclerView.Adapter<HomeGoodsFragmentAdapter.holder> {
    private final Context context;
    private final List<HomeGoodsListBean.DataBean.SpecBean> spec;

    HomeGoodsFragmentAdapter.setOnClickItem setOnClickItem;

    public HomeGoodsFragmentAdapter(Context context, List<HomeGoodsListBean.DataBean.SpecBean> spec) {
        this.context = context ;
        this.spec = spec ;

    }

    @NonNull
    @Override
    public HomeGoodsFragmentAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_homegoods_fragment, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeGoodsFragmentAdapter.holder holder, final int position) {
        holder.textView.setText(spec.get(position).getSpec_norms());
        holder.textView.setChecked(false);
        holder.textView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setOnClickItem.onGreat(spec.get(position).getId(),spec.get(position).getSpec_money());
            }
        });
    }

    @Override
    public int getItemCount() {
        return spec.size();
    }

    //点击事件，接口回调
    public void setSetOnClickItem(HomeGoodsFragmentAdapter.setOnClickItem item){
        setOnClickItem = item;
    }
    public interface setOnClickItem{
        void onGreat(int id,String e);
    }


    class holder extends RecyclerView.ViewHolder {
        CheckBox textView ;

        public holder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.adapter_homegoods_fragmenrt_t);
        }
    }
}
