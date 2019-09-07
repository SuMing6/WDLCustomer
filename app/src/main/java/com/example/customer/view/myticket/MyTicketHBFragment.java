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
import com.example.customer.bean.MyTicketHbBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;
import com.example.customer.view.homepage.HomepageGoodsActivity;

import java.util.ArrayList;
import java.util.List;

public class MyTicketHBFragment extends Fragment implements MyContract.MyView.MyTicketHBFragment {

    private View view;
    List<MyTicketHbBean.DataBean> dataBeans = new ArrayList<>();
    private MyTicketHbAdapter hbAdapter;
    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_ticket_hb, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        data();

    }

    private void data() {
        RecyclerView recyclerView = view.findViewById(R.id.fragment_my_ticket_hb_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        String token = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(token);
        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
        myPresenter.PMyTicketHBcData(MainActivity.user_id,passwordjiami,2);

        hbAdapter = new MyTicketHbAdapter(getActivity(),dataBeans);
        hbAdapter.setSetOnClickItem(new MyTicketHbAdapter.setOnClickItem() {
            @Override
            public void onGreat(int money) {
                Intent intent = new Intent(getContext(), HomepageGoodsActivity.class);
                intent.putExtra("id",money);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(hbAdapter);

    }

    @Override
    public void ShowMyTicketHBcData(Object object) {
        MyTicketHbBean myTicketHbBean = (MyTicketHbBean) object;
        if (myTicketHbBean.getCode()==0){
            dataBeans.clear();
            dataBeans.addAll(myTicketHbBean.getData());
            hbAdapter.notifyDataSetChanged();
        }
    }
}
