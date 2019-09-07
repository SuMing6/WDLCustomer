package com.example.customer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.activity.MyView;
import com.example.customer.activity.PasswordJiami;
import com.example.customer.adapter.OrderAdapter;
import com.example.customer.bean.OrderBean;
import com.example.customer.contract.MyContract;
import com.example.customer.data.OrderInfoBean;
import com.example.customer.presenter.MyPresenter;
import com.example.customer.view.homepage.HomepageGoodsActivity;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment implements MyContract.MyView.OrderFragment {

    private View view;
    List<OrderBean.DataBean> dataBeans = new ArrayList<>();
    private OrderAdapter orderAdapter;
    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //数据展示
        shuju();
    }

    private void shuju() {
        RecyclerView recyclerView = view.findViewById(R.id.fragment_order_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        String s = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(s);
        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
        myPresenter.POrderFragment(MainActivity.user_id,passwordjiami);

        orderAdapter = new OrderAdapter(getActivity(),dataBeans);
        recyclerView.setAdapter(orderAdapter);
        orderAdapter.setSetOnClickItem(new OrderAdapter.setOnClickItem() {
            @Override
            public void onGreat(int order, int status) {
                if (status==1){
                    //Log.e("订单页面",status+"待付款");
                    /*Intent intent = new Intent();
                    intent.putExtra("id","order");
                    startActivity(intent);*/
                }else if(status==2){
                    //Log.e("订单页面",status+"退款");
                    /*Intent intent = new Intent();
                    intent.putExtra("id","order");
                    startActivity(intent);*/
                }else if(status==3){
                    //Log.e("订单页面",status+"评价");
                    Intent intent = new Intent(getContext(), MyView.class);
                    intent.putExtra("id","order");
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void ShowOrderFragment(Object object) {
        OrderBean orderBean = (OrderBean) object;
        //Log.e("订单页面",orderBean.getCode()+"");
        if (orderBean.getCode() == 0){
            dataBeans.addAll(orderBean.getData());
            orderAdapter.notifyDataSetChanged();
        }
    }
}
