package com.example.yokoyamaarata.myapplication25;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/*感想
#### わかったこと
- バッテリー容量の変化は以下のコマンドで可能
  - adb shell dumpsys battery set level n (n=容量)
- バッテリー警告は，放電状態じゃないと無理
  - adb shell dumpsys battery unplug
- Toastはos8.0以降では動かない
- absコマンドは，.bash_profileにてパスを通す必要がある
 */

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver mReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ブロードキャストレシーバ
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String intentAction = intent.getAction();
                if (Intent.ACTION_BATTERY_LOW.equals(intentAction)){
                    Toast toast = Toast.makeText(context, "バッテリーすくない", Toast.LENGTH_LONG);
                    toast.show();
                }
                if (Intent.ACTION_BATTERY_OKAY.equals(intentAction)){
                    Toast toast = Toast.makeText(context, "バッテリー復活", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        };


        Button sendButton = findViewById(R.id.button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(), SubActivity.class);
//                startActivity(intent);
                // 独自のインテントをブロードキャスト送信
                Intent myBroadcast = new Intent("com.example.yokoyamaarata.myapplication25.BUTTON_CLICKED");
                myBroadcast.putExtra("message","ブロードキャストテスト");
                sendBroadcast(myBroadcast);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // インテントフィルタ
         IntentFilter filter = new IntentFilter();
         filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_BATTERY_OKAY);
        // ブロードキャストレシーバ登録
         registerReceiver(mReceiver, filter);
    }
     @Override
     protected void onPause() {
         super.onPause();
         unregisterReceiver(mReceiver);
     }
}
