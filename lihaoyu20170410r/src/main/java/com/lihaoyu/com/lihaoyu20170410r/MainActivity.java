package com.lihaoyu.com.lihaoyu20170410r;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.lihaoyu.com.lihaoyu20170410r.Modul.Bean;
import com.lihaoyu.com.lihaoyu20170410r.utils.OkhtttpUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private List<Bean.NewslistBean> list;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

             list = (List<Bean.NewslistBean>) msg.obj;
             adapter();
        }
    };

    private void adapter() {

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String dataUrl = "http://api.tianapi.com/nba/?key=4e02b00866015348ced734803a9292ee&num=50";

        OkhtttpUtils.doGet(dataUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                Gson gson = new Gson();
                Bean bean = gson.fromJson(s, Bean.class);
                list = bean.getNewslist();

                Message msg = new Message();
                msg.obj = list;
                handler.sendMessage(msg);

            }
        });

    }
}
