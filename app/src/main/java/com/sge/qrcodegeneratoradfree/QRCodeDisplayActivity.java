package com.sge.qrcodegeneratoradfree;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QRCodeDisplayActivity extends AppCompatActivity {

    private static final String TAG = "QRCodeDisplayActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate of QRCodeDisplayActivity " + getIntent().getStringExtra(Intent.EXTRA_TEXT));

        QRCodeView qrCodeView;
        qrCodeView = new QRCodeView(getApplicationContext(), getIntent().getStringExtra(Intent.EXTRA_TEXT));
        setContentView(qrCodeView);
    }


    public void sendMessage(View view) {
        Log.i(TAG, "sendMessage of QRCodeDisplayActivity");
        Intent intent = new Intent(QRCodeDisplayActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
