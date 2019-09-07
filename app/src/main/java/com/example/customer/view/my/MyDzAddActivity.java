package com.example.customer.view.my;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.activity.PasswordJiami;
import com.example.customer.adapter.my.MyDzAddCenterAdapter;
import com.example.customer.adapter.my.MyDzAddLeftAdapter;
import com.example.customer.adapter.my.MyDzAddRightAdapter;
import com.example.customer.bean.AddDzBean;
import com.example.customer.bean.SanBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

public class MyDzAddActivity extends Activity implements MyContract.MyView.MyDzAddActivity {

    int screenWidth;//屏幕宽度
    int screenHeight;//屏幕高度
    private WindowManager windowManager;
    private PopupWindow popupWindow;

    private EditText edit_name,edit_phone,edit_wx,edit_xx;
    TextView dz ;
    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);

    List<SanBean.DataBean> dataBeans = new ArrayList<>();

    int sheng , shi , qu ;
    String Ssheng,Sshi,Squ;



    private SanBean sanBean;
    private View inflate;
    private MyDzAddLeftAdapter leftAdapter;
    private MyDzAddCenterAdapter centerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_my_dz_add);
        //返回
        back();
        //添加
        adddz();


    }

    private void adddz() {
        Button buttond = findViewById(R.id.my_dz_add_button);
        edit_name = findViewById(R.id.my_dz_add_name);
        edit_phone = findViewById(R.id.my_dz_add_phone);
        edit_wx = findViewById(R.id.my_dz_add_wx);
        edit_xx = findViewById(R.id.my_dz_add_xx);
        dz = findViewById(R.id.my_dz_add_dz);
        buttond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edit_name.getText().toString();
                String phone = edit_phone.getText().toString();
                String wx = edit_wx.getText().toString();
                String xx = edit_xx.getText().toString();

                String s = MainActivity.token;
                String passwjiemi = PasswordJiami.passwjiemi(s);
                String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
                myPresenter.PAddDz(MainActivity.user_id,passwordjiami,name,phone,wx,sheng, shi,qu,Ssheng+Sshi+Squ+xx,0,MainActivity.longitude+"",MainActivity.latitude+"");
            }
        });

        inflate = LayoutInflater.from(this).inflate(R.layout.popupwindow_my_dz_add, null);
        windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        screenWidth = display.getWidth();
        screenHeight = display.getHeight();
        popup();
    }

    private void popup() {
        dz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPresenter.PAddDzSSQ();
                TextView relative = findViewById(R.id.my_dz_add_e_food);
                popupWindow = new PopupWindow(inflate, screenWidth, 780, true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                popupWindow.setOutsideTouchable(true);
                popupWindow.setTouchable(true);
                popupWindow.showAsDropDown(relative,0 ,relative.getHeight() );
                //变色
                bgAlpha(0.618f);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        bgAlpha(1.0f);
                    }
                });
                RecyclerView left= inflate.findViewById(R.id.my_dz_add_leftRecyclerView);
                final RecyclerView center = inflate.findViewById(R.id.my_dz_add_centerRecyclerView);
                final RecyclerView right = inflate.findViewById(R.id.my_dz_add_rightRecyclerView);
                final TextView TextView = inflate.findViewById(R.id.my_dz_add_text);
                TextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                left.setLayoutManager(new LinearLayoutManager(MyDzAddActivity.this));
                center.setLayoutManager(new LinearLayoutManager(MyDzAddActivity.this));
                right.setLayoutManager(new LinearLayoutManager(MyDzAddActivity.this));

                leftAdapter = new MyDzAddLeftAdapter(MyDzAddActivity.this,dataBeans);
                leftAdapter.setSetOnClickItem(new MyDzAddLeftAdapter.setOnClickItem() {
                    @Override
                    public void onGreat(List<SanBean.DataBean.ChildrenBeanX> data, int value, String text) {
                        sheng = value ;
                        Ssheng = text ;
                        centerAdapter = new MyDzAddCenterAdapter(MyDzAddActivity.this,data);
                        centerAdapter.setSetOnClickItem(new MyDzAddCenterAdapter.setOnClickItem() {
                            @Override
                            public void onGreat(List<SanBean.DataBean.ChildrenBeanX.ChildrenBean> data, int value, String text) {
                                shi = value ;
                                Sshi = text ;
                                MyDzAddRightAdapter rightAdapter = new MyDzAddRightAdapter(MyDzAddActivity.this,data);
                                rightAdapter.setSetOnClickItem(new MyDzAddRightAdapter.setOnClickItem() {
                                    @Override
                                    public void onGreat(int value, String text) {
                                        qu = value ;
                                        Squ = text ;
                                        Log.e("类似我l",sheng+"-"+shi+"-"+qu);
                                        dz.setText(Ssheng+"   "+Sshi+"   "+Squ);
                                        popupWindow.dismiss();
                                    }
                                });
                                right.setAdapter(rightAdapter);
                                rightAdapter.notifyDataSetChanged();
                            }
                        });
                        center.setAdapter(centerAdapter);
                        centerAdapter.notifyDataSetChanged();
                    }
                });
                left.setAdapter(leftAdapter);




            }
        });
    }

    @Override
    public void ShowAddDz(Object object) {
        AddDzBean addDzBean = (AddDzBean) object;

        //Log.e("添加地址",addDzBean.getCode()+"");
        if (addDzBean.getCode()==0){
            //Log.e("添加地址",addDzBean.getMsg());
            if (addDzBean.getMsg().equals("添加地址成功！")){
                finish();
            }
        }
    }

    @Override
    public void ShowAddDzSSQ(Object object) {
        sanBean = (SanBean) object;
        if (sanBean.getCode()==0){
            dataBeans.addAll(sanBean.getData());
            leftAdapter.notifyDataSetChanged();
        }
    }


    private void XNAJ() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    //为避免底部导航栏覆盖注释掉这一行
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {

        }
    }
    private void back() {
        final TextView textView = findViewById(R.id.my_dz_add_back);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private  void bgAlpha(float f){
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha = f;
        getWindow().setAttributes(layoutParams);
    }


}
