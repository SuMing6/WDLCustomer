package com.example.customer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.activity.PasswordJiami;
import com.example.customer.bean.UserInfoBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;
import com.example.customer.view.my.MyDzActivity;
import com.example.customer.view.my.MyHyActivity;
import com.example.customer.view.my.MyMoneyActivity;
import com.example.customer.view.my.MyScActivity;
import com.example.customer.view.my.MySheZhiActivity;
import com.example.customer.view.my.MyTicketActivity;
import com.example.customer.view.my.MyWmActivity;
import com.example.customer.view.my.MyYjActivity;
import com.facebook.drawee.view.SimpleDraweeView;

public class MyFragment extends Fragment implements MyContract.MyView.MyFragment {

    private View view;
    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);
    private TextView name;
    private SimpleDraweeView headima;
    private UserInfoBean userInfoBean;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, null);
        name = view.findViewById(R.id.my_name);
        headima = view.findViewById(R.id.my_touxiang);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //展示数据
        show();
        //设置
        shezhi();
        //我的钱包
        money();
        //我的卡券
        ticket();
        //收货地址
        dz();
        //我的收藏
        sc();
        //邀请好友
        hy();
        //语言选择
        yy();
        //联系客服
        kf();
        //意见
        yj();
        //我们
        wm();

    }

    private void show() {
        //Log.e("Token值1",MainActivity.token);
        String s = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(s);
        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);

        Log.e("Token解密",passwordjiami+"--"+MainActivity.user_id);
        myPresenter.PMyInfo(MainActivity.user_id,passwordjiami);
    }


    private void money() {
        LinearLayout linearLayout = view.findViewById(R.id.my_LinearLayout_money);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MyMoneyActivity.class);
                intent.putExtra("money",userInfoBean.getData().getMoney());
                startActivity(intent);
            }
        });
    }

    private void ticket() {
        LinearLayout linearLayout = view.findViewById(R.id.my_LinearLayout_ticket);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MyTicketActivity.class);
                startActivity(intent);
            }
        });
    }

    private void dz() {
        RelativeLayout relativeLayout = view.findViewById(R.id.my_RelativeLayout_dz);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MyDzActivity.class);
                startActivity(intent);
            }
        });
    }

    private void sc() {
        RelativeLayout relativeLayout = view.findViewById(R.id.my_RelativeLayout_sc);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MyScActivity.class);
                startActivity(intent);
            }
        });
    }

    private void hy() {
        RelativeLayout relativeLayout = view.findViewById(R.id.my_RelativeLayout_hy);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MyHyActivity.class);
                startActivity(intent);
            }
        });
    }

    private void yy() {
        RelativeLayout relativeLayout = view.findViewById(R.id.my_RelativeLayout_yy);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void kf() {
        RelativeLayout relativeLayout = view.findViewById(R.id.my_RelativeLayout_kf);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void yj() {
        RelativeLayout relativeLayout = view.findViewById(R.id.my_RelativeLayout_yj);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MyYjActivity.class);
                startActivity(intent);
            }
        });
    }

    private void wm() {
        RelativeLayout relativeLayout = view.findViewById(R.id.my_RelativeLayout_wm);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MyWmActivity.class);
                startActivity(intent);
            }
        });
    }

    //设置
    private void shezhi() {
        ImageView imageView = view.findViewById(R.id.my_shezhi);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MySheZhiActivity.class);/*
                intent.putExtra("name",userInfoBean.getData().getNickname());
                intent.putExtra("headimg",userInfoBean.getData().getHeadimg());
                intent.putExtra("sex",userInfoBean.getData().getSex());*/
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        String s = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(s);
        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);

        //Log.e("Token解密",passwordjiami+"--"+MainActivity.user_id);
        myPresenter.PMyInfo(MainActivity.user_id,passwordjiami);
    }

    /*--------------------------------------------------------------------------------------*/
    @Override
    public void ShowMyInfo(Object object) {
        userInfoBean = (UserInfoBean) object;
        if (userInfoBean.getCode()==0){
            if (userInfoBean.getData().getNickname()!=null){
                name.setText(userInfoBean.getData().getNickname());
            }
            if (userInfoBean.getData().getHeadimg()!=null){
                headima.setImageURI(userInfoBean.getData().getHeadimg());
            }
        }
    }
}
