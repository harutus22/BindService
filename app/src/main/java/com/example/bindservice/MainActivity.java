package com.example.bindservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private boolean isBound = false;
    private MyBindService myBindService;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            MyBindService.LocalBinder localBinder = (MyBindService.LocalBinder) iBinder;
            myBindService = localBinder.getService();
            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MyBindService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        textView = findViewById(R.id.numberHolder);
    }

    public void getNumber(View view){
        if(isBound) {
            textView.setText(String.valueOf(myBindService.getNumber()));
        }
    }
}
