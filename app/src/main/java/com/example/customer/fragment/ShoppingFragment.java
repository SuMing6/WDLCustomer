package com.example.customer.fragment;

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
import android.widget.Toast;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.activity.PasswordJiami;
import com.example.customer.adapter.ShoppingDataAdapter;
import com.example.customer.bean.ShoppingBean;
import com.example.customer.bean.ShoppingDelBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;
import com.example.customer.view.homegoods.HomeGoodsSubmissionActivity;
import com.example.customer.view.homepage.HomepageGoodsActivity;

import java.util.ArrayList;
import java.util.List;

public class ShoppingFragment extends Fragment implements MyContract.MyView.ShoppingFragment {

    List<ShoppingBean.DataBean> dataBeans = new ArrayList<>();
    private View view;
    private ShoppingDataAdapter dataAdapter;
    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shopping, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //数据
        data();
    }

    private void data() {
        RecyclerView recyclerView = view.findViewById(R.id.shopping_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        String s = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(s);
        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
        myPresenter.PShoppingFragment(MainActivity.user_id,passwordjiami);

        dataAdapter = new ShoppingDataAdapter(getActivity(),dataBeans);
        recyclerView.setAdapter(dataAdapter);
        dataAdapter.setSetOnClickItem(new ShoppingDataAdapter.setOnClickItem() {
            @Override
            public void onGreat(int money) {
                Intent intent = new Intent(getContext(), HomeGoodsSubmissionActivity.class);
                intent.putExtra("id",money);
                startActivity(intent);
            }
        });
        dataAdapter.setSetOnClickItemdel(new ShoppingDataAdapter.setOnClickItemdel() {
            @Override
            public void onGreat(int money) {
                String s = MainActivity.token;
                String passwjiemi = PasswordJiami.passwjiemi(s);
                String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
                myPresenter.PShoppingDel(money,MainActivity.user_id,passwordjiami);
                dataAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void ShowShoppingData(Object object) {
        ShoppingBean shoppingBean = (ShoppingBean) object;
        if (shoppingBean.getCode()==0){
            dataBeans.clear();
            dataBeans.addAll(shoppingBean.getData());
            dataAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void ShowShoppingDataDel(Object object) {
        ShoppingDelBean shoppingDelBean = (ShoppingDelBean) object;
        if (shoppingDelBean.getCode()==0){
            Toast.makeText(getContext(),shoppingDelBean.getMsg()+"",Toast.LENGTH_SHORT).show();
            dataBeans.clear();
            String s = MainActivity.token;
            String passwjiemi = PasswordJiami.passwjiemi(s);
            String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
            myPresenter.PShoppingFragment(MainActivity.user_id,passwordjiami);
            dataAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        String s = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(s);
        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
        myPresenter.PShoppingFragment(MainActivity.user_id,passwordjiami);
    }
}
