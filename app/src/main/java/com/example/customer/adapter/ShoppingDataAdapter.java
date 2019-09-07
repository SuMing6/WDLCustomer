package com.example.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.bean.ShoppingBean;
import com.example.customer.bean.SubmissionTimeBean;
import com.example.customer.fragment.ShoppingFragment;

import java.util.List;

public class ShoppingDataAdapter extends RecyclerView.Adapter<ShoppingDataAdapter.holder> {

    private final Context context;
    private final List<ShoppingBean.DataBean> dataBeans;

    ShoppingDataAdapter.setOnClickItem setOnClickItem;
    ShoppingDataAdapter.setOnClickItemdel setOnClickItemdel;

    public ShoppingDataAdapter(Context context, List<ShoppingBean.DataBean> dataBeans) {
        this.context = context ;
        this.dataBeans = dataBeans ;
    }

    @NonNull
    @Override
    public ShoppingDataAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_shopping, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShoppingDataAdapter.holder holder, final int position) {
        if (dataBeans!=null){
            holder.textView.setText(dataBeans.get(position).getShop());

            holder.recyclerView.setLayoutManager(new GridLayoutManager(context,4));

            ShoppingDataXAdapter xAdapter = new ShoppingDataXAdapter(context,dataBeans.get(position).getGood());
            holder.recyclerView.setAdapter(xAdapter);
            xAdapter.notifyDataSetChanged();


            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setOnClickItem.onGreat(dataBeans.get(position).getId());
                }
            });
            holder.recyclerView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return holder.linearLayout.onTouchEvent(motionEvent);
                }
            });
            holder.del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setOnClickItemdel.onGreat(dataBeans.get(position).getId());
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        if (dataBeans!=null){
            return dataBeans.size();
        }
        return 0;
    }

    //点击事件，接口回调
    public void setSetOnClickItem(ShoppingDataAdapter.setOnClickItem item){
        setOnClickItem = item;
    }
    public interface setOnClickItem{
        void onGreat(int money);
    }

    //点击事件，接口回调
    public void setSetOnClickItemdel(ShoppingDataAdapter.setOnClickItemdel item){
        setOnClickItemdel = item;
    }
    public interface setOnClickItemdel{
        void onGreat(int money);
    }


    class holder extends RecyclerView.ViewHolder {
        TextView textView ;
        RecyclerView recyclerView ;
        LinearLayout linearLayout ,del;
        public holder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.adapter_shopping_name);
            recyclerView = itemView.findViewById(R.id.adapter_shopping_RecyclerView);
            linearLayout = itemView.findViewById(R.id.adapter_shopping_LinearLayout);
            del = itemView.findViewById(R.id.adapter_shopping_del);
        }
    }
}
