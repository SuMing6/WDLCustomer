package com.example.customer.view.myticket;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.activity.PasswordJiami;
import com.example.customer.adapter.my.MyTicketHbAdapter;
import com.example.customer.adapter.my.MyTicketKqAdapter;
import com.example.customer.bean.MyTicketKqBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;
import com.example.customer.view.homepage.HomepageGoodsActivity;

import java.util.ArrayList;
import java.util.List;

public class MyTicketKQFragment extends Fragment implements MyContract.MyView.MyTicketKQFragment {

    private View view;
    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);
    private MyTicketKqAdapter kqAdapter;
    List<MyTicketKqBean.DataBean> dataBeans = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_ticket_kq, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        data();
    }

    private void data() {
        RecyclerView recyclerView = view.findViewById(R.id.fragment_my_ticket_kq_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        String token = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(token);
        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
        myPresenter.PMyTicketKQcData(MainActivity.user_id,passwordjiami,1);

        kqAdapter = new MyTicketKqAdapter(getActivity(), dataBeans);
        kqAdapter.setSetOnClickItem(new MyTicketKqAdapter.setOnClickItem() {
            @Override
            public void onGreat(int money) {
                Intent intent = new Intent(getContext(), HomepageGoodsActivity.class);
                intent.putExtra("id",money);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(kqAdapter);

    }

    @Override
    public void ShowMyTicketKQcData(Object object) {
        MyTicketKqBean myTicketKqBean = (MyTicketKqBean) object;
        if (myTicketKqBean.getCode()==0){
            dataBeans.addAll(myTicketKqBean.getData());
            kqAdapter.notifyDataSetChanged();
        }
    }
}
