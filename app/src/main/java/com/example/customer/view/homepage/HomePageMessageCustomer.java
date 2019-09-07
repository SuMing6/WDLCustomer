package com.example.customer.view.homepage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.activity.PasswordJiami;
import com.example.customer.adapter.MySocketAdapter;
import com.example.customer.bean.MySocketBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class HomePageMessageCustomer extends AppCompatActivity implements MyContract.MyView.HomePageMessageCustomer{


    //private Socket socket;
    private RecyclerView recyclerView;
    private EditText et;
    private ImageView btn;
    List<MySocketBean.DataBean> dataBeans = new ArrayList<>();
    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);
    private MySocketBean mySocketBean;
    private MySocketAdapter adapter;


    private static final String HOST = "192.168.8.137";
    private static final int PORT = 8282;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private Socket socket;
    private String content = "";
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_home_page_message_customer);
        //返回
        back();
        //信息
        data();
    }

    private void data() {
        String s = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(s);
        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
        myPresenter.PMySocket(MainActivity.user_id,passwordjiami);

        recyclerView = findViewById(R.id.home_page_message_socket);

        textView = findViewById(R.id.home_page_message_TextView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.e("聊天啊啊1",dataBeans.toString());
        adapter = new MySocketAdapter(this,dataBeans);
        recyclerView.setAdapter(adapter);

        btn = findViewById(R.id.home_page_message_ImageView);
        et = findViewById(R.id.home_page_message_EditText);

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    //1.创建监听指定服务器地址以及指定服务器监听的端口号
                    Socket socket = new Socket("122.114.92.37", 8282);//192.168.1.101为我这个本机的IP地址，端口号为9080.

                    //Log.e("聊天Socket", bild.getBytes());
                    //2.拿到客户端的socket对象的输出流发送给服务器数据
                    OutputStream os = socket.getOutputStream();
                    //写入要发送给服务器的数据

                    String bild = "{'type':'bind','uid':'2'}";

                    String s1 = new String(bild.getBytes(),"UTF-8");
                    Log.e("聊天Socket", s1);
                    os.write(s1.getBytes());
                    os.flush();
                    socket.shutdownOutput();
                    //拿到socket的输入流，这里存储的是服务器返回的数据
                    InputStream is = socket.getInputStream();
                    //解析服务器返回的数据
                    int lenght = 0;
                    byte[] buff = new byte[1024];
                    final StringBuffer sb = new StringBuffer();
                    while((lenght = is.read(buff)) != -1){
                        sb.append(new String(buff,0,lenght,"UTF-8"));
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //这里更新UI
                        }
                    });
                    //3、关闭IO资源（注：实际开发中需要放到finally中）
                    is.close();
                    os.close();
                    socket.close();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }
    public void ShowDialog(String msg) {
        new AlertDialog.Builder(this).setTitle("notification").setMessage(msg)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                    }
                }).show();
    }

    public void run() {
        try {
            while (true) {
                if (socket.isConnected()) {
                    if (!socket.isInputShutdown()) {
                        if ((content = in.readLine()) != null) {
                            content += "\n";
                            mHandler.sendMessage(mHandler.obtainMessage());
                        } else {

                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText(textView.getText().toString() + content);
        }
    };

    @Override
    public void ShowSocket(Object object) {
        mySocketBean = (MySocketBean) object;
        if (mySocketBean.getCode()==0){
            Log.e("聊天啊啊", mySocketBean.getData()+"");
            dataBeans.addAll(mySocketBean.getData());
            adapter.notifyDataSetChanged();
        }
    }

    private void back() {
        TextView textView = findViewById(R.id.homepage_message_customer_back);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /*-------------------------------------------------------------------------------------------------*/
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
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }


}
