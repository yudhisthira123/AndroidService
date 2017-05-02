package com.example.yudhisthira.localservice;

import android.os.Looper;

/**
 * Created by yudhisthira on 19/04/17.
 */

public class MyThread extends Thread {
    public MyHandler myHandler;
    @Override
    public void run() {
        Looper.prepare();
        myHandler = new MyHandler();
        Looper.loop();
    }

    public void calculate() {

    }
}
