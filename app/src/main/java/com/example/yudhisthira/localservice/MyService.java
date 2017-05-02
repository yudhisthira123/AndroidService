package com.example.yudhisthira.localservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

/**
 * Created by yudhisthira on 16/04/17.
 */

public class MyService extends Service {

    int randamNumber;
    boolean bStop = false;
    final int MIN = 0;
    final int MAX = 100;

    public class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    IBinder _binder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return _binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("MyService", " In onStartCommand ");

        final Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                while (false == bStop) {
//                    Log.i("MyService", " In Thread Run() ");
                    try {
                        Thread.sleep(1000);

                        randamNumber = new Random().nextInt(MAX) + MIN;

                        Log.i("MyService", " In onStartCommand randamNumber = " + randamNumber);
                    }
                    catch (Exception e) {

                    }
                }
            }
        });

        t.start();

        return START_STICKY;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i("MyService", " In onDestroy ");
        stopRandom();
    }

    private void stopRandom() {
        bStop = true;
    }

    public int getRandamNumber() {
        return randamNumber;
    }
}
