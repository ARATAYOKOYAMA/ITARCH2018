package com.example.yokoyamaarata.aidlstudy;

import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.ServiceConnection;
import android.content.ComponentName;
import android.os.IBinder;
import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText text;
    Intent serviceIntent;
    IMyAidlInterface myService;

    private ServiceConnection myConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            myService = IMyAidlInterface.Stub.asInterface(service);
        }

        public void onServiceDisconnected(ComponentName className) {
            myService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        text = findViewById(R.id.text);

        serviceIntent = new Intent(
                MainActivity.this, MyService.class);
        serviceIntent.setAction(IMyAidlInterface.class.getName());
        serviceIntent.setPackage("com.example.yokoyamaarata.aidlstudy");
        bindService(
                serviceIntent,
                myConnection,
                Context.BIND_AUTO_CREATE);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendValue = text.getText().toString();
                try {
                    myService.ShowToast(sendValue);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
