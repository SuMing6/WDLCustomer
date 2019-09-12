package com.example.customer.view.homegoods;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.adapter.EvaluateDataAdapter;
import com.example.customer.bean.EvaluateDataBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

public class HomeEvaluateFragment extends Fragment implements MyContract.MyView.HomeEvaluateFragment {

    private View view;
    List<EvaluateDataBean.DataBean.ListBean> dataBeans = new ArrayList<>();
    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);
    private EvaluateDataAdapter dataAdapter;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_evaluate, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myPresenter.PHomePageEvaluateData(MainActivity.user_id);
        //展示
        show();
        textView = view.findViewById(R.id.fragment_home_TextView);

    }

    private void show() {
        RecyclerView recyclerView = view.findViewById(R.id.fragment_home_evaluate);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dataAdapter = new EvaluateDataAdapter(getActivity(),dataBeans);
        recyclerView.setAdapter(dataAdapter);
    }


    @Override
    public void ShowHomePageEvaluateData(Object object) {
        EvaluateDataBean evaluateDataBean = (EvaluateDataBean) object;
        if (evaluateDataBean.getCode()==0){
            dataBeans.addAll(evaluateDataBean.getData().getList());
            textView.setText("全部（"+evaluateDataBean.getData().getCount()+"）");
            dataAdapter.notifyDataSetChanged();
        }
    }
}
