package com.example.yokoyamaarata.myapplication25;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_main);

        final TextView text = findViewById(R.id.TextView);
        text.setText("http://www.yahoo.co.jp");

        Button returnButton = findViewById(R.id.button2);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence sb = text.getText();
                String str = sb.toString();
                System.out.print(str);
                Uri uri = Uri.parse(str);
                Intent i = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
//                finish();
            }
        });
    }
}
