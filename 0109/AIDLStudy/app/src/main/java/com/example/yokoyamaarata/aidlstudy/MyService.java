package com.example.yokoyamaarata.aidlstudy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) { return mBinder; }

    private final IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        public void ShowToast(String input) {
            String result;

            switch (input) {
                case "akb":
                    result = String.valueOf(48);
                    break;
                case "ske":
                    result = String.valueOf(48);
                    break;
                case "nmb":
                    result = String.valueOf(48);
                    break;
                case "hkt":
                    result = String.valueOf(48);
                    break;
                case "ngt":
                    result = String.valueOf(48);
                    break;
                case "stu":
                    result = String.valueOf(48);
                    break;
                case "nogizaka":
                    result = String.valueOf(46);
                    break;
                case "keyakizaka":
                    result = String.valueOf(46);
                    break;
                default:
                    result = "該当なし or 海外は未対応";
            }

            Toast toast = Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT);
            toast.show();
        }
    };
}
