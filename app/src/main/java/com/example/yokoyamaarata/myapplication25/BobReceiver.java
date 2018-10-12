package com.example.yokoyamaarata.myapplication25;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BobReceiver extends BroadcastReceiver{
    public BobReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("com.example.yokoyamaarata.myapplication25.BUTTON_CLICKED".equals(intent.getAction())) {
            String massage = intent.getStringExtra("message");
            Log.v("hoge",massage);
            Toast toast = Toast.makeText(context, massage, Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
