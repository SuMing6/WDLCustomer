package com.example.customer.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.customer.R;
import com.example.customer.activity.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePageFragment extends Fragment {

    public static View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_homepage, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //滑动冲突
        huaDong();
        //定位
        weizhi();
        //输入框焦点
        editText();



    }
    private void editText() {
        LinearLayout linearLayout = view.findViewById(R.id.homepage_activity);
        final EditText editText = view.findViewById(R.id.homepage_shousuo);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setFocusable(false);
                editText.clearFocus();
            }
        });

        editText.setFocusable(false);
        editText.clearFocus();
        //输入框获取焦点
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                editText.requestFocus();
            }
        });
    //点击软键盘外部，收起软键盘
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    InputMethodManager manager = ((InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE));
                    if (manager != null)
                        manager.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

        final NestedScrollView scrollView = view.findViewById(R.id.homepage_ScrollView);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scrollView.setFocusable(true);
                scrollView.setFocusableInTouchMode(true);
                scrollView.requestFocus();
                HideKeyboard(getView());
                return false;
            }
        });

    }

    private void weizhi() {
        TextView textView = view.findViewById(R.id.homepage_dingwei);
        if (MainActivity.district!= null){
            textView.setText(MainActivity.district);
        }else {
            textView.setText("定位中....");
        }
    }

    //点击NestedScrollView收起输入框
    public static void HideKeyboard(View v)
    {
        InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );
        if ( imm.isActive( ) ) {
            imm.hideSoftInputFromWindow( v.getApplicationWindowToken( ) , 0 );
        } }

    private void huaDong() {
        NestedScrollView scrollView = view.findViewById(R.id.homepage_ScrollView);
        //解决滑动冲突
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            int page = 0 ;
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    //底部加载
                    page++;
                    //myPresenter.PHomePageGoods();
                }
            }
        });
    }
}
