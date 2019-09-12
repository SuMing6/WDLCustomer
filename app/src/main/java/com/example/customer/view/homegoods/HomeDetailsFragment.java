package com.example.customer.view.homegoods;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.bean.DetailsBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;

public class HomeDetailsFragment extends Fragment implements MyContract.MyView.HomeDetailsFragment {

    private View view;

    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);
    private TextView addss;
    private TextView phone;
    private TextView time;
    private TextView info;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_details, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //展示
        myPresenter.PHomeDetailsData(MainActivity.user_id);
        show();

    }

    private void show() {
        addss = view.findViewById(R.id.fragment_home_details_addss);
        phone = view.findViewById(R.id.fragment_home_details_phone);
        time = view.findViewById(R.id.fragment_home_details_time);
        info = view.findViewById(R.id.fragment_home_details_info);
        info = view.findViewById(R.id.fragment_home_details_info);

    }

    @Override
    public void HomeDetailsData(Object object) {
        DetailsBean detailsBean = (DetailsBean) object;
        if (detailsBean.getCode() == 0){
            addss.setText(detailsBean.getData().getShop_address());
            phone.setText(detailsBean.getData().getShop_phone());
            time.setText(detailsBean.getData().getTime_start()+"-"+detailsBean.getData().getTime_end());
            info.setText(detailsBean.getData().getShop_info());
        }
    }
}
