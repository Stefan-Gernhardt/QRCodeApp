package com.sge.qrcodegeneratoradfree;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate of MainActivity");
        setContentView(R.layout.activity_main);


        EditText mEdit = (EditText)findViewById(R.id.textview_first);
        mEdit.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            sendMessage(findViewById(android.R.id.content).getRootView());
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

    }


    public void sendMessage(View view) {
        Log.i(TAG, "sendMessage of MainActivity");

        Intent intent = new Intent(MainActivity.this, QRCodeDisplayActivity.class);
        EditText mEdit = (EditText)findViewById(R.id.textview_first);

        Log.i(TAG, "sendMessage of MainActivity " + mEdit.getText());

        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, mEdit.getText().toString());
        startActivity(intent);
    }

}