package shixun.lj.bw.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import shixun.lj.bw.R;
import shixun.lj.bw.mvp.Bean.Address;
import shixun.lj.bw.mvp.Bean.Xiang;
import shixun.lj.bw.mvp.adapter.Dingdanadapter;
import shixun.lj.bw.mvp.adapter.Mydingdan;
import shixun.lj.bw.mvp.inteface.Addressinterface;
import shixun.lj.bw.mvp.inteface.Xinagqing;
import shixun.lj.bw.mvp.presenter.Addrsspresenter;
import shixun.lj.bw.mvp.presenter.Xpresenter;

public class Dingdan extends AppCompatActivity implements Addressinterface.Addressview, Xinagqing.Xview {
    private Xpresenter xpresenter;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private Addrsspresenter addrsspresenter;
    private Handler handler = new Handler();
    private int userId;
    private String sessionId;
    private TextView money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan);
        recyclerView1 = findViewById(R.id.ding1);
        recyclerView2 = findViewById(R.id.ding2);
        money = findViewById(R.id.money);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Dingdan.this);
        recyclerView1.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(Dingdan.this);
        recyclerView2.setLayoutManager(linearLayoutManager1);
        SharedPreferences sharedPreferences = getSharedPreferences("c", Xiangqing.MODE_PRIVATE);
        sessionId = sharedPreferences.getString("sessionId", "");
        userId = sharedPreferences.getInt("userId", 20);
        addrsspresenter = new Addrsspresenter();
        addrsspresenter.addressAttachview(this);
        addrsspresenter.addressQequest(userId + "", sessionId);

        //商品
        Intent intent = getIntent();
        int id = intent.getExtras().getInt("id");
        xpresenter = new Xpresenter();
        xpresenter.attachview(Dingdan.this);
        xpresenter.getdata(id);

    }

    //地址返回的数据
    @Override
    public void Addressview(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.i("qqqqqqq", "run: " + data);
                Gson gson = new Gson();
                Address address = gson.fromJson(data, Address.class);
                List<Address.ResultBean> result = address.getResult();
                Mydingdan mydingdan = new Mydingdan(result, Dingdan.this);
                recyclerView1.setAdapter(mydingdan);
            }
        });
    }

    //商品返回的数据
    @Override
    public void Xview(final String data) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Xiang xiang = gson.fromJson(data, Xiang.class);
                Xiang.ResultBean result = xiang.getResult();
                ArrayList<Xiang.ResultBean> list = new ArrayList<Xiang.ResultBean>();
                list.add(result);
                Dingdanadapter dingdanadapter = new Dingdanadapter(list, Dingdan.this);
                dingdanadapter.setOnclick(new Dingdanadapter.onclick() {
                    @Override
                    public void onclick(int sum) {
                        money.setText("共2件商品,需付款" + sum + "元");
                    }
                });
                recyclerView2.setAdapter(dingdanadapter);
            }
        });

    }
}
