package com.example.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import androidx.annotation.NonNull;

import java.util.Random;

public class MyBindService extends Service {

    private final IBinder iBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {

        return iBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    public class LocalBinder extends Binder{
        MyBindService getService(){
            return MyBindService.this;
        }
    }

    public int getNumber(){
        Random random = new Random();
        return random.nextInt(200);
    }
}
