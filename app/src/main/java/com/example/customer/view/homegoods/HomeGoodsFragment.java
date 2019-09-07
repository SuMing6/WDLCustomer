package com.example.customer.view.homegoods;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.activity.PasswordJiami;
import com.example.customer.adapter.HomeGoodsFragmentAdapter;
import com.example.customer.adapter.HomeGoodsLeftAdapter;
import com.example.customer.adapter.HomeGoodsRightAdapter;
import com.example.customer.bean.AddSoppingCarBean;
import com.example.customer.bean.HomeGoodsBean;
import com.example.customer.bean.HomeGoodsCarBean;
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
    private HomeGoodsListBean goodsListBean1;
    private TextView okx;
    private TextView gwcar;
    private PopupWindow popupWindow;
    private View inflate;
    private RecyclerView right;
    private int zs ;
    private int sid ;
    public static int gid ;
    private double mo ;
    private double aa =0;


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

        okx = view.findViewById(R.id.adapter_home_goods_right_okx);
        gwcar = view.findViewById(R.id.adapter_home_goods_right_gwcar);
        //Log.e("啊大大",""+HomepageGoodsActivity.id);
        //数据展示
        left();
        right();
        //判断是否拥有规格
        GG();
        //下面的购物车



    }

    private void GG() {
        //有规格

        inflate = LayoutInflater.from(getContext()).inflate(R.layout.popup_window_homepage_goods_right, null);
        rightAdapter.setSetOnClickItem(new HomeGoodsRightAdapter.setOnClickItem() {
            @Override
            public void onGreat(int id,int ggid) {
                //Log.e("啊大大",""+id);
                gid = ggid ;
                LinearLayout linearLayout =  view.findViewById(R.id.fragment_home_goods_LinearLayout);
                popupWindow = new PopupWindow(inflate, linearLayout.getHeight(), 1100, true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                popupWindow.setOutsideTouchable(true);
                popupWindow.setTouchable(true);
                popupWindow.showAsDropDown(right,0 ,0 );

                TextView name = inflate.findViewById(R.id.popup_window_homepage_goods_right_name);
                final TextView money = inflate.findViewById(R.id.popup_window_homepage_goods_right_money);
                RelativeLayout jian = inflate.findViewById(R.id.popup_window_homepage_goods_right_jian);
                RelativeLayout jia = inflate.findViewById(R.id.popup_window_homepage_goods_right_jia);
                final TextView sum = inflate.findViewById(R.id.popup_window_homepage_goods_right_sum);
                RecyclerView recyclerView = inflate.findViewById(R.id.popup_window_homepage_goods_right_RecyclerView);
                Button ok = inflate.findViewById(R.id.popup_window_homepage_goods_right_ok);
                name.setText(goodsListBean1.getData().get(id).getTitle());

                //money.setText(goodsListBean1.getData().get(id).getSpec().get(1).getSpec_money());

                //横向发展
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                List<HomeGoodsListBean.DataBean.SpecBean> spec = goodsListBean1.getData().get(id).getSpec();

                HomeGoodsFragmentAdapter fragmentAdapter = new HomeGoodsFragmentAdapter(getContext(),spec);
                recyclerView.setAdapter(fragmentAdapter);
                fragmentAdapter.notifyDataSetChanged();
                //规格适配器
                fragmentAdapter.setSetOnClickItem(new HomeGoodsFragmentAdapter.setOnClickItem() {
                    @Override
                    public void onGreat(final int eid,String e) {
                        //Toast.makeText(getContext(),""+eid,Toast.LENGTH_SHORT).show();
                        sid = eid ;
                        money.setText(e);
                    }
                });
                fragmentAdapter.notifyDataSetChanged();
                jia.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        zs= Integer.parseInt(sum.getText().toString());
                        zs = zs+1;
                        sum.setText(zs+"");/*
                        mo= Double.parseDouble(money.getText().toString());
                        aa = zs*mo;
                        money.setText(aa+"");*/

                    }
                });
                jian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        zs= Integer.parseInt(sum.getText().toString());
                        if (zs!=0){
                            zs = zs-1;
                            sum.setText(zs+"");/*
                            mo= Double.parseDouble(money.getText().toString());
                            aa = zs*mo;
                            money.setText(aa+"");*/
                        }
                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String s = MainActivity.token;
                        String passwjiemi = PasswordJiami.passwjiemi(s);
                        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
                        String s1 = sum.getText().toString();
                        myPresenter.PHomePageGoods(HomepageGoodsActivity.id ,gid,sid,Integer.parseInt(s1), MainActivity.user_id,passwordjiami);
                        //Log.e("啊大大",HomepageGoodsActivity.id+"--"+gid+"--"+sid+"--"+Integer.parseInt(s1)+"--"+MainActivity.user_id+"--"+passwordjiami);
                    }
                });


            }
        });
        //无规格
        rightAdapter.setSetOnCArClickItem(new HomeGoodsRightAdapter.setOnClickIteme() {
            @Override
            public void onGreat(int id,int ggid) {
                //Log.e("啊大大",""+id);
            }
        });

    }



    private void right() {
        right = view.findViewById(R.id.fragment_home_goods_right);
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
            goodsListBean1 = (HomeGoodsListBean) object;
            //Log.e("啊大大",""+ goodsListBean1.getData());

            if (goodsListBean1.getCode() == 0){
                dataBeans.addAll(goodsListBean1.getData());
            }
            rightAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void ShowHomePageGoods(Object object) {
        AddSoppingCarBean addSoppingCarBean = (AddSoppingCarBean) object;
        if (addSoppingCarBean.getCode()==0){
            Toast.makeText(getContext(),""+addSoppingCarBean.getMsg(),Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        cateBeans.clear();
        dataBeans.clear();
    }
}
