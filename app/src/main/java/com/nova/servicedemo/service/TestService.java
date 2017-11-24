package com.nova.servicedemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Administrator on 2017/11/24 0024.
 */

public class TestService extends Service {
    private String TAG = "TestService";

    //通过binder实现调用者client与Service之间通信
    public Mbinder mBinder = new Mbinder();


    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(TAG,"concreate()方法执行");
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        Log.d(TAG,"onstartcommand()方法执行");
        return super.onStartCommand(intent,flags,startId);
    }


    @Override
    public void onDestroy(){
        Log.d(TAG,"ondestroy执行");
        super.onDestroy();
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind()方法执行");
        return mBinder;
    }

    public class Mbinder extends Binder {
        public void hello(){
            Log.d(TAG,"hello方法执行");
        }
    }
}
