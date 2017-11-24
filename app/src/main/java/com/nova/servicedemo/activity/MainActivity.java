package com.nova.servicedemo.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nova.servicedemo.R;
import com.nova.servicedemo.service.TestService;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @Bind(R.id.tv_startservice)
    TextView tv_startservice;//使用start方式启动服务

    @Bind(R.id.tv_stopservice)
    TextView tv_stopservice;//停止服务

    @Bind(R.id.tv_bindservice)
    TextView tv_bindservice;//使用bind方式开启服务
    @Bind(R.id.tv_unbindservice)
    TextView tv_unbindservice;//取消绑定

    @Bind(R.id.tv_finish)
    TextView tv_finish;

    private TestService.Mbinder mBinder;
    private String TAG = "TestService";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_stopservice,R.id.tv_startservice,R.id.tv_bindservice,R.id.tv_unbindservice,R.id.tv_finish})
    public void Onclick(View v){
        switch(v.getId()){
            case R.id.tv_startservice:
                Intent startIntent = new Intent(MainActivity.this, TestService.class);
                startService(startIntent);
                break;
            case R.id.tv_stopservice:
                Intent stopIntent = new Intent(MainActivity.this,TestService.class);
                stopService(stopIntent);
                break;
            case R.id.tv_bindservice:
                Intent bindIntent = new Intent(MainActivity.this,TestService.class);
                bindService(bindIntent,conn,BIND_AUTO_CREATE);
                break;
            case R.id.tv_unbindservice:
                unbindService(conn);
                break;
            case R.id.tv_finish:
                finish();
                break;

        }

    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (TestService.Mbinder)service;
            mBinder.hello();
            Log.d(TAG,"connected()方法执行");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG,"disconnected()方法执行");
        }
    };



}
