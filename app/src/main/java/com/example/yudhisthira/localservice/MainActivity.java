package com.example.yudhisthira.localservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * By Yudhisthira
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView            _txtView;
    Button              _btnStart;
    Button              _btnStop;
    Button              _btnBind;
    Button              _btnUnbind;
    Button              _btnGetRandom;

    Intent              _serviceIntent;

    MyService           _myService;
    ServiceConnection   _serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _txtView = (TextView)findViewById(R.id.display_txt);

        _btnStart = (Button)findViewById(R.id.start_btn);
        _btnStart.setOnClickListener(this);

        _btnStop = (Button)findViewById(R.id.stop_btn);
        _btnStop.setOnClickListener(this);

        _btnBind = (Button)findViewById(R.id.bind_btn);
        _btnBind.setOnClickListener(this);

        _btnUnbind = (Button)findViewById(R.id.unbind_btn);
        _btnUnbind.setOnClickListener(this);

        _btnGetRandom = (Button)findViewById(R.id.getnumber_btn);
        _btnGetRandom.setOnClickListener(this);

        _serviceIntent = new Intent(getApplicationContext(), MyService.class);
        _serviceIntent.putExtra(Intent.EXTRA_TEXT, "");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.start_btn:
                start();
                break;

            case R.id.stop_btn:
                stop();
                break;

            case R.id.bind_btn:
                bind();
                break;

            case R.id.unbind_btn:
                unbind();
                break;

            case R.id.getnumber_btn:
                getRandomNumber();
                break;
        }
    }

    private void start() {
        startService(_serviceIntent);
    }

    private void stop() {
        stopService(_serviceIntent);
    }

    private void bind() {

        if(null == _serviceConnection) {
            _serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {

                    MyService.MyBinder myBinder = (MyService.MyBinder)service;

                    _myService = myBinder.getService();
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }
            };
        }

        bindService(_serviceIntent, _serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unbind() {
        unbindService(_serviceConnection);
    }

    private void getRandomNumber() {
        final int number = _myService.getRandamNumber();

        _txtView.post(new Runnable() {
            @Override
            public void run() {
                _txtView.setText("" + number);
            }
        });
    }
}
