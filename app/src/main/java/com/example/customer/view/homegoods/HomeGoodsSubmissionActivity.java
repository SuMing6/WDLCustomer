package com.example.customer.view.homegoods;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.activity.PasswordJiami;
import com.example.customer.adapter.SubmissionTimeLeftAdapter;
import com.example.customer.adapter.SubmissionTimeRightAdapter;
import com.example.customer.adapter.homeCarInforAdapter;
import com.example.customer.bean.CarInfoTiJiaoBean;
import com.example.customer.bean.HomeCarInforBean;
import com.example.customer.bean.SubmissionHBBean;
import com.example.customer.bean.SubmissionTimeBean;
import com.example.customer.bean.SubmissionYHBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;
import com.example.customer.view.homepage.HomepageGoodsActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeGoodsSubmissionActivity extends Activity implements MyContract.MyView.HomeGoodsSubmissionActivity{

    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);
    private View inflate;

    List<SubmissionTimeBean.DataBean> dataBeans = new ArrayList<>();
    List<HomeCarInforBean.DataBean.GoodListBean> goodListBeans = new ArrayList<>();

    int screenWidth;//屏幕宽度
    int screenHeight;//屏幕高度
    private WindowManager windowManager;
    private PopupWindow popupWindow;
    private SubmissionTimeLeftAdapter leftAdapter;
    private SubmissionTimeRightAdapter rightAdapter;
    //时间id
    int timeId ;
    //红包
    int rid ;
    //优惠券id
    int cid ;
    //收货地址id
    int aid ;


    private TextView textView;
    private HomeCarInforBean homeCarInforBean;
    private homeCarInforAdapter inforAdapter;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_home_goods_submission);
        textView = findViewById(R.id.home_goods_submission_time);
        Intent intent = getIntent();
        //店铺id
        id = intent.getExtras().getInt("id");
        //红包
        hb();
        //优惠券
        yh();
        //时间
        time();
        //地址
        dz();
        //数据
        dianpu();
        //立即下单
        ljxd();
        //返回
        back();
    }

    private void back() {
        ImageView textView = findViewById(R.id.home_goods_submission_back);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void ljxd() {
        TextView ljxd = findViewById(R.id.home_goods_submission_ljxd);
        ljxd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = MainActivity.token;
                String passwjiemi = PasswordJiami.passwjiemi(s);
                String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
                EditText editText = findViewById(R.id.home_goods_submission_edittext);
                if (aid!=0){
                    myPresenter.PSubmissionTiJiao(id,MainActivity.user_id,passwordjiami,rid,cid,aid,editText.getText().toString(),0, String.valueOf(timeId));
                }else {

                    Toast.makeText(HomeGoodsSubmissionActivity.this,"请选择收货地址",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void dianpu() {
        RecyclerView recyclerView = findViewById(R.id.home_goods_submission_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Token加密
        String s = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(s);
        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
        myPresenter.PSubmissionInfo(id,MainActivity.user_id,passwordjiami,rid,cid,aid);
        inforAdapter = new homeCarInforAdapter(HomeGoodsSubmissionActivity.this,goodListBeans);
        recyclerView.setAdapter(inforAdapter);
    }

    private void dz() {
        TextView textView = findViewById(R.id.home_goods_submission_RelativeLayoutDz);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeGoodsSubmissionActivity.this,SubmissionDZActivity.class);
                startActivity(intent);
            }
        });
        if (SubmissionDZActivity.address!=null){
            textView.setText(SubmissionDZActivity.address+"      >");
        }
        aid = SubmissionDZActivity.id;
    }

    private void time() {
        RelativeLayout relativeLayout = findViewById(R.id.home_goods_submission_RelativeLayoutTime);

        windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        screenWidth = display.getWidth();
        screenHeight = display.getHeight();

        inflate = LayoutInflater.from(this).inflate(R.layout.popupwindow_submission, null);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            final LinearLayout linearLayout = inflate.findViewById(R.id.LinearLayout_cccc);
            @Override
            public void onClick(View view) {
                RelativeLayout relative = findViewById(R.id.home_goods_submission_food);

                popupWindow = new PopupWindow(inflate, screenWidth,780 , true);
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
                RecyclerView left= inflate.findViewById(R.id.popup_leftRecyclerView);
                final RecyclerView right = inflate.findViewById(R.id.popup_righRecyclerView);

                left.setLayoutManager(new LinearLayoutManager(HomeGoodsSubmissionActivity.this));
                right.setLayoutManager(new LinearLayoutManager(HomeGoodsSubmissionActivity.this));
                myPresenter.PSubmissionTime();
                leftAdapter = new SubmissionTimeLeftAdapter(HomeGoodsSubmissionActivity.this,dataBeans);
                left.setAdapter(leftAdapter);
                leftAdapter.setSetOnClickItem(new SubmissionTimeLeftAdapter.setOnClickItem() {
                    @Override
                    public void onGreat(List<SubmissionTimeBean.DataBean.TimeBean> money) {
                        if (money!=null){

                            rightAdapter = new SubmissionTimeRightAdapter(HomeGoodsSubmissionActivity.this,money);
                            right.setAdapter(rightAdapter);
                            rightAdapter.setSetOnClickItem(new SubmissionTimeRightAdapter.setOnClickItem() {
                                @Override
                                public void onGreat(int timeid,String tt) {
                                    timeId = timeid ;
                                    //onResume();
                                    onCreate(null);
                                    textView.setText("大约"+tt+"送达");
                                    popupWindow.dismiss();
                                }
                            });
                        }
                    }
                });



            }
        });
    }

    private void yh() {
        RelativeLayout RelativeLayoutHB = findViewById(R.id.home_goods_submission_RelativeLayoutHB);
        RelativeLayoutHB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeGoodsSubmissionActivity.this,SubmissionHBActivity.class);
                startActivity(intent);
            }
        });
        TextView textView = findViewById(R.id.home_goods_submission_HBtext);
        if (SubmissionHBActivity.mo!=null){
            String mo = SubmissionHBActivity.mo;
            textView.setText(mo);
        }
        rid = SubmissionHBActivity.id;

    }

    private void hb() {
        RelativeLayout RelativeLayoutYH = findViewById(R.id.home_goods_submission_RelativeLayoutYH);
        RelativeLayoutYH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeGoodsSubmissionActivity.this,SubmissionYHActivity.class);
                startActivity(intent);
            }
        });
        TextView textView = findViewById(R.id.home_goods_submission_YHtext);
        if (SubmissionYHActivity.money!=null){
            String mo = SubmissionYHActivity.money;
            textView.setText(mo);
        }
        cid = SubmissionYHActivity.id;
    }

    @Override
    protected void onResume() {
        super.onResume();
        onCreate(null);
        TextView textView = findViewById(R.id.home_goods_submission_time);
        if (timeId!=0){
            textView.setText(timeId);
        }
        inforAdapter.notifyDataSetChanged();

    }

    @Override
    public void ShowSubmissionTime(Object object) {
        SubmissionTimeBean submissionTimeBean = (SubmissionTimeBean) object;
        if (submissionTimeBean.getCode()==0){
            dataBeans.addAll(submissionTimeBean.getData());
            leftAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void ShowSubmissionInfo(Object object) {
        homeCarInforBean = (HomeCarInforBean) object;
        goodListBeans.clear();
        if (homeCarInforBean.getCode()==0){
            goodListBeans.addAll(homeCarInforBean.getData().getGood_list());
            TextView peisong = findViewById(R.id.home_goods_submission_peisong);
            TextView dabao = findViewById(R.id.home_goods_submission_dabao);
            TextView shoudan = findViewById(R.id.home_goods_submission_shoudan);
            TextView gong = findViewById(R.id.home_goods_submission_gong);
            TextView sum = findViewById(R.id.home_goods_submission_sum);
            TextView daizhifu = findViewById(R.id.home_goods_submission_daizhifu);

            peisong.setText(homeCarInforBean.getData().getShop_info().getMatch_give());
            dabao.setText(homeCarInforBean.getData().getShop_info().getPack());
            shoudan.setText("- "+homeCarInforBean.getData().getShop_info().getRise_give());
            gong.setText("共 "+homeCarInforBean.getData().getGood_list().size()+" 件商品");
            sum.setText("￥"+homeCarInforBean.getData().getMoney());
            daizhifu.setText("￥"+homeCarInforBean.getData().getMoney());
        }
        inforAdapter.notifyDataSetChanged();
    }

    @Override
    public void ShowSubmissionTiJiao(Object object) {
        CarInfoTiJiaoBean carInfoTiJiaoBean = (CarInfoTiJiaoBean) object;
        if (carInfoTiJiaoBean.getCode() == 0){
            Toast.makeText(this,""+carInfoTiJiaoBean.getMsg(),Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private  void bgAlpha(float f){
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha = f;
        getWindow().setAttributes(layoutParams);
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
            actionBar.hide();
        }
    }


}
