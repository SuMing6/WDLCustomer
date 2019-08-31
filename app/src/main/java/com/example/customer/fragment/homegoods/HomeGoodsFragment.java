package com.example.customer.fragment.homegoods;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.customer.R;
import com.example.customer.adapter.HomeGoodsLeftAdapter;
import com.example.customer.adapter.HomeGoodsRightAdapter;
import com.example.customer.bean.HomeGoodsBean;
import com.example.customer.bean.HomeGoodsListBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;
import com.example.customer.view.homepage.HomepageGoodsActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeGoodsFragment extends Fragment implements MyContract.MyView.HomeGoodsFragment {

    private View view;
    private HomeGoodsBean homeGoodsBean;
    List<HomeGoodsBean.DataBean.CateBean> cateBeans = new ArrayList<>();
    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);
    private HomeGoodsLeftAdapter leftAdapter;
    private HomeGoodsRightAdapter rightAdapter;
    private HomeGoodsListBean goodsListBean;
    List<HomeGoodsListBean.DataBean> dataBeans = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_goods, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myPresenter.PHomepageGoodsF(HomepageGoodsActivity.id);
        //Log.e("啊大大",""+HomepageGoodsActivity.id);
        //数据展示
        left();
        right();

    }

    private void right() {
        RecyclerView right = view.findViewById(R.id.fragment_home_goods_right);
        right.setLayoutManager(new LinearLayoutManager(getContext()));
        myPresenter.PHomepageGoodsL(HomepageGoodsActivity.id,1);

        rightAdapter = new HomeGoodsRightAdapter(getActivity(),dataBeans);
        right.setAdapter(rightAdapter);

    }

    private void left() {
        RecyclerView left = view.findViewById(R.id.fragment_home_goods_left);
        left.setLayoutManager(new LinearLayoutManager(getContext()));

        leftAdapter = new HomeGoodsLeftAdapter(getActivity(),cateBeans);
        left.setAdapter(leftAdapter);
        leftAdapter.setOnRecyclerViewItemClickListener(new HomeGoodsLeftAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position,int cate_id) {
                leftAdapter.setThisPosition(position);
                leftAdapter.notifyDataSetChanged();
                //Toast.makeText(getContext(), "點擊了" + cate_id, Toast.LENGTH_SHORT).show();

                myPresenter.PHomepageGoodsL(HomepageGoodsActivity.id,cate_id);
                dataBeans.clear();
                rightAdapter.notifyDataSetChanged();
            }
            @Override
            public void onLongClick(int position) {

            }
        });
    }

    @Override
    public void ShowHomePageGoodsF(Object object) {
        if (object!=null){
            homeGoodsBean = (HomeGoodsBean) object;

            //Log.e("啊大大",""+homeGoodsBean.getData().getCate());
            if (homeGoodsBean.getData().getCate()!=null){
                cateBeans.addAll(homeGoodsBean.getData().getCate());
            }
            leftAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void ShowHomePageGoodsL(Object object) {
        if (object!=null){
            HomeGoodsListBean goodsListBean = (HomeGoodsListBean) object;

            Log.e("啊大大",""+goodsListBean.getData());
            if (goodsListBean.getCode() == 0){
                dataBeans.addAll(goodsListBean.getData());
                rightAdapter.notifyDataSetChanged();

            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        cateBeans.clear();
        dataBeans.clear();
    }
}
